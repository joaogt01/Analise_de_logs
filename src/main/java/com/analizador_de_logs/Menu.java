package com.analizador_de_logs;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private AnalisadorLog analisador;
    int opcao = scanner.nextInt();

    public void iniciar(){
        exibirMenu();
        do{
            switch (opcao){
                case 1:
                    analisador.RecursosGrandes();
                    break;
                case 2:
                    analisador.NaoRespondidas();
                    break;
                case 3:
                    analisador.SistemasOperacionais();
                case 4:
                    analisador.MediaPost();
            }
        } while (opcao == 0);
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
