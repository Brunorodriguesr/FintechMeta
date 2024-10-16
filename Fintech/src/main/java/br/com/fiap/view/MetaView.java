package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException; // Corrigido o nome da exceção
import br.com.fiap.model.Meta;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MetaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MetaDao dao;
        System.out.println("Bem-vindo ao FIAP Fintech!");
        try {
            dao = new MetaDao();
            int escolha;
            do {
                System.out.println("Escolha uma opção: \n1-Cadastrar \n2-Pesquisar por Código \n3-Listar \n4-Atualizar \n5-Remover \n0-Sair");

                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    case 2:
                        pesquisarMeta(scanner, dao);
                        break;
                    case 3:
                        listar(dao);
                        break;
                    case 4:
                        atualizar(scanner, dao);
                        break;
                    case 5:
                        removerMeta(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static void cadastrar(Scanner scanner, MetaDao dao) {
        System.out.println("Digite o código da meta:");
        long codigo = scanner.nextLong(); // Leitura do código como long
        scanner.nextLine(); // Limpar o buffer
        System.out.println("Digite a descrição da meta:");
        String descricao = scanner.nextLine();
        System.out.println("Digite o valor da meta:");
        double valor = scanner.nextDouble();
        System.out.println("Digite a data da meta (AAAA-MM-DD HH:MM):");
        String dataInput = scanner.next(); // Suponha que a data é passada como string

        LocalDateTime data = LocalDateTime.parse(dataInput); // Converter a string em LocalDateTime

        Meta novaMeta = new Meta(codigo, descricao, valor, data);
        try {
            dao.cadastrar(novaMeta);
            System.out.println("Meta cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar META: " + e.getMessage());
        }
    }

    private static void pesquisarMeta(Scanner scanner, MetaDao dao) {
        System.out.println("Digite o código da meta:");
        long codigo = scanner.nextLong();

        try {
            Meta meta = dao.pesquisar(codigo);
            System.out.println("Meta encontrada:");
            System.out.println(meta.getCodigo() + " - " + meta.getDescricao() + ", R$" + meta.getValor() + " - Data: " + meta.getData());
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao pesquisar meta: " + e.getMessage());
        }
    }

    private static void listar(MetaDao dao) {
        try {
            List<Meta> metas = dao.listar();
            System.out.println("Lista de metas:");
            for (Meta meta : metas) {
                System.out.println(meta.getCodigo() + " - " + meta.getDescricao() + ", R$" + meta.getValor() + " - Data: " + meta.getData());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar metas: " + e.getMessage());
        }
    }

    private static void atualizar(Scanner scanner, MetaDao dao) {
        System.out.println("Digite o código da meta que deseja atualizar:");
        long codigo = scanner.nextLong();
        try {
            Meta meta = dao.pesquisar(codigo);
            System.out.println("Digite o novo código da meta:");
            long novoCodigo = scanner.nextLong();
            scanner.nextLine(); // Limpar o buffer
            System.out.println("Digite a nova descrição da meta:");
            String descricao = scanner.nextLine();
            System.out.println("Digite o novo valor da meta:");
            double valor = scanner.nextDouble();
            System.out.println("Digite a nova data da meta (AAAA-MM-DD HH:MM):");
            String dataInput = scanner.next(); // Supondo que a data é passada como string

            LocalDateTime novaData = LocalDateTime.parse(dataInput); // Converter a string em LocalDateTime

            meta.setCodigo(novoCodigo);
            meta.setDescricao(descricao);
            meta.setValor(valor);
            meta.setData(novaData);
            dao.atualizar(meta);
            System.out.println("Meta atualizada com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao atualizar meta: " + e.getMessage());
        }
    }

    private static void removerMeta(Scanner scanner, MetaDao dao) {
        System.out.println("Digite o código da meta que deseja remover:");
        long codigo = scanner.nextLong();
        try {
            dao.remover(codigo);
            System.out.println("Meta removida com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao remover meta: " + e.getMessage());
        }
    }
}



