package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.model.Meta;

import java.sql.SQLException;

public class AtualizacaoMetaView {


    public static void main(String[] args) {
        try {
            MetaDao dao = new MetaDao();
            Meta meta = dao.pesquisar(4);
            meta.setDescricao("Carro");
            meta.setValor(Double.valueOf("1000"));
            dao.atualizar(meta);
            dao.fecharConexao();
            System.out.println("Meta atualizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Meta não encontrada");
        }
    }
}
