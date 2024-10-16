package br.com.fiap.model;

import java.time.LocalDateTime;

public class Meta {
    private Long codigo;
    private String descricao;
    private Double valor;
    private LocalDateTime data;

    // Construtor com todos os parâmetros
    public Meta(Long codigo, String descricao, Double valor, LocalDateTime data) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Construtor com descrição, valor e data
    public Meta(String descricao, Double valor, LocalDateTime data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    // Corrigido para aceitar LocalDateTime
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}







