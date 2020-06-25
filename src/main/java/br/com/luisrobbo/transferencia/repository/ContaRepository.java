package br.com.luisrobbo.transferencia.repository;

import br.com.luisrobbo.transferencia.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("SELECT c FROM Conta c WHERE c.numeroConta =:numeroConta")
    Conta findByNumeroConta(int numeroConta);




}
