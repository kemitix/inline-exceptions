package net.kemitix.inline.exceptions;

/**
 * Represents an error using {@link InlineExceptions}.
 *
 * @author pcampbell
 */
public class InlineException extends Exception {

    /**
     * Creates an {@code InlineException}.
     *
     * @param cause the original exception
     */
    public InlineException(final Exception cause) {
        super(cause);
    }

}
