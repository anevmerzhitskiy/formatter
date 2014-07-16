package it.sevenbits.codeformatter;


import it.sevenbits.streams.InStream;
import it.sevenbits.streams.OutStream;
import it.sevenbits.streams.StreamException;

/**
*
*/
public class CodeFormatter {

    private int braceLevel = 0;

    public CodeFormatter() {}

    private void increaseBrace() {
        braceLevel++;
    }

    private void reduceBrace() {
        braceLevel--;
    }

    /**
    * @param out symbol output stream
    * @param formatOptions
    */
    private void shiftNextString(final OutStream out, final FormatOptions formatOptions) throws StreamException {
        try {
            out.writeSymbol(formatOptions.getSymbolEndOfString());
            for (int i = 0; i < formatOptions.getIndent() * braceLevel; i++) {
                out.writeSymbol(formatOptions.getTabSymbol());
            }
        } catch (StreamException e) {
            throw e;
        }
    }

    /**
     * @param in symbol input stream
     * @param out symbol output stream
     * @param formatOptions
     * @throws FormatterException
     */
    public void format(final InStream in, final OutStream out, final FormatOptions formatOptions) throws FormatterException {

        boolean isNewString = false;
        boolean isAloneSpaceButton = false;
        char c;

        try {
            while (!in.isEnd()) {
                c = in.getSymbol();
                switch (c) {
                    case ';': {
                        out.writeSymbol(c);
                        isNewString = true;
                        shiftNextString(out, formatOptions);
                        break;
                    }
                    case '{': {
                        out.writeSymbol(c);
                        isNewString = true;
                        increaseBrace();
                        shiftNextString(out, formatOptions);
                        break;
                    }
                    case '}': {
                        isNewString = true;
                        reduceBrace();
                        // add try-catch
                        shiftNextString(out, formatOptions);
                        out.writeSymbol(c);
                        break;
                    }
                    case ' ': {
                        if (!isNewString) {
                            if (isAloneSpaceButton) {
                                isAloneSpaceButton = false;
                                out.writeSymbol(c);
                            }
                        }
                        break;
                    }
                    default: {
                        isNewString = false;
                        isAloneSpaceButton = true;
                        out.writeSymbol(c);
                        break;
                    }
                }
            }
        } catch (StreamException e) {
            throw new FormatterException(e);
        }
    }
}
