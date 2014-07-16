package it.sevenbits.streams;


import java.io.IOException;

/**
* input data stream
*/
public interface InStream {

    /**
     * @return current symbol
     * @throws StreamException
     */
    char getSymbol() throws StreamException;

    /**
     * @throws StreamException
     */
    void close() throws StreamException, IOException;

    /**
     * @return true, if end of file
     */
    boolean isEnd() throws StreamException;
}
