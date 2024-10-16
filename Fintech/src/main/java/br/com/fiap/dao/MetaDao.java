package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Meta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetaDao {

    private Connection conexao;

    public MetaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Meta meta) throws SQLException {
        String sql = "INSERT INTO t_meta (cd_meta, ds_meta, vl_meta, dt_meta) VALUES (seq_meta.nextval, ?, ?, ?)";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, meta.getDescricao());
            stm.setDouble(2, meta.getValor());
            // Usar Timestamp para LocalDateTime
            stm.setTimestamp(3, Timestamp.valueOf(meta.getData()));
            stm.executeUpdate();
        }
    }

    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    public Meta pesquisar(long codigo) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM t_meta WHERE cd_meta = ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, codigo);
            try (ResultSet result = stm.executeQuery()) {
                if (!result.next()) {
                    throw new EntidadeNaoEncontradaException("Produto não encontrado");
                }
                return parseMeta(result);
            }
        }
    }

    private Meta parseMeta(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_meta");
        String descricao = result.getString("ds_meta");
        double valor = result.getDouble("vl_meta");
        LocalDateTime data = result.getTimestamp("dt_meta").toLocalDateTime();
        return new Meta(id, descricao, valor, data);
    }

    public List<Meta> listar() throws SQLException {
        List<Meta> lista = new ArrayList<>();
        String sql = "SELECT * FROM t_meta";
        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {
            while (result.next()) {
                lista.add(parseMeta(result));
            }
        }
        return lista;
    }

    public void atualizar(Meta meta) throws SQLException {
        String sql = "UPDATE t_meta SET ds_meta = ?, vl_meta = ?, dt_meta = ? WHERE cd_meta = ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, meta.getDescricao());
            stm.setDouble(2, meta.getValor());
            stm.setTimestamp(3, Timestamp.valueOf(meta.getData()));
            stm.setLong(4, meta.getCodigo()); // Corrigido para definir o codigo
            stm.executeUpdate();
        }
    }


    public void remover(long codigo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_meta where cd_meta = ?");
        stm.setLong(4, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Meta não encontrada para ser removido");
    }

}


