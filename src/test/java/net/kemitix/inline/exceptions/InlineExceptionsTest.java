package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class InlineExceptionsTest implements WithAssertions {

    @Test
    void whenShouldIsTrueThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(true))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void whenShouldIsFalseThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .should(false))
                .doesNotThrowAnyException();
    }

    @Test
    void whenUnlessIsFalseThenThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(false))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void whenUnlessIsTrueThenDoNotThrowSubject() {
        assertThatCode(() ->
                InlineExceptions.doThrow(() -> new ArrayIndexOutOfBoundsException("pass"))
                        .unless(true))
                .doesNotThrowAnyException();
    }

    @Test
    void whenSourceIsNullThenShouldThrowError() {
        assertThatCode(() ->
                InlineExceptions.doThrow(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("source");
    }

}
