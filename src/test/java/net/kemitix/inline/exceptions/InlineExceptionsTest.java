package net.kemitix.inline.exceptions;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class InlineExceptionsTest implements WithAssertions {

    @Test
    public void shouldThrowExceptionWhenShouldIsTrue() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .should(true))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void shouldNotThrowExceptionWhenShouldIsFalse() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .should(false))
                .doesNotThrowAnyException();
    }

    @Test
    public void shouldThrowExceptionWhenUnlessIsFalse() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .unless(false))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    public void shouldNotThrowExceptionWhenUnlessIsTrue() {
        assertThatCode(() ->
                InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                        .unless(true))
                .doesNotThrowAnyException();
    }

    @Test
    public void shouldThrowExceptionOnUtilityInstantiation() {
        assertThatCode(InlineExceptions::new)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldThrowInlineException() {
        assertThatCode(() -> InlineExceptions.doThrow(null, null))
                .isInstanceOf(InlineException.class);
    }
}
