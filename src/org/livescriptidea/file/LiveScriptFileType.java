package org.livescriptidea.file;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.livescriptidea.LiveScriptIcons;
import org.livescriptidea.LiveScriptLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * LiveScript file properties
 *
 * @author Rack Lin
 * @since 0.1.0
 */
public class LiveScriptFileType extends LanguageFileType {

  public static final LiveScriptFileType LIVE_SCRIPT_FILE_TYPE = new LiveScriptFileType();
  public static final Language LIVE_SCRIPT_LANGUAGE = LIVE_SCRIPT_FILE_TYPE.getLanguage();

  @NonNls
  public static final String DEFAULT_EXTENSION = "ls";

  private LiveScriptFileType() {
    super(new LiveScriptLanguage());
  }

  @NotNull
  @NonNls
  public String getName() {
    return "LiveScript";
  }

  @NonNls
  @NotNull
  public String getDescription() {
    return "LiveScript Files";
  }

  @NotNull
  @NonNls
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  @NotNull
   public Icon getIcon() {
    return LiveScriptIcons.FILE_TYPE;
  }

}
