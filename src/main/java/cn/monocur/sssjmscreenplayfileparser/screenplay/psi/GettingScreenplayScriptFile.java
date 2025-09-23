package cn.monocur.sssjmscreenplayfileparser.screenplay.psi;

import cn.monocur.sssjmscreenplayfileparser.screenplay.GettingScreenplayScriptFileType;
import cn.monocur.sssjmscreenplayfileparser.screenplay.GettingScreenplayScriptLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class GettingScreenplayScriptFile extends PsiFileBase {

    public GettingScreenplayScriptFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, GettingScreenplayScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return GettingScreenplayScriptFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Getting Screenplay Script File";
    }

}