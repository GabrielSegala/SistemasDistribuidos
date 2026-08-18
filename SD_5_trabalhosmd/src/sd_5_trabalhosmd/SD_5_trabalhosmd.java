/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_5_trabalhosmd;

/**
 *
 * @author Gabriel e Artur
 */
public class SD_5_trabalhosmd {

    /**
     * @param args the command line arguments
     */
   
    // Variável compartilhada entre todas as threads
    private static double saldo_central = 0.0;

    // Método sincronizado para evitar condição de corrida
    public static synchronized void adicionarVenda(double valor) {
        saldo_central += valor;
    }

    // Classe que representa um caixa
    static class Caixa extends Thread {

        private int numeroCaixa;

        public Caixa(int numeroCaixa) {
            this.numeroCaixa = numeroCaixa;
        }

        @Override
        public void run() {

            // Cada caixa realiza 1000 vendas
            for (int i = 0; i < 1000; i++) {
                adicionarVenda(10.00);
            }

            System.out.println("Caixa " + numeroCaixa + " finalizou as vendas.");
        }
    }

    public static void main(String[] args) {
        
        // Criando os 5 caixas
        Caixa[] caixas = new Caixa[5];

        for (int i = 0; i < 5; i++) {
            caixas[i] = new Caixa(i + 1);
            caixas[i].start();
        }

        // Espera todas as threads terminarem
        for (int i = 0; i < 5; i++) {
            try {
                caixas[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Exibe o saldo final
        System.out.printf("Saldo central final: R$ %.2f%n", saldo_central);
    }
}