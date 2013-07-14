package org.livescriptidea.watcher;


import com.intellij.ide.macro.FileDirMacro;
import com.intellij.ide.macro.FileNameMacro;
import com.intellij.ide.macro.FileNameWithoutExtension;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.plugins.watcher.config.BackgroundTaskConsumer;
import com.intellij.plugins.watcher.model.TaskOptions;
import com.intellij.psi.PsiBundle;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.livescriptidea.file.LiveScriptFileType;
import org.livescriptidea.lang.psi.LiveScriptFile;

public class LiveScriptTaskConsumer extends BackgroundTaskConsumer {

    public boolean isAvailable(PsiFile file) {
        return file instanceof LiveScriptFile;
    }

    @NotNull
    public TaskOptions getOptionsTemplate() {

        TaskOptions options = new TaskOptions();
        options.setName(LiveScriptFileType.LIVE_SCRIPT_LANGUAGE.getDisplayName());
        options.setDescription("Compiles .ls files into .js files");
        options.setFileExtension("ls");
        options.setScopeName(PsiBundle.message("psi.search.scope.project", new Object[0]));

        options.setWorkingDir("$" + new FileDirMacro().getName() + "$");

        options.setArguments("--compile $" + new FileNameMacro().getName() + "$");
        options.setOutput("$" + new FileNameWithoutExtension().getName() + "$.js");

        return options;

    }

    public void configureWithLastOptions(TaskOptions options, @Nullable TaskOptions defaultOptions) {
        super.configureWithLastOptions(options, defaultOptions);
        if (defaultOptions != null) {
            options.setArguments(defaultOptions.getArguments());
            options.setOutput(defaultOptions.getOutput());
            options.setOutputFromStdout(defaultOptions.isOutputFromStdout());
        }
    }

    public void additionalConfiguration(@NotNull Project project, @NotNull TaskOptions options) {
        if (project == null)
            throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/watcher/LiveScriptTaskConsumer.additionalConfiguration must not be null");
        if (options == null)
            throw new IllegalArgumentException("Argument 1 for @NotNull parameter of org/livescriptidea/watcher/LiveScriptTaskConsumer.additionalConfiguration must not be null");
        super.additionalConfiguration(project, options);
        String programPath = findExecutableInPath(SystemInfo.isWindows ? "livescript.cmd" : "livescript");
        options.setProgram(programPath);
    }

}
