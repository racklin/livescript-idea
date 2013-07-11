package org.livescriptidea.lang.lexer;


import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.livescriptidea.lang.lexer.LiveScriptTokenTypes;
import java.util.Stack;

/**
 * The LiveScript lexer is responsible for generating a token stream of any LiveScript source file.
 *
 * @author Rack Lin
 * @since 0.1.0
 */
%%

/* %debug */

%unicode

%public
%class LiveScriptLexer
%implements FlexLexer
%type IElementType

%function advance

%{

  private IElementType characterClassType;

  private final Stack<Integer> stack = new Stack<Integer>();

  /**
   * Push the actual state on top of the stack
   */
  private void pushState() {
    stack.push(yystate());
  }

  /**
   * Push the actual state on top of the stack
   * and change into another state
   *
   * @param state The new state
   */
  private void pushStateAndBegin(int state) {
    stack.push(yystate());
    yybegin(state);
  }

  /**
   * Pop the last state from the stack and change to it.
   * If the stack is empty, go to YYINITIAL
   */
  private void popState() {
    if (!stack.empty()) {
      yybegin(stack.pop());
    } else {
      yybegin(YYINITIAL);
    }
  }

  /**
   * Push the stream back to the position before the text match
   *
   * @param text The text to match
   * @return true when matched
   */
  private boolean pushBackTo(String text) {
    final int position = yytext().toString().indexOf(text);

    if (position != -1) {
      yypushback(yylength() - position);
      return true;
    }

    return false;
  }

  /**
   * Push the stream back to the position before the text match
   * and change into the given state
   *
   * @param text The text to match
   * @param state The new state
   * @return true when matched
   */
  private boolean pushBackAndState(String text, int state) {
    final boolean success = pushBackTo(text);

    if (success) {
      pushStateAndBegin(state);
    }

    return success;
  }

%}

TERMINATOR      = [\n\r]|\\\n
WHITE_SPACE     = [ \t]+

IDENTIFIER      = [$_a-z][$_a-zA-Z0-9]*
CLASS_NAME      = [A-Z][$_a-zA-Z0-9]*
CONSTANT        = [A-Z][$_A-Z0-9]*
NUMBER          = (0(x|X)[0-9a-fA-F]+)|(-?[0-9]+(\.[0-9]+)?(e[+\-]?[0-9]+)?)
FUNCTION        = [_a-zA-Z]([$\-_a-zA-Z0-9])*?[:]([^\n\r])*?(->|~>|-->|~~>)
OBJECT_KEY      = [_a-zA-Z]([$\-_a-zA-Z0-9])*[:][^:]

RESERVED        = with|enum|native|__hasProp|__extends|__slice|__bind|__indexOf
LOGIC           = and|&&|or|\|\||&|\||\^|\?|xor
COMPARE         = ==|\!=|<|>|<=|>=|is|isnt|not
COMPOUND_ASSIGN = -=|\+=|\/=|\*=|%=|\|\|=|&&=|\?=|<<=|>>=|>>>=|&=|\^=|\|=|or=
BOOL            = true|yes|on|false|no|off|undefined|null
UNARY           = do|new|typeof|typeof\!|delete|\~|\!|not
QUOTE           = this|class|extends|try|catch|finally|throw|if|then|else|unless|for|in|of|by|while|until|switch|when|break|continue|return|instanceof|true|yes|on|false|no|off|undefined|null|do|new|typeof|delete|not|and|or|case|default|from|xor|match|own|otherwise|function|var|let|super
IMPORT          = <<<|import
IMPORT_ALL      = <<<<
KEYWORDS        = let|export|fallthrough|from|otherwise|var


%state YYIDENTIFIER, YYNUMBER, YYJAVASCRIPT, YYJAVASCRIPT_CALL
%state YYDOUBLEQUOTESTRING, YYSINGLEQUOTESTRING, YYBACKSLASHQUOTESTRING
%state YYDOUBLEQUOTEHEREDOC, YYSINGLEQUOTEHEREDOC
%state YYREGEX, YYHEREGEX, YYREGEXFLAG, YYREGEXCHARACTERCLASS
%state YYINTERPOLATION, YYQUOTEPROPERTY, YYCLASSNAME
%state YYBLOCKCOMMENT

%%

/*************************************************************************************************/
/* The initial state recognizes keywords, most operators and characters that start another state */
/*************************************************************************************************/

<YYINITIAL> {
  {RESERVED}                  { return LiveScriptTokenTypes.ERROR_ELEMENT; }
  {QUOTE}:                    { yypushback(1);
                                return LiveScriptTokenTypes.IDENTIFIER; }

  "@"                         { return LiveScriptTokenTypes.THIS; }
  "this"                      { return LiveScriptTokenTypes.THIS; }
  "super"                     { return LiveScriptTokenTypes.SUPER; }

  "class"                     { return LiveScriptTokenTypes.CLASS; }
  "extends"                   { return LiveScriptTokenTypes.EXTENDS; }
  "try"                       { return LiveScriptTokenTypes.TRY; }
  "catch"                     { return LiveScriptTokenTypes.CATCH; }
  "finally"                   { return LiveScriptTokenTypes.FINALLY; }
  "throw"                     { return LiveScriptTokenTypes.THROW; }
  "if"                        { return LiveScriptTokenTypes.IF; }
  "then"                      { return LiveScriptTokenTypes.THEN; }
  "else"                      { return LiveScriptTokenTypes.ELSE; }
  "unless"                    { return LiveScriptTokenTypes.UNLESS; }
  "for"                       { return LiveScriptTokenTypes.FOR; }
  "in"                        { return LiveScriptTokenTypes.IN; }
  "of"                        { return LiveScriptTokenTypes.OF; }
  "by"                        { return LiveScriptTokenTypes.BY; }
  "while"                     { return LiveScriptTokenTypes.WHILE; }
  "until"                     { return LiveScriptTokenTypes.UNTIL; }
  "switch"                    { return LiveScriptTokenTypes.SWITCH; }
  "when"                      { return LiveScriptTokenTypes.WHEN; }
  "case"                      { return LiveScriptTokenTypes.WHEN; }
  "default"                   { return LiveScriptTokenTypes.ELSE; }
  "break"                     { return LiveScriptTokenTypes.BREAK; }
  "continue"                  { return LiveScriptTokenTypes.CONTINUE; }
  "return"                    { return LiveScriptTokenTypes.RETURN; }
  "instanceof"                { return LiveScriptTokenTypes.INSTANCE_OF; }

  {BOOL}                      { return LiveScriptTokenTypes.BOOL; }
  {LOGIC}                     { return LiveScriptTokenTypes.LOGIC; }
  {COMPARE}                   { return LiveScriptTokenTypes.COMPARE; }
  {COMPOUND_ASSIGN}           { return LiveScriptTokenTypes.COMPOUND_ASSIGN; }
  {UNARY}                     { return LiveScriptTokenTypes.UNARY; }

  \"                          { yybegin(YYDOUBLEQUOTESTRING);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  \'                          { yybegin(YYSINGLEQUOTESTRING);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  \\                          { yybegin(YYBACKSLASHQUOTESTRING);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  "\"\"\""                    { yybegin(YYDOUBLEQUOTEHEREDOC);
                                return LiveScriptTokenTypes.HEREDOC_START; }

  "'''"                       { yybegin(YYSINGLEQUOTEHEREDOC);
                                return LiveScriptTokenTypes.HEREDOC_START; }

  "``"                        { yybegin(YYJAVASCRIPT);
                                return LiveScriptTokenTypes.JAVASCRIPT_LITERAL; }

  "`"                         { yybegin(YYJAVASCRIPT_CALL);
                                return LiveScriptTokenTypes.JAVASCRIPT_LITERAL; }

  {IMPORT_ALL}                { return LiveScriptTokenTypes.IMPORT_ALL; }

  {IMPORT}                    { return LiveScriptTokenTypes.IMPORT; }

  {KEYWORDS}                  { return LiveScriptTokenTypes.KEYWORDS; }

  {IDENTIFIER}                { yybegin(YYIDENTIFIER);
                                return LiveScriptTokenTypes.IDENTIFIER; }

  {CONSTANT}                  { yybegin(YYIDENTIFIER);
                                return LiveScriptTokenTypes.CONSTANT; }

  {CLASS_NAME}                { yybegin(YYCLASSNAME);
                                return LiveScriptTokenTypes.CLASS_NAME; }

  {OBJECT_KEY}                { pushBackTo(":");
                                return LiveScriptTokenTypes.OBJECT_KEY; }

  {FUNCTION}                  { if (pushBackTo("::")) {
                                  yybegin(YYCLASSNAME);
                                  return LiveScriptTokenTypes.CLASS_NAME;
                                }
                                pushBackTo(":");
                                return LiveScriptTokenTypes.FUNCTION_NAME;
                              }

  {NUMBER}                    { yybegin(YYNUMBER);
                                return LiveScriptTokenTypes.NUMBER; }

  "-->"                        { return LiveScriptTokenTypes.FUNCTION; }
  "~~>"                        { return LiveScriptTokenTypes.FUNCTION_BIND; }

  "=>"                        { return LiveScriptTokenTypes.THEN; }

  "->"                        { return LiveScriptTokenTypes.FUNCTION; }
  "~>"                        { return LiveScriptTokenTypes.FUNCTION_BIND; }

  "<-"                        { return LiveScriptTokenTypes.BACKCALL; }
  "<~"                        { return LiveScriptTokenTypes.BACKCALL_BIND; }

  "|>"                        { return LiveScriptTokenTypes.PIPE; }
  "<|"                        { return LiveScriptTokenTypes.PIPE; }

  "="                         { return LiveScriptTokenTypes.EQUAL; }

  "["                         { return LiveScriptTokenTypes.BRACKET_START; }
  "]"                         { return LiveScriptTokenTypes.BRACKET_END; }

  "("                         { return LiveScriptTokenTypes.PARENTHESIS_START; }
  ")"                         { return LiveScriptTokenTypes.PARENTHESIS_END; }

  "<["                         { return LiveScriptTokenTypes.BRACKET_START; }
  "]>"                         { return LiveScriptTokenTypes.BRACKET_END; }

  "/*"                        { yybegin(YYBLOCKCOMMENT);
                                return LiveScriptTokenTypes.BLOCK_COMMENT; }

  /* Push the state because the braces are important for determining the interpolation */
  "{"                         { pushState();
                                return LiveScriptTokenTypes.BRACE_START; }

  "."                         { return LiveScriptTokenTypes.DOT; }
  ":"                         { return LiveScriptTokenTypes.COLON; }
  ";"                         { return LiveScriptTokenTypes.SEMICOLON; }
  ","                         { return LiveScriptTokenTypes.COMMA; }

  "+"                         { return LiveScriptTokenTypes.PLUS; }
  "-"                         { return LiveScriptTokenTypes.MINUS; }
  "*"                         { return LiveScriptTokenTypes.MATH; }
  "%"                         { return LiveScriptTokenTypes.MATH; }
  "/" / [ ]+                  { return LiveScriptTokenTypes.MATH; }

  "..."                       { return LiveScriptTokenTypes.SPLAT; }


  "//"                       { yybegin(YYHEREGEX);
                                return LiveScriptTokenTypes.HEREGEX_START; }

  "/" / [^/= ]                { yybegin(YYREGEX);
                                return LiveScriptTokenTypes.REGEX_START; }

  (##?)(.*)*[^\n\r]?          { return LiveScriptTokenTypes.LINE_COMMENT; }

  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }
  {WHITE_SPACE}               { return LiveScriptTokenTypes.WHITE_SPACE; }
}

/*********************************************************************************************************************/
/* A closing brace pops a state from the stack. If this state is YYINITIAL, then it is a normal BRACE_END, otherwise */
/* push it back to the steram an let the specific state recognize the special brace type. */
/*********************************************************************************************************************/

<YYINITIAL, YYIDENTIFIER, YYNUMBER> {
  "}"                         { popState();
                                if (yystate() == YYINITIAL) {
                                  return LiveScriptTokenTypes.BRACE_END;
                                } else {
                                  yypushback(1);
                                }
                              }
}

/*****************************************************************/
/* Characters than can follow an identifier or a number directly */
/*****************************************************************/

<YYIDENTIFIER, YYNUMBER> {
  "."                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.DOT; }

  ":"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.COLON; }

  ";"                         { return LiveScriptTokenTypes.SEMICOLON; }

  "::"                        { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PROTOTYPE; }

  ","                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.COMMA; }

  "["                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BRACKET_START; }

  "]"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BRACKET_END; }

  ")"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PARENTHESIS_END; }

  "+"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PLUS; }

  "-"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.MINUS; }

  "*"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.MATH; }

  "%"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.MATH; }

  "/"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.MATH; }

  {TERMINATOR}                { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.TERMINATOR; }

  {WHITE_SPACE}               { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.WHITE_SPACE; }
}

/**********************************************************************/
/* An identifier has some more characters that can follow it directly */
/**********************************************************************/

<YYIDENTIFIER> {
  \.{QUOTE} / [^a-zA-Z0-9]    { yybegin(YYQUOTEPROPERTY);
                                yypushback(yylength()); }

  \\                          { yybegin(YYBACKSLASHQUOTESTRING);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  "!"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BANG; }

  "?"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.EXIST; }

  "..."                       { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.SPLAT; }

  "("                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PARENTHESIS_START; }
}

/*****************/
/* A class name  */
/*****************/

<YYCLASSNAME> {
  \.{QUOTE} / [^a-zA-Z0-9]    { yybegin(YYQUOTEPROPERTY);
                                yypushback(yylength()); }

  "."                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.DOT; }

  ","                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.COMMA; }

  "::"                        { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PROTOTYPE; }

  "("                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PARENTHESIS_START; }

  ")"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.PARENTHESIS_END; }

  {TERMINATOR}                { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.TERMINATOR; }

  {WHITE_SPACE}               { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.WHITE_SPACE; }
}

/*******************************************************************************/
/* Certain reserved words an keywords are allowed as property of an identifier */
/*******************************************************************************/

<YYQUOTEPROPERTY> {
  "."                         { return LiveScriptTokenTypes.DOT; }

  {QUOTE}                     { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.IDENTIFIER; }
}


/*****************************************************************/
/* A number has some more characters that can follow it directly */
/*****************************************************************/

<YYNUMBER> {
  [ |\t]+to[ \t]+                        { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.RANGE; }

  [ |\t]+til[ \t]+                       { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.RANGE; }

  [ |\t]+by[ \t]+                        { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.RANGE; }
}

/********************/
/* Escape sequences */
/********************/

<YYSINGLEQUOTESTRING, YYDOUBLEQUOTESTRING, YYSINGLEQUOTEHEREDOC, YYDOUBLEQUOTEHEREDOC, YYREGEX, YYHEREGEX, YYREGEXCHARACTERCLASS> {
  [\\][^\n\r]                 |
  [\\][0-8]{1,3}              |
  [\\]x[0-9a-fA-F]{1,2}       |
  [\\]u[0-9a-fA-F]{1,4}       { return LiveScriptTokenTypes.ESCAPE_SEQUENCE; }
}

/*************************************/
/* Content of a single quoted string */
/*************************************/

<YYSINGLEQUOTESTRING> {
  \'                          { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  [^\'\n\r\\]+                { return LiveScriptTokenTypes.STRING; }

  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*************************************/
/* Content of a double quoted string */
/*************************************/

<YYDOUBLEQUOTESTRING> {
  \"                          { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.STRING_LITERAL; }

  [^\"\n\r\\]+                { pushBackAndState("#{", YYINTERPOLATION);
                                if (yylength() != 0) {
                                  return LiveScriptTokenTypes.STRING;
                                }
                              }


  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/****************************************/
/* Content of a backslash quoted string */
/****************************************/

<YYBACKSLASHQUOTESTRING> {
  {WHITE_SPACE}               { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.WHITE_SPACE; }

  [^\\\n\r\\ \t\]]+                { return LiveScriptTokenTypes.STRING; }

  {TERMINATOR}                { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.TERMINATOR; }

  "]"                        { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BRACKET_END; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}
/***************************************/
/* Content of a double quoted heredocs */
/***************************************/

<YYSINGLEQUOTEHEREDOC> {
  "'''"                       |
  "'''" / [^\n\r]+            { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.HEREDOC_END; }

  [^\n\r\\]+                  { pushBackTo("'''");
                                return LiveScriptTokenTypes.HEREDOC; }

  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/***************************************/
/* Content of a double quoted heredocs */
/***************************************/

<YYDOUBLEQUOTEHEREDOC> {
  "\"\"\""                    |
  "\"\"\"" / [^\n\r]+         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.HEREDOC_END; }

  [^\n\r\\]+                  { if (!pushBackAndState("#{", YYINTERPOLATION)) {
                                  pushBackTo("\"\"\"");
                                }
                                if (yylength() != 0) {
                                  return LiveScriptTokenTypes.HEREDOC;
                                }
                              }

  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*****************************************************************************/
/* Content of both regular expressions and here doc like regular expressions */
/*****************************************************************************/

<YYREGEX, YYHEREGEX> {
  "("                         { return LiveScriptTokenTypes.REGEX_PARENTHESIS_START; }
  ")"                         { return LiveScriptTokenTypes.REGEX_PARENTHESIS_END; }
  "{"                         { return LiveScriptTokenTypes.REGEX_BRACE_START; }
  "}"                         { return LiveScriptTokenTypes.REGEX_BRACE_END; }
}

/***********************************/
/* Content of a regular expression */
/***********************************/

<YYREGEX> {
  "/"                         |
  "/" / [imgy]{1,4}           { yybegin(YYREGEXFLAG);
                                return LiveScriptTokenTypes.REGEX_END;
                              }

  "["                         { characterClassType = LiveScriptTokenTypes.REGEX;
                                pushStateAndBegin(YYREGEXCHARACTERCLASS);
                                return LiveScriptTokenTypes.REGEX_BRACKET_START; }


  [^/\\\r\n\[\]\(\)\{\}]+     { return LiveScriptTokenTypes.REGEX; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*************************************************/
/* Content of a here doc like regular expression */
/*************************************************/

<YYHEREGEX> {
  "//"                       |
  "//" / [^\n\r]+            { yybegin(YYREGEXFLAG);
                                return LiveScriptTokenTypes.HEREGEX_END; }

  [^\\\[\]\{\}\(\)#\n\r]+     { pushBackTo("//");
                                if (yylength() != 0) {
                                  return LiveScriptTokenTypes.HEREGEX;
                                }
                              }

  "#{"                        { yypushback(2);
                                pushStateAndBegin(YYINTERPOLATION); }

  "["                         { characterClassType = LiveScriptTokenTypes.HEREGEX;
                                pushStateAndBegin(YYREGEXCHARACTERCLASS);
                                return LiveScriptTokenTypes.REGEX_BRACKET_START; }


  #[^\n\r{]+                  { return LiveScriptTokenTypes.LINE_COMMENT; }

  {TERMINATOR}                { return LiveScriptTokenTypes.TERMINATOR; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*******************************************/
/* Content of the regular expression flags */
/*******************************************/

<YYREGEXFLAG> {
  [imgy]{1,4}                 { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.REGEX_FLAG; }

  [ .\[\n\r]                  { yybegin(YYINITIAL);
                                yypushback(1);
                              }
  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*****************************************************/
/* Content of the regular expression character class */
/*****************************************************/

<YYREGEXCHARACTERCLASS> {
  "]"                         { popState();
                                return LiveScriptTokenTypes.REGEX_BRACKET_END; }

  [^\\\]\n\r]+                { return characterClassType; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/*******************************************************************************************/
/* An intermediate interpolation state that pops itself to the stack and starts over again */
/*******************************************************************************************/

<YYINTERPOLATION> {
  "#{"                        { pushStateAndBegin(YYINITIAL);
                                return LiveScriptTokenTypes.INTERPOLATION_START; }

  "}"                         { popState();
                                return LiveScriptTokenTypes.INTERPOLATION_END; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/***********************/
/* Embedded JavaScript */
/***********************/

<YYJAVASCRIPT> {
  "``"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.JAVASCRIPT_LITERAL; }


  [^``]+                       { return LiveScriptTokenTypes.JAVASCRIPT; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}


/***********************/
/* Call JavaScript Function*/
/***********************/

<YYJAVASCRIPT_CALL> {
  "`"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.JAVASCRIPT_LITERAL; }


  [^`]+                       { return LiveScriptTokenTypes.JAVASCRIPT; }

  [^]                         { yypushback(yytext().length());
                                yybegin(YYINITIAL); }
}

/***********************/
/* Block Comment       */
/***********************/
<YYBLOCKCOMMENT> {
  "*/"                         { yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BLOCK_COMMENT; }

  [^*\n]+                     { return LiveScriptTokenTypes.BLOCK_COMMENT; }

  "*"                         { return LiveScriptTokenTypes.BLOCK_COMMENT; }

  \n                         { return LiveScriptTokenTypes.BLOCK_COMMENT; }

}


/*******************/
/* Nothing matched */
/*******************/

.                             { stack.clear();
                                yybegin(YYINITIAL);
                                return LiveScriptTokenTypes.BAD_CHARACTER; }
