package cn.monocur.sssjmscreenplayfileparser;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

public class GettingScreenplayScriptFileParserBundle extends DynamicBundle {
    @NonNls
    private static final String BUNDLE = "messages.SPFileParserBundle";
    private static final GettingScreenplayScriptFileParserBundle INSTANCE = new GettingScreenplayScriptFileParserBundle();

    private GettingScreenplayScriptFileParserBundle() {
        super(BUNDLE);
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }

    @SuppressWarnings("unused")
    public static java.util.function.Supplier<String> messagePointer(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return INSTANCE.getLazyMessage(key, params);
    }
}