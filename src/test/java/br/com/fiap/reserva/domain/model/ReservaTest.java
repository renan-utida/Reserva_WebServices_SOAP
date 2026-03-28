package br.com.fiap.reserva.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - classe Reserva")
public class ReservaTest {

    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        reserva = new Reserva(1, 1, "Renan");
    }

    @Test
    @DisplayName("Deve criar reserva com status ATIVA por padrão")
    public void testCriacaoReserva() {
        assertEquals(1, reserva.getId());
        assertEquals(1, reserva.getEquipamentoId());
        assertEquals("Renan", reserva.getResponsavel());
        assertEquals(StatusReserva.ATIVA, reserva.getStatus());
    }

    @Test
    @DisplayName("Deve cancelar reserva ativa com sucesso")
    public void testCancelarReservaAtiva() {
        reserva.cancelar();
        assertEquals(StatusReserva.CANCELADA, reserva.getStatus());
    }

    @Test
    @DisplayName("Não deve cancelar reserva já cancelada")
    public void testCancelarReservaJaCancelada() {
        reserva.cancelar();
        assertThrows(RuntimeException.class, () -> reserva.cancelar());
    }

    @Test
    @DisplayName("Deve criar reserva com construtor vazio")
    public void testConstrutorVazio() {
        Reserva r = new Reserva();
        assertNotNull(r);
    }
}