package org.livescriptidea.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.livescriptidea.lang.psi.LiveScriptElement;

/**
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptElementImpl extends ASTWrapperPsiElement implements LiveScriptElement {

  private final ASTNode node;

  public LiveScriptElementImpl(final ASTNode node) {
    super(node);
    this.node = node;
  }

  public String toString() {
    return "LS:" + node.getElementType().toString();
  }

}
