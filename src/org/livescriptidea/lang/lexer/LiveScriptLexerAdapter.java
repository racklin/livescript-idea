package org.livescriptidea.lang.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;
/**
 * Created with IntelliJ IDEA.
 * User: rack
 * Date: 13/6/19
 * Time: AM2:54
 * To change this template use File | Settings | File Templates.
 */
public class LiveScriptLexerAdapter extends FlexAdapter {
    public LiveScriptLexerAdapter() {
        super(new LiveScriptLexer((Reader) null));
    }
}
