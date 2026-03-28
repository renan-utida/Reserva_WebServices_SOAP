package br.com.fiap.reserva.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - classe Equipamento")
public class EquipamentoTest {

    private Equipamento equipamento;

    @BeforeEach
    public void setUp() {
        equipamento = new Equipamento(1, "Microscópio Óptico", "Ampliação de até 1000x", true);
    }

    @Test
    @DisplayName("Deve criar equipamento com valores corretos")
    public void testCriacaoEquipamento() {
        assertEquals(1, equipamento.getId());
        assertEquals("Microscópio Óptico", equipamento.getNome());
        assertEquals("Ampliação de até 1000x", equipamento.getDescricao());
        assertTrue(equipamento.isDisponivel());
    }

    @Test
    @DisplayName("Deve marcar equipamento como indisponível")
    public void testIndisponibilizar() {
        equipamento.indisponibilizar();
        assertFalse(equipamento.isDisponivel());
    }

    @Test
    @DisplayName("Deve marcar equipamento como disponível novamente")
    public void testDisponibilizar() {
        equipamento.indisponibilizar();
        equipamento.disponibilizar();
        assertTrue(equipamento.isDisponivel());
    }

    @Test
    @DisplayName("Deve criar equipamento com construtor vazio")
    public void testConstrutorVazio() {
        Equipamento e = new Equipamento();
        assertNotNull(e);
    }
}