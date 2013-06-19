package org.livescriptidea.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class LiveScriptCodeStyleSettings extends CustomCodeStyleSettings {

    public boolean SPACE_BEFORE_PROPERTY_COLON = false;
    public boolean SPACE_AFTER_PROPERTY_COLON = true;
    public boolean ALIGN_FUNCTION_BODY = false;
    public boolean SPACE_WITHIN_INDEX_BRACKETS = false;
    public boolean SPACE_WITHIN_RANGE_BRACKETS = false;
    public boolean SPACE_WITHIN_OBJECT_BRACES = false;

    protected LiveScriptCodeStyleSettings(CodeStyleSettings container) {
        super("LiveScriptCodeStyleSettings", container);
    }

}
