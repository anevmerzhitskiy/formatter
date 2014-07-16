package it.sevenbits.streams;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 */
public class FileInStream implements InStream {

    private FileInputStream in;
    private File file;

    public FileInStream(final String fileName) throws FileNotFoundException {
        file = new File(fileName);
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Logger LOG = Logger.getLogger(String.valueOf(FileInStream.class));
            //LOG.fatal("Create stream error");
        }
    }

    public char getSymbol() throws StreamException {

        char temp = 0;
        try {
            temp = (char) in.read();
        } catch (IOException e) {
            throw new StreamException();
        }
        return temp;
    }


    public void close() throws StreamException {
        //in.close();
    }


    public boolean isEnd() {
        return true;
    }
}
