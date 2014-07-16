package it.sevenbits.streams;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by anton on 02/07/14.
 */
public class FileOutStream implements OutStream {

    private FileOutputStream fileOutputStream;

    public FileOutStream(final String fileName) throws StreamException {
        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (final FileNotFoundException e) {
            throw new StreamException(e);
        }
    }

    public void writeSymbol(final char c) throws StreamException {
        try {
            fileOutputStream.write(c);
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }


    public void close() throws StreamException {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }
}
