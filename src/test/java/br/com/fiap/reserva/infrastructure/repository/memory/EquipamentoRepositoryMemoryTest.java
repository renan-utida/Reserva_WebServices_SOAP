package br.com.fiap.reserva.infrastructure.repository.memory;

import br.com.fiap.reserva.domain.model.Equipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - EquipamentoRepositoryMemory")
public class EquipamentoRepositoryMemoryTest {

    private EquipamentoRepositoryMemory repository;

    @BeforeEach
    public void setUp() {
        repository = new EquipamentoRepositoryMemory();
    }

    @Test
    @DisplayName("Deve listar os 5 equipamentos pré-cadastrados")
    public void testListarEquipamentos() {
        List<Equipamento> equipamentos = repository.listar();
        assertEquals(5, equipamentos.size());
    }

    @Test
    @DisplayName("Deve buscar equipamento por ID existente")
    public void testBuscarPorIdExistente() {
        Equipamento equipamento = repository.buscarPorId(1);
        assertNotNull(equipamento);
        assertEquals("Microscópio Óptico", equipamento.getNome());
    }

    @Test
    @DisplayName("Deve retornar null para ID inexistente")
    public void testBuscarPorIdInexistente() {
        Equipamento equipamento = repository.buscarPorId(99);
        assertNull(equipamento);
    }

    @Test
    @DisplayName("Deve inicializar todos os equipamentos como disponíveis")
    public void testTodosEquipamentosDisponiveis() {
        List<Equipamento> equipamentos = repository.listar();
        assertTrue(equipamentos.stream().allMatch(Equipamento::isDisponivel));
    }
}