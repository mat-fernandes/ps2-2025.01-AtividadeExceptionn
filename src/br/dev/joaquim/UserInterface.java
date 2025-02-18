package br.dev.joaquim;

import java.util.Random;
import java.util.Scanner;

import br.dev.joaquim.bank.BankAccount;

public class UserInterface {
    private Scanner input = new Scanner(System.in); /** Utiliza Scanner para receber entradas do usuário. */
    private BankAccount account;

    private void welcome() { /** Exibe um menu de opções. */
        System.out.println("Bem-vindo ao sistema bancário");
        System.out.print("Vamos criar usa conta, informe seu nome: ");
        String holderName = input.nextLine();
        int accountNumber = 1000 + (new Random()).nextInt(8999); /** Gera um número de conta aleatório */
        System.out.println("Criamos uma conta com o número: " + accountNumber + ", com saldo igual a 0 (zero).");
        this.account = new BankAccount(accountNumber, 0, holderName); /** Cria uma conta bancária com saldo inicial zero. */
    }

    private void showMenu() { /** Exibe opções disponíveis para o usuário */
        System.out.println("\n\n-----------------------");
        System.out.println("Escolha uma das opções:");
        System.out.println("\t1. Verificar dados da conta.");
        System.out.println("\t2. Depositar.");
        System.out.println("\t3. Sacar.");
        System.out.println("\t4. Sair.");
        System.out.print("opção > ");
    }

    public void start() { /** Controla o fluxo do programa com start() */
        welcome();
        if (account == null)
            return;

        while (true) {
            showMenu();
            try {       /** Usa um try-catch para capturar exceções de entrada inválida */
                int choice = readOption();  /** Converte a entrada do usuário para um número inteiro */
                switch (choice) {       /** Loop infinito para manter o programa em execução até que o usuário escolha sair */
                    case 1:
                        System.out.println("\n" + this.account);
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw(); // pode dar problema
                        break;
                    case 4:
                        System.out.println("Até a próxima.");
                        return;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
                waitUser();
            } catch (NumberFormatException ex) {
                System.out.println("Valor informado não é um número");
            }
        }
    }

    private void deposit() {    /** Pede um valor ao usuário e chama account.deposit(value) */
        System.out.print("\nInforme o valor a ser depositado: ");
        double value = readValue(); /** Converte a entrada para double, evitando erros de formatação */
        account.deposit(value);
        System.out.println("Desposito realizado com sucesso.");
    }

    private void withdraw() {   /** Pede um valor ao usuário e chama account.withdraw(value), tratando a exceção caso o saldo seja insuficiente. */
        System.out.print("\nInforme o valor a ser sacado: ");
        double value = readValue();
        try {
            account.withdraw(value); // pode dar problema
            System.out.println("Saque realizado com sucesso");
        } catch (Exception e) {
            System.out.println("Problema no saque: " + e.getMessage()); /** "catch" para tratar um possível erro, quando o saldo é insuficiente */
        }
    }

    private int readOption() {
        String choiceString = input.nextLine();
        return Integer.parseInt(choiceString);
    }

    private double readValue() {
        String line = input.nextLine();
        return Double.parseDouble(line);
    }

    private void waitUser() {   /** Aguarda o usuário pressionar ENTER antes de continuar */
        System.out.println("pressione ENTER para continuar...");
        input.nextLine();
    }
}
