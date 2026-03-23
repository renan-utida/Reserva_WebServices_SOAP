package br.com.fiap.reserva.infrastructure.repository.memory;

import br.com.fiap.reserva.domain.model.Equipamento;
import br.com.fiap.reserva.domain.repository.EquipamentoRepository;

import java.util.*;

public class EquipamentoRepositoryMemory implements EquipamentoRepository {

    private final List<Equipamento> equipamentos = new ArrayList<>();

    public EquipamentoRepositoryMemory() {
        equipamentos.add(new Equipamento(1, "Microscópio Óptico",    "Microscópio com ampliação de até 1000x",          true));
        equipamentos.add(new Equipamento(2, "Computador Dell",       "Computador para análise de dados laboratoriais",  true));
        equipamentos.add(new Equipamento(3, "Centrífuga",            "Centrífuga de alta rotação para amostras",        true));
        equipamentos.add(new Equipamento(4, "Espectrofotômetro",     "Medição de absorbância de soluções",              true));
        equipamentos.add(new Equipamento(5, "Balança Analítica",     "Balança de precisão 0,0001g",                     true));
    }

    @Override
    public Equipamento buscarPorId(int id) {
        return equipamentos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Equipamento> listar() {
        return equipamentos;
    }
}