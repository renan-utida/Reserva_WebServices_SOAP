package br.com.fiap.reserva.infrastructure.repository.memory;

import br.com.fiap.reserva.domain.model.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - ReservaRepositoryMemory")
public class ReservaRepositoryMemoryTest {

    private ReservaRepositoryMemory repository;

    @BeforeEach
    public void setUp() {
        repository = new ReservaRepositoryMemory();
    }

    @Test
    @DisplayName("Deve salvar reserva e gerar ID automaticamente")
    public void testSalvarReserva() {
        Reserva reserva = new Reserva(0, 1, "Renan");
        Reserva salva = repository.salvar(reserva);

        assertNotNull(salva);
        assertTrue(salva.getId() > 0);
    }

    @Test
    @DisplayName("Deve buscar reserva pelo ID corretamente")
    public void testBuscarPorId() {
        Reserva reserva = new Reserva(0, 2, "Renan");
        repository.salvar(reserva);

        Reserva encontrada = repository.buscarPorId(reserva.getId());
        assertNotNull(encontrada);
        assertEquals("Renan", encontrada.getResponsavel());
    }

    @Test
    @DisplayName("Deve retornar null para ID inexistente")
    public void testBuscarPorIdInexistente() {
        Reserva encontrada = repository.buscarPorId(99);
        assertNull(encontrada);
    }

    @Test
    @DisplayName("Deve listar todas as reservas salvas")
    public void testListar() {
        repository.salvar(new Reserva(0, 1, "Renan"));
        repository.salvar(new Reserva(0, 2, "Maria"));

        assertEquals(2, repository.listar().size());
    }
}