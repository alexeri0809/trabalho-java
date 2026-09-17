package pt.exemplo.matriculas;

import pt.exemplo.matriculas.model.Marca;
import pt.exemplo.matriculas.model.Matricula;
import pt.exemplo.matriculas.model.Proprietario;
import pt.exemplo.matriculas.model.Veiculo;
import pt.exemplo.matriculas.service.GestaoMatriculas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final GestaoMatriculas gestao = new GestaoMatriculas();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Opção: ");
            switch (opcao) {
                case 1:
                    registarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    procurarPorMatricula();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("A sair. Até breve.");
                    break;
                default:
                    System.out.println("Opção inválida. Escolhe 0 a 4.");
            }
        } while (opcao != 0);

        in.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("        MATRÍCULAS DE CARRO");
        System.out.println("========================================");
        System.out.println("1. Registar veículo");
        System.out.println("2. Listar veículos");
        System.out.println("3. Procurar por matrícula");
        System.out.println("4. Remover veículo");
        System.out.println("0. Sair");
        System.out.println("========================================");
    }

    private static void registarVeiculo() {
        try {
            System.out.println();
            System.out.println("--- Novo registo ---");

            System.out.print("Matrícula (AA-00-AA): ");
            Matricula matricula = new Matricula(in.nextLine());

            System.out.print("Marca: ");
            Marca marca = Marca.fromString(in.nextLine());

            System.out.print("Modelo: ");
            String modelo = in.nextLine();

            int ano = lerInteiro("Ano: ");

            System.out.print("Nome do proprietário: ");
            String nome = in.nextLine();

            System.out.print("NIF (9 dígitos): ");
            String nif = in.nextLine();

            Proprietario dono = new Proprietario(nome, nif);
            Veiculo veiculo = new Veiculo(matricula, marca, modelo, ano, dono);
            gestao.registar(veiculo);

            System.out.println();
            System.out.println("Veículo registado com sucesso:");
            imprimirCabecalho();
            imprimirLinha(veiculo);
            imprimirSeparador();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarVeiculos() {
        List<Veiculo> lista = gestao.listar();
        System.out.println();
        if (lista.isEmpty()) {
            System.out.println("Não há veículos registados.");
            return;
        }

        imprimirCabecalho();
        for (Veiculo veiculo : lista) {
            imprimirLinha(veiculo);
        }
        imprimirSeparador();
        System.out.println("Total: " + lista.size() + " veículo(s)");
    }

    private static void procurarPorMatricula() {
        System.out.print("Matrícula a procurar: ");
        String codigo = in.nextLine();
        Optional<Veiculo> encontrado = gestao.procurar(codigo);

        System.out.println();
        if (encontrado.isPresent()) {
            System.out.println("Veículo encontrado:");
            imprimirCabecalho();
            imprimirLinha(encontrado.get());
            imprimirSeparador();
        } else {
            System.out.println("Matrícula não encontrada: " + codigo);
        }
    }

    private static void removerVeiculo() {
        System.out.print("Matrícula a remover: ");
        String codigo = in.nextLine();
        Optional<Veiculo> encontrado = gestao.procurar(codigo);

        if (!encontrado.isPresent()) {
            System.out.println("Matrícula não encontrada: " + codigo);
            return;
        }

        System.out.println();
        System.out.println("A remover:");
        imprimirCabecalho();
        imprimirLinha(encontrado.get());
        imprimirSeparador();

        gestao.remover(codigo);
        System.out.println("Removido.");
    }

    private static void imprimirCabecalho() {
        imprimirSeparador();
        System.out.printf("%-12s | %-12s | %-12s | %-6s | %-20s | %-9s%n",
                "MATRÍCULA", "MARCA", "MODELO", "ANO", "PROPRIETÁRIO", "NIF");
        imprimirSeparador();
    }

    private static void imprimirLinha(Veiculo v) {
        System.out.printf("%-12s | %-12s | %-12s | %-6d | %-20s | %-9s%n",
                v.getMatricula().getCodigo(),
                v.getMarca().name(),
                v.getModelo(),
                Integer.valueOf(v.getAno()),
                v.getProprietario().getNome(),
                v.getProprietario().getNif());
    }

    private static void imprimirSeparador() {
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static int lerInteiro(String prompt) {
        System.out.print(prompt);
        while (!in.hasNextInt()) {
            in.next();
            System.out.print("Introduz um número: ");
        }
        int valor = in.nextInt();
        in.nextLine();
        return valor;
    }
}