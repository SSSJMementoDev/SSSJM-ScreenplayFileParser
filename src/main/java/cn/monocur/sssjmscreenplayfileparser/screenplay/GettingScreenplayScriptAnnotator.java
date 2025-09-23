package cn.monocur.sssjmscreenplayfileparser.screenplay;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.*;
import org.jetbrains.annotations.NotNull;

public class GettingScreenplayScriptAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof GettingScreenplayScriptCommandName) {
            holder.createInfoAnnotation(element, null)
                    .setTextAttributes(GettingScreenplayScriptSyntaxHighlighter.COMMAND_NAME);
        } else if (element instanceof GettingScreenplayScriptScreenplayConfig) {
            holder.createInfoAnnotation(element, null)
                    .setTextAttributes(GettingScreenplayScriptSyntaxHighlighter.CONFIG);
        } else if (element instanceof GettingScreenplayScriptOption) {
            holder.createInfoAnnotation(element, null)
                    .setTextAttributes(GettingScreenplayScriptSyntaxHighlighter.OPTION);
        } else if (element instanceof GettingScreenplayScriptVariable) {
            holder.createInfoAnnotation(element, null)
                    .setTextAttributes(GettingScreenplayScriptSyntaxHighlighter.VARIABLE);
        }
    }
}
