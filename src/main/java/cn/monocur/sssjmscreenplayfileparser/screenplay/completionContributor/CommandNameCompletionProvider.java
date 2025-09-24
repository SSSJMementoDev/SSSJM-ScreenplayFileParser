package cn.monocur.sssjmscreenplayfileparser.screenplay.completionContributor;

import cn.monocur.sssjmscreenplayfileparser.GettingScreenplayScriptFileParserBundle;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

import static cn.monocur.sssjmscreenplayfileparser.screenplay.completionContributor.GettingScreenplayScriptCompletionContributor.*;

class CommandNameCompletionProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        // 添加常用命令建议
        addCommandSuggestions(result);

        // 添加上下文相关的命令建议
        addContextualCommandSuggestions(parameters, result);
    }

    private void registerSuggestions(CompletionResultSet resultSet, String... commandNames) {
        for (var commandName : commandNames) {
            var I18nDescriptionKey = MessageFormat.format("completionContributor.commandName.{0}", commandName.toLowerCase());
            resultSet.addElement(
                    createCommandLookup(
                            commandName,
                            GettingScreenplayScriptFileParserBundle.message(I18nDescriptionKey)
                    )
            );
        }
    }

    private void addCommandSuggestions(CompletionResultSet result) {
        // 多媒体命令
        registerSuggestions(result,
                BGM,
                PLAY_SOUND,
                PLAY_VIDEO
        );
        // 图像命令
        registerSuggestions(result,
                CHANGE_BG,
                CHANGE_FIGURE
        );
        // 切换命令
        registerSuggestions(result,
                CALL_SCENE,
                CHOOSE,
                LABEL,
                JUMP_LABEL
        );
        // 系统命令
        registerSuggestions(result,
                SET_VAR,
                SET_ANIMATION,
                SET_TRANSFORM,
                GEN_OBJECT,
                SET_TEXTBOX,
                GET_USER_INPUT
        );
    }

    private void addContextualCommandSuggestions(CompletionParameters parameters, CompletionResultSet result) {
        PsiElement position = parameters.getPosition();
        PsiFile file = position.getContainingFile();

            /*// 分析文件内容，提供上下文相关的建议
            if (file != null) {
                String text = file.getText();

                // 如果文件中已经使用了某些命令，推荐相关的命令
                if (text.contains("open")) {
                    result.addElement(createCommandLookup("login", "登录操作"));
                    result.addElement(createCommandLookup("logout", "登出操作"));
                }

                if (text.contains("click") || text.contains("type")) {
                    result.addElement(createCommandLookup("clear", "清除输入"));
                    result.addElement(createCommandLookup("submit", "提交表单"));
                }

                if (text.contains("verify") || text.contains("assert")) {
                    result.addElement(createCommandLookup("check", "检查条件"));
                    result.addElement(createCommandLookup("validate", "验证数据"));
                }
            }*/
    }

    private LookupElement createCommandLookup(String command, String description) {
        return LookupElementBuilder.create(command)
                .withTypeText(description)
                .withIcon(AllIcons.Nodes.Class)
                .withInsertHandler((context, item) -> {
                    Editor editor = context.getEditor();
                    Document document = editor.getDocument();
                    int offset = context.getTailOffset();

                    // 检查后面是否已经有冒号
                    boolean hasColonAfter = hasColonAfterPosition(editor, offset);

                    if (!hasColonAfter) {
                        // 插入带空格的冒号增强可读性
                        document.insertString(offset, " : ");
                        editor.getCaretModel().moveToOffset(offset + 3);
                    } else {
                        // 如果已有冒号，只移动光标到冒号后
                        moveCaretAfterColon(editor, offset);
                    }
                });
    }

    /**
     * 检查指定位置后是否有冒号
     */
    private boolean hasColonAfterPosition(Editor editor, int offset) {
        Document document = editor.getDocument();
        int textLength = document.getTextLength();

        // 从当前位置开始检查后续字符
        for (int i = offset; i < textLength; i++) {
            char c = document.getText().charAt(i);
            if (c == ':') {
                return true;
            }
            if (!Character.isWhitespace(c)) {
                // 遇到非空白字符，说明没有冒号
                break;
            }
        }
        return false;
    }

    /**
     * 移动光标到最近的冒号后面
     */
    private void moveCaretAfterColon(Editor editor, int offset) {
        Document document = editor.getDocument();
        String text = document.getText();

        // 查找最近的冒号
        for (int i = offset; i < text.length(); i++) {
            if (text.charAt(i) == ':') {
                // 移动到冒号后，跳过可能的空格
                int newOffset = i + 1;
                while (newOffset < text.length() && Character.isWhitespace(text.charAt(newOffset))) {
                    newOffset++;
                }
                editor.getCaretModel().moveToOffset(newOffset);
                return;
            }
        }
    }
}