package org.livescriptidea.lang.lexer;

import com.intellij.psi.tree.IElementType;
import org.livescriptidea.file.LiveScriptFileType;

/**
 * Custom LiveScript element types.
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptElementType extends IElementType {

  private String name = null;

  public LiveScriptElementType(String name) {
    super(name, LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getLanguage());

    this.name = name;
  }

  public String toString() {
    return name;
  }

}
