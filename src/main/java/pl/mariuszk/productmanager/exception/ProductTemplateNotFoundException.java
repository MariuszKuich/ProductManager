package pl.mariuszk.productmanager.exception;

public class ProductTemplateNotFoundException extends Exception {

    public ProductTemplateNotFoundException() {
        super();
    }

    public ProductTemplateNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
