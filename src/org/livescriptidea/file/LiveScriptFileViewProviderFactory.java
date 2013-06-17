package org.livescriptidea.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;

/**
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptFileViewProviderFactory implements FileViewProviderFactory {

  public FileViewProvider createFileViewProvider(VirtualFile file, Language language, PsiManager manager, boolean physical) {
    return new LiveScriptViewProvider(manager, file, physical, language);
  }
}
