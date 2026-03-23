package br.com.fiap.reserva.domain.repository;

import br.com.fiap.reserva.domain.model.Reserva;
import java.util.List;

public interface ReservaRepository {
    Reserva salvar(Reserva reserva);
    Reserva buscarPorId(int id);
    List<Reserva> listar();
}
