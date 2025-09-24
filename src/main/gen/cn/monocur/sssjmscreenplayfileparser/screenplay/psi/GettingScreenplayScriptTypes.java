// This is a generated file. Not intended for manual editing.
package cn.monocur.sssjmscreenplayfileparser.screenplay.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.impl.*;

public interface GettingScreenplayScriptTypes {

  IElementType COMMAND = new GettingScreenplayScriptElementType("COMMAND");
  IElementType COMMAND_NAME = new GettingScreenplayScriptElementType("COMMAND_NAME");
  IElementType JSON = new GettingScreenplayScriptElementType("JSON");
  IElementType MAIN_PARAM = new GettingScreenplayScriptElementType("MAIN_PARAM");
  IElementType OPTION = new GettingScreenplayScriptElementType("OPTION");
  IElementType OPTION_CONTENT = new GettingScreenplayScriptElementType("OPTION_CONTENT");
  IElementType OPTION_TARGET = new GettingScreenplayScriptElementType("OPTION_TARGET");
  IElementType SCREENPLAY_CONFIG = new GettingScreenplayScriptElementType("SCREENPLAY_CONFIG");
  IElementType SUB_PARAM = new GettingScreenplayScriptElementType("SUB_PARAM");
  IElementType SUB_PARAMS = new GettingScreenplayScriptElementType("SUB_PARAMS");
  IElementType SUB_PARAM_KEY = new GettingScreenplayScriptElementType("SUB_PARAM_KEY");
  IElementType SUB_PARAM_VALUE = new GettingScreenplayScriptElementType("SUB_PARAM_VALUE");
  IElementType VARIABLE = new GettingScreenplayScriptElementType("VARIABLE");

  IElementType ALPHABET = new GettingScreenplayScriptTokenType("ALPHABET");
  IElementType BLOCK_COMMENT = new GettingScreenplayScriptTokenType("BLOCK_COMMENT");
  IElementType COLON = new GettingScreenplayScriptTokenType(":");
  IElementType COMMA = new GettingScreenplayScriptTokenType(",");
  IElementType DOUBLE_QUOTED_STRING = new GettingScreenplayScriptTokenType("DOUBLE_QUOTED_STRING");
  IElementType EQUALS = new GettingScreenplayScriptTokenType("=");
  IElementType FALSE = new GettingScreenplayScriptTokenType("false");
  IElementType HASH = new GettingScreenplayScriptTokenType("#");
  IElementType HYPHEN = new GettingScreenplayScriptTokenType("-");
  IElementType IDENTIFIER = new GettingScreenplayScriptTokenType("testComment");
  IElementType LINE_COMMENT = new GettingScreenplayScriptTokenType("LINE_COMMENT");
  IElementType L_BRACKET = new GettingScreenplayScriptTokenType("[");
  IElementType L_CURLY = new GettingScreenplayScriptTokenType("{");
  IElementType NULL = new GettingScreenplayScriptTokenType("null");
  IElementType NUMBER = new GettingScreenplayScriptTokenType("NUMBER");
  IElementType POINT_TO = new GettingScreenplayScriptTokenType("->");
  IElementType R_BRACKET = new GettingScreenplayScriptTokenType("]");
  IElementType R_CURLY = new GettingScreenplayScriptTokenType("}");
  IElementType SEMICOLON = new GettingScreenplayScriptTokenType(";");
  IElementType SINGLE_QUOTED_STRING = new GettingScreenplayScriptTokenType("SINGLE_QUOTED_STRING");
  IElementType TRUE = new GettingScreenplayScriptTokenType("true");
  IElementType VAR_PREFIX = new GettingScreenplayScriptTokenType("$");
  IElementType WORD = new GettingScreenplayScriptTokenType("WORD");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMAND) {
        return new GettingScreenplayScriptCommandImpl(node);
      }
      else if (type == COMMAND_NAME) {
        return new GettingScreenplayScriptCommandNameImpl(node);
      }
      else if (type == JSON) {
        return new GettingScreenplayScriptJsonImpl(node);
      }
      else if (type == MAIN_PARAM) {
        return new GettingScreenplayScriptMainParamImpl(node);
      }
      else if (type == OPTION) {
        return new GettingScreenplayScriptOptionImpl(node);
      }
      else if (type == OPTION_CONTENT) {
        return new GettingScreenplayScriptOptionContentImpl(node);
      }
      else if (type == OPTION_TARGET) {
        return new GettingScreenplayScriptOptionTargetImpl(node);
      }
      else if (type == SCREENPLAY_CONFIG) {
        return new GettingScreenplayScriptScreenplayConfigImpl(node);
      }
      else if (type == SUB_PARAM) {
        return new GettingScreenplayScriptSubParamImpl(node);
      }
      else if (type == SUB_PARAMS) {
        return new GettingScreenplayScriptSubParamsImpl(node);
      }
      else if (type == SUB_PARAM_KEY) {
        return new GettingScreenplayScriptSubParamKeyImpl(node);
      }
      else if (type == SUB_PARAM_VALUE) {
        return new GettingScreenplayScriptSubParamValueImpl(node);
      }
      else if (type == VARIABLE) {
        return new GettingScreenplayScriptVariableImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
