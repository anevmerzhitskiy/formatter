package it.sevenbits.codeformatter;

/**
 *
 * Created by anton on 04/07/14.
 */
public class FormatOptions {

    private int indentSize;
    private char endOfString;
    private char tabSymbol;

    public FormatOptions() {
        indentSize = 4;
        endOfString = '\n';
        tabSymbol = ' ';
    }

    public FormatOptions(final int indentSize, final boolean useSpaces) {
        this.indentSize = indentSize;
        this.endOfString = '\n';
        if (useSpaces) {
            this.tabSymbol = ' ';
        } else {
            this.tabSymbol = '\t';
        }
    }

    public int getIndent() {
        return indentSize;
    }

    public char getTabSymbol() {
        return tabSymbol;
    }

    public char getSymbolEndOfString() {
        return endOfString;
    }

    public void useTabs() {
        tabSymbol = '\t';
    }

    public void useSpaces() {
        tabSymbol = ' ';
    }

    public void setIndentSize(final int count) {
        indentSize = count;
    }
}
