INLINE-EXCEPTIONS
=================

Optionally throw exceptions without adding an if-then branch that affects code
coverage.

Provides a method of throwing exceptions on certain conditions without adding an
if-branch to the calling method.

    import net.kemitix.inline.exceptions.InlineExceptions.doThrow;
    ...
    doThrow(MyException.class, "List is empty").should(list.isEmpty());
    doThrow(MyException.class, "List is too big").unless(list.size() < 10);
 