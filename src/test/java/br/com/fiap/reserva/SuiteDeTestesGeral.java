package br.com.fiap.reserva;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// Roda todos os testes do projeto de uma vez
@Suite
@SelectPackages({
        "br.com.fiap.reserva.domain.model",
        "br.com.fiap.reserva.application.service",
        "br.com.fiap.reserva.infrastructure.repository.memory"
})
public class SuiteDeTestesGeral {
    // Nenhum código necessário aqui
}