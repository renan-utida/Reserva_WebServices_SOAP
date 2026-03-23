package br.com.fiap.reserva.publisher;

import br.com.fiap.reserva.presentation.soap.ReservaEndpoint;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {

        String url = "http://localhost:8080/reserva";

        // Cria um servidor HTTP embutido e publica o serviço
        Endpoint.publish(url, new ReservaEndpoint());

        System.out.println("SOAP WebService rodando em:");
        System.out.println(url + "?wsdl");
    }
}