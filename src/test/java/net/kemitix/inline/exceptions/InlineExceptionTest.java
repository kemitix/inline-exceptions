package net.kemitix.inline.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link InlineException}.
 *
 * @author pcampbell
 */
public class InlineExceptionTest {

    /**
     * Class under test.
     */
    private InlineException inlineException;

    /**
     * Test constructor with a null cause.
     */
    @Test
    public void testNullCause() {
        //given
        Exception cause = null;
        //when
        inlineException = new InlineException(cause);
        //then
        assertNull(inlineException.getCause());
    }

    /**
     * Test constructor with a non-null cause.
     */
    @Test
    public void testNonNullCause() {
        //given
        Exception cause = new ArrayIndexOutOfBoundsException();
        //when
        inlineException = new InlineException(cause);
        //then
        assertEquals(cause, inlineException.getCause());
    }

}
