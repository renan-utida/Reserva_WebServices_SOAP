package br.com.fiap.reserva.application.dto;

import br.com.fiap.reserva.domain.model.Reserva;

public class ReservaResponse {

    private Reserva reserva;
    private String erro;

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

    public String getErro() {
        return erro;
    }
}
