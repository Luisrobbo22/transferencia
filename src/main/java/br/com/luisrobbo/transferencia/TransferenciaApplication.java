package br.com.luisrobbo.transferencia;

import br.com.luisrobbo.transferencia.model.Conta;
import br.com.luisrobbo.transferencia.repository.ContaRepository;
import br.com.luisrobbo.transferencia.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransferenciaApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TransferenciaApplication.class, args);

        Conta contaJoao = new Conta(1, 123,111,1897.00,1000.00);
        Conta contaMaria = new Conta(2, 456,444,12358.00,600.00);



        ContaService contaService = new ContaService();

        //contaService.transferir(contaJoao.getNumeroConta(), contaMaria.getNumeroConta(), 870.00);
    }

}
