package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;

import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.model.Meta;

import java.sql.SQLException;

public class PesquisaMetaPorIdView {


    public static void main(String[] args) {
        try {
            MetaDao dao = new MetaDao();
            Meta meta = dao.pesquisar(4);
            System.out.println(meta.getCodigo() + " " + meta.getDescricao() + ", " + meta.getValor() + ", " + meta.getData());
            System.out.println("R$ " + meta.getValor() + ", " + meta.getData());
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Código não existe na tabela");
        }
    }
}
