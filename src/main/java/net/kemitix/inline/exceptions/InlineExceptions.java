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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;

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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("hideutilityclassconstructor")
public class InlineExceptions {

    /**
     * Creates an inline clause that will throw in instance of the supplied
     * class with the message.
     *
     * @param throwable the throwable class
     * @param message   the message to add the the throwable
     *
     * @return the inline clause
     *
     * @throws NoSuchMethodException if the throwable lacks a constructor that takes a single String parameter
     * @throws IllegalAccessException if the class or constructor can not be seen from this context
     * @throws InvocationTargetException if the constructor threw an exception
     * @throws InstantiationException if the exception can not be instantiated
     */
    public static InlineClause doThrow(
            @NonNull final Class<? extends Exception> throwable,
            @NonNull final String message
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            return new InlineClause<>(throwable.getConstructor(String.class).newInstance(message));
    }

    /**
     * An inline clause that will throw an exception if the clause matches.
     *
     * @param <T> the type of exception
     */
    public static class InlineClause<T extends Throwable> {

        /**
         * The exception that could be thrown.
         */
        private final T contingency;

        /**
         * Creates an inline clause.
         *
         * @param throwable the exception that would be thrown
         */
        InlineClause(final T throwable) {
            contingency = throwable;
        }

        /**
         * Checks the clause and throws the exception is if matches.
         *
         * @param be the condition to check
         *
         * @throws T if the clause is true
         */
        public void should(final boolean be) throws T {
            if (be) {
                throw contingency;
            }
        }

        /**
         * Checks the clause and throws the exception is if does not match.
         *
         * @param be the condition to check
         *
         * @throws T if the clause is true
         */
        public void unless(final boolean be) throws T {
            should(!be);
        }

    }

}
