package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Meta;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CadastroMetaView {

    public static void main(String[] args) {
        try {
            Connection conexao = ConnectionFactory.getConnection();
            Statement stm = conexao.createStatement();
            stm.executeUpdate("INSERT INTO t_meta (cd_meta, cd_usuario, ds_meta, vl_meta, dt_meta) VALUES (seq_meta.nextval, '10', 'Casa', '1000', '01022025')");
            System.out.println("Meta cadastrada com sucesso!");
            stm.close();
            conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
