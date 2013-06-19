package org.livescriptidea.formatter;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class LiveScriptCodeStyleConfigurable extends CodeStyleAbstractConfigurable {

    public LiveScriptCodeStyleConfigurable(@NotNull CodeStyleSettings settings, CodeStyleSettings cloneSettings)
    {
        super(settings, cloneSettings, "LiveScript");
    }

    protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings)
    {
        return new LiveScriptCodeStyleMainPanel(getCurrentSettings(), settings);
    }

    public String getHelpTopic()
    {
        return "reference.settingsdialog.codestyle.livescript";
    }
}
