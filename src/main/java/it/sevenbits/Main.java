package it.sevenbits;

import it.sevenbits.codeformatter.FormatterException;
import it.sevenbits.streams.StreamException;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import it.sevenbits.codeformatter.CodeFormatter;
import it.sevenbits.codeformatter.FormatOptions;
import it.sevenbits.streams.FileInStream;
import it.sevenbits.streams.FileOutStream;

import java.io.FileNotFoundException;


final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main(){}


    public static void main(final String[] args) {
        if (args.length > 1) {
            FileInStream in;
            FileOutStream out;
            CodeFormatter codeFormatter = new CodeFormatter();
            FormatOptions formatOptions = new FormatOptions();
            try {
                in = new FileInStream(args[0]);
                out = new FileOutStream(args[1]);
                codeFormatter.format(in, out, formatOptions);
            } catch (FormatterException e) {
                if (LOGGER.isEnabledFor(Level.ERROR)) {
                    LOGGER.error(e.getMessage());
                }
            } catch (StreamException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.error("Stream error");
        }
    }
}
