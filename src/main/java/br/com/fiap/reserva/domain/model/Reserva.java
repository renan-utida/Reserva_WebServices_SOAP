package br.com.fiap.reserva.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reserva {

    private int id;
    private int equipamentoId;
    private String responsavel;
    private StatusReserva status;

    public Reserva() {}

    public Reserva(int id, int equipamentoId, String responsavel) {
        this.id = id;
        this.equipamentoId = equipamentoId;
        this.responsavel = responsavel;
        this.status = StatusReserva.ATIVA;
    }

    public void cancelar() {
        if (this.status == StatusReserva.CANCELADA) {
            throw new RuntimeException("Reserva já cancelada");
        }
        this.status = StatusReserva.CANCELADA;
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

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}