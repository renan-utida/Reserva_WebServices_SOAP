package br.com.fiap.reserva.domain.repository;

import br.com.fiap.reserva.domain.model.Equipamento;
import java.util.List;

public interface EquipamentoRepository {
    Equipamento buscarPorId(int id);
    List<Equipamento> listar();
}