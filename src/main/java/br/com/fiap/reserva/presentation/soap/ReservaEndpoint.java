package br.com.fiap.reserva.presentation.soap;

import br.com.fiap.reserva.application.dto.ReservaResponse;
import br.com.fiap.reserva.application.service.ReservaApplicationService;
import br.com.fiap.reserva.infrastructure.repository.memory.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ReservaEndpoint {

    private final ReservaApplicationService service;

    public ReservaEndpoint() {
        var reservaRepo = new ReservaRepositoryMemory();
        var equipamentoRepo = new EquipamentoRepositoryMemory();

        this.service = new ReservaApplicationService(reservaRepo, equipamentoRepo);
    }

    @WebMethod
    public ReservaResponse criarReserva(int equipamentoId, String responsavel) {
        return service.criarReserva(equipamentoId, responsavel);
    }

    @WebMethod
    public ReservaResponse cancelarReserva(int reservaId) {
        return service.cancelarReserva(reservaId);
    }
}