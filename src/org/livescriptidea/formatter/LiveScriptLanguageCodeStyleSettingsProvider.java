package org.livescriptidea.formatter;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.livescriptidea.file.LiveScriptFileType;

public class LiveScriptLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    public static final String[] STANDARD_SPACING_OPTIONS = { "SPACE_AFTER_COMMA", "SPACE_AROUND_ADDITIVE_OPERATORS", "SPACE_AROUND_ASSIGNMENT_OPERATORS", "SPACE_AROUND_BITWISE_OPERATORS", "SPACE_AROUND_BITWISE_OPERATORS", "SPACE_AROUND_EQUALITY_OPERATORS", "SPACE_AROUND_LOGICAL_OPERATORS", "SPACE_AROUND_MULTIPLICATIVE_OPERATORS", "SPACE_AROUND_RELATIONAL_OPERATORS", "SPACE_BEFORE_COMMA", "SPACE_BEFORE_TYPE", "SPACE_AROUND_SHIFT_OPERATORS", "SPACE_AROUND_UNARY_OPERATOR", "SPACE_WITHIN_METHOD_CALL_PARENTHESES", "SPACE_WITHIN_METHOD_PARENTHESES", "SPACE_WITHIN_ARRAY_INITIALIZER_BRACES" };

    public static final String[] STANDARD_WRAPPING_OPTIONS = { "KEEP_LINE_BREAKS", "KEEP_FIRST_COLUMN_COMMENT", "KEEP_SIMPLE_METHODS_IN_ONE_LINE", "METHOD_PARAMETERS_WRAP", "METHOD_PARAMETERS_LPAREN_ON_NEXT_LINE", "METHOD_PARAMETERS_RPAREN_ON_NEXT_LINE", "ALIGN_MULTILINE_PARAMETERS", "CALL_PARAMETERS_WRAP", "CALL_PARAMETERS_LPAREN_ON_NEXT_LINE", "CALL_PARAMETERS_RPAREN_ON_NEXT_LINE", "ALIGN_MULTILINE_PARAMETERS_IN_CALLS", "ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION", "ARRAY_INITIALIZER_WRAP", "ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE", "ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE" };
    public static final String[] STANDARD_BLANK_LINES_OPTIONS = { "KEEP_BLANK_LINES_IN_CODE" };

    @NotNull
    public Language getLanguage()
    {
        return LiveScriptFileType.LIVE_SCRIPT_LANGUAGE;
    }

    public String getCodeSample(@NotNull LanguageCodeStyleSettingsProvider.SettingsType settingsType)
    {
        return "/*\n" +
                "Some tests\n" +
                "*/\n" +
                "class Animal\n" +
                "  (@name) -> \n" +
                "  move: (meters) -> alert @name + \" moved \" + meters + \"m.\"\n" +
                "  get-name: -> @name\n"+
                "\n" +
                "class Snake extends Animal\n" +
                "  move: -> \n" +
                "    alert \'Slithering...\'\n" +
                "    super 5\n" +
                "\n" +
                "number   = 42; opposite = true\n" +
                "\n" +
                "/^a\\/\\\\[a-Z/\\n]\\u00A3b$/.test 'a//b'\n" +
                "\n" +
                "square = (x) -> x * x\n" +
                "\n" +
                "[1 to 3] |> map |> sort\n" +
                "\n" +
                "e <- $ \\#element onclick\n" +
                "doSomething e\n" +
                "\n" +
                "list = [1 til 5]\n" +
                "\n" +
                "alert list.toString!\n" +
                "\n" +
                "race = (winner, ...runners) ->\n" +
                "  print winner, runners\n" +
                "\n" +
                "alert \"I knew it!\" if elvis?\n" +
                "\n" +
                "cubes = math.cube num for num in list\n" +
                "\n" +
                "text = \"\"\"\n" +
                " Result \n" +
                "    is #{ @number }\"\"\"\n" +
                "\n" +
                "html = ''' " +
                "  <body></body>" +
                "'''\n" +
                "let me = 0 # let is reserved\n" +
                "\n" +
                "String::dasherize = ->\n" +
                "  this.replace /_/g, \"-\"" +
                "\n" +
                "SINGERS = {Jagger: \"Rock\", Elvis: \"Roll\"}\n" +
                "\n" +
                "t = //\n" +
                "#{ something }[a-z]\n" +
                "//igm\n" +
                "\n" +
                "$('.shopping_cart').bind 'click', (event) ~>\n" +
                "    @customer.purchase @cart\n" +
                "\n" +
                "hi = ``function() {\n" +
                "  return [document.title, \"Hello JavaScript\"].join(\": \");\n" +
                "}``";
    }

    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull LanguageCodeStyleSettingsProvider.SettingsType settingsType)
    {
        if (consumer == null) throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/formatter/LiveScriptLanguageCodeStyleSettingsProvider.customizeSettings must not be null");
        if (settingsType == null) throw new IllegalArgumentException("Argument 1 for @NotNull parameter of org/livescriptidea/formatter/LiveScriptLanguageCodeStyleSettingsProvider.customizeSettings must not be null");

        if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions(STANDARD_SPACING_OPTIONS);
            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "SPACE_BEFORE_PROPERTY_COLON", "Before property name-value separator ':'", CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]);

            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "SPACE_AFTER_PROPERTY_COLON", "After property name-value separator ':'", CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]);

            consumer.renameStandardOption("SPACE_WITHIN_ARRAY_INITIALIZER_BRACES", "Array initializer brackets");
            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "SPACE_WITHIN_INDEX_BRACKETS", "Array index brackets", CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]);

            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "SPACE_WITHIN_RANGE_BRACKETS", "Range brackets", CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]);

            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "SPACE_WITHIN_OBJECT_BRACES", "Object braces", CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]);

        } else if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.WRAPPING_AND_BRACES_SETTINGS) {

            consumer.showStandardOptions(STANDARD_WRAPPING_OPTIONS);
            consumer.renameStandardOption("ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE", "New line after '['");
            consumer.renameStandardOption("ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE", "Place ']' on new line");
            consumer.renameStandardOption("METHOD_PARAMETERS_WRAP", "Function declaration parameters");
            consumer.renameStandardOption("CALL_PARAMETERS_WRAP", "Function call arguments inside parenthesis");

            consumer.showCustomOption(LiveScriptCodeStyleSettings.class, "ALIGN_FUNCTION_BODY", "Align function body", CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]);

        } else if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions(STANDARD_BLANK_LINES_OPTIONS);
        }
    }

    public CommonCodeStyleSettings getDefaultCommonSettings()
    {
        CommonCodeStyleSettings commonCodeStyleSettings = new CommonCodeStyleSettings(getLanguage());
        CommonCodeStyleSettings.IndentOptions indentOptions = commonCodeStyleSettings.initIndentOptions();
        indentOptions.INDENT_SIZE = 2;
        indentOptions.TAB_SIZE = 2;
        indentOptions.CONTINUATION_INDENT_SIZE = 2;

        commonCodeStyleSettings.ARRAY_INITIALIZER_WRAP = 1;
        commonCodeStyleSettings.ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION = true;

        commonCodeStyleSettings.CATCH_ON_NEW_LINE = true;
        commonCodeStyleSettings.FINALLY_ON_NEW_LINE = true;
        commonCodeStyleSettings.ELSE_ON_NEW_LINE = true;

        commonCodeStyleSettings.CALL_PARAMETERS_WRAP = 1;
        commonCodeStyleSettings.ALIGN_MULTILINE_PARAMETERS_IN_CALLS = false;
        commonCodeStyleSettings.CALL_PARAMETERS_LPAREN_ON_NEXT_LINE = false;
        commonCodeStyleSettings.CALL_PARAMETERS_RPAREN_ON_NEXT_LINE = false;
        return commonCodeStyleSettings;
    }

    public IndentOptionsEditor getIndentOptionsEditor()
    {
        return new SmartIndentOptionsEditor();
    }
}
