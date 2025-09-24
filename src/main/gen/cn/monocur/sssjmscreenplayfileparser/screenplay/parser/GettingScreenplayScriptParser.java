// This is a generated file. Not intended for manual editing.
package cn.monocur.sssjmscreenplayfileparser.screenplay.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class GettingScreenplayScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return screenplay(b, l + 1);
  }

  /* ********************************************************** */
  // ALPHABET
  static boolean alphabet(PsiBuilder b, int l) {
    return consumeToken(b, ALPHABET);
  }

  /* ********************************************************** */
  // '[' array_element* ']'
  static boolean array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, L_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, array_1(b, l + 1));
    r = p && consumeToken(b, R_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // array_element*
  private static boolean array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!array_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // value (','|&']')
  static boolean array_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_element")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = value(b, l + 1);
    p = r; // pin = 1
    r = r && array_element_1(b, l + 1);
    exit_section_(b, l, m, r, p, GettingScreenplayScriptParser::not_bracket_or_next_value);
    return r || p;
  }

  // ','|&']'
  private static boolean array_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_element_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = array_element_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &']'
  private static boolean array_element_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_element_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, R_BRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BLOCK_COMMENT
  static boolean block_comment(PsiBuilder b, int l) {
    return consumeToken(b, BLOCK_COMMENT);
  }

  /* ********************************************************** */
  // TRUE | FALSE
  static boolean boolean_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_literal")) return false;
    if (!nextTokenIs(b, "", FALSE, TRUE)) return false;
    boolean r;
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    return r;
  }

  /* ********************************************************** */
  // !(semicolon | alphabet | generalWord | hash | comment)
  static boolean cmdRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cmdRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !cmdRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // semicolon | alphabet | generalWord | hash | comment
  private static boolean cmdRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cmdRecover_0")) return false;
    boolean r;
    r = semicolon(b, l + 1);
    if (!r) r = alphabet(b, l + 1);
    if (!r) r = generalWord(b, l + 1);
    if (!r) r = hash(b, l + 1);
    if (!r) r = comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // COLON
  static boolean colon(PsiBuilder b, int l) {
    return consumeToken(b, COLON);
  }

  /* ********************************************************** */
  // COMMA
  static boolean comma(PsiBuilder b, int l) {
    return consumeToken(b, COMMA);
  }

  /* ********************************************************** */
  // commandName mainParam? comment? subParams? semicolon
  public static boolean command(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMMAND, "<command>");
    r = commandName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, command_1(b, l + 1));
    r = p && report_error_(b, command_2(b, l + 1)) && r;
    r = p && report_error_(b, command_3(b, l + 1)) && r;
    r = p && semicolon(b, l + 1) && r;
    exit_section_(b, l, m, r, p, GettingScreenplayScriptParser::cmdRecover);
    return r || p;
  }

  // mainParam?
  private static boolean command_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_1")) return false;
    mainParam(b, l + 1);
    return true;
  }

  // comment?
  private static boolean command_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_2")) return false;
    comment(b, l + 1);
    return true;
  }

  // subParams?
  private static boolean command_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_3")) return false;
    subParams(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // alphabet | generalWord
  public static boolean commandName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commandName")) return false;
    if (!nextTokenIs(b, "<command name>", ALPHABET, WORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMAND_NAME, "<command name>");
    r = alphabet(b, l + 1);
    if (!r) r = generalWord(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // line_comment | block_comment
  static boolean comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment")) return false;
    if (!nextTokenIs(b, "", BLOCK_COMMENT, LINE_COMMENT)) return false;
    boolean r;
    r = line_comment(b, l + 1);
    if (!r) r = block_comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // number | word | variable | json
  static boolean content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content")) return false;
    boolean r;
    r = number(b, l + 1);
    if (!r) r = word(b, l + 1);
    if (!r) r = variable(b, l + 1);
    if (!r) r = json(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTED_STRING
  static boolean double_quoted_string(PsiBuilder b, int l) {
    return consumeToken(b, DOUBLE_QUOTED_STRING);
  }

  /* ********************************************************** */
  // EQUALS
  static boolean equals(PsiBuilder b, int l) {
    return consumeToken(b, EQUALS);
  }

  /* ********************************************************** */
  // FALSE
  static boolean false_$(PsiBuilder b, int l) {
    return consumeToken(b, FALSE);
  }

  /* ********************************************************** */
  // WORD
  static boolean generalWord(PsiBuilder b, int l) {
    return consumeToken(b, WORD);
  }

  /* ********************************************************** */
  // HASH
  static boolean hash(PsiBuilder b, int l) {
    return consumeToken(b, HASH);
  }

  /* ********************************************************** */
  // HYPHEN
  static boolean hyphen(PsiBuilder b, int l) {
    return consumeToken(b, HYPHEN);
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean identifier(PsiBuilder b, int l) {
    return consumeToken(b, IDENTIFIER);
  }

  /* ********************************************************** */
  // VAR_PREFIX
  static boolean isVariable(PsiBuilder b, int l) {
    return consumeToken(b, VAR_PREFIX);
  }

  /* ********************************************************** */
  // value*
  public static boolean json(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "json")) return false;
    Marker m = enter_section_(b, l, _NONE_, JSON, "<json>");
    while (true) {
      int c = current_position_(b);
      if (!value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "json", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // L_BRACKET
  static boolean l_bracket(PsiBuilder b, int l) {
    return consumeToken(b, L_BRACKET);
  }

  /* ********************************************************** */
  // L_CURLY
  static boolean l_curly(PsiBuilder b, int l) {
    return consumeToken(b, L_CURLY);
  }

  /* ********************************************************** */
  // LINE_COMMENT
  static boolean line_comment(PsiBuilder b, int l) {
    return consumeToken(b, LINE_COMMENT);
  }

  /* ********************************************************** */
  // string_literal | number_literal | boolean_literal | null_literal
  static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    r = string_literal(b, l + 1);
    if (!r) r = number_literal(b, l + 1);
    if (!r) r = boolean_literal(b, l + 1);
    if (!r) r = null_literal(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // colon content
  public static boolean mainParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mainParam")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = colon(b, l + 1);
    r = r && content(b, l + 1);
    exit_section_(b, m, MAIN_PARAM, r);
    return r;
  }

  /* ********************************************************** */
  // !('}'|value)
  static boolean not_brace_or_next_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_brace_or_next_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_brace_or_next_value_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '}'|value
  private static boolean not_brace_or_next_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_brace_or_next_value_0")) return false;
    boolean r;
    r = consumeToken(b, R_CURLY);
    if (!r) r = value(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(']'|value)
  static boolean not_bracket_or_next_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_bracket_or_next_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_bracket_or_next_value_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ']'|value
  private static boolean not_bracket_or_next_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_bracket_or_next_value_0")) return false;
    boolean r;
    r = consumeToken(b, R_BRACKET);
    if (!r) r = value(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // NULL
  static boolean null_$(PsiBuilder b, int l) {
    return consumeToken(b, NULL);
  }

  /* ********************************************************** */
  // NULL
  static boolean null_literal(PsiBuilder b, int l) {
    return consumeToken(b, NULL);
  }

  /* ********************************************************** */
  // NUMBER
  static boolean number(PsiBuilder b, int l) {
    return consumeToken(b, NUMBER);
  }

  /* ********************************************************** */
  // NUMBER
  static boolean number_literal(PsiBuilder b, int l) {
    return consumeToken(b, NUMBER);
  }

  /* ********************************************************** */
  // '{' object_element* '}'
  static boolean object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, L_CURLY);
    p = r; // pin = 1
    r = r && report_error_(b, object_1(b, l + 1));
    r = p && consumeToken(b, R_CURLY) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // object_element*
  private static boolean object_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // property (','|&'}')
  static boolean object_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_element")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = property(b, l + 1);
    p = r; // pin = 1
    r = r && object_element_1(b, l + 1);
    exit_section_(b, l, m, r, p, GettingScreenplayScriptParser::not_brace_or_next_value);
    return r || p;
  }

  // ','|&'}'
  private static boolean object_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_element_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = object_element_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &'}'
  private static boolean object_element_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_element_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, R_CURLY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // optionTarget optionContent
  public static boolean option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = optionTarget(b, l + 1);
    r = r && optionContent(b, l + 1);
    exit_section_(b, m, OPTION, r);
    return r;
  }

  /* ********************************************************** */
  // word
  public static boolean optionContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_CONTENT, "<option content>");
    r = word(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // l_bracket point_to alphabet r_bracket
  public static boolean optionTarget(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionTarget")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OPTION_TARGET, "<option target>");
    r = l_bracket(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, point_to(b, l + 1));
    r = p && report_error_(b, alphabet(b, l + 1)) && r;
    r = p && r_bracket(b, l + 1) && r;
    exit_section_(b, l, m, r, p, GettingScreenplayScriptParser::optionTarget_Recover);
    return r || p;
  }

  /* ********************************************************** */
  // !(l_bracket | r_bracket | optionContent)
  static boolean optionTarget_Recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionTarget_Recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !optionTarget_Recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // l_bracket | r_bracket | optionContent
  private static boolean optionTarget_Recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionTarget_Recover_0")) return false;
    boolean r;
    r = l_bracket(b, l + 1);
    if (!r) r = r_bracket(b, l + 1);
    if (!r) r = optionContent(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(semicolon | alphabet | hyphen | comment)
  static boolean param_Recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_Recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !param_Recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // semicolon | alphabet | hyphen | comment
  private static boolean param_Recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_Recover_0")) return false;
    boolean r;
    r = semicolon(b, l + 1);
    if (!r) r = alphabet(b, l + 1);
    if (!r) r = hyphen(b, l + 1);
    if (!r) r = comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // POINT_TO
  static boolean point_to(PsiBuilder b, int l) {
    return consumeToken(b, POINT_TO);
  }

  /* ********************************************************** */
  // property_name (':' property_value)
  static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = property_name(b, l + 1);
    p = r; // pin = 1
    r = r && property_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ':' property_value
  private static boolean property_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_1")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COLON);
    p = r; // pin = 1
    r = r && property_value(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // literal | reference_expression
  static boolean property_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_name")) return false;
    boolean r;
    r = literal(b, l + 1);
    if (!r) r = reference_expression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // value
  static boolean property_value(PsiBuilder b, int l) {
    return value(b, l + 1);
  }

  /* ********************************************************** */
  // R_BRACKET
  static boolean r_bracket(PsiBuilder b, int l) {
    return consumeToken(b, R_BRACKET);
  }

  /* ********************************************************** */
  // R_CURLY
  static boolean r_curly(PsiBuilder b, int l) {
    return consumeToken(b, R_CURLY);
  }

  /* ********************************************************** */
  // ALPHABET
  static boolean reference_expression(PsiBuilder b, int l) {
    return consumeToken(b, ALPHABET);
  }

  /* ********************************************************** */
  // (comment | screenplayConfig)? (comment | command)* comment?
  static boolean screenplay(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = screenplay_0(b, l + 1);
    r = r && screenplay_1(b, l + 1);
    r = r && screenplay_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (comment | screenplayConfig)?
  private static boolean screenplay_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay_0")) return false;
    screenplay_0_0(b, l + 1);
    return true;
  }

  // comment | screenplayConfig
  private static boolean screenplay_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    if (!r) r = screenplayConfig(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (comment | command)*
  private static boolean screenplay_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!screenplay_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "screenplay_1", c)) break;
    }
    return true;
  }

  // comment | command
  private static boolean screenplay_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    if (!r) r = command(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment?
  private static boolean screenplay_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplay_2")) return false;
    comment(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // hash (word | json)? hash
  public static boolean screenplayConfig(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplayConfig")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SCREENPLAY_CONFIG, "<screenplay config>");
    r = hash(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, screenplayConfig_1(b, l + 1));
    r = p && hash(b, l + 1) && r;
    exit_section_(b, l, m, r, p, GettingScreenplayScriptParser::screenplayConfig_Recover);
    return r || p;
  }

  // (word | json)?
  private static boolean screenplayConfig_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplayConfig_1")) return false;
    screenplayConfig_1_0(b, l + 1);
    return true;
  }

  // word | json
  private static boolean screenplayConfig_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplayConfig_1_0")) return false;
    boolean r;
    r = word(b, l + 1);
    if (!r) r = json(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(hash | alphabet | semicolon |generalWord)
  static boolean screenplayConfig_Recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplayConfig_Recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !screenplayConfig_Recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // hash | alphabet | semicolon |generalWord
  private static boolean screenplayConfig_Recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "screenplayConfig_Recover_0")) return false;
    boolean r;
    r = hash(b, l + 1);
    if (!r) r = alphabet(b, l + 1);
    if (!r) r = semicolon(b, l + 1);
    if (!r) r = generalWord(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // SEMICOLON
  static boolean semicolon(PsiBuilder b, int l) {
    return consumeToken(b, SEMICOLON);
  }

  /* ********************************************************** */
  // SINGLE_QUOTED_STRING
  static boolean single_quoted_string(PsiBuilder b, int l) {
    return consumeToken(b, SINGLE_QUOTED_STRING);
  }

  /* ********************************************************** */
  // SINGLE_QUOTED_STRING | DOUBLE_QUOTED_STRING
  static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    if (!nextTokenIs(b, "", DOUBLE_QUOTED_STRING, SINGLE_QUOTED_STRING)) return false;
    boolean r;
    r = consumeToken(b, SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    return r;
  }

  /* ********************************************************** */
  // (hyphen subParamKey) subParamValue?
  public static boolean subParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParam")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUB_PARAM, "<sub param>");
    r = subParam_0(b, l + 1);
    r = r && subParam_1(b, l + 1);
    exit_section_(b, l, m, r, false, GettingScreenplayScriptParser::param_Recover);
    return r;
  }

  // hyphen subParamKey
  private static boolean subParam_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParam_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = hyphen(b, l + 1);
    r = r && subParamKey(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // subParamValue?
  private static boolean subParam_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParam_1")) return false;
    subParamValue(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // alphabet
  public static boolean subParamKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParamKey")) return false;
    if (!nextTokenIs(b, ALPHABET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = alphabet(b, l + 1);
    exit_section_(b, m, SUB_PARAM_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // equals (option | content)
  public static boolean subParamValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParamValue")) return false;
    if (!nextTokenIs(b, EQUALS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = equals(b, l + 1);
    r = r && subParamValue_1(b, l + 1);
    exit_section_(b, m, SUB_PARAM_VALUE, r);
    return r;
  }

  // option | content
  private static boolean subParamValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParamValue_1")) return false;
    boolean r;
    r = option(b, l + 1);
    if (!r) r = content(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // subParam+
  public static boolean subParams(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subParams")) return false;
    if (!nextTokenIs(b, HYPHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subParam(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!subParam(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "subParams", c)) break;
    }
    exit_section_(b, m, SUB_PARAMS, r);
    return r;
  }

  /* ********************************************************** */
  // TRUE
  static boolean true_$(PsiBuilder b, int l) {
    return consumeToken(b, TRUE);
  }

  /* ********************************************************** */
  // object | array | literal | reference_expression
  static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    r = object(b, l + 1);
    if (!r) r = array(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = reference_expression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // VAR_PREFIX
  static boolean var_prefix(PsiBuilder b, int l) {
    return consumeToken(b, VAR_PREFIX);
  }

  /* ********************************************************** */
  // l_curly var_prefix "Base."? alphabet r_curly
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE, null);
    r = l_curly(b, l + 1);
    r = r && var_prefix(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, variable_2(b, l + 1));
    r = p && report_error_(b, alphabet(b, l + 1)) && r;
    r = p && r_curly(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "Base."?
  private static boolean variable_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_2")) return false;
    consumeToken(b, "Base.");
    return true;
  }

  /* ********************************************************** */
  // ( alphabet | double_quoted_string | generalWord ) variable? ( alphabet | double_quoted_string | generalWord )?
  static boolean word(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = word_0(b, l + 1);
    r = r && word_1(b, l + 1);
    r = r && word_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // alphabet | double_quoted_string | generalWord
  private static boolean word_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_0")) return false;
    boolean r;
    r = alphabet(b, l + 1);
    if (!r) r = double_quoted_string(b, l + 1);
    if (!r) r = generalWord(b, l + 1);
    return r;
  }

  // variable?
  private static boolean word_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_1")) return false;
    variable(b, l + 1);
    return true;
  }

  // ( alphabet | double_quoted_string | generalWord )?
  private static boolean word_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_2")) return false;
    word_2_0(b, l + 1);
    return true;
  }

  // alphabet | double_quoted_string | generalWord
  private static boolean word_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_2_0")) return false;
    boolean r;
    r = alphabet(b, l + 1);
    if (!r) r = double_quoted_string(b, l + 1);
    if (!r) r = generalWord(b, l + 1);
    return r;
  }

}
