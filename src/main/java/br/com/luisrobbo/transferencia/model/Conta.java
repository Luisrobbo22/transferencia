package br.com.luisrobbo.transferencia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numeroConta;

    private int numeroAgencia;

    private double saldo;

    private double chequeEspecial;



    public Conta() {
    }

    public Conta(Integer id, int numeroConta, int numeroAgencia, double saldo, double chequeEspecial) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public void sacar(double valorSaque) throws Exception {
        if (saldo >= valorSaque) {
            saldo -= valorSaque;
        }else{
            throw new Exception("Saldo insuficiente para Saque");
        }
    }

    public void depositar(double valorDeposito) {
        setSaldo(getSaldo() + valorDeposito);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta conta = (Conta) o;
        return getId().equals(conta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


