package br.com.fiap.soap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reserva {

    private int id;
    private int equipamentoId;
    private String responsavel;
    private String status; // "ATIVA" ou "CANCELADA"

    public Reserva() {}

    public Reserva(int id, int equipamentoId, String responsavel, String status) {
        this.id = id;
        this.equipamentoId = equipamentoId;
        this.responsavel = responsavel;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}