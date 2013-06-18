package org.livescriptidea.highlighter;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.livescriptidea.LiveScriptIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Map;

/**
 * LiveScript color settings page
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] ATTRS = new AttributesDescriptor[]{
          new AttributesDescriptor("Bad character", LiveScriptSyntaxHighlighter.BAD_CHARACTER),
          new AttributesDescriptor("Bang", LiveScriptSyntaxHighlighter.BANG),
          new AttributesDescriptor("Dot", LiveScriptSyntaxHighlighter.DOT),
          new AttributesDescriptor("Colon", LiveScriptSyntaxHighlighter.COLON),
          new AttributesDescriptor("Comma", LiveScriptSyntaxHighlighter.COMMA),
          new AttributesDescriptor("Semicolon", LiveScriptSyntaxHighlighter.SEMICOLON),
          new AttributesDescriptor("Parenthesis", LiveScriptSyntaxHighlighter.PARENTHESIS),
          new AttributesDescriptor("Brackets", LiveScriptSyntaxHighlighter.BRACKETS),
          new AttributesDescriptor("Braces", LiveScriptSyntaxHighlighter.BRACES),
          new AttributesDescriptor("Line comment", LiveScriptSyntaxHighlighter.LINE_COMMENT),
          new AttributesDescriptor("Block comment", LiveScriptSyntaxHighlighter.BLOCK_COMMENT),
          new AttributesDescriptor("Identifier", LiveScriptSyntaxHighlighter.IDENTIFIER),
          new AttributesDescriptor("Class", LiveScriptSyntaxHighlighter.CLASS_NAME),
          new AttributesDescriptor("Function name", LiveScriptSyntaxHighlighter.FUNCTION_NAME),
          new AttributesDescriptor("Function", LiveScriptSyntaxHighlighter.FUNCTION),
          new AttributesDescriptor("Function binding", LiveScriptSyntaxHighlighter.FUNCTION_BINDING),
          new AttributesDescriptor("Object key", LiveScriptSyntaxHighlighter.OBJECT_KEY),
          new AttributesDescriptor("Constant", LiveScriptSyntaxHighlighter.CONSTANT),
          new AttributesDescriptor("Number", LiveScriptSyntaxHighlighter.NUMBER),
          new AttributesDescriptor("Boolean", LiveScriptSyntaxHighlighter.BOOLEAN),
          new AttributesDescriptor("String literal", LiveScriptSyntaxHighlighter.STRING_LITERAL),
          new AttributesDescriptor("String", LiveScriptSyntaxHighlighter.STRING),
          new AttributesDescriptor("Expression substitution mark", LiveScriptSyntaxHighlighter.EXPRESSIONS_SUBSTITUTION_MARK),
          new AttributesDescriptor("Escape sequence", LiveScriptSyntaxHighlighter.ESCAPE_SEQUENCE),
          new AttributesDescriptor("This references", LiveScriptSyntaxHighlighter.THIS),
          new AttributesDescriptor("Prototype", LiveScriptSyntaxHighlighter.PROTOTYPE),
          new AttributesDescriptor("Operations", LiveScriptSyntaxHighlighter.OPERATIONS),
          new AttributesDescriptor("Existential operator", LiveScriptSyntaxHighlighter.EXISTENTIAL),
          new AttributesDescriptor("Keyword", LiveScriptSyntaxHighlighter.KEYWORD),
          new AttributesDescriptor("Range", LiveScriptSyntaxHighlighter.RANGE),
          new AttributesDescriptor("Splat", LiveScriptSyntaxHighlighter.SPLAT),
          new AttributesDescriptor("Regular expression id", LiveScriptSyntaxHighlighter.REGULAR_EXPRESSION_ID),
          new AttributesDescriptor("Regular expression content", LiveScriptSyntaxHighlighter.REGULAR_EXPRESSION_CONTENT),
          new AttributesDescriptor("Regular expression flag", LiveScriptSyntaxHighlighter.REGULAR_EXPRESSION_FLAG),
          new AttributesDescriptor("Heredoc id", LiveScriptSyntaxHighlighter.HEREDOC_ID),
          new AttributesDescriptor("Heredoc content", LiveScriptSyntaxHighlighter.HEREDOC_CONTENT),
          new AttributesDescriptor("Heregex id", LiveScriptSyntaxHighlighter.HEREGEX_ID),
          new AttributesDescriptor("Heregex content", LiveScriptSyntaxHighlighter.HEREGEX_CONTENT),
          new AttributesDescriptor("Javascript id", LiveScriptSyntaxHighlighter.JAVASCRIPT_ID),
          new AttributesDescriptor("Javascript content", LiveScriptSyntaxHighlighter.JAVASCRIPT_CONTENT),

          new AttributesDescriptor("Backcall", LiveScriptSyntaxHighlighter.BACKCALL),
          new AttributesDescriptor("Backcall binding", LiveScriptSyntaxHighlighter.BACKCALL_BINDING),
          new AttributesDescriptor("Pipe", LiveScriptSyntaxHighlighter.PIPE),

  };

  @NotNull
  public String getDisplayName() {
    return "LiveScript";
  }

  public Icon getIcon() {
    return LiveScriptIcons.FILE_TYPE;
  }

  @NotNull
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @NotNull
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  public LiveScriptSyntaxHighlighter getHighlighter() {
    return new LiveScriptSyntaxHighlighter();
  }

  @NotNull
  public String getDemoText() {
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

  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

}
