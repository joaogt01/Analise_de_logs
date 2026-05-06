package com.analizador_de_logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnalisadorLog {
    //private LeitorLog leitorLog;
    private EscritorArquivo escritorArquivo;

    public void RecursosGrandes(){
        try{
            FileWriter arquivoGrande = new FileWriter("recursosGrandes.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NaoRespondidas(){
        try {
            FileWriter arquivoNaoRespondido = new FileWriter("naoRespondidosNovembro.txt");
        } catch (IOException e) {
           e.printStackTrace();
        }

    }

    public void SistemasOperacionais(){
        try{
            FileWriter arquivoSO = new FileWriter("sistemasOperacionais.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MediaPost(){

    }
}
