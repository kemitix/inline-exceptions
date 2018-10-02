package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class InlineExceptionsTest implements WithAssertions {

    @Test
    public void whenShouldIsTrue_ThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .should(true))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenShouldIsFalse_ThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .should(false))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenUnlessIsFalse_ThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .unless(false))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenUnlessIsTrue_ThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .unless(true))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenThrowableIsNull_ThenShouldThrowError() {
        assertThatCode(() -> InlineExceptions.doThrow(null, "message"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("throwable");
    }

    @Test
    public void whenMessageIsNull_ThenShouldThrowError() {
        assertThatCode(() -> InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("message");
    }
}
