package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;

import java.sql.SQLException;

public class RemocaoMetaView {

    public static void main(String[] args) {
        try {
            MetaDao dao = new MetaDao();
            dao.remover(5);
            System.out.println("Meta Removido!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Meta não encontrado");
        }
    }
}