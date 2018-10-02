package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class InlineExceptionsTest implements WithAssertions {

    @Test
    public void whenShouldIsTrueThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(true))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenShouldIsFalseThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(false))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenUnlessIsFalseThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(false))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void whenUnlessIsTrueThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(true))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenSourceIsNullThenShouldThrowError() {
        assertThatCode(() ->
                InlineExceptions.doThrow(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("source");
    }

}
