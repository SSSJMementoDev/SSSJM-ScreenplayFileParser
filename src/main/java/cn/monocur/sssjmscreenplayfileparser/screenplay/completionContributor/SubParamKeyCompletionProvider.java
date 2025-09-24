package cn.monocur.sssjmscreenplayfileparser.screenplay.completionContributor;

import cn.monocur.sssjmscreenplayfileparser.GettingScreenplayScriptFileParserBundle;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptCommand;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptCommandName;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

import static cn.monocur.sssjmscreenplayfileparser.screenplay.completionContributor.GettingScreenplayScriptCompletionContributor.*;

class SubParamKeyCompletionProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        // 获取当前命令的上下文
        String commandName = getCurrentCommandName(parameters.getPosition());

        // 添加上下文相关的参数键建议
        addContextualParamSuggestions(result, commandName);

        // 添加通用参数键建议
        addGeneralParamSuggestions(result);
    }

    private String getCurrentCommandName(PsiElement position) {
        // 向上遍历找到最近的命令元素
        PsiElement current = position;
        while (current != null && !(current instanceof GettingScreenplayScriptCommand)) {
            current = current.getParent();
        }

        if (current instanceof GettingScreenplayScriptCommand) {
            GettingScreenplayScriptCommand command = (GettingScreenplayScriptCommand) current;
            GettingScreenplayScriptCommandName commandNameElement = command.getCommandName();
            if (commandNameElement != null) {
                return commandNameElement.getText();
            }
        }
        return null;
    }

    private void registerSuggestions(CompletionResultSet resultSet, String... commandNames) {
        for (var commandName : commandNames) {
            var I18nDescriptionKey = MessageFormat.format("completionContributor.subParam.{0}", commandName);
            resultSet.addElement(
                    createParamLookup(
                            commandName,
                            GettingScreenplayScriptFileParserBundle.message(I18nDescriptionKey)
                    )
            );
        }
    }

    private void addContextualParamSuggestions(CompletionResultSet result, String commandName) {
        if (commandName != null) {
            switch (commandName.trim()) {
                case GET_USER_INPUT:
                    registerSuggestions(result,
                            TITLE,
                            BUTTON_TEXT,
                            DEFAULT_TEXT
                    );
                    break;
                case BGM, PLAY_SOUND, PLAY_VIDEO:
                    registerSuggestions(result,
                            VOLUME,
                            ENTER,
                            EXIT,
                            SKIP_OFF,
                            ID,
                            TARGET
                    );
                    break;
                case CHANGE_BG, CHANGE_FIGURE:
                    registerSuggestions(result,
                            ID,
                            ENTER,
                            EXIT,
                            LAYER,
                            MOTION,
                            TARGET,
                            TRANSFORM,
                            LEFT,
                            RIGHT,
                            CENTER
                    );
                    break;
                case CHOOSE:
                    registerSuggestions(result,
                            OPTION
                    );
                    break;
                case SET_VAR:
                    registerSuggestions(result,
                            GLOBAL
                    );
                    break;
                case SET_ANIMATION:
                    registerSuggestions(result,
                            TARGET
                    );
                    break;
                case SET_TRANSFORM:
                    registerSuggestions(result,
                            TARGET,
                            DURATION
                    );
                    break;
                case GEN_OBJECT:
                    registerSuggestions(result,
                            ID
                    );
                    break;
                default:
                    // 为未知命令添加通用参数
                    addGeneralParamSuggestions(result);
                    System.out.println(commandName);
                    break;
            }
        }
    }

    private void addGeneralParamSuggestions(CompletionResultSet result) {
        registerSuggestions(result,
                ASYNC,
                CLEAR,
                CONCAT,
                HINDER,
                NEXT,
                STATE,
                WHEN,
                VOICE
        );
    }

    private LookupElement createParamLookup(String param, String description) {
        switch (param){
            case OPTION -> {
                return LookupElementBuilder.create(param)
                        .withTypeText(description)
                        .withIcon(AllIcons.Nodes.Parameter)
                        .withInsertHandler((context, item) -> {
                            Editor editor = context.getEditor();
                            Document document = editor.getDocument();
                            int offset = context.getTailOffset();

                            // 检查后面是否已经有等号
                            boolean hasEqualsAfter = hasEqualsAfterPosition(editor, offset);

                            if (!hasEqualsAfter) {
                                // 插入带空格的等号增强可读性
                                document.insertString(offset, " = ");
                                offset += 3; // 更新偏移量
                            } else {
                                // 如果已有等号，移动光标到等号后
                                moveCaretAfterEquals(editor, offset);
                                // 重新获取偏移量
                                offset = editor.getCaretModel().getOffset();
                            }

                            // 检查是否已存在 option 结构
                            if (hasOptionStructureAfterPosition(editor, offset)) {
                                // 如果已存在 option 结构，移动光标到合适位置
                                moveCaretToOptionContent(editor, offset);
                            } else {
                                // 插入 option 参数模板
                                String template = "[->]";
                                document.insertString(offset, template);

                                // 将光标定位在 -> 和 ] 之间
                                int caretPosition = offset + template.indexOf(']');
                                editor.getCaretModel().moveToOffset(caretPosition);
                            }
                        });
            }
            case NEXT, CONCAT, HINDER, LEFT, RIGHT, CENTER, GLOBAL -> {
                return LookupElementBuilder.create(param)
                        .withTypeText(description)
                        .withIcon(AllIcons.Nodes.Parameter)
                        .withInsertHandler((context, item) -> {
                            Editor editor = context.getEditor();
                            Document document = editor.getDocument();
                            int offset = context.getTailOffset();

                            // 检查后面是否已经有分号
                            boolean hasSemicolonAfter = hasSemicolonAfterPosition(editor, offset);

                            if (!hasSemicolonAfter) {
                                // 插入分号
                                document.insertString(offset, ";");
                                // 移动光标到分号后
                                editor.getCaretModel().moveToOffset(offset + 1);
                            } else {
                                // 如果已有分号，移动光标到分号后
                                moveCaretAfterSemicolon(editor, offset);
                            }
                        });
            }
            default -> // 其他参数使用默认插入处理器
            {
                return LookupElementBuilder.create(param)
                .withTypeText(description)
                .withIcon(AllIcons.Nodes.Parameter)
                .withInsertHandler((context, item) -> {
                    Editor editor = context.getEditor();
                    Document document = editor.getDocument();
                    int offset = context.getTailOffset();

                    // 检查后面是否已经有等号
                    boolean hasEqualsAfter = hasEqualsAfterPosition(editor, offset);

                    if (!hasEqualsAfter) {
                        // 插入带空格的等号增强可读性
                        document.insertString(offset, " = ");
                        editor.getCaretModel().moveToOffset(offset + 3);
                    } else {
                        // 如果已有等号，只移动光标到等号后
                        moveCaretAfterEquals(editor, offset);
                    }
                });
            }
        }
    }

    /**
     * 检查指定位置后是否有等号
     */
    private boolean hasEqualsAfterPosition(Editor editor, int offset) {
        Document document = editor.getDocument();
        int textLength = document.getTextLength();

        // 从当前位置开始检查后续字符
        for (int i = offset; i < textLength; i++) {
            char c = document.getText().charAt(i);
            if (c == '=') {
                return true;
            }
            if (!Character.isWhitespace(c)) {
                // 遇到非空白字符，说明没有等号
                break;
            }
        }
        return false;
    }

    /**
     * 移动光标到最近的等号后面
     */
    private void moveCaretAfterEquals(Editor editor, int offset) {
        Document document = editor.getDocument();
        String text = document.getText();

        // 查找最近的等号
        for (int i = offset; i < text.length(); i++) {
            if (text.charAt(i) == '=') {
                // 移动到等号后，跳过可能的空格
                int newOffset = i + 1;
                while (newOffset < text.length() && Character.isWhitespace(text.charAt(newOffset))) {
                    newOffset++;
                }
                editor.getCaretModel().moveToOffset(newOffset);
                return;
            }
        }
    }


    // 检查指定位置后是否有分号
    private boolean hasSemicolonAfterPosition(Editor editor, int offset) {
        Document document = editor.getDocument();
        int textLength = document.getTextLength();

        // 从当前位置开始检查后续字符
        for (int i = offset; i < textLength; i++) {
            char c = document.getText().charAt(i);
            if (c == ';') {
                return true;
            }
            if (!Character.isWhitespace(c)) {
                // 遇到非空白字符，说明没有分号
                break;
            }
        }
        return false;
    }

    // 移动光标到最近的分号后面
    private void moveCaretAfterSemicolon(Editor editor, int offset) {
        Document document = editor.getDocument();
        String text = document.getText();

        // 查找最近的分号
        for (int i = offset; i < text.length(); i++) {
            if (text.charAt(i) == ';') {
                // 移动到分号后，跳过可能的空格
                int newOffset = i + 1;
                while (newOffset < text.length() && Character.isWhitespace(text.charAt(newOffset))) {
                    newOffset++;
                }
                editor.getCaretModel().moveToOffset(newOffset);
                return;
            }
        }
    }

    /**
     * 检查指定位置后是否存在 option 结构
     */
    private boolean hasOptionStructureAfterPosition(Editor editor, int offset) {
        Document document = editor.getDocument();
        String text = document.getText();
        int textLength = text.length();

        // 从当前位置开始检查后续字符
        for (int i = offset; i < textLength; i++) {
            char c = text.charAt(i);

            // 跳过空白字符
            if (Character.isWhitespace(c)) {
                continue;
            }

            // 检查是否以 [-> 开头
            if (i + 2 < textLength &&
                    text.charAt(i) == '[' &&
                    text.charAt(i + 1) == '-' &&
                    text.charAt(i + 2) == '>') {
                return true;
            }

            // 如果遇到非空白字符但不是 [->，说明没有 option 结构
            break;
        }

        return false;
    }

    /**
     * 移动光标到 option 内容区域
     */
    private void moveCaretToOptionContent(Editor editor, int offset) {
        Document document = editor.getDocument();
        String text = document.getText();

        // 查找 option 结构的开始
        for (int i = offset; i < text.length(); i++) {
            // 检查是否找到 [->
            if (i + 2 < text.length() &&
                    text.charAt(i) == '[' &&
                    text.charAt(i + 1) == '-' &&
                    text.charAt(i + 2) == '>') {

                // 查找结束的 ]
                for (int j = i + 3; j < text.length(); j++) {
                    if (text.charAt(j) == ']') {
                        // 移动到 ] 后面，跳过可能的空格
                        int newOffset = j + 1;
                        while (newOffset < text.length() && Character.isWhitespace(text.charAt(newOffset))) {
                            newOffset++;
                        }
                        editor.getCaretModel().moveToOffset(newOffset - 1);
                        return;
                    }
                }

                // 如果没有找到结束的 ]，移动到 -> 后面
                editor.getCaretModel().moveToOffset(i + 3);
                return;
            }
        }
    }
}
