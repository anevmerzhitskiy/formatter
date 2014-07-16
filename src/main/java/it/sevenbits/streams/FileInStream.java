package it.sevenbits.streams;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class FileInStream implements InStream {

    private FileInputStream in;
    private File file;

    public FileInStream(final String fileName) throws StreamException {
        file = new File(fileName);
        try {
            in = new FileInputStream(file);
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }

    public char getSymbol() throws StreamException {

        char temp;
        try {
            temp = (char) in.read();
        } catch (IOException e) {
            throw new StreamException();
        }
        return temp;
    }

    public void close() throws StreamException, IOException {
        in.close();
    }

    public boolean isEnd() throws StreamException {
        try {
            return in.available() < 1;
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }
}
