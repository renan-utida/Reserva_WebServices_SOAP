package br.com.fiap.reserva.application.service;

import br.com.fiap.reserva.application.dto.ReservaResponse;
import br.com.fiap.reserva.infrastructure.repository.memory.EquipamentoRepositoryMemory;
import br.com.fiap.reserva.infrastructure.repository.memory.ReservaRepositoryMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - ReservaApplicationService")
public class ReservaApplicationServiceTest {

    private ReservaApplicationService service;

    @BeforeEach
    public void setUp() {
        var equipamentoRepo = new EquipamentoRepositoryMemory();
        var reservaRepo     = new ReservaRepositoryMemory();
        service = new ReservaApplicationService(reservaRepo, equipamentoRepo);
    }

    @Test
    @DisplayName("Deve criar reserva com sucesso para equipamento disponível")
    public void testCriarReservaSucesso() {
        ReservaResponse response = service.criarReserva(1, "Renan");

        assertNull(response.getErro());
        assertNotNull(response.getReserva());
        assertEquals("Renan", response.getReserva().getResponsavel());
    }

    @Test
    @DisplayName("Deve retornar erro ao reservar equipamento indisponível")
    public void testCriarReservaEquipamentoIndisponivel() {
        service.criarReserva(1, "Renan"); // reserva pela primeira vez
        ReservaResponse response = service.criarReserva(1, "Maria"); // tenta reservar de novo

        assertNotNull(response.getErro());
        assertEquals("Equipamento indisponível", response.getErro());
    }

    @Test
    @DisplayName("Deve retornar erro ao reservar equipamento inexistente")
    public void testCriarReservaEquipamentoInexistente() {
        ReservaResponse response = service.criarReserva(99, "Renan");

        assertNotNull(response.getErro());
        assertEquals("Equipamento não encontrado", response.getErro());
    }

    @Test
    @DisplayName("Deve cancelar reserva ativa com sucesso")
    public void testCancelarReservaSucesso() {
        ReservaResponse criada = service.criarReserva(1, "Renan");
        int reservaId = criada.getReserva().getId();

        ReservaResponse cancelada = service.cancelarReserva(reservaId);

        assertNull(cancelada.getErro());
        assertEquals("CANCELADA", cancelada.getReserva().getStatus().toString());
    }

    @Test
    @DisplayName("Deve retornar erro ao cancelar reserva já cancelada")
    public void testCancelarReservaJaCancelada() {
        ReservaResponse criada = service.criarReserva(1, "Renan");
        int reservaId = criada.getReserva().getId();

        service.cancelarReserva(reservaId);
        ReservaResponse response = service.cancelarReserva(reservaId);

        assertNotNull(response.getErro());
        assertEquals("Reserva já cancelada", response.getErro());
    }
}