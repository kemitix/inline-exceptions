package net.kemitix.inline.exceptions;

import org.junit.Test;

/**
 * Tests for {@link InlineExceptions} and {@link InlineExceptions.InlineClause}.
 *
 * @author pcampbell
 */
public class InlineExceptionsTest {

    /**
     * Test that exception is thrown for
     * {@link InlineExceptions.InlineClause#should() should(true)}.
     *
     * @throws InlineException                if there is an error in the test
     * @throws ArrayIndexOutOfBoundsException is test passes
     * @throws Exception                      if the test passes (specifically
     *                                        ArrayIndexOufOfBoundsException)
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenShouldIsTrue()
            throws InlineException, ArrayIndexOutOfBoundsException, Exception {
        //when
        InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                .should(true);
    }

    /**
     * Test that exception is not thrown for
     * {@link InlineExceptions.InlineClause#should() should(false)}.
     *
     * @throws InlineException                if there is an error in the test
     * @throws ArrayIndexOutOfBoundsException is test fails
     * @throws Exception                      if the test fails (specifically
     *                                        ArrayIndexOufOfBoundsException)
     */
    @Test
    public void shouldNotThrowExceptionWhenShouldIsFalse()
            throws InlineException, ArrayIndexOutOfBoundsException, Exception {
        //when
        InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                .should(false);
        //then
        // no exception thrown is a pass
    }

    /**
     * Test that exception is thrown for
     * {@link InlineExceptions.InlineClause#unless() unless(false)}.
     *
     * @throws InlineException                if there is an error in the test
     * @throws ArrayIndexOutOfBoundsException is test passes
     * @throws Exception                      if the test passes (specifically
     *                                        ArrayIndexOufOfBoundsException)
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenUnlessIsFalse()
            throws InlineException, ArrayIndexOutOfBoundsException, Exception {
        //when
        InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                .unless(false);
    }

    /**
     * Test that exception is not thrown for
     * {@link InlineExceptions.InlineClause#unless() unless(true)}.
     *
     * @throws InlineException                if there is an error in the test
     * @throws ArrayIndexOutOfBoundsException is test fails
     * @throws Exception                      if the test fails (specifically
     *                                        ArrayIndexOufOfBoundsException)
     */
    @Test
    public void shouldNotThrowExceptionWhenUnlessIsTrue()
            throws InlineException, ArrayIndexOutOfBoundsException, Exception {
        //when
        InlineExceptions.doThrow(ArrayIndexOutOfBoundsException.class, "pass")
                .unless(true);
        //then
        // no exception thrown is a pass
    }

    /**
     * Test that exception is thrown when trying to instantiate a utility class.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionOnUtilityInstantiation() {
        //when
        InlineExceptions failed = new InlineExceptions();
        //then
        // failed if we didn't throw an exception
    }

    /**
     * Test that attempting to create an invalid clause will throw an
     * {@link InlineException}.
     *
     * @throws InlineException if test passes
     */
    @Test(expected = InlineException.class)
    public void shouldThrowInlineException() throws InlineException {
        //when
        InlineExceptions.doThrow(null, null);
    }
}
