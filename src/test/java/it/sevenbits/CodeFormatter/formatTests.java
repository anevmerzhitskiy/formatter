package it.sevenbits.CodeFormatter;

import it.sevenbits.codeformatter.CodeFormatter;
import it.sevenbits.codeformatter.FormatOptions;
import it.sevenbits.codeformatter.FormatterException;
import it.sevenbits.streams.InStream;
import it.sevenbits.streams.StreamException;
import it.sevenbits.streams.StringInStream;
import it.sevenbits.streams.StringOutStream;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anton on 02/07/14.
 */
public class formatTests {

    @Test
    public void lineFeed() throws StreamException, FormatterException {
        InStream in = new StringInStream("a;");
        StringOutStream out = new StringOutStream("");
        String res = new String("a;\n");
        CodeFormatter formatter = new CodeFormatter();
        FormatOptions formatOpt = new FormatOptions();
        formatter.format(in, out, formatOpt);
        String temp = out.getString();
        assertTrue(res.equals(temp));
    }

    @Test
    public void isTab() throws StreamException, FormatterException, IOException {
        InStream in = new StringInStream("{a}");
        String res = new String("{\n    a\n}");
        StringOutStream out = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        FormatOptions formatOpt = new FormatOptions();
        formatter.format(in, out, formatOpt);
        String temp = out.getString();
        assertTrue(res.equals(temp));
    }

    @Test
    public void tabAndLineFeed() throws StreamException, FormatterException, IOException {
        InStream in = new StringInStream("{a;}");
        String res = new String("{\n    a;\n}\n");
        StringOutStream out = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        FormatOptions formatOpt = new FormatOptions();
        formatter.format(in, out, formatOpt);
        String temp = out.getString();
        assertTrue(res.equals(temp));
    }

    /*@Test
    public void deleteSpace() throws StreamException, FormatterException, IOException {
        InStream in = new StringInStream("{  a;}");
        String res = new String("{\n    a;\n}");
        StringOutStream out = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        FormatOptions formatOpt = new FormatOptions();
        formatter.format(in, out, formatOpt);
        String temp = out.getString();
        assertTrue(res.equals(temp));
    }*/
}