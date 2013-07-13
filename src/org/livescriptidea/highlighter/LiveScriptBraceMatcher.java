package org.livescriptidea.highlighter;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.livescriptidea.lang.lexer.LiveScriptTokenTypes;

/**
 * Brace matcher for the LiveScript language
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptBraceMatcher implements PairedBraceMatcher {

  private static final BracePair[] PAIRS = {
          new BracePair(LiveScriptTokenTypes.PARENTHESIS_START, LiveScriptTokenTypes.PARENTHESIS_END, false),
          new BracePair(LiveScriptTokenTypes.BRACKET_START, LiveScriptTokenTypes.BRACKET_END, false),
          new BracePair(LiveScriptTokenTypes.BRACE_START, LiveScriptTokenTypes.BRACE_END, false),
          new BracePair(LiveScriptTokenTypes.REGEX_START, LiveScriptTokenTypes.REGEX_END, false),
          new BracePair(LiveScriptTokenTypes.REGEX_BRACKET_START, LiveScriptTokenTypes.REGEX_BRACKET_END, false),
          new BracePair(LiveScriptTokenTypes.REGEX_PARENTHESIS_START, LiveScriptTokenTypes.REGEX_PARENTHESIS_END, false),
          new BracePair(LiveScriptTokenTypes.REGEX_BRACE_START, LiveScriptTokenTypes.REGEX_BRACE_END, true),
          new BracePair(LiveScriptTokenTypes.INTERPOLATION_START, LiveScriptTokenTypes.INTERPOLATION_END, true),
  };

  public BracePair[] getPairs() {
    return PAIRS;
  }

  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType ibraceType, @Nullable IElementType tokenType) {
    return true;
  }

  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    return openingBraceOffset;
  }

}
