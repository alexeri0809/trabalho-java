package pt.exemplo.matriculas.model;

import java.time.Year;
import java.util.Objects;

public class Veiculo {

    private static final int ANO_MINIMO = 1886;

    private final Matricula matricula;
    private Marca marca;
    private String modelo;
    private int ano;
    private Proprietario proprietario;

    public Veiculo(Matricula matricula, Marca marca, String modelo, int ano, Proprietario proprietario) {
        this.matricula = Objects.requireNonNull(matricula, "A matrícula é obrigatória.");
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setProprietario(proprietario);
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = Objects.requireNonNull(marca, "A marca é obrigatória.");
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("O modelo é obrigatório.");
        }
        this.modelo = modelo.trim();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        int anoMaximo = Year.now().getValue() + 1;
        if (ano < ANO_MINIMO || ano > anoMaximo) {
            throw new IllegalArgumentException(
                    "Ano inválido. Deve estar entre " + ANO_MINIMO + " e " + anoMaximo + ".");
        }
        this.ano = ano;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = Objects.requireNonNull(proprietario, "O proprietário é obrigatório.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Veiculo that)) {
            return false;
        }
        return matricula.equals(that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return matricula + " | " + marca + " " + modelo + " (" + ano + ") — " + proprietario;
    }
}