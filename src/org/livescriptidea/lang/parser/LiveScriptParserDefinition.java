package org.livescriptidea.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.livescriptidea.file.LiveScriptFileType;
import org.livescriptidea.lang.lexer.LiveScriptLexerAdapter;
import org.livescriptidea.lang.lexer.LiveScriptTokenSets;
import org.livescriptidea.lang.psi.LiveScriptFile;
import org.livescriptidea.lang.psi.impl.LiveScriptElementImpl;

/**
 * Parser definition for LiveScript language support
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptParserDefinition implements ParserDefinition {

  private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType(LiveScriptFileType.LIVE_SCRIPT_LANGUAGE);

  @NotNull
  public Lexer createLexer(Project project) {
    return new LiveScriptLexerAdapter();
  }

  public PsiParser createParser(Project project) {
    return new LiveScriptParser();
  }

  public IFileElementType getFileNodeType() {
    return FILE_ELEMENT_TYPE;
  }

  @NotNull
  public TokenSet getWhitespaceTokens() {
    return LiveScriptTokenSets.WHITESPACE_TOKEN_SET;
  }

  @NotNull
  public TokenSet getCommentTokens() {
    return LiveScriptTokenSets.COMMENTS_TOKEN_SET;
  }

  @NotNull
  public TokenSet getStringLiteralElements() {
    return LiveScriptTokenSets.STRING_TOKEN_SET;
  }

  @NotNull
  public PsiElement createElement(ASTNode node) {
    return new LiveScriptElementImpl(node);
  }

  public PsiFile createFile(FileViewProvider viewProvider) {
    return new LiveScriptFile(viewProvider);
  }

  public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return ParserDefinition.SpaceRequirements.MAY;
  }

}
