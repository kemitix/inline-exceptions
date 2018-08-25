/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.inline.exceptions;

/**
 * Provides a method of throwing exceptions on certain conditions without adding
 * an if-branch to the calling method.
 * <pre>
 * import net.kemitix.inline.exceptions.InlineExceptions.doThrow;
 *
 * ...
 *
 * doThrow(MyException.class, "List is empty").should(list.isEmpty());
 * doThrow(MyException.class, "List is too big").unless(list.size() &lt; 10);
 * </pre>
 *
 * @author pcampbell
 */
public class InlineExceptions {

    /**
     * Don't instantiate utility class.
     */
    protected InlineExceptions() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an inline clause that will throw in instance of the supplied
     * class with the message.
     *
     * @param throwable the throwable class
     * @param message   the message to add the the throwable
     *
     * @return the inline clause
     *
     * @throws InlineException if error configuring {@link InlineClause}
     */
    @SuppressWarnings("illegalcatch")
    public static InlineClause doThrow(
            final Class<? extends Exception> throwable,
            final String message) throws InlineException {
        try {
            return new InlineClause(
                    throwable.getConstructor(String.class)
                    .newInstance(message));
        } catch (Exception ex) {
            throw new InlineException(ex);
        }
    }

    /**
     * An inline clause that will throw an exception if the clause matches.
     */
    public static class InlineClause {

        /**
         * The exception that could be thrown.
         */
        private final Exception contingency;

        /**
         * Creates an inline clause.
         *
         * @param throwable the exception that would be thrown
         */
        public InlineClause(final Exception throwable) {
            contingency = throwable;
        }

        /**
         * Checks the clause and throws the exception is if matches.
         *
         * @param be the condition to check
         *
         * @throws java.lang.Exception if the clause is true
         */
        public void should(final boolean be) throws Exception {
            if (be) {
                throw contingency;
            }
        }

        /**
         * Checks the clause and throws the exception is if does not match.
         *
         * @param be the condition to check
         *
         * @throws java.lang.Exception if the clause is true
         */
        public void unless(final boolean be) throws Exception {
            should(!be);
        }

    }

}
