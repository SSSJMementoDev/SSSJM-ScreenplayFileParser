package cn.monocur.sssjmscreenplayfileparser.screenplay.psi;

import cn.monocur.sssjmscreenplayfileparser.screenplay.GettingScreenplayScriptLexerAdapter;
import cn.monocur.sssjmscreenplayfileparser.screenplay.GettingScreenplayScriptLanguage;
import cn.monocur.sssjmscreenplayfileparser.screenplay.parser.GettingScreenplayScriptParser;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class GettingScreenplayScriptParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(GettingScreenplayScriptLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new GettingScreenplayScriptLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new GettingScreenplayScriptParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode astNode) {
        return GettingScreenplayScriptTypes.Factory.createElement(astNode);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider) {
        return new GettingScreenplayScriptFile( fileViewProvider );
    }
}
