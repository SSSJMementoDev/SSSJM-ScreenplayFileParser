package cn.monocur.sssjmscreenplayfileparser.screenplay;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.*;
import org.jetbrains.annotations.NotNull;

public class GettingScreenplayScriptAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof GettingScreenplayScriptCommandName) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                    .range(element)
                    .textAttributes(GettingScreenplayScriptSyntaxHighlighter.COMMAND_NAME)
                    .create();
        } else if (element instanceof GettingScreenplayScriptScreenplayConfig) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                    .range(element)
                    .textAttributes(GettingScreenplayScriptSyntaxHighlighter.CONFIG)
                    .create();
        } else if (element instanceof GettingScreenplayScriptOption) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                    .range(element)
                    .textAttributes(GettingScreenplayScriptSyntaxHighlighter.OPTION)
                    .create();
        } else if (element instanceof GettingScreenplayScriptVariable) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                    .range(element)
                    .textAttributes(GettingScreenplayScriptSyntaxHighlighter.VARIABLE)
                    .create();
        }
    }
}
