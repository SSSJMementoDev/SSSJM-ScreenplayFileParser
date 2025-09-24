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

public class GettingScreenplayScriptCommandNameImpl extends ASTWrapperPsiElement implements GettingScreenplayScriptCommandName {

  public GettingScreenplayScriptCommandNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GettingScreenplayScriptVisitor visitor) {
    visitor.visitCommandName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GettingScreenplayScriptVisitor) accept((GettingScreenplayScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getAlphabet() {
    return findChildByType(ALPHABET);
  }

  @Override
  @Nullable
  public PsiElement getWord() {
    return findChildByType(WORD);
  }

}
