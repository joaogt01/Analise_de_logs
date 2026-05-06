package com.analizador_de_logs;

public interface ProcessadorLinhaInterface {
    public void processar(String ip,
                          String metodo,
                          int status,
                          int tamanho,
                          int mes,
                          int ano,
                          String referer,
                          String userAgent);
}
