package cn.monocur.sssjmscreenplayfileparser.screenplay;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes.*;

%%

%{
  public _GettingScreenplayScriptLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _GettingScreenplayScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

DOUBLE_QUOTED_STRING=\"([^\\\"\r\n]|\\[^\r\n])*\"?
SINGLE_QUOTED_STRING='([^\\'\r\n]|\\[^\r\n])*'?
NUMBER=-?(0|[1-9][0-9]*)(\.[0-9]+)?([eE][+-]?[0-9]*)?
ALPHABET=[a-zA-Z_][a-zA-Z_0-9 ,.!?]*
WORD=[^\s:;=\-\"'\[\]\{\}$#,.-/]*
SPACE=[ \t\n\x0B\f\r]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?

%%
<YYINITIAL> {
  {WHITE_SPACE}                { return WHITE_SPACE; }

  "-"                          { return HYPHEN; }
  "="                          { return EQUALS; }
  ";"                          { return SEMICOLON; }
  ":"                          { return COLON; }
  ","                          { return COMMA; }
  "{"                          { return L_CURLY; }
  "}"                          { return R_CURLY; }
  "["                          { return L_BRACKET; }
  "]"                          { return R_BRACKET; }
  "$"                          { return VAR_PREFIX; }
  "#"                          { return HASH; }
  "->"                         { return POINT_TO; }
  "true"                       { return TRUE; }
  "false"                      { return FALSE; }
  "null"                       { return NULL; }
  "testComment"                { return IDENTIFIER; }

  {DOUBLE_QUOTED_STRING}       { return DOUBLE_QUOTED_STRING; }
  {SINGLE_QUOTED_STRING}       { return SINGLE_QUOTED_STRING; }
  {NUMBER}                     { return NUMBER; }
  {ALPHABET}                   { return ALPHABET; }
  {WORD}                       { return WORD; }
  {SPACE}                      { return SPACE; }
  {LINE_COMMENT}               { return LINE_COMMENT; }
  {BLOCK_COMMENT}              { return BLOCK_COMMENT; }

}

[^] { return BAD_CHARACTER; }
