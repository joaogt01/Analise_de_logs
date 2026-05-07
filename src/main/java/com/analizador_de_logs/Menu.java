package com.analizador_de_logs;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private AnalisadorLog analisador;

    public Menu(Scanner scanner, AnalisadorLog analisador) {
        this.scanner = scanner;
        this.analisador = analisador;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            try {
                switch (opcao) {
                    case 1:
                        analisador.recursosGrandes();
                        break;
                    case 2:
                        analisador.naoRespondidas();
                        break;
                    case 3:
                        analisador.sistemasOperacionais();
                        break;
                    case 4:
                        analisador.mediaPost();
                        break;
                    case 0:
                        System.out.println("Finalizando...");
                        break;
                    default: System.out.println("Opção inválida, tente novamente.");
                }
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("""
                ======== Menu ========
                1 - Recursos grandes respondidos
                2 - Não respondidos
                3 - % de requisições por SO
                4 - Média das requisições POST
                0 - Sair""");
    }

    private int lerOpcao() {
        return Integer.parseInt(scanner.nextLine());

    }
}