package com.analizador_de_logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorArquivo {

    private final File pastaAnalise;

    public EscritorArquivo(){
        this.pastaAnalise= new File("Analise");
        this.pastaAnalise.mkdirs();
    }

    public void salvar(String nomeDoArquivo, String conteudo) throws IOException {
        File arquivo = new File(pastaAnalise, nomeDoArquivo);

        BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo));
        escritor.write(conteudo);
        escritor.close();

        System.out.println("arquivo salvo");
    }

}
