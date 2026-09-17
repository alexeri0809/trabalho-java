package pt.exemplo.matriculas.model;

import java.util.Objects;

public class Proprietario {

    private final String nif;
    private String nome;

    public Proprietario(String nome, String nif) {
        setNome(nome);
        this.nif = validarNif(nif);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do proprietário é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public String getNif() {
        return nif;
    }

    private static String validarNif(String nif) {
        if (nif == null || !nif.trim().matches("\\d{9}")) {
            throw new IllegalArgumentException("NIF inválido. Deve ter 9 dígitos.");
        }
        return nif.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proprietario that)) {
            return false;
        }
        return nif.equals(that.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }

    @Override
    public String toString() {
        return nome + " (NIF " + nif + ")";
    }
}