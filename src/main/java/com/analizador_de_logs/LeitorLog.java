package com.analizador_de_logs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeitorLog {
    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) \\S+ \\S+ \\[([^\\]]+)\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\S+)" +
                    "(?: \"([^\"]*)\" \"([^\"]*)\")?.*$"
    );

    private static final Pattern DATA_PATTERN = Pattern.compile(
            "(\\d{2})/(\\w+)/(\\d{4})"
    );

    private final File arquivoLog;

    public LeitorLog(File arquivoLog) {
        this.arquivoLog = arquivoLog;
    }

    public void processar(ProcessadorLinhaInterface processador) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(arquivoLog));
        String linha;

        while ((linha = leitor.readLine()) != null) {
            Matcher m = LOG_PATTERN.matcher(linha);

            // Ignora linhas que não correspondem ao formato esperado do access.log
            if (!m.matches()) continue;

            String ip = m.group(1);
            String dataHora  = m.group(2);
            String metodo = m.group(3);
            int status = Integer.parseInt(m.group(6));
            String tamanho = m.group(7);

            // Grupos opcionais: retorna string vazia se forem ausentes no registro
            String referer   = m.group(8) != null ? m.group(8) : "";
            String userAgent = m.group(9) != null ? m.group(9) : "";

            // Extrai mês e ano separadamente da data/hora com o segundo Regex
            Matcher dm = DATA_PATTERN.matcher(dataHora);
            String mes = "";
            String ano = "";
            if (dm.find()) {
                mes = dm.group(2); // ex: Nov
                ano = dm.group(3); // ex: 2021
            }

            processador.processar(ip, metodo, status, tamanho, mes, ano, referer, userAgent);
        }

        leitor.close();
    }
}
