package com.example.technovium.util.enums;

public enum MetodoPago {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta");

    private final String value;

    MetodoPago(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static MetodoPago fromValue(String value) {
        for (MetodoPago metodo : MetodoPago.values()) {
            if (metodo.value.equalsIgnoreCase(value)) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Método de pago inválido: " + value);
    }
}
