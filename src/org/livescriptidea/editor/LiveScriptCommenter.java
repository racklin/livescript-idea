package org.livescriptidea.editor;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import org.livescriptidea.lang.lexer.LiveScriptTokenTypes;

/**
 * Commenting and uncommenting of LiveScript code blocks
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptCommenter implements CodeDocumentationAwareCommenter {

  public String getLineCommentPrefix() {
    return "#";
  }

  public String getBlockCommentPrefix() {
    return "/*";
  }

  public String getBlockCommentSuffix() {
    return "*/";
  }

  public String getCommentedBlockCommentPrefix() {
    return null;
  }

  public String getCommentedBlockCommentSuffix() {
    return null;
  }

  @Nullable
  public IElementType getLineCommentTokenType() {
    return LiveScriptTokenTypes.LINE_COMMENT;
  }

  @Nullable
  public IElementType getBlockCommentTokenType() {
    return LiveScriptTokenTypes.BLOCK_COMMENT;
  }

  @Nullable
  public IElementType getDocumentationCommentTokenType() {
    return null;
  }

  @Nullable
  public String getDocumentationCommentPrefix() {
    return null;
  }

  @Nullable
  public String getDocumentationCommentLinePrefix() {
    return null;
  }

  @Nullable
  public String getDocumentationCommentSuffix() {
    return null;
  }

  public boolean isDocumentationComment(PsiComment element) {
    return false;
  }

}
