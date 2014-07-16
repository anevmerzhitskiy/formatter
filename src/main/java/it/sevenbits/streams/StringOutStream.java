package it.sevenbits.streams;

/**
 *
 */
public class StringOutStream implements OutStream {

    private StringBuffer out;

    public StringOutStream() {
        out = new StringBuffer("");
    }

    public StringOutStream(final String str) {
        out = new StringBuffer(str);
    }

    public void writeSymbol(final char c) throws StreamException {

        out.append(c);
    }

    public void close() throws StreamException {
        out = null;
    }

    public String getString() {
        return out.toString();
    }
}
