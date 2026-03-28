package br.com.fiap.reserva.application.dto;

import br.com.fiap.reserva.domain.model.Reserva;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaResponse {

    private Reserva reserva;
    private String erro;

    public ReservaResponse() {}

    public static ReservaResponse sucesso(Reserva reserva) {
        ReservaResponse r = new ReservaResponse();
        r.reserva = reserva;
        return r;
    }

    public static ReservaResponse erro(String mensagem) {
        ReservaResponse r = new ReservaResponse();
        r.erro = mensagem;
        return r;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
