package it.sevenbits;

import it.sevenbits.codeformatter.FormatterException;
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
            FileInStream fis;
            FileOutStream fos;
            CodeFormatter codeFormatter = new CodeFormatter();
            FormatOptions formatOptions = new FormatOptions();
            try {
                fis = new FileInStream(args[0]);
                fos = new FileOutStream(args[1]);
                codeFormatter.format(fis, fos, formatOptions);
            } catch (FormatterException e) {
                if (LOGGER.isEnabledFor(Level.ERROR)) {
                    LOGGER.error(e.getMessage());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.error("Parameters: input_file_path output_file_path");
        }
    }
}
