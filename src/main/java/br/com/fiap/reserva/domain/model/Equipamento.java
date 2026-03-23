package br.com.fiap.reserva.domain.model;

public class Equipamento {

    private int id;
    private String nome;
    private String descricao;
    private boolean disponivel;

    public Equipamento(int id, String nome, String descricao, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void indisponibilizar() {
        this.disponivel = false;
    }

    public void disponibilizar() {
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }
}
