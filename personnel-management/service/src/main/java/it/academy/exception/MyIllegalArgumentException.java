package it.academy.exception;

public class MyIllegalArgumentException extends Exception{

    public MyIllegalArgumentException() {
        super();
    }

    public MyIllegalArgumentException(String message) {
        super(message);
    }
}
