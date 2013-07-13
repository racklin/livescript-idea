package org.livescriptidea.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.impl.DocumentImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.Disposer;
import com.intellij.psi.PsiFile;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.jetbrains.annotations.NotNull;

public class LiveScriptCompiledPreviewForm extends DialogWrapper {

    private JPanel myContentPanel;
    private JSplitPane mySplitPanel;
    private JPanel leftEditorPanel;
    private JPanel rightEditorPanel;
    private JLabel lblLeftEditor;
    private PsiFile myLiveScriptFile;
    private PsiFile myJavaScriptFile;

    public LiveScriptCompiledPreviewForm(@NotNull Project project, @NotNull PsiFile liveScriptFile, @NotNull PsiFile javaScriptFile) {
        super(project);
        this.myLiveScriptFile = liveScriptFile;
        this.myJavaScriptFile = javaScriptFile;
        setupUI();
        setTitle("Compiled LiveScript File Preview");
        this.lblLeftEditor.setText("Source LiveScript file");

        init();
    }

    @NotNull
    protected Action[] createActions() {
        return new Action[]{getOKAction()};
    }

    private Editor createEditor(@NotNull PsiFile psiFile, @NotNull Project project) {
        if (psiFile == null)
            throw new IllegalArgumentException("Argument 0 for @NotNull parameter of org/livescriptidea/compiler/LiveScriptCompiledPreviewForm.createEditor must not be null");
        if (project == null)
            throw new IllegalArgumentException("Argument 1 for @NotNull parameter of org/livescriptidea/compiler/LiveScriptCompiledPreviewForm.createEditor must not be null");
        Document document = new DocumentImpl(psiFile.getText());
        EditorFactory editorFactory = EditorFactory.getInstance();
        Editor result = editorFactory.createEditor(document, project, psiFile.getFileType(), true);

        EditorSettings editorSettings = result.getSettings();
        editorSettings.setLineMarkerAreaShown(true);
        editorSettings.setLineNumbersShown(true);
        editorSettings.setIndentGuidesShown(true);
        return result;
    }

    protected JComponent createCenterPanel() {
        final Editor leftEditor = createEditor(this.myLiveScriptFile, this.myLiveScriptFile.getProject());
        final Editor rightEditor = createEditor(this.myJavaScriptFile, this.myLiveScriptFile.getProject());

        Disposer.register(getDisposable(), new Disposable() {
            public void dispose() {
                EditorFactory.getInstance().releaseEditor(leftEditor);
            }
        });
        this.leftEditorPanel.add(leftEditor.getComponent());

        Disposer.register(getDisposable(), new Disposable() {
            public void dispose() {
                EditorFactory.getInstance().releaseEditor(rightEditor);
            }
        });
        this.rightEditorPanel.add(rightEditor.getComponent());

        this.mySplitPanel.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent componentEvent) {
                LiveScriptCompiledPreviewForm.this.mySplitPanel.setDividerLocation(LiveScriptCompiledPreviewForm.this.mySplitPanel.getWidth() / 2);
            }

            public void componentMoved(ComponentEvent componentEvent) {
            }

            public void componentShown(ComponentEvent componentEvent) {
            }

            public void componentHidden(ComponentEvent componentEvent) {
            }
        });
        return this.myContentPanel;
    }

    private void setupUI()
    {
        JPanel jpanel = new JPanel();
        myContentPanel = jpanel;
        jpanel.setLayout(new FormLayout("fill:max(d;800px):grow", "center:max(d;600px):grow"));
        JSplitPane jsplitpane = new JSplitPane();
        mySplitPanel = jsplitpane;
        jsplitpane.setDividerLocation(122);
        jpanel.add(jsplitpane, new CellConstraints(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL, new Insets(0, 0, 0, 0)));
        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new BorderLayout(0, 0));
        jsplitpane.setLeftComponent(jpanel1);
        JLabel jlabel = new JLabel();
        lblLeftEditor = jlabel;
        loadLabelText(jlabel, "Source LiveScript file");
        jpanel1.add(jlabel, "North");
        JPanel jpanel2 = new JPanel();
        leftEditorPanel = jpanel2;
        jpanel2.setLayout(new BorderLayout(0, 0));
        jpanel1.add(jpanel2, "Center");
        JPanel jpanel3 = new JPanel();
        jpanel3.setLayout(new BorderLayout(0, 0));
        jsplitpane.setRightComponent(jpanel3);
        JLabel jlabel1 = new JLabel();
        loadLabelText(jlabel1, "Generated JavaScript file");
        jpanel3.add(jlabel1, "North");
        JPanel jpanel4 = new JPanel();
        rightEditorPanel = jpanel4;
        jpanel4.setLayout(new BorderLayout(0, 0));
        jpanel3.add(jpanel4, "Center");
    }

    private void loadLabelText(JLabel jlabel, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        boolean flag = false;
        char c = '\0';
        int i = -1;
        for(int j = 0; j < s.length(); j++)
        {
            if(s.charAt(j) == '&')
            {
                if(++j == s.length())
                    break;
                if(!flag && s.charAt(j) != '&')
                {
                    flag = true;
                    c = s.charAt(j);
                    i = stringbuffer.length();
                }
            }
            stringbuffer.append(s.charAt(j));
        }

        jlabel.setText(stringbuffer.toString());
        if(flag)
        {
            jlabel.setDisplayedMnemonic(c);
            jlabel.setDisplayedMnemonicIndex(i);
        }
    }


}
