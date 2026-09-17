package pt.exemplo.matriculas.exception;

public class MatriculaInvalidaException extends RuntimeException {

    public MatriculaInvalidaException(String codigo) {
        super(codigo == null 
            ? "Matrícula não pode ser nula" 
            : "Matrícula inválida: " + codigo);
    }
}