package pl.mariuszk.productmanager.exception;

public class DictionaryNotFoundException extends Exception {

    public DictionaryNotFoundException() {
        super();
    }

    public DictionaryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
