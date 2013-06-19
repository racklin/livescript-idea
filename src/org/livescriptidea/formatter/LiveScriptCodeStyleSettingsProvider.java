package org.livescriptidea.formatter;

import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class LiveScriptCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings)
    {
        return new LiveScriptCodeStyleSettings(settings);
    }

    @NotNull
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings)
    {
        Configurable configurable = new LiveScriptCodeStyleConfigurable(settings, originalSettings);
        if (configurable == null) throw new IllegalStateException("@NotNull method org/livescriptidea/formatter/LiveScriptCodeStyleSettingsProvider.createSettingsPage must not return null");
        return configurable;
    }

    public String getConfigurableDisplayName()
    {
        return "LiveScript";
    }
}
