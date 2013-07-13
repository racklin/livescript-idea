package org.livescriptidea.editor;

import com.intellij.lang.Commenter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.templateLanguages.MultipleLangCommentProvider;
import org.jetbrains.annotations.Nullable;
import org.livescriptidea.file.LiveScriptFileType;

public class LiveScriptCommentProvider implements MultipleLangCommentProvider {

    @Nullable
    public Commenter getLineCommenter(PsiFile file, Editor editor, Language lineStartLanguage, Language lineEndLanguage)
    {
        return new LiveScriptCommenter();
    }

    public boolean canProcess(PsiFile file, FileViewProvider viewProvider)
    {
        if (viewProvider.getLanguages().contains(LiveScriptFileType.LIVE_SCRIPT_LANGUAGE)) {
            return true;
        }
        return false;
    }
}
