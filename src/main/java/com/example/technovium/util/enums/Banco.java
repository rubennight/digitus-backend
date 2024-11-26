package com.example.technovium.util.enums;

public enum Banco {
    BBVA("BBVA"),
    SANTANDER("Santander"),
    BANORTE("Banorte"),
    HSBC("HSBC"),
    CITIBANAMEX("Citibanamex"),
    SCOTIABANK("Scotiabank");

    private final String value;

    Banco(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    // Método para convertir de String a Banco
    public static Banco fromValue(String value) {
        for (Banco banco : Banco.values()) {
            if (banco.value.equalsIgnoreCase(value)) {
                return banco;
            }
        }
        throw new IllegalArgumentException("Banco inválido: " + value);
    }
}

