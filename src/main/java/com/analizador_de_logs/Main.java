package com.analizador_de_logs;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final String caminhoLog = "logs" + File.separator + "access.log";

    public static void main(String[] args) {
        File arquivoLog = new File(caminhoLog);

        if (!arquivoLog.exists()){
            System.out.println("arquivo não encontrado");
        }

        Scanner scanner = new Scanner(System.in);
        EscritorArquivo escritor = new EscritorArquivo();
        AnalisadorLog analisador = new AnalisadorLog();
        Menu menu = new Menu();

        menu.iniciar();

        scanner.close();
    }

}
