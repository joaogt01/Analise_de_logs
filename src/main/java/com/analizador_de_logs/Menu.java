package com.analizador_de_logs;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private AnalisadorLog analisador;

    public void iniciar(){
        exibirMenu();
        int opcao;
        do{
            opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    analisador.RecursosGrandes();
                    break;
                case 2:
                    analisador.NaoRespondidas();
                    break;
                case 3:
                    analisador.SistemasOperacionais();
                    break;
                case 4:
                    analisador.MediaPost();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Essa opçao nao existe, digite uma opçao válida(1 a 4, 0 para sair)");
                    exibirMenu();
                    break;
            }
        } while (opcao != 0);
    }

    private void exibirMenu(){
        System.out.println("""
                ======== Menu ========
                1 - Recursos grandes respondidos
                2 - Não respondido
                3 - % de requisitosMédia das requisições por SO
                0 - Sair""");
    }

    private int lerOpcao(){
        return 0;
    }
}