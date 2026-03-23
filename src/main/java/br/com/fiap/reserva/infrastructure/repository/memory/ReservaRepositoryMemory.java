package br.com.fiap.reserva.infrastructure.repository.memory;

import br.com.fiap.reserva.domain.model.Reserva;
import br.com.fiap.reserva.domain.repository.ReservaRepository;

import java.util.*;

public class ReservaRepositoryMemory implements ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private int id = 1;

    @Override
    public Reserva salvar(Reserva reserva) {
        if (reserva.getId() == 0) {
            reserva.setId(id++);
            reservas.add(reserva);
        }
        return reserva;
    }

    @Override
    public Reserva buscarPorId(int id) {
        return reservas.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Reserva> listar() {
        return reservas;
    }
}