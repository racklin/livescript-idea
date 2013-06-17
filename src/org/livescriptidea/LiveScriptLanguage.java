package org.livescriptidea;

import com.intellij.lang.Language;

/**
 * All main properties for the LiveScript language
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptLanguage extends Language {

  public LiveScriptLanguage() {
    super("LiveScript", "text/ls");
  }

  @Override
  public boolean isCaseSensitive() {
    return true;
  }

}
