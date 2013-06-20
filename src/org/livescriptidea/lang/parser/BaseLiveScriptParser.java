package org.livescriptidea.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.containers.HashMap;
import java.util.ArrayList;
import java.util.List;
import org.livescriptidea.lang.lexer.LiveScriptTokenTypes;
import org.livescriptidea.lang.lexer.LiveScriptTokenSets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class BaseLiveScriptParser implements PsiParser {

    private final TokenSet ARRAY_PREFIX = TokenSet.create(new IElementType[] { LiveScriptTokenTypes.BRACKET_START, LiveScriptTokenTypes.BRACE_START, LiveScriptTokenTypes.PARENTHESIS_START, LiveScriptTokenTypes.EQUAL, LiveScriptTokenTypes.COLON, LiveScriptTokenTypes.WHITE_SPACE, LiveScriptTokenTypes.COMMA });
    private volatile int myCurrentIndent;
    private PsiBuilder myBuilder;
    private HashMap<PsiBuilder.Marker, Integer> myIndents;
    private HashMap<PsiBuilder.Marker, Boolean> myNewLines;
    private HashMap<PsiBuilder.Marker, List<List<String>>> myDeclarationStack;
    private List<List<String>> declaredVariables = new ArrayList();
    private boolean myNewLine;
    private int myNewLineOffset;

    protected int getCurrentIndent()
    {
        return this.myCurrentIndent;
    }

    private void init() {
        this.myCurrentIndent = 0;
        this.myNewLine = true;
        this.myIndents = new HashMap();
        this.myNewLines = new HashMap();
        this.myDeclarationStack = new HashMap();

        int offset = getCurrentOffset();
        if (offset > 0) {
            String whiteSpaceText = this.myBuilder.getOriginalText().subSequence(0, offset).toString();
            int i = whiteSpaceText.lastIndexOf('\n');
            if (i >= 0)
                this.myCurrentIndent = (whiteSpaceText.length() - i - 1);
            else
                this.myCurrentIndent = whiteSpaceText.length();
        }
    }

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder)
    {
        parseWithoutBuildingTree(root, builder);
        ASTNode tmp15_10 = this.myBuilder.getTreeBuilt(); if (tmp15_10 == null) throw new IllegalStateException("@NotNull method org/coffeescript/lang/parser/BaseCoffeeScriptParser.parse must not return null"); return tmp15_10;
    }

    public void parseWithoutBuildingTree(IElementType root, PsiBuilder builder) {
        this.myBuilder = builder;
        PsiBuilder.Marker rootMarker = this.myBuilder.mark();
        init();

        if (!this.myBuilder.eof()) {
            parseRoot();
        }
        rootMarker.done(root);
    }

    protected abstract void parseRoot();

    private void cloneDeclarationScopes(PsiBuilder.Marker marker) {
        List result = new ArrayList(this.declaredVariables.size());
        for (List scope : this.declaredVariables) {
            List clonnedScope = new ArrayList(scope.size());
            clonnedScope.addAll(scope);
            result.add(clonnedScope);
        }
        this.myDeclarationStack.put(marker, result);
    }

    @NotNull
    protected PsiBuilder.Marker mark()
    {
        PsiBuilder.Marker tmp9_4 = this.myBuilder.mark(); if (tmp9_4 == null) throw new IllegalStateException("@NotNull method org/coffeescript/lang/parser/BaseCoffeeScriptParser.mark must not return null"); return tmp9_4;
    }

    protected PsiBuilder.Marker mark(boolean couldBeRolledBack) {
        PsiBuilder.Marker marker = mark();
        if (couldBeRolledBack) {
            this.myIndents.put(marker, Integer.valueOf(this.myCurrentIndent));
            this.myNewLines.put(marker, Boolean.valueOf(this.myNewLine));
            cloneDeclarationScopes(marker);
        }
        return marker;
    }

    protected void rollback(@NotNull PsiBuilder.Marker marker) {
        if (marker == null) throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/coffeescript/lang/parser/BaseCoffeeScriptParser.rollback must not be null"); if (this.myIndents.get(marker) == null) {
            throw new RuntimeException("CoffeeScriptParser can't rollback marker that was created by mark() method, use mark(true) instead");
        }
        this.myCurrentIndent = ((Integer)this.myIndents.get(marker)).intValue();
        this.myNewLine = ((Boolean)this.myNewLines.get(marker)).booleanValue();
        this.declaredVariables = ((List)this.myDeclarationStack.get(marker));

        this.myIndents.remove(marker);
        this.myNewLines.remove(marker);
        this.myDeclarationStack.remove(marker);
        marker.rollbackTo();
    }

    protected void done(@NotNull PsiBuilder.Marker marker, @NotNull IElementType type) {
        if (marker == null) throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/coffeescript/lang/parser/BaseCoffeeScriptParser.done must not be null"); if (type == null) throw new IllegalArgumentException("Argument 1 for @NotNull parameter of org/coffeescript/lang/parser/BaseCoffeeScriptParser.done must not be null"); this.myIndents.remove(marker);
        this.myNewLines.remove(marker);
        this.myDeclarationStack.remove(marker);
        marker.done(type);
    }

    protected void startScope() {
        this.declaredVariables.add(new ArrayList());
    }

    protected void endScope() {
        this.declaredVariables.remove(this.declaredVariables.size() - 1);
    }

    protected int getScopeDeep() {
        return this.declaredVariables.size();
    }

    protected void addVariableInCurrentScope(@NotNull String variableName) {
        if (variableName == null) throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/coffeescript/lang/parser/BaseCoffeeScriptParser.addVariableInCurrentScope must not be null"); ((List)this.declaredVariables.get(this.declaredVariables.size() - 1)).add(variableName);
    }

    protected boolean isInScope(String name) {
        for (List scope : this.declaredVariables) {
            if (scope.contains(name)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isNewLine() {
        return this.myNewLine;
    }

    protected void advance() {
        String tokenText = this.myBuilder.getTokenText();
        int tokenLength = tokenText == null ? 0 : tokenText.length();

        int whiteSpaceStart = getCurrentOffset() + tokenLength;
        this.myBuilder.advanceLexer();
        int whiteSpaceEnd = getCurrentOffset();
        String whiteSpaceText = this.myBuilder.getOriginalText().subSequence(whiteSpaceStart, whiteSpaceEnd).toString();

        int i = whiteSpaceText.lastIndexOf('\n');
        if (i >= 0) {
            int slashIndex = whiteSpaceText.indexOf("\\");
            if ((slashIndex < 0) || (slashIndex > whiteSpaceText.indexOf("\n"))) {
                this.myCurrentIndent = (whiteSpaceText.length() - i - 1);
                this.myNewLine = true;
                this.myNewLineOffset = (getCurrentOffset() - i);
            }
        } else {
            this.myNewLine = false;
        }
    }

    protected IElementType lookAhead(int step) {
        return this.myBuilder.lookAhead(step);
    }

    protected IElementType rawLookup(int step) {
        return this.myBuilder.rawLookup(step);
    }

    protected void error(String message) {
        this.myBuilder.error(message);
    }

    protected IElementType getTokenType() {
        return this.myBuilder.getTokenType();
    }

    @NotNull
    protected String getTokenText() {
        String result = this.myBuilder.getTokenText();
        if (result == null)
            result = "";
        String tmp19_18 = result; if (tmp19_18 == null) throw new IllegalStateException("@NotNull method org/coffeescript/lang/parser/BaseCoffeeScriptParser.getTokenText must not return null"); return tmp19_18;
    }

    @NotNull
    protected String getTokenText(int stepAhead) {
        PsiBuilder.Marker marker = mark();
        for (int i = 0; i < stepAhead; i++) {
            this.myBuilder.advanceLexer();
        }
        String result = getTokenText();
        marker.rollbackTo();
        String tmp39_38 = result; if (tmp39_38 == null) throw new IllegalStateException("@NotNull method org/coffeescript/lang/parser/BaseCoffeeScriptParser.getTokenText must not return null"); return tmp39_38;
    }

    protected int getOffsetFromCurrentLine() {
        return getCurrentOffset() - this.myNewLineOffset;
    }

    protected int getCurrentOffset() {
        return this.myBuilder.getCurrentOffset();
    }

    protected char charAt(int offset) {
        return this.myBuilder.getOriginalText().charAt(offset);
    }

    protected void skipCommaAndTerminator() {
        while ((!eof()) && ((isComma()) || (isExpressionTerminator())))
            advance();
    }

    protected boolean isExpressionTerminator()
    {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.SEMICOLON });
    }

    protected boolean eof() {
        return this.myBuilder.eof();
    }

    protected static boolean isTokenIn(IElementType tokenType, IElementType[] types) {
        if (types != null) {
            for (IElementType type : types) {
                if (tokenType == type) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean isCurrentTokenIn(IElementType[] types) {
        IElementType token = this.myBuilder.getTokenType();
        if (token != null) {
            return isTokenIn(token, types);
        }
        return false;
    }

    protected boolean isPostfixOperator() {
        return LiveScriptTokenSets.POSTFIX_OPERATORS.contains(getTokenType());
    }

    protected boolean isOperationSymbol() {
        boolean isOperation = false;

        isOperation |= (LiveScriptTokenSets.ARITHMETIC_MUL_OPERATORS.contains(getTokenType()));
        isOperation |= ((LiveScriptTokenSets.ARITHMETIC_SUM_OPERATORS.contains(getTokenType())) && ((rawLookup(-1) != LiveScriptTokenTypes.WHITE_SPACE) || (rawLookup(1) == LiveScriptTokenTypes.WHITE_SPACE)));
        isOperation |= (LiveScriptTokenSets.ARITHMETIC_BIT_OPERATORS.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.RELATIONAL_OPERATIONS.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.EQUALITY_OPERATIONS.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.ASSIGNMENT_OPERATIONS.contains(getTokenType()));

        isOperation |= (LiveScriptTokenSets.BIT_AND.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.BIT_OR.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.BIT_XOR.contains(getTokenType()));

        isOperation |= (LiveScriptTokenSets.LOGIC_AND.contains(getTokenType()));
        isOperation |= (LiveScriptTokenSets.LOGIC_OR.contains(getTokenType()));


        return isOperation ;
    }

    protected boolean isIf()
    {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.IF, LiveScriptTokenTypes.UNLESS });
    }
    protected boolean isPrototype() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.PROTOTYPE });
    }

    protected boolean isIdentifier() {
        return isIdentifier(getTokenType());
    }

    protected static boolean isIdentifier(@Nullable IElementType tokenType) {
        return isTokenIn(tokenType, new IElementType[] { LiveScriptTokenTypes.IDENTIFIER });
    }

    protected boolean isSuper() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.SUPER });
    }

    protected boolean isComma() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.COMMA });
    }

    protected boolean isParenthetical() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.PARENTHESIS_START });
    }

    protected boolean isThis() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.THIS });
    }

    protected boolean isShortThis() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.THIS })) && (getTokenText().equals("@"));
    }

    protected boolean isSplatSymbol() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.SPLAT });
    }

    protected boolean isSplatOrRange() {
        if (!isSplatSymbol());
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.RANGE });
    }

    protected boolean isRangeSymbol() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.RANGE })) || (isSplatSymbol());
    }

    protected boolean isArray() {
        if (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.BRACKET_START })) {
            IElementType previousElement = rawLookup(-1);
            return (getCurrentOffset() == 0) || ((LiveScriptTokenSets.OPERATION_SIGNS.contains(previousElement)) && (!LiveScriptTokenSets.EXIST_OPERATORS.contains(previousElement))) || (this.ARRAY_PREFIX.contains(previousElement));
        }

        return false;
    }

    protected boolean isIndex() {
        if (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.BRACKET_START })) {
            return !isArray();
        }

        return false;
    }

    protected boolean isAccessor(int indent) {
        if (indent > getCurrentIndent()) {
            return false;
        }
        IElementType tokenType = getTokenType();

        if (tokenType == LiveScriptTokenTypes.DOT) {
            tokenType = lookAhead(1);
            String tokenText = getTokenText(1);
            if ((!isIdentifier(tokenType)) && (!tokenText.equals("is"))) { if (!isTokenIn(tokenType, new IElementType[] { LiveScriptTokenTypes.BOOL }));
            } else return true;
        }
        else if ((isPrototype()) || (isIndex())) {
            return true;
        }
        return false;
    }

    protected boolean isExist()
    {
        return ((isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.EXIST })) || (getTokenText().equals("?"))) && (!hasSpaceBefore());
    }

    protected boolean hasSpaceBefore() {
        return rawLookup(-1) == LiveScriptTokenTypes.WHITE_SPACE;
    }

    protected boolean isSimpleAssignable() {
        return (isIdentifier()) || (isThis());
    }

    protected boolean isThisProperty() {
        return (isThis()) && (isIdentifier(rawLookup(1)));
    }

    protected boolean isSwitchEnd() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.CATCH, LiveScriptTokenTypes.FINALLY });
    }

    protected boolean isInvocationWithBraces() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.PARENTHESIS_START })) && (!isNewLine()) && (charAt(getCurrentOffset() - 1) != ' ');
    }

    protected boolean isString() {
        return isString(getTokenType());
    }

    protected boolean isString(IElementType tokenType) {
        return isTokenIn(tokenType, new IElementType[] { LiveScriptTokenTypes.HEREDOC_START, LiveScriptTokenTypes.STRING_LITERAL });
    }

    protected boolean isRegexp() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.HEREGEX_START, LiveScriptTokenTypes.HEREGEX_START, LiveScriptTokenTypes.REGEX_START });
    }

    protected boolean isWhile() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.WHILE, LiveScriptTokenTypes.UNTIL });
    }

    protected boolean isThrow() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.THROW });
    }

    protected boolean isCode() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.FUNCTION, LiveScriptTokenTypes.FUNCTION_BIND })) || (isParenthetical());
    }

    protected boolean isReturn() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.RETURN });
    }

    protected boolean isSwitch() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.SWITCH });
    }

    protected boolean isAlphaNumeric() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.NUMBER, LiveScriptTokenTypes.STRING_LITERAL })) || (isString());
    }

    protected boolean isLiteral() {
        if ((!isAlphaNumeric()) && (!isRegexp()));
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.JAVASCRIPT_LITERAL, LiveScriptTokenTypes.BOOL });
    }

    protected boolean isObjAssignable() {
        return (isIdentifier()) || (isAlphaNumeric()) || (isThisProperty()) || (isString());
    }

    protected boolean isStatement() {
        return (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.BREAK, LiveScriptTokenTypes.DEBUGGER, LiveScriptTokenTypes.CONTINUE })) || (isReturn()) || (isVariable());
    }

    protected boolean isVariable() {
        if (isIdentifier());
        return (isTokenIn(lookAhead(1), new IElementType[] { LiveScriptTokenTypes.EQUAL })) && (!isInScope(getTokenText()));
    }

    protected boolean isForBody() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.FOR });
    }

    protected boolean isLoop() {
        return isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.LOOP });
    }

    protected boolean isRelationSymbol() {
        if (isCurrentTokenIn(new IElementType[] { LiveScriptTokenTypes.EXCL })) {
            return LiveScriptTokenSets.RELATIONAL_OPERATIONS.contains(lookAhead(1));
        }
        return LiveScriptTokenSets.RELATIONAL_OPERATIONS.contains(getTokenType());
    }

}
