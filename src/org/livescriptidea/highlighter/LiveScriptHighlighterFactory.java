package org.livescriptidea.highlighter;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;

/**
 * LiveScript Syntax highlighter factory
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {

  @NotNull
  protected LiveScriptSyntaxHighlighter createHighlighter() {
    return new LiveScriptSyntaxHighlighter();
  }

}
