package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.model.Meta;

import java.sql.SQLException;
import java.util.List;

public class ListagemMetaView {


    public static void main(String[] args) {
        try {
            MetaDao dao = new MetaDao();
            List<Meta> metas = dao.listar();
            for (Meta meta : metas) {
                System.out.println(meta.getCodigo() + " " + meta.getDescricao() + ", R$ " + meta.getValor() + ", " + meta.getData());
            }
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

//git add .
//git commit-m"Atividadecap11"
//git push
//git push --set-upstream
