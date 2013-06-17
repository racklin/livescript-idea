package org.livescriptidea.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;
import org.jetbrains.annotations.NotNull;

/**
 * LiveScript view provider that disables incremental reparsing
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptViewProvider extends SingleRootFileViewProvider {

  public LiveScriptViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile file) {
   super(manager, file);
  }

  public LiveScriptViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, final boolean physical) {
    super(manager, virtualFile, physical);
  }

  protected LiveScriptViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, final boolean physical, @NotNull Language language) {
    super(manager, virtualFile, physical, language);
  }

  public boolean supportsIncrementalReparse(final Language rootLanguage) {
    return false;
  }

}
