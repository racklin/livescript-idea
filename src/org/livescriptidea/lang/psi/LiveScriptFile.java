package org.livescriptidea.lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import static org.livescriptidea.file.LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE;

/**
 * A LiveScript file
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptFile extends PsiFileBase {

  public LiveScriptFile(FileViewProvider viewProvider) {
    super(viewProvider, LIVE_SCRIPT_FILE_TYPE.getLanguage());
  }

  @NotNull
  public FileType getFileType() {
    return LIVE_SCRIPT_FILE_TYPE;
  }

  public String toString() {
    return "LiveScript File: " + getName();
  }

}
