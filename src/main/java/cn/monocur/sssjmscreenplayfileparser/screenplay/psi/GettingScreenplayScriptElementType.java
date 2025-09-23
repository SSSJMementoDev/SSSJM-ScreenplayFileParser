package cn.monocur.sssjmscreenplayfileparser.screenplay.psi;

import cn.monocur.sssjmscreenplayfileparser.screenplay.GettingScreenplayScriptLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GettingScreenplayScriptElementType extends IElementType {
    public GettingScreenplayScriptElementType(@NonNls @NotNull String debugName) {
        super(debugName, GettingScreenplayScriptLanguage.INSTANCE);
    }
}
