package it.academy.exception;

public class MyNotFoundException extends Exception{

    public MyNotFoundException(String message) {
        super(message);
    }

    public MyNotFoundException() {
        super();
    }
}
