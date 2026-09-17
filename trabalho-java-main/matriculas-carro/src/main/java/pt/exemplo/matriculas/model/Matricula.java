package pt.exemplo.matriculas.model;

import pt.exemplo.matriculas.exception.MatriculaInvalidaException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Matricula {

    private static final Pattern FORMATO_ATUAL = Pattern.compile("^[A-Z]{2}-\\d{2}-[A-Z]{2}$");
    private static final Pattern FORMATO_00_AA_00 = Pattern.compile("^\\d{2}-[A-Z]{2}-\\d{2}$");
    private static final Pattern FORMATO_AA_00_00 = Pattern.compile("^[A-Z]{2}-\\d{2}-\\d{2}$");

    private final String codigo;

    public Matricula(String codigo) {
        if (codigo == null) {
            throw new MatriculaInvalidaException(null);
        }
        String normalizada = codigo.trim().toUpperCase();
        if (!isValida(normalizada)) {
            throw new MatriculaInvalidaException(codigo);
        }
        this.codigo = normalizada;
    }

    public static boolean isValida(String codigo) {
        if (codigo == null) {
            return false;
        }
        String valor = codigo.trim().toUpperCase();
        return FORMATO_ATUAL.matcher(valor).matches()
                || FORMATO_00_AA_00.matcher(valor).matches()
                || FORMATO_AA_00_00.matcher(valor).matches();
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matricula that)) {
            return false;
        }
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo;
    }
}