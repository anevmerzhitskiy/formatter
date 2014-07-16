package it.sevenbits.codeformatter;


import it.sevenbits.streams.InStream;
import it.sevenbits.streams.OutStream;
import it.sevenbits.streams.StreamException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
*
*/
public class CodeFormatter {

    private int braceLevel = 0;
    private Logger logger = Logger.getLogger(CodeFormatter.class);
    /** tabulation symbol in output file */
    public static final char INDENT_SYMBOL = ' ';
    /** end of string symbol in output file */
    public static final char SYMBOL_END_OF_STRING = '\n';
    /** indent size */
    public static final int INDENT_SIZE = 4;

    public CodeFormatter() {}

    private void increaseBrace() {
        braceLevel++;
    }

    private void reduceBrace() {
        braceLevel--;
    }

    /**
     *
     * @param out
     * @param indentSymbol
     * @param indentSize
     * @param braceLevel
     * @throws FormatterException
     */
    private void shiftNextString(
            final OutStream out, final char indentSymbol, final int indentSize, final int braceLevel
    ) throws FormatterException {
        try {
            for (int i = 0; i < indentSize * braceLevel; i++) {
                out.writeSymbol(indentSymbol);
            }
        } catch (StreamException e) {
            throw new FormatterException(e);
        }
    }

    /**
     * @param in symbol input stream
     * @param out symbol output stream
     * @param formatOptions
     * @throws FormatterException
     */
    public void format(final InStream in, final OutStream out, final FormatOptions formatOptions) throws FormatterException {

        char symbolEndOfString;
        char indentSymbol;
        int indentSize;

        if (formatOptions == null) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn("Null format options find, using default parameters");
            }
            symbolEndOfString = SYMBOL_END_OF_STRING;
            indentSymbol = INDENT_SYMBOL;
            indentSize = INDENT_SIZE;
        } else {
            symbolEndOfString = formatOptions.getSymbolEndOfString();
            indentSymbol = formatOptions.getTabSymbol();
            indentSize = formatOptions.getIndent();
        }

        boolean isNewString = false;
        boolean isShifting = false;
        boolean isAloneSpaceButton = false;
        char currentSymbol;
        char previousSymbol = '\0';

        try {
            while (!in.isEnd()) {
                currentSymbol = in.getSymbol();
                switch (currentSymbol) {
                    case ';':
                        out.writeSymbol(currentSymbol);
                        isShifting = true;
                        break;
                    case '{':
                        if ((previousSymbol != ' ') && (previousSymbol != '\0') && (previousSymbol != '{')) {
                            out.writeSymbol(indentSymbol);
                            out.writeSymbol(currentSymbol);
                        } else {
                            out.writeSymbol(currentSymbol);
                        }
                        isNewString = true;
                        increaseBrace();
                        out.writeSymbol(symbolEndOfString);
                        shiftNextString(out, indentSymbol, indentSize, braceLevel);
                        break;
                    case '}':
                        isNewString = true;
                        reduceBrace();
                        out.writeSymbol(symbolEndOfString);
                        shiftNextString(out, indentSymbol, indentSize, braceLevel);
                        out.writeSymbol(currentSymbol);
                        break;
                    case ' ':
                        if (!isNewString) {
                            if (isAloneSpaceButton) {
                                isAloneSpaceButton = false;
                                out.writeSymbol(currentSymbol);
                            }
                        }
                        break;
                    default:
                        isNewString = false;
                        isAloneSpaceButton = true;
                        out.writeSymbol(currentSymbol);
                        break;
                }

                previousSymbol = currentSymbol;
            }
            if (isShifting) {
                out.writeSymbol(symbolEndOfString);
            }
        } catch (StreamException e) {
            throw new FormatterException(e);
        }
    }
}
