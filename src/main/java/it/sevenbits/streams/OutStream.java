package it.sevenbits.streams;


/**
 *
 */
public interface OutStream {

    /**
     * @param c symbol, which must be written
     * @throws StreamException
     * @throws StreamException
     */
    void writeSymbol(char c) throws StreamException;

    /**
     * @throws StreamException
     */
    void close() throws StreamException;
}
