package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InlineExceptionTest implements WithAssertions {

    @Test
    public void testNullCause() {
        //given
        final Exception cause = null;
        //when
        final InlineException inlineException = new InlineException(cause);
        //then
        assertThat(inlineException.getCause()).isNull();
    }

    @Test
    public void testNonNullCause() {
        //given
        final Exception cause = new ArrayIndexOutOfBoundsException();
        //when
        final InlineException inlineException = new InlineException(cause);
        //then
        assertThat(inlineException.getCause()).isSameAs(cause);
    }

}
