package br.com.luisrobbo.transferencia.controller;

import br.com.luisrobbo.transferencia.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/banco/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/transferir/")
    public ResponseEntity<Boolean> transferir(
            @RequestParam(value = "numeroContaOrigem", defaultValue = "") int numeroContaOrigem,
            @RequestParam(value = "numeroContaDestino", defaultValue = "") int numeroContaDestino,
            @RequestParam(value = "valorTransferencia", defaultValue = "0") double valorTransferencia) throws Exception {


        boolean transferir = contaService.transferir(numeroContaOrigem, numeroContaDestino, valorTransferencia);

        return ResponseEntity.ok().body(transferir);
    }

}
