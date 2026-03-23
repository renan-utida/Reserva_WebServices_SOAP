package br.com.fiap.soap.service;

import br.com.fiap.soap.model.Equipamento;
import br.com.fiap.soap.model.Reserva;
import br.com.fiap.soap.repository.ReservaRepository;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ReservaService {

    private final ReservaRepository repository = new ReservaRepository();

    // Operação 1: listar todos os equipamentos disponíveis
    @WebMethod
    public List<Equipamento> listarEquipamentos() {
        return repository.listarEquipamentos();
    }


}
