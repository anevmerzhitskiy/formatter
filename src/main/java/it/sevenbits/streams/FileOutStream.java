package it.sevenbits.streams;


import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by anton on 02/07/14.
 */
public class FileOutStream implements OutStream {
    PrintWriter printWriter;

    public FileOutStream(final String fileName) throws FileNotFoundException {
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            Logger LOG = Logger.getLogger(FileOutStream.class);
            LOG.fatal("Create stream error");
        }
    }

    public void writeSymbol(final char c) throws StreamException {
        printWriter.print(c);
    }


    public void close() throws StreamException {

    }
}
