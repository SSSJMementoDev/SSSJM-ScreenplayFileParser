package cn.monocur.sssjmscreenplayfileparser.screenplay;

import cn.monocur.sssjmscreenplayfileparser.GettingScreenplayScriptFileParserBundle;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class GettingScreenplayScriptColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Command Name", GettingScreenplayScriptSyntaxHighlighter.COMMAND_NAME),
            new AttributesDescriptor("Separators", GettingScreenplayScriptSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Strings", GettingScreenplayScriptSyntaxHighlighter.STRING),
            new AttributesDescriptor("Numbers", GettingScreenplayScriptSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Alphabet", GettingScreenplayScriptSyntaxHighlighter.ALPHABET),
            new AttributesDescriptor("Words", GettingScreenplayScriptSyntaxHighlighter.WORD),
            new AttributesDescriptor("Config", GettingScreenplayScriptSyntaxHighlighter.CONFIG),
            new AttributesDescriptor("Options", GettingScreenplayScriptSyntaxHighlighter.OPTION),
            new AttributesDescriptor("Variables", GettingScreenplayScriptSyntaxHighlighter.VARIABLE),
            new AttributesDescriptor("Comments", GettingScreenplayScriptSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Common Values", GettingScreenplayScriptSyntaxHighlighter.COMMON_VALUE)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return GettingScreenplayScriptIcons.ScriptFile;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new GettingScreenplayScriptSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                // 其中command和subParamKey需要限定为英文字符串
                // Param一类需填入content
                command:mainParam;
                command:mainParam -subParamKey1=subParamValue1;
                command:mainParam -subParamKey1=subParamValue1 -subParamKey2=subParamValue2 -subParamKey3=subParamValue3;
                command : "mainParam";
                command : 我说，什么意思！！！;
                command : "Hello Gays";
                command : Hi Gays;
                command : 我日你{$Test};
                command : 啊对对对{$Test1} -subParamKey={$Test2} -subParamKey={};
                command:mainParam -option=[->optionTarget]optionContent;
                command : mainParam
                    -option = [->optionTarget]optionContent;
                command :
                    {
                        "testField1":"testValue",
                        "testField1":2
                    };
                command : mainParam;
                command : mainParam -subParamKey1 = subParamValue1;
                command : mainParam -subParamKey1 = subParamValue1 -subParamKey2 = subParamValue2 -subParamKey3 = subParamValue3;
                command:mainParam -subParamKey1;
                command : mainParam -subParamKey1;
                command:mainParam -subParamKey1 -subParamKey2=subParamValue2;
                command : mainParam -subParamKey1 -subParamKey2 = subParamValue2;
                command : mainParam
                    -subParamKey1 = subParamValue1
                    -subParamKey2 = subParamValue2
                    -subParamKey3 = subParamValue3;
                command : mainParam
                    -subParamKey1
                    -subParamKey2 = subParamValue2;
                """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return GettingScreenplayScriptFileParserBundle.message("filetype.displayname");
    }
}