package pt.exemplo.matriculas.service;

import pt.exemplo.matriculas.model.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GestaoMatriculas {

    private final List<Veiculo> veiculos = new ArrayList<>();

    public void registar(Veiculo veiculo) {
        Objects.requireNonNull(veiculo, "O veículo é obrigatório.");
        boolean existe = veiculos.stream()
                .anyMatch(v -> v.getMatricula().equals(veiculo.getMatricula()));
        if (existe) {
            throw new IllegalArgumentException(
                    "Já existe um veículo com a matrícula " + veiculo.getMatricula());
        }
        veiculos.add(veiculo);
    }

    public List<Veiculo> listar() {
        return Collections.unmodifiableList(veiculos);
    }

    public Optional<Veiculo> procurar(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return Optional.empty();
        }
        String alvo = codigo.trim().toUpperCase();
        return veiculos.stream()
                .filter(v -> v.getMatricula().getCodigo().equals(alvo))
                .findFirst();
    }

    public boolean remover(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return false;
        }
        String alvo = codigo.trim().toUpperCase();
        return veiculos.removeIf(v -> v.getMatricula().getCodigo().equals(alvo));
    }
}