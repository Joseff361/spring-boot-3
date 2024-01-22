package com.luv2code.demo.rest;

// All the exceptions which derive from RuntimeException are referred to as unchecked exceptions.
// And all the other exceptions are checked exceptions.
// A checked exception must be caught somewhere in your code, otherwise, it will not compile.
// On the other hand, with unchecked exceptions, the calling method is under no obligation to handle or declare it.
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
