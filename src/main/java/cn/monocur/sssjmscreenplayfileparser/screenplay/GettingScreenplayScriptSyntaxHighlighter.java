package cn.monocur.sssjmscreenplayfileparser.screenplay;

import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GettingScreenplayScriptSyntaxHighlighter extends SyntaxHighlighterBase {// 在您的 SyntaxHighlighter 或相关类中定义这些颜色

    // 定义 TextAttributesKey 常量
    public static final TextAttributesKey COMMAND_NAME =
            createTextAttributesKey("GETTING_SCREENPLAY.COMMAND_NAME",
                    DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("GETTING_SCREENPLAY.SEPARATOR",
                    DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey STRING =
            createTextAttributesKey("GETTING_SCREENPLAY.STRING",
                    DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("GETTING_SCREENPLAY.NUMBER",
                    DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey ALPHABET =
            createTextAttributesKey("GETTING_SCREENPLAY.ALPHABET",
                    DefaultLanguageHighlighterColors.IDENTIFIER);

    public static final TextAttributesKey WORD =
            createTextAttributesKey("GETTING_SCREENPLAY.WORD",
                    DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey CONFIG =
            createTextAttributesKey("GETTING_SCREENPLAY.CONFIG",
                    DefaultLanguageHighlighterColors.METADATA);

    public static final TextAttributesKey OPTION =
            createTextAttributesKey("GETTING_SCREENPLAY.OPTION",
                    DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    public static final TextAttributesKey VARIABLE =
            createTextAttributesKey("GETTING_SCREENPLAY.VARIABLE",
                    DefaultLanguageHighlighterColors.LOCAL_VARIABLE);

    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("GETTING_SCREENPLAY.COMMENT",
                    DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey COMMON_VALUE =
            createTextAttributesKey("GETTING_SCREENPLAY.COMMON_VALUE",
                    DefaultLanguageHighlighterColors.KEYWORD);

    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

    static {
        // 为每个分隔符 token 单独设置属性
        ATTRIBUTES.put(GettingScreenplayScriptTypes.COLON, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.SEMICOLON, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.EQUALS, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.HYPHEN, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.COMMA, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.VAR_PREFIX, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.HASH, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.POINT_TO, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.L_CURLY, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.R_CURLY, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.L_BRACKET, SEPARATOR);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.R_BRACKET, SEPARATOR);

        // 布尔与空
        ATTRIBUTES.put(GettingScreenplayScriptTypes.TRUE, COMMON_VALUE);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.FALSE, COMMON_VALUE);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.NULL, COMMON_VALUE);

        // 设置其他 token 的属性
        // 字符串绿
        ATTRIBUTES.put(GettingScreenplayScriptTypes.DOUBLE_QUOTED_STRING, STRING);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.SINGLE_QUOTED_STRING, STRING);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.IDENTIFIER, STRING);
        // 数字蓝
        ATTRIBUTES.put(GettingScreenplayScriptTypes.NUMBER, NUMBER);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.WORD, WORD);
        // 标识符白
        ATTRIBUTES.put(GettingScreenplayScriptTypes.ALPHABET, ALPHABET);
        // 关键字亮橙
        ATTRIBUTES.put(GettingScreenplayScriptTypes.COMMAND_NAME, COMMAND_NAME);
        // 变量深绿
        ATTRIBUTES.put(GettingScreenplayScriptTypes.VARIABLE, VARIABLE);
        // 注释深灰
        ATTRIBUTES.put(GettingScreenplayScriptTypes.LINE_COMMENT, COMMENT);
        ATTRIBUTES.put(GettingScreenplayScriptTypes.BLOCK_COMMENT, COMMENT);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new GettingScreenplayScriptLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}