package br.com.luisrobbo.transferencia.service;

import br.com.luisrobbo.transferencia.model.Conta;
import br.com.luisrobbo.transferencia.repository.ContaRepository;
import br.com.luisrobbo.transferencia.service.exception.DataIntegretyException;
import br.com.luisrobbo.transferencia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public boolean transferir(int numeroContaOrigem, int numeroContaDestino, double valorTransferencia) throws Exception {
        Conta contaOrigem = findByNumeroConta(numeroContaOrigem);
        Conta contaDestino = findByNumeroConta(numeroContaDestino);
        double saldoContaOrigem = contaOrigem.getSaldo();
        double saldoContaDestigo = contaDestino.getSaldo();

        boolean isSaldoValido = validaSaldo(contaOrigem, valorTransferencia);
        if (isSaldoValido) {
            updateSaldo(contaOrigem, contaDestino, valorTransferencia);
        }
        return validarTransferencia(saldoContaOrigem, saldoContaDestigo, valorTransferencia, contaOrigem.getNumeroConta(), contaDestino.getNumeroConta());
    }

    public Conta findByNumeroConta(int numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta);
    }

    public Conta find(Integer id) {
        Optional<Conta> obj = contaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
    }

    public boolean validaSaldo(Conta contaOrigem, double valorTransferencia) {
        double saldo = contaOrigem.getSaldo();
        if (saldo >= valorTransferencia) {
            return true;
        }
        return false;
    }

    public void updateSaldo(Conta contaOrigem, Conta contaDestino, double valorTransferencia) throws Exception {
        contaOrigem.sacar(valorTransferencia);
        contaDestino.depositar(valorTransferencia);

        contaRepository.saveAndFlush(contaOrigem);
        contaRepository.saveAndFlush(contaDestino);
    }


    public Conta update(Conta conta) {
        Conta newConta = (Conta) findByNumeroConta(conta.getNumeroConta());
        return contaRepository.save(newConta);
    }

    @Transactional
    public Conta insert(Conta conta) {
        Conta nConta = findByNumeroConta(conta.getNumeroConta());
        if (conta.getId() != nConta.getId()) {
            conta = contaRepository.save(conta);
        } else {
            throw new DataIntegretyException("Conta já cadastrado");
        }
        return conta;
    }


    public boolean validarTransferencia(double saldoContaOrigem, double saldoContaDestino,
										double valorTransferencia, int numeroContaOrigem, int numeroContaDestino) {
        Conta contaOrigemAtualizada = findByNumeroConta(numeroContaOrigem);
        Conta contaDestinoAtualizada = findByNumeroConta(numeroContaDestino);

        if (saldoContaOrigem - valorTransferencia == contaOrigemAtualizada.getSaldo() &&
                saldoContaDestino + valorTransferencia == contaDestinoAtualizada.getSaldo()) {
            return true;
        } else {
            return false;
        }
    }


}
