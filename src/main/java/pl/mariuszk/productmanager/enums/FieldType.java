package pl.mariuszk.productmanager.enums;

public enum FieldType {
    STRING("Napis"),
    LONG("Liczba całkowita"),
    DOUBLE("Liczba zmiennoprzecinkowa");

    private String label;

    FieldType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}