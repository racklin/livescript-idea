package org.livescriptidea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NotNull;
import org.livescriptidea.file.LiveScriptFileType;

/**
 * Main application component that loads the LiveScript language support
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptLoader implements ApplicationComponent {

  public void initComponent() {
    ApplicationManager.getApplication().runWriteAction(
            new Runnable() {
              public void run() {
                FileTypeManager.getInstance().registerFileType(LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE, LiveScriptFileType.DEFAULT_EXTENSION);
              }
            }
    );
  }

  public void disposeComponent() {
  }

  @NotNull
  public String getComponentName() {
    return "livescript.support.loader";
  }

}
