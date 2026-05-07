package com.analizador_de_logs;

import java.io.IOException;

public interface ProcessadorLinhaInterface {
    public void processar(String ip,
                          String metodo,
                          int status,
                          String tamanho,
                          String mes,
                          String ano,
                          String referer,
                          String userAgent) throws IOException;
}
