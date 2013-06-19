package org.livescriptidea.formatter;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.livescriptidea.file.LiveScriptFileType;

public class LiveScriptCodeStyleMainPanel extends TabbedLanguageCodeStylePanel{

    protected LiveScriptCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings)
    {
        super(LiveScriptFileType.LIVE_SCRIPT_LANGUAGE, currentSettings, settings);
    }

    protected void initTabs(CodeStyleSettings settings)
    {
        LanguageCodeStyleSettingsProvider provider = LanguageCodeStyleSettingsProvider.forLanguage(getDefaultLanguage());
        addIndentOptionsTab(settings);
        if ((provider != null) && (!provider.usesSharedPreview())) {
            addSpacesTab(settings);
            addWrappingAndBracesTab(settings);
            addBlankLinesTab(settings);
        }
    }
}
