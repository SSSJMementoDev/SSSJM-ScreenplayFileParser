package cn.monocur.sssjmscreenplayfileparser.screenplay;

import com.intellij.lang.Language;

public class GettingScreenplayScriptLanguage extends Language {

    public static final GettingScreenplayScriptLanguage INSTANCE =  new GettingScreenplayScriptLanguage();

    protected GettingScreenplayScriptLanguage() {
        super("GettingScreenplayScript");
    }
}
