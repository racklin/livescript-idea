package org.livescriptidea.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.livescriptidea.lang.lexer.LiveScriptFlexLexer;
import org.livescriptidea.lang.lexer.LiveScriptTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * LiveScript syntax highlighter
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptSyntaxHighlighter extends SyntaxHighlighterBase {

  private static final Map<IElementType, TextAttributesKey> TOKENS_TO_STYLES;

  @NotNull
  public Lexer getHighlightingLexer() {
    return new LiveScriptFlexLexer();
  }

  static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BAD_CHARACTER",
          HighlighterColors.BAD_CHARACTER.getDefaultAttributes()
  );

  static final TextAttributesKey SEMICOLON = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.SEMICOLON",
          SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
  );

  static final TextAttributesKey COMMA = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.COMMA",
          SyntaxHighlighterColors.COMMA.getDefaultAttributes()
  );

  static final TextAttributesKey BANG = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BANG",
          SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
  );

  static final TextAttributesKey DOT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.DOT",
          SyntaxHighlighterColors.DOT.getDefaultAttributes()
  );

  static final TextAttributesKey CLASS_NAME = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.CLASS_NAME",
          HighlighterColors.TEXT.getDefaultAttributes()
  );

  static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.IDENTIFIER",
          HighlighterColors.TEXT.getDefaultAttributes()
  );

  static final TextAttributesKey CONSTANT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.CONSTANT",
          HighlighterColors.TEXT.getDefaultAttributes()
  );

  static final TextAttributesKey FUNCTION_NAME = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.FUNCTION_NAME",
          HighlighterColors.TEXT.getDefaultAttributes()
  );

  static final TextAttributesKey OBJECT_KEY = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.OBJECT_KEY",
          HighlighterColors.TEXT.getDefaultAttributes()
  );

  static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.NUMBER",
          SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
  );

  static final TextAttributesKey BOOLEAN = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BOOLEAN",
          SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
  );

  static final TextAttributesKey STRING_LITERAL = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.STRING_LITERAL",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.STRING",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey HEREDOC_ID = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.HEREDOC_ID",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey HEREDOC_CONTENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.HEREDOC_CONTENT",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey HEREGEX_ID = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.HEREGEX_ID",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey HEREGEX_CONTENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.HEREGEX_CONTENT",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey JAVASCRIPT_ID = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.JAVASCRIPT_ID",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey JAVASCRIPT_CONTENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.JAVASCRIPT_CONTENT",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey EXPRESSIONS_SUBSTITUTION_MARK = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.EXPRESSIONS_SUBSTITUTION_MARK",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.LINE_COMMENT",
          SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
  );

  static final TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BLOCK_COMMENT",
          SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes()
  );

  static final TextAttributesKey PARENTHESIS = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.PARENTHESIS",
          SyntaxHighlighterColors.PARENTHS.getDefaultAttributes()
  );

  static final TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BRACKET",
          SyntaxHighlighterColors.BRACKETS.getDefaultAttributes()
  );

  static final TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.BRACE",
          SyntaxHighlighterColors.BRACES.getDefaultAttributes()
  );

  static final TextAttributesKey OPERATIONS = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.OPERATIONS",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey EXISTENTIAL = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.EXISTENTIAL",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.KEYWORD",
          SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
  );

  static final TextAttributesKey RANGE = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.RANGE",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey SPLAT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.SPLAT",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey THIS = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.THIS",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey COLON = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.COLON",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey PROTOTYPE = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.PROTOTYPE",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.FUNCTION",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey FUNCTION_BINDING = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.FUNCTION_BINDING",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey REGULAR_EXPRESSION_ID = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.REGULAR_EXPRESSION_ID",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey REGULAR_EXPRESSION_CONTENT = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.REGULAR_EXPRESSION_CONTENT",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey REGULAR_EXPRESSION_FLAG = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.REGULAR_EXPRESSION_FLAG",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  static final TextAttributesKey ESCAPE_SEQUENCE = TextAttributesKey.createTextAttributesKey(
          "LIVESCRIPT.ESCAPE_SEQUENCE",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );


    static final TextAttributesKey BACKCALL = TextAttributesKey.createTextAttributesKey(
            "LIVESCRIPT.BACKCALL",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

    static final TextAttributesKey BACKCALL_BINDING = TextAttributesKey.createTextAttributesKey(
            "LIVESCRIPT.BACKCALL_BINDING",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

    static final TextAttributesKey PIPE = TextAttributesKey.createTextAttributesKey(
            "LIVESCRIPT.PIPE",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

  static {
    TOKENS_TO_STYLES = new HashMap<IElementType, TextAttributesKey>();
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BAD_CHARACTER, BAD_CHARACTER);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.ERROR_ELEMENT, BAD_CHARACTER);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.WHITE_SPACE, HighlighterColors.TEXT);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.TERMINATOR, HighlighterColors.TEXT);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BANG, BANG);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.DOT, DOT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.COMMA, COMMA);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.COLON, COLON);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.SEMICOLON, SEMICOLON);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.IDENTIFIER, IDENTIFIER);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.CLASS_NAME, CLASS_NAME);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.CONSTANT, CONSTANT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.FUNCTION_NAME, FUNCTION_NAME);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.OBJECT_KEY, OBJECT_KEY);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.NUMBER, NUMBER);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BOOL, BOOLEAN);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.ESCAPE_SEQUENCE, ESCAPE_SEQUENCE);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.STRING_LITERAL, STRING_LITERAL);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.STRING, STRING);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREDOC_START, HEREDOC_ID);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREDOC, HEREDOC_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREDOC_END, HEREDOC_ID);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_START, REGULAR_EXPRESSION_ID);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_BRACKET_START, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_BRACKET_END, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_PARENTHESIS_START, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_PARENTHESIS_END, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_BRACE_START, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_BRACE_END, REGULAR_EXPRESSION_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_END, REGULAR_EXPRESSION_ID);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.REGEX_FLAG, REGULAR_EXPRESSION_FLAG);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREGEX_START, HEREGEX_ID);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREGEX, HEREGEX_CONTENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.HEREGEX_END, HEREGEX_ID);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.INTERPOLATION_START, EXPRESSIONS_SUBSTITUTION_MARK);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.INTERPOLATION_END, EXPRESSIONS_SUBSTITUTION_MARK);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.JAVASCRIPT_LITERAL, JAVASCRIPT_ID);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.JAVASCRIPT, JAVASCRIPT_CONTENT);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.LINE_COMMENT, LINE_COMMENT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BLOCK_COMMENT, BLOCK_COMMENT);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.PARENTHESIS_START, PARENTHESIS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.PARENTHESIS_END, PARENTHESIS);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BRACKET_START, BRACKETS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BRACKET_END, BRACKETS);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BRACE_START, BRACES);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BRACE_END, BRACES);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.EQUAL, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.COMPOUND_ASSIGN, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.COMPARE, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.LOGIC, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.PLUS, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.MINUS, OPERATIONS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.MATH, OPERATIONS);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.EXIST, EXISTENTIAL);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.RANGE, RANGE);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.SPLAT, SPLAT);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.THIS, THIS);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.PROTOTYPE, PROTOTYPE);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.FUNCTION, FUNCTION);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.FUNCTION_BIND, FUNCTION_BINDING);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.UNARY, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.CLASS, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.EXTENDS, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.IF, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.ELSE, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.THEN, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.UNLESS, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.FOR, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.IN, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.OF, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BY, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.WHILE, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.UNTIL, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.SWITCH, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.WHEN, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.TRY, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.CATCH, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.THROW, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.FINALLY, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BREAK, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.CONTINUE, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.RETURN, KEYWORD);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.INSTANCE_OF, KEYWORD);

    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BACKCALL, BACKCALL);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.BACKCALL_BIND, BACKCALL_BINDING);
    TOKENS_TO_STYLES.put(LiveScriptTokenTypes.PIPE, PIPE);

  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    if (!TOKENS_TO_STYLES.containsKey(tokenType)) {
      throw new UnsupportedOperationException(tokenType.toString());
    }
    return pack(TOKENS_TO_STYLES.get(tokenType));
  }

}
