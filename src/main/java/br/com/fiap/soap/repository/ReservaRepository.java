package br.com.fiap.soap.repository;

import br.com.fiap.soap.model.Equipamento;
import br.com.fiap.soap.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    // Lista de equipamentos de laboratório pré-cadastrados
    private static final List<Equipamento> equipamentos = new ArrayList<>();

    // Lista de reservas realizadas
    private static final List<Reserva> reservas = new ArrayList<>();

    // Contador para IDs das reservas
    private static int proximoIdReserva = 1;

    // Bloco estático: adiciona os equipamentos na primeira vez que a classe é carregada
    static {
        equipamentos.add(new Equipamento(1, "Microscópio Óptico", "Microscópio com ampliação de até 1000x", true));
        equipamentos.add(new Equipamento(2, "Computador Dell", "Computador para análise de dados laboratoriais", true));
        equipamentos.add(new Equipamento(3, "Centrífuga", "Centrífuga de alta rotação para amostras", true));
        equipamentos.add(new Equipamento(4, "Espectrofotômetro", "Medição de absorbância de soluções", true));
        equipamentos.add(new Equipamento(5, "Balança Analítica", "Balança de precisão 0,0001g", true));
    }

    // Equipamentos

    public List<Equipamento> listarEquipamentos() {
        return equipamentos;
    }

    public Equipamento buscarEquipamentoPorId(int id) {
        return equipamentos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}