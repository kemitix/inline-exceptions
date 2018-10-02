package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class InlineExceptionsTest implements WithAssertions {

    @Test
    public void whenShouldIsTrue_ThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(true))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenShouldIsFalse_ThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(false))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenUnlessIsFalse_ThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(false))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenUnlessIsTrue_ThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(true))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenSourceIsNull_ThenShouldThrowError() {
        assertThatCode(() ->
                InlineExceptions.doThrow(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("source");
    }

}
