package com.example.technovium.util.enums;

public enum MetodoPago {
    OXXO(1, "oxxo", "1234567890123456"),
    WALMART(2, "walmart", "2345678901234567"),
    SEVEN_ELEVEN(3, "7eleven", "3456789012345678"),
    SPEI_1(4, "spei", "4567890123456789"),
    SPEI_2(5, "spei", "5678901234567890");

    private final int id;
    private final String descripcion;
    private final String referencia;

    MetodoPago(int id, String descripcion, String referencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.referencia = referencia;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    // Método para obtener un método de pago por su ID
    public static MetodoPago getById(int id) {
        for (MetodoPago metodo : values()) {
            if (metodo.id == id) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("ID no válido: " + id);
    }
}
