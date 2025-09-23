package cn.monocur.sssjmscreenplayfileparser.screenplay;

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
        return "#screenplayConfig#\n" +
                "command:mainParam;\n" +
                "command:123;\n" +
                "command:\"string\";\n" +
                "command:汉字;\n" +
                "command:value -key=value;\n" +
                "command:value -option=[->target]content;\n" +
                "${variable}\n" +
                "// 这是一条注释";
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
        return "Getting Screenplay Script";
    }
}