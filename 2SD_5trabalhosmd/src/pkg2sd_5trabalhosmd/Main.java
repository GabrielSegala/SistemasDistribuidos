/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2sd_5trabalhosmd;

/**
 *
 * @author Gabriel e Artur
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
/**
     * @param args the command line arguments
     */
    
    
    // Thread responsável por processar uma filial
    static class FilialThread extends Thread {

        private List<Double> vendas;
        private double resultado;

        public FilialThread(List<Double> vendas) {
            this.vendas = vendas;
            this.resultado = 0;
        }

        @Override
        public void run() {

            // Soma apenas as vendas da sua própria filial
            for (double venda : vendas) {
                resultado += venda;
            }
        }

        public double getResultado() {
            return resultado;
        }
    }

    public static void main(String[] args) {

        // 4 listas independentes, uma para cada filial
        List<Double> filial1 = new ArrayList<>();
        List<Double> filial2 = new ArrayList<>();
        List<Double> filial3 = new ArrayList<>();
        List<Double> filial4 = new ArrayList<>();

        // Simulando 10.000 vendas por filial
        for (int i = 0; i < 10000; i++) {
            filial1.add(100.0);
            filial2.add(200.0);
            filial3.add(300.0);
            filial4.add(400.0);
        }

        // Criando uma thread para cada filial
        FilialThread thread1 = new FilialThread(filial1);
        FilialThread thread2 = new FilialThread(filial2);
        FilialThread thread3 = new FilialThread(filial3);
        FilialThread thread4 = new FilialThread(filial4);

        // Iniciando as threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Aguarda todas as threads terminarem
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // A thread principal junta os resultados
        double faturamentoTotal =
                thread1.getResultado()
                + thread2.getResultado()
                + thread3.getResultado()
                + thread4.getResultado();

        // Exibe os resultados
        System.out.printf("Faturamento da Filial 1: R$ %.2f%n", thread1.getResultado());
        System.out.printf("Faturamento da Filial 2: R$ %.2f%n", thread2.getResultado());
        System.out.printf("Faturamento da Filial 3: R$ %.2f%n", thread3.getResultado());
        System.out.printf("Faturamento da Filial 4: R$ %.2f%n", thread4.getResultado());

        System.out.printf("Faturamento total: R$ %.2f%n", faturamentoTotal);
    }
}