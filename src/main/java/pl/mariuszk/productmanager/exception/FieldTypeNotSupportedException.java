package pl.mariuszk.productmanager.exception;

public class FieldTypeNotSupportedException extends Exception{

    public FieldTypeNotSupportedException() {
        super();
    }

    public FieldTypeNotSupportedException(String message) {
        super(message);
    }
}
