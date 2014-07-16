package it.sevenbits.streams;

/**
 *
 */
public class StringInStream implements InStream {

    private int index;
    private String code;

    public StringInStream(final String str) {
        code = str;
        index = 0;
    }

    public char getSymbol() throws StreamException {
            char result = code.charAt(index);
            index++;
            return result;
    }

    public void close() throws StreamException {

    }

    public boolean isEnd() {
        return index == code.length();
    }
}
