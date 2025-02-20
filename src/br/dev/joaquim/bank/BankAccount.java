package br.dev.joaquim.bank;

import br.dev.joaquim.exception.InsufficientFundsException;

public class BankAccount {
  private int accountNumber;
  private double balance;
  private String accountHolderName;

  public BankAccount() {
  }
  /** A classe encapsula os dados da conta bancária (accountNumber, balance, accountHolderName), fornecendo métodos públicos para acessar e modificar o saldo. */
  public BankAccount(int accountNumber, double balance, String accountHolderName) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.accountHolderName = accountHolderName;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public void deposit(double value) {
/** Impede depósitos negativos, lançando uma IllegalArgumentException. */
/** Adiciona o valor ao saldo da conta. */
    if (value < 0) {
      throw new IllegalArgumentException("O valor precisa ser positivo, foi informado o valor R$ " + value);
    }

    this.balance += value;
  }

  public void withdraw(double value) throws InsufficientFundsException {
/** Impede saques negativos, lançando uma IllegalArgumentException. */
    if (value < 0) {
      throw new IllegalArgumentException("O valor precisa ser positivo, foi informado o valor R$ " + value);
    }

    if (value > this.balance) {
      /** "throw é usado dentro do método para lançar uma exceção específica" */
      /** Interrompe a execução normal e precisa ser tratado ou prorrogado */
      throw new InsufficientFundsException("Saldo insuficiente. " + /** Impede saques maiores que o saldo, lançando uma InsufficientFundsException. */
      "O valor R$ " + value + " é superior ao saldo [R$ " + this.balance + "]");
    }

    this.balance -= value;
  }

  @Override
  public String toString() {
    return "Conta " + accountNumber + " de " + accountHolderName + " têm R$ " + balance + " de saldo";
  } /** exibe as informações */
}
