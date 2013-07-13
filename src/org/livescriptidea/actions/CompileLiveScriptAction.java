package org.livescriptidea.actions;

import com.intellij.lang.javascript.JavaScriptFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.Task.Modal;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.livescriptidea.compiler.LiveScriptCompiledPreviewForm;
import org.livescriptidea.compiler.LiveScriptCompiler;
import org.livescriptidea.lang.psi.LiveScriptFile;
import org.livescriptidea.file.LiveScriptFileType;

public class CompileLiveScriptAction extends AnAction {
    private static final Logger LOG = Logger.getInstance(CompileLiveScriptAction.class.getName());

    @Nullable
    private String getTextToCompile(DataContext dataContext) {
        Editor editor = (Editor) PlatformDataKeys.EDITOR.getData(dataContext);
        if (editor != null) {
            if (editor.getSelectionModel().hasSelection()) {
                return editor.getSelectionModel().getSelectedText();
            }
            return editor.getDocument().getText();
        }
        PsiFile psiFile = (PsiFile) LangDataKeys.PSI_FILE.getData(dataContext);
        if (psiFile != null) {
            return psiFile.getText();
        }

        return null;
    }

    public void actionPerformed(AnActionEvent actionEvent) {
        final String textToCompile = getTextToCompile(actionEvent.getDataContext());

        if (textToCompile != null) {
            Project project = actionEvent.getProject();
            final Ref compileCodeRef = new Ref();
            final Ref canceled = new Ref(Boolean.valueOf(false));
            Task task = new Task.Modal(project, "LiveScript file compilation", true) {
                public void run(@NotNull ProgressIndicator indicator) {
                    if (indicator == null)
                        throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/compiler/CompileLiveScriptAction.run must not be null");
                    try {
                        String compiledCode = LiveScriptCompiler.compile(textToCompile);
                        compileCodeRef.set(compiledCode);
                    } catch (IOException e) {
                        CompileLiveScriptAction.LOG.error(e);
                    }
                }

                public void onCancel() {
                    canceled.set(Boolean.valueOf(true));
                }
            };
            ProgressManager.getInstance().run(task);
            if (((Boolean) canceled.get()).booleanValue()) {
                return;
            }


            String lsFileName = "_Dummy_." + LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE.getDefaultExtension();
            PsiFile liveScriptFile = PsiFileFactory.getInstance(project).createFileFromText(LiveScriptFileType.LIVE_SCRIPT_FILE_TYPE, lsFileName, textToCompile, 0, textToCompile.length());

            String jsFileName = "_Dummy_." + JavaScriptFileType.INSTANCE.getDefaultExtension();
            PsiFile javaScriptFile;
            if (compileCodeRef.isNull()) {
                javaScriptFile = PsiFileFactory.getInstance(project).createFileFromText(PlainTextFileType.INSTANCE, jsFileName, "LiveScript file is invalid.", 0, "LiveScript file is invalid.".length());
            } else {
                javaScriptFile = PsiFileFactory.getInstance(project).createFileFromText(JavaScriptFileType.INSTANCE, jsFileName, (CharSequence) compileCodeRef.get(), 0, ((String) compileCodeRef.get()).length());
            }

            try {
                LiveScriptCompiledPreviewForm compiledCodeForm = new LiveScriptCompiledPreviewForm(project, liveScriptFile, javaScriptFile);
                compiledCodeForm.show();

            }catch (Exception e) {
                CompileLiveScriptAction.LOG.error(e);
            }
        }
    }

    public void update(AnActionEvent e) {
        PsiFile psiFile = (PsiFile) LangDataKeys.PSI_FILE.getData(e.getDataContext());
        boolean visible = (psiFile != null) && ((psiFile instanceof LiveScriptFile));
        e.getPresentation().setVisible(visible);
    }
}
