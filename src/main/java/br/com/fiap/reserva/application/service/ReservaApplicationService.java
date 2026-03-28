package br.com.fiap.reserva.application.service;

import br.com.fiap.reserva.application.dto.ReservaResponse;
import br.com.fiap.reserva.domain.model.*;
import br.com.fiap.reserva.domain.repository.*;

public class ReservaApplicationService {

    private final ReservaRepository reservaRepository;
    private final EquipamentoRepository equipamentoRepository;

    public ReservaApplicationService(ReservaRepository reservaRepository,
                                     EquipamentoRepository equipamentoRepository) {
        this.reservaRepository = reservaRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public ReservaResponse criarReserva(int equipamentoId, String responsavel) {

        Equipamento equipamento = equipamentoRepository.buscarPorId(equipamentoId);

        if (equipamento == null) {
            return ReservaResponse.erro("Equipamento não encontrado");
        }

        if (!equipamento.isDisponivel()) {
            return ReservaResponse.erro("Equipamento indisponível");
        }

        Reserva reserva = new Reserva(0, equipamentoId, responsavel);

        equipamento.indisponibilizar();

        reservaRepository.salvar(reserva);

        return ReservaResponse.sucesso(reserva);
    }

    public ReservaResponse cancelarReserva(int reservaId) {

        Reserva reserva = reservaRepository.buscarPorId(reservaId);

        if (reserva == null) {
            return ReservaResponse.erro("Reserva não encontrada");
        }

        if (reserva.getStatus() == StatusReserva.CANCELADA) {
            return ReservaResponse.erro("Reserva já cancelada");
        }

        reserva.cancelar();

        Equipamento equipamento = equipamentoRepository.buscarPorId(reserva.getEquipamentoId());

        if (equipamento != null) {
            equipamento.disponibilizar();
        }

        return ReservaResponse.sucesso(reserva);
    }
}