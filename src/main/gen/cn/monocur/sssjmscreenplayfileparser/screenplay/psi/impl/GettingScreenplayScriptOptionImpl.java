// This is a generated file. Not intended for manual editing.
package cn.monocur.sssjmscreenplayfileparser.screenplay.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.*;

public class GettingScreenplayScriptOptionImpl extends ASTWrapperPsiElement implements GettingScreenplayScriptOption {

  public GettingScreenplayScriptOptionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GettingScreenplayScriptVisitor visitor) {
    visitor.visitOption(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GettingScreenplayScriptVisitor) accept((GettingScreenplayScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GettingScreenplayScriptOptionContent getOptionContent() {
    return findNotNullChildByClass(GettingScreenplayScriptOptionContent.class);
  }

  @Override
  @NotNull
  public GettingScreenplayScriptOptionTarget getOptionTarget() {
    return findNotNullChildByClass(GettingScreenplayScriptOptionTarget.class);
  }

}
