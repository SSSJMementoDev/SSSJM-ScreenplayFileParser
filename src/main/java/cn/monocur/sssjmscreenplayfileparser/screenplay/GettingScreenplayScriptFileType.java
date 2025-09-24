package cn.monocur.sssjmscreenplayfileparser.screenplay;

import cn.monocur.sssjmscreenplayfileparser.GettingScreenplayScriptFileParserBundle;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GettingScreenplayScriptFileType extends LanguageFileType {

    public static final  GettingScreenplayScriptFileType INSTANCE = new GettingScreenplayScriptFileType();

    private GettingScreenplayScriptFileType() {
        super(GettingScreenplayScriptLanguage.INSTANCE);
    }

    protected GettingScreenplayScriptFileType(@NotNull Language language) {
        super(language);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "GettingScreenplayScript";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return GettingScreenplayScriptFileParserBundle.message("filetype.description");
    }

    @Override
    public @NotNull String getDisplayName() {
        // 正确：从资源包返回本地化的显示名称
        return GettingScreenplayScriptFileParserBundle.message("filetype.displayname");
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "gss";
    }

    @Override
    public @Nullable Icon getIcon() {
        return GettingScreenplayScriptIcons.ScriptFile;
    }

    @Override
    public @NonNls @Nullable String getCharset(@NotNull VirtualFile file, byte @NotNull [] content) {
        return super.getCharset(file, content);
    }
}
