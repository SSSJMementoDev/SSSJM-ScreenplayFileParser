package cn.monocur.sssjmscreenplayfileparser.screenplay;

import com.intellij.lexer.FlexAdapter;

public class GettingScreenplayScriptLexerAdapter extends FlexAdapter {

    public GettingScreenplayScriptLexerAdapter() {
        super(new _GettingScreenplayScriptLexer(null));
    }
}