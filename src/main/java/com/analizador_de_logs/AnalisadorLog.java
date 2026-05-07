package com.analizador_de_logs;

import java.io.File;
import java.io.IOException;

public class AnalisadorLog {

    private LeitorLog leitorLog;
    private EscritorArquivo escritorArquivo;

    public AnalisadorLog(File arquivoLog, EscritorArquivo escritor) {
        this.leitorLog = new LeitorLog(arquivoLog);
        this.escritorArquivo = escritor;
    }

    public void recursosGrandes() throws IOException {
        ProcessadorRecursosGrandes processador = new ProcessadorRecursosGrandes();
        leitorLog.processar(processador);
        escritorArquivo.salvar("recursosGrandes.txt", processador.getResultado());
    }

    private class ProcessadorRecursosGrandes implements ProcessadorLinhaInterface {
        private StringBuilder stringBuilder = new StringBuilder();

        public String getResultado() { return stringBuilder.toString(); }

        public void processar(String ip, String metodo, int status, String tamanho, String mes, String ano, String referer, String userAgent) {
            if (tamanho.equals("-")) return;
            int tam = Integer.parseInt(tamanho);
            if (status >= 200 && status <= 299 && tam > 2000) {
                stringBuilder.append(" ").append(status).append(" ").append(tam).append(" ").append(ip).append(System.lineSeparator());
            }
        }
    }

    public void naoRespondidas() throws IOException {
        ProcessadorNaoRespondidas processador = new ProcessadorNaoRespondidas();
        leitorLog.processar(processador);
        escritorArquivo.salvar("naoRespondidosNovembro.txt", processador.getResultado());
    }

    private class ProcessadorNaoRespondidas implements ProcessadorLinhaInterface {
        private StringBuilder sb = new StringBuilder();

        public String getResultado() { return sb.toString(); }

        public void processar(String ip, String metodo, int status, String tamanho,
                              String mes, String ano, String referer, String userAgent) {
            if (status < 400 || status > 499) return;
            if (!mes.equals("Nov") || !ano.equals("2021")) return;

            String url = !referer.isEmpty() ? referer : "-";
            sb.append(status).append(" \"").append(url).append("\"").append(" ").append(mes).append("/").append(ano).append(System.lineSeparator());
        }
    }

    public void sistemasOperacionais() throws IOException {
        ProcessadorSO processador = new ProcessadorSO();
        leitorLog.processar(processador);
        if (processador.getTotal() == 0) {
            System.out.println("Nenhum registro para 2021.");
            return;
        }
        escritorArquivo.salvar("sistemasOperacionais.txt", processador.getResultado());
    }

    private class ProcessadorSO implements ProcessadorLinhaInterface {
        private int windows = 0, mac = 0, ubuntu = 0, fedora = 0, mobile = 0, linux = 0, total = 0;

        public int getTotal() { return total; }

        public String getResultado() {
            return String.format(
                    "Windows %.4f%n" + "Macintosh %.4f%n"  + "Ubuntu %.4f%n" + "Fedora %.4f%n" + "Mobile %.4f%n" + "Linux, outros %.4f%n", (windows*100.0)/total,(mac*100.0)/total,(ubuntu*100.0)/total, (fedora*100.0)/total,(mobile*100.0)/total,(linux*100.0)/total
            );
        }

        public void processar(String ip, String metodo, int status, String tamanho, String mes, String ano, String referer, String userAgent) {
            if (!ano.equals("2021") || userAgent.isEmpty()) return;
            total++;
            if (userAgent.contains("Android") || userAgent.contains("Mobile")) mobile++;
            else if (userAgent.contains("Windows")) windows++;
            else if (userAgent.contains("Macintosh")) mac++;
            else if (userAgent.contains("Ubuntu")) ubuntu++;
            else if (userAgent.contains("Fedora")) fedora++;
            else if (userAgent.contains("X11")) linux++;
        }
    }

    public void mediaPost() throws IOException {
        ProcessadorMediaPost processador = new ProcessadorMediaPost();
        leitorLog.processar(processador);
        if (processador.getCount() > 0)
            System.out.printf("Média POST (2021): %.2f%n", processador.getMedia());
        else
            System.out.println("Nenhuma requisição POST encontrada.");
    }

    private class ProcessadorMediaPost implements ProcessadorLinhaInterface {
        private long soma  = 0;
        private int count = 0;

        public int    getCount() { return count; }
        public double getMedia() { return (double) soma / count; }

        public void processar(String ip, String metodo, int status, String tamanho, String mes, String ano, String referer, String userAgent) {
            if (!metodo.equals("POST")) return;
            if (status < 200 || status > 299) return;
            if (tamanho.equals("-")) return;
            if (!ano.equals("2021")) return;
            soma += Long.parseLong(tamanho);
            count++;
        }
    }
}