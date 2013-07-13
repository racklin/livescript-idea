package org.livescriptidea.compiler;

import com.google.common.io.CharStreams;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class LiveScriptCompiler {

    private static final Logger LOG = Logger.getInstance(LiveScriptCompiler.class.getName());

    @Nullable
    private static String readFileFromJar(@NotNull String resourceName) {
        if (resourceName == null)
            throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/compiler/LiveScriptCompiler.readFileFromJar must not be null");
        ClassLoader classLoader = LiveScriptCompiler.class.getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(resourceName);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            try {
                return CharStreams.toString(reader);
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static String compile(@NotNull String textToCompile) throws IOException {
        if (textToCompile == null)
            throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/compiler/LiveScriptCompiler.compile must not be null");
        String liveScriptJS = readFileFromJar("livescript.js");
        Context context = Context.enter();
        try {
            context.setOptimizationLevel(-1);
            ScriptableObject scope = context.initStandardObjects();
            context.evaluateString(scope, liveScriptJS, "original LiveScript compiler", 0, null);

            scope.put("liveScriptSource", scope, textToCompile);
            return (String) context.evaluateString(scope, "LiveScript.compile(liveScriptSource);", "LiveScript compiler for JetBrains", 0, null);
        } finally {
            Context.exit();
        }
    }

}
