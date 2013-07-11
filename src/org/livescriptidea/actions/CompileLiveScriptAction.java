package org.livescriptidea.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog.Builder;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.livescriptidea.file.LiveScriptFileType;

public class CompileLiveScriptAction extends CreateFileFromTemplateAction
        implements DumbAware
{
    public CompileLiveScriptAction()
    {
        super("LiveScript File", "Creates a LiveScript file", LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getIcon());
    }

    protected void buildDialog(Project project, PsiDirectory directory, Builder builder)
    {
        //builder.setTitle("New LiveScript File").addKind("LiveScript File", LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getIcon(), "LiveScript File").addKind("LiveScript Class", LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getIcon(), "LiveScript Class");
        builder.setTitle("New LiveScript File").addKind("LiveScript File", LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getIcon(), "LiveScript_Empty_File").addKind("LiveScript Class", LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getIcon(), "LiveScript_Class");
    }

    protected String getActionName(PsiDirectory directory, String newName, String templateName)
    {
        return "Create LiveScript file " + newName;
    }
}
