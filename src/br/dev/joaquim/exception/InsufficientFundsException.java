package br.dev.joaquim.exception; /** classe criada pois "InsufficientFundsException" é uma exceção personalizada*/

public class InsufficientFundsException extends Exception{ /** herda de Exception */
    public InsufficientFundsException(String message) {
        super(message); /** exceção que só aparece  se houver tentativa de saque de um valor maior do que o saldo disponível na conta */
    }
}
