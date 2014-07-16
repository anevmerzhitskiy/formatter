package it.sevenbits.streams;

import java.io.IOException;

/**
 * Created by anton on 02/07/14.
 */
public class StreamException extends Exception {
    StreamException() {}
    StreamException(final String message) {}
    StreamException(final IOException e) {}
}
