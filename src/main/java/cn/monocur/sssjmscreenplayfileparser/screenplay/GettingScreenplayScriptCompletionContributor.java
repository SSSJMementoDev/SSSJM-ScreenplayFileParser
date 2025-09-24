package cn.monocur.sssjmscreenplayfileparser.screenplay;

import cn.monocur.sssjmscreenplayfileparser.GettingScreenplayScriptFileParserBundle;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptCommand;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptCommandName;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptSubParamKey;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class GettingScreenplayScriptCompletionContributor extends CompletionContributor {
    public GettingScreenplayScriptCompletionContributor() {
        // 为 commandName 添加补全
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(GettingScreenplayScriptTypes.ALPHABET)
                        .withParent(GettingScreenplayScriptCommandName.class),
                new CommandNameCompletionProvider());

        // 为 subParamKey 添加补全
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(GettingScreenplayScriptTypes.ALPHABET)
                        .withParent(GettingScreenplayScriptSubParamKey.class),
                new SubParamKeyCompletionProvider());
    }

    // 命令名
    // 文本相关命令
    public static final String SET_TEXTBOX = "SetTextBox";
    public static final String GET_USER_INPUT = "GetUserInput";
    // 多媒体相关命令
    public static final String BGM = "BGM";
    public static final String PLAY_SOUND = "PlaySound";
    public static final String PLAY_VIDEO = "playVideo";
    // 图像相关命令
    public static final String CHANGE_BG = "ChangeBG";
    public static final String CHANGE_FIGURE = "ChangeFigure";
    // 切换相关命令
    public static final String CALL_SCENE = "CallScene";
    public static final String CHOOSE = "Choose";
    public static final String LABEL = "Label";
    public static final String JUMP_LABEL = "JumpLabel";
    // 系统相关命令
    public static final String SET_VAR = "SetVar";
    public static final String SET_ANIMATION = "SetAnimation";
    public static final String SET_TRANSFORM = "SetTransform";
    public static final String GEN_OBJECT = "GenObject";


    // 次要参数
    // 基本参数
    public static final String CONCAT = "concat";
    public static final String NEXT = "next";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String GLOBAL = "global";

    // 目标指向参数
    public static final String ID = "id";
    public static final String TARGET = "target";
    public static final String OPTION = "option";

    // 多媒体参数
    public static final String VOLUME = "volume";
    public static final String VOICE = "voice";
    public static final String SKIP_OFF = "skipOff";

    // 图像参数
    public static final String ENTER = "enter";
    public static final String EXIT = "exit";
    public static final String LAYER = "layer";

    // 文本参数
    public static final String WHEN = "when";
    public static final String TITLE = "title";
    public static final String BUTTON_TEXT = "buttonText";
    public static final String DEFAULT_TEXT = "defaultText";


    // CommandName 补全提供器
    private static class CommandNameCompletionProvider extends CompletionProvider<CompletionParameters> {
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

    // SubParamKey 补全提供器
    private static class SubParamKeyCompletionProvider extends CompletionProvider<CompletionParameters> {
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
                switch (commandName) {
                    case "open":
                        result.addElement(createParamLookup("url", "URL地址"));
                        result.addElement(createParamLookup("browser", "浏览器类型"));
                        result.addElement(createParamLookup("timeout", "超时时间"));
                        result.addElement(createParamLookup("maximize", "最大化窗口"));
                        break;
                    case "click":
                    case "type":
                    case "select":
                        result.addElement(createParamLookup("by", "定位方式"));
                        result.addElement(createParamLookup("index", "元素索引"));
                        result.addElement(createParamLookup("timeout", "超时时间"));
                        result.addElement(createParamLookup("retry", "重试次数"));
                        break;
                    case "verify":
                    case "assert":
                        result.addElement(createParamLookup("expected", "期望值"));
                        result.addElement(createParamLookup("message", "验证消息"));
                        result.addElement(createParamLookup("timeout", "超时时间"));
                        break;
                    case "wait":
                        result.addElement(createParamLookup("time", "等待时间"));
                        result.addElement(createParamLookup("condition", "等待条件"));
                        result.addElement(createParamLookup("polling", "轮询间隔"));
                        break;
                    case "navigate":
                        result.addElement(createParamLookup("url", "导航URL"));
                        result.addElement(createParamLookup("forward", "前进"));
                        result.addElement(createParamLookup("back", "后退"));
                        break;
                    default:
                        // 为未知命令添加通用参数
                        addGeneralParamSuggestions(result);
                        break;
                }
            }
        }

        private void addGeneralParamSuggestions(CompletionResultSet result) {
            // 通用参数键
            result.addElement(createParamLookup("timeout", "超时时间"));
            result.addElement(createParamLookup("retry", "重试次数"));
            result.addElement(createParamLookup("delay", "延迟时间"));
            result.addElement(createParamLookup("condition", "执行条件"));
            result.addElement(createParamLookup("message", "消息文本"));
            result.addElement(createParamLookup("value", "参数值"));
            result.addElement(createParamLookup("index", "索引位置"));
            result.addElement(createParamLookup("count", "数量统计"));
            result.addElement(createParamLookup("from", "来源位置"));
            result.addElement(createParamLookup("to", "目标位置"));
        }

        private LookupElement createParamLookup(String param, String description) {
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
    }
}
