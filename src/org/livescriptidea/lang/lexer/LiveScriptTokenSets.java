package org.livescriptidea.lang.lexer;

import com.intellij.psi.tree.TokenSet;

/**
 * Group the lexer lexer
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public abstract class LiveScriptTokenSets implements LiveScriptTokenTypes {

    public static final TokenSet WHITESPACE_TOKEN_SET = TokenSet.create(
            WHITE_SPACE
    );

    public static final TokenSet COMMENTS_TOKEN_SET = TokenSet.create(
            LINE_COMMENT,
            BLOCK_COMMENT
    );

    public static final TokenSet STRING_TOKEN_SET = TokenSet.create(
            STRING,
            STRING_LITERAL
    );

    public static final TokenSet RELATIONAL_OPERATIONS = TokenSet.create(
            GT, GE, LT, LE, INSTANCE_OF, IN
    );

    public static final TokenSet PREFIX_OPERATORS = TokenSet.create(
            TILDE, EXCL, TYPEOF_KEYWORD, PLUSPLUS, MINUSMINUS,
            NEW_KEYWORD, DELETE_KEYWORD, MINUS, PLUS
    );

    public static final TokenSet POSTFIX_OPERATORS = TokenSet.create(
            PLUSPLUS, MINUSMINUS, BANG
    );

    public static final TokenSet ARITHMETIC_MUL_OPERATORS = TokenSet.create(
            MULT, DIV, PERC
    );

    public static final TokenSet ARITHMETIC_SUM_OPERATORS = TokenSet.create(
            PLUS, MINUS
    );

    public static final TokenSet ARITHMETIC_BIT_OPERATORS = TokenSet.create(
            LTLT, GTGT, GTGTGT
    );

    public static final TokenSet EQUALITY_OPERATIONS = TokenSet.create(
            EQEQ, EQEQEQ, NEQEQ, NE
    );

    public static final TokenSet EXIST_OPERATORS = TokenSet.create(
            EXIST
    );

    public static final TokenSet BIT_AND = TokenSet.create(
            DOTANDDOT
    );
    public static final TokenSet BIT_OR = TokenSet.create(
            DOTORDOT
    );
    public static final TokenSet BIT_XOR = TokenSet.create(
    );

    public static final TokenSet LOGIC_AND = TokenSet.create(
            ANDAND
    );

    public static final TokenSet LOGIC_OR = TokenSet.create(
            OROR
    );

    public static final TokenSet OPERATION_SIGNS = TokenSet.orSet(
            PREFIX_OPERATORS, POSTFIX_OPERATORS,
            ARITHMETIC_MUL_OPERATORS, ARITHMETIC_SUM_OPERATORS,
            EXIST_OPERATORS, ARITHMETIC_BIT_OPERATORS, RELATIONAL_OPERATIONS,
            EQUALITY_OPERATIONS,
            BIT_AND, BIT_XOR, BIT_OR,
            LOGIC_AND, LOGIC_OR
    );


    public static final TokenSet ASSIGNMENT_OPERATIONS = TokenSet.create(
            EQUAL, ANDEQ, AND_AND_EQ, OREQ, OR_OR_EQ, XOREQ,
            LTLTEQ, GTGTEQ, GTGTGTEQ, PLUSEQ,
            MINUSEQ, MULTEQ, DIVEQ, PERCEQ, EXIST_EQ
    );

}
