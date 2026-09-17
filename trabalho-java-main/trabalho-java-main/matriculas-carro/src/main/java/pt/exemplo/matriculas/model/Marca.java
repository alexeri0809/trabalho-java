package pt.exemplo.matriculas.model;

public enum Marca {
    TOYOTA,
    BMW,
    RENAULT,
    MERCEDES,
    VOLKSWAGEN,
    FORD,
    PEUGEOT,
    CITROEN,
    AUDI,
    FIAT,
    HONDA,
    NISSAN,
    HYUNDAI,
    KIA,
    OPEL,
    SEAT,
    SKODA,
    VOLVO,
    TESLA;

    public static Marca fromString(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("A marca não pode ser vazia.");
        }
        try {
            return Marca.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Marca desconhecida: " + texto + ". Valores: " + java.util.Arrays.toString(values()));
        }
    }
}