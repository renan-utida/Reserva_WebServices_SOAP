package br.com.fiap.reserva.presentation.soap;

import br.com.fiap.reserva.application.dto.ReservaResponse;
import br.com.fiap.reserva.application.service.ReservaApplicationService;
import br.com.fiap.reserva.domain.model.Equipamento;
import br.com.fiap.reserva.infrastructure.repository.memory.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ReservaEndpoint {

    private final ReservaApplicationService service;
    private final EquipamentoRepositoryMemory equipamentoRepo;

    public ReservaEndpoint() {
        this.equipamentoRepo = new EquipamentoRepositoryMemory();
        var reservaRepo = new ReservaRepositoryMemory();

        this.service = new ReservaApplicationService(reservaRepo, this.equipamentoRepo);
    }

    @WebMethod
    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepo.listar();
    }

    @WebMethod
    public ReservaResponse criarReserva(
            @WebParam(name = "equipamentoId") int equipamentoId,
            @WebParam(name = "responsavel")   String responsavel
    ) {
        return service.criarReserva(equipamentoId, responsavel);
    }

    @WebMethod
    public ReservaResponse cancelarReserva(
            @WebParam(name = "reservaId") int reservaId
    ) {
        return service.cancelarReserva(reservaId);
    }
}