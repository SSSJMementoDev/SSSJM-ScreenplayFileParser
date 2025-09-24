package cn.monocur.sssjmscreenplayfileparser.screenplay.completionContributor;

import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptCommandName;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptSubParamKey;
import cn.monocur.sssjmscreenplayfileparser.screenplay.psi.GettingScreenplayScriptTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;

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
    public static final String HINDER = "Hinder";
    public static final String NEXT = "next";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String CENTER = "center";

    // 目标指向参数
    public static final String ID = "id";
    public static final String TARGET = "target";
    public static final String TRANSFORM = "transform";
    public static final String OPTION = "option";

    // 多媒体参数
    public static final String VOLUME = "volume";
    public static final String VOICE = "voice";
    public static final String SKIP_OFF = "skipOff";

    // 图像参数
    public static final String ENTER = "enter";
    public static final String EXIT = "exit";
    public static final String MOTION = "motion";
    public static final String LAYER = "layer";

    // 文本参数
    public static final String TITLE = "title";
    public static final String BUTTON_TEXT = "buttonText";
    public static final String DEFAULT_TEXT = "defaultText";
    public static final String CLEAR  = "clear";

    // 系统参数
    public static final String ASYNC = "async";
    public static final String DURATION = "duration";
    public static final String GLOBAL = "global";
    public static final String WHEN = "when";
    public static final String STATE = "state";


    // CommandName 补全提供器


    // SubParamKey 补全提供器
}
