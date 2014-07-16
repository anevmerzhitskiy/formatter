package it.sevenbits.codeformatter;

import it.sevenbits.streams.StreamException;

/**
 *
 * Created by anton on 02/07/14.
 */
public class FormatterException extends Exception {

    FormatterException() {}
    FormatterException(final String message) {}
    //FormatterException(String message, Throwable cause){}
    public FormatterException(final StreamException e) {}

}
