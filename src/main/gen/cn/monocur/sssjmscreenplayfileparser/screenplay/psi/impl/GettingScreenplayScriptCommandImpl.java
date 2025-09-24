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

public class GettingScreenplayScriptCommandImpl extends ASTWrapperPsiElement implements GettingScreenplayScriptCommand {

  public GettingScreenplayScriptCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GettingScreenplayScriptVisitor visitor) {
    visitor.visitCommand(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GettingScreenplayScriptVisitor) accept((GettingScreenplayScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GettingScreenplayScriptCommandName getCommandName() {
    return findNotNullChildByClass(GettingScreenplayScriptCommandName.class);
  }

  @Override
  @Nullable
  public GettingScreenplayScriptMainParam getMainParam() {
    return findChildByClass(GettingScreenplayScriptMainParam.class);
  }

  @Override
  @Nullable
  public GettingScreenplayScriptSubParams getSubParams() {
    return findChildByClass(GettingScreenplayScriptSubParams.class);
  }

  @Override
  @Nullable
  public PsiElement getBlockComment() {
    return findChildByType(BLOCK_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getLineComment() {
    return findChildByType(LINE_COMMENT);
  }

}
