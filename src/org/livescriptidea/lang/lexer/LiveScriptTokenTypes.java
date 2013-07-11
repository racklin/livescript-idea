package org.livescriptidea.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

/**
 * Define the LiveScript tokens used within the LiveScriptLexer
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public interface LiveScriptTokenTypes {

  public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
  public static final IElementType ERROR_ELEMENT = TokenType.ERROR_ELEMENT;

  public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

  public static final IElementType TERMINATOR = new LiveScriptElementType("TERMINATOR");

  public static final IElementType BANG = new LiveScriptElementType("BANG");
  public static final IElementType DOT = new LiveScriptElementType("DOT");
  public static final IElementType COMMA = new LiveScriptElementType("COMMA");
  public static final IElementType COLON = new LiveScriptElementType("COLON");
  public static final IElementType SEMICOLON = new LiveScriptElementType("SEMICOLON");

  public static final IElementType IDENTIFIER = new LiveScriptElementType("IDENTIFIER");
  public static final IElementType CLASS_NAME = new LiveScriptElementType("CLASS_NAME");
  public static final IElementType CONSTANT = new LiveScriptElementType("CONSTANT");
  public static final IElementType FUNCTION_NAME = new LiveScriptElementType("FUNCTION_NAME");
  public static final IElementType OBJECT_KEY = new LiveScriptElementType("OBJECT_KEY");

  public static final IElementType NUMBER = new LiveScriptElementType("NUMBER");
  public static final IElementType BOOL = new LiveScriptElementType("BOOL");

  public static final IElementType ESCAPE_SEQUENCE = new LiveScriptElementType("ESCAPE_SEQUENCE");

  public static final IElementType STRING_LITERAL = new LiveScriptElementType("STRING_LITERAL");
  public static final IElementType STRING = new LiveScriptElementType("STRING");

  public static final IElementType HEREDOC_START = new LiveScriptElementType("HEREDOC_START");
  public static final IElementType HEREDOC = new LiveScriptElementType("HEREDOC");
  public static final IElementType HEREDOC_END = new LiveScriptElementType("HEREDOC_END");

  public static final IElementType REGEX_START = new LiveScriptElementType("REGEX_START");
  public static final IElementType REGEX = new LiveScriptElementType("REGEX");
  public static final IElementType REGEX_BRACKET_START = new LiveScriptElementType("REGEX_BRACKET_START");
  public static final IElementType REGEX_BRACKET_END = new LiveScriptElementType("REGEX_BRACKET_END");
  public static final IElementType REGEX_PARENTHESIS_START = new LiveScriptElementType("REGEX_PARENTHESIS_START");
  public static final IElementType REGEX_PARENTHESIS_END = new LiveScriptElementType("REGEX_PARENTHESIS_END");
  public static final IElementType REGEX_BRACE_START = new LiveScriptElementType("REGEX_BRACE_START");
  public static final IElementType REGEX_BRACE_END = new LiveScriptElementType("REGEX_BRACE_END");
  public static final IElementType REGEX_END = new LiveScriptElementType("REGEX_END");
  public static final IElementType REGEX_FLAG = new LiveScriptElementType("REGEX_FLAG");

  public static final IElementType HEREGEX_START = new LiveScriptElementType("HEREGEX_START");
  public static final IElementType HEREGEX = new LiveScriptElementType("HEREGEX");
  public static final IElementType HEREGEX_END = new LiveScriptElementType("HEREGEX_END");

  public static final IElementType INTERPOLATION_START = new LiveScriptElementType("INTERPOLATION_START");
  public static final IElementType INTERPOLATION_END = new LiveScriptElementType("INTERPOLATION_END");

  public static final IElementType JAVASCRIPT_LITERAL = new LiveScriptElementType("JAVASCRIPT_LITERAL");
  public static final IElementType JAVASCRIPT = new LiveScriptElementType("JAVASCRIPT");

  public static final IElementType LINE_COMMENT = new LiveScriptElementType("COMMENT");
  public static final IElementType BLOCK_COMMENT = new LiveScriptElementType("BLOCK_COMMENT");

  public static final IElementType PARENTHESIS_START = new LiveScriptElementType("PARENTHESIS_START");
  public static final IElementType PARENTHESIS_END = new LiveScriptElementType("PARENTHESIS_END");

  public static final IElementType BRACKET_START = new LiveScriptElementType("BRACKET_START");
  public static final IElementType BRACKET_END = new LiveScriptElementType("BRACKET_END");

  public static final IElementType BRACE_START = new LiveScriptElementType("BRACE_START");
  public static final IElementType BRACE_END = new LiveScriptElementType("BRACE_END");

  public static final IElementType EQUAL = new LiveScriptElementType("EQUAL");
  public static final IElementType COMPOUND_ASSIGN = new LiveScriptElementType("COMPOUND_ASSIGN");
  public static final IElementType COMPARE = new LiveScriptElementType("COMPARE");
  public static final IElementType LOGIC = new LiveScriptElementType("LOGIC");
  public static final IElementType RANGE = new LiveScriptElementType("RANGE");
  public static final IElementType SPLAT = new LiveScriptElementType("SPLAT");
  public static final IElementType THIS = new LiveScriptElementType("THIS");
  public static final IElementType SUPER = new LiveScriptElementType("SUPER");
  public static final IElementType PROTOTYPE = new LiveScriptElementType("PROTOTYPE");
  public static final IElementType FUNCTION = new LiveScriptElementType("FUNCTION");
  public static final IElementType FUNCTION_BIND = new LiveScriptElementType("FUNCTION_BIND");
  public static final IElementType EXIST = new LiveScriptElementType("EXIST");
  public static final IElementType PLUS = new LiveScriptElementType("PLUS");
  public static final IElementType MINUS = new LiveScriptElementType("MINUS");
  public static final IElementType MATH = new LiveScriptElementType("MATH");

  public static final IElementType UNARY = new LiveScriptElementType("UNARY");
  public static final IElementType CLASS = new LiveScriptElementType("CLASS");
  public static final IElementType EXTENDS = new LiveScriptElementType("EXTENDS");
  public static final IElementType IF = new LiveScriptElementType("IF");
  public static final IElementType ELSE = new LiveScriptElementType("ELSE");
  public static final IElementType THEN = new LiveScriptElementType("THEN");
  public static final IElementType UNLESS = new LiveScriptElementType("UNLESS");
  public static final IElementType FOR = new LiveScriptElementType("FOR");
  public static final IElementType IN = new LiveScriptElementType("IN");
  public static final IElementType OF = new LiveScriptElementType("OF");
  public static final IElementType BY = new LiveScriptElementType("BY");
  public static final IElementType WHILE = new LiveScriptElementType("WHILE");
  public static final IElementType UNTIL = new LiveScriptElementType("UNTIL");
  public static final IElementType SWITCH = new LiveScriptElementType("SWITCH");
  public static final IElementType WHEN = new LiveScriptElementType("WHEN");
  public static final IElementType TRY = new LiveScriptElementType("TRY");
  public static final IElementType CATCH = new LiveScriptElementType("CATCH");
  public static final IElementType THROW = new LiveScriptElementType("THROW");
  public static final IElementType FINALLY = new LiveScriptElementType("FINALLY");
  public static final IElementType BREAK = new LiveScriptElementType("BREAK");
  public static final IElementType CONTINUE = new LiveScriptElementType("CONTINUE");
  public static final IElementType RETURN = new LiveScriptElementType("RETURN");
  public static final IElementType INSTANCE_OF = new LiveScriptElementType("INSTANCE_OF");

  public static final IElementType IMPORT = new LiveScriptElementType("IMPORT");
  public static final IElementType IMPORT_ALL = new LiveScriptElementType("IMPORT_ALL");

  public static final IElementType BACKCALL = new LiveScriptElementType("BACKCALL");
  public static final IElementType BACKCALL_BIND = new LiveScriptElementType("BACKCALL_BIND");
  public static final IElementType PIPE = new LiveScriptElementType("PIPE");

  public static final IElementType KEYWORDS = new LiveScriptElementType("KEYWORDS");


}
