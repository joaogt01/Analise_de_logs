package com.analizador_de_logs;

import java.io.File;
import java.util.Scanner;

/**
 *Ponto de Inicio da aplicação.
        *
        * <p>Responsabilidade única: a responsabilidade da Main é somente inicializar os componentes do sistema e
 * iniciar a execução.</p>
        *
        * <p>Decisão de design: a Main segue o princípio da Responsabilidade Única
 * (SRP) da programação orientada a objetos. Ela apenas instancia os objetos,
        * injeta as dependências via construtor e deixa a execução ao Menu.
 * Isso facilita a manutenção, pois qualquer alteração na lógica de negócio
 * ou de interface não afeta esta classe.</p>
        *
 * </pre>
        *
        */
public class Main {
    /**
     * Caminho fixo do arquivo de log.
     *
     * <p>Decisão de design: o caminho do arquivo é definido como constante estática
     * para facilitar a manutenção (qualquer alteração na localização do
     * arquivo exige mudança em apenas um lugar). O uso de {@link File#separator}
     * garante compatibilidade com todos os sistemas operacionais: no Windows
     * o separador é "\" e no Linux/Mac é "/", sem necessidade de alterar
     * o código.</p>
     */

    private static final String caminhoLog = "logs" + File.separator + "access.log";
    /**
     * Método principal do sistema.
     *
     * <p>Verifica a existência do arquivo de log, inicializa os componentes
     * e inicia o menu.</p>
     *
     * <p>Decisão de design: os objetos {@link EscritorArquivo} e
     * {@link AnalisadorLog} são criados aqui e injetados via construtor
     * (injeção de dependência). Isso desacopla as classes (o AnalisadorLog
     * não precisa saber como o EscritorArquivo é criado, apenas o utiliza).
     * O {@link Scanner} é criado aqui e passado ao Menu para que o
     * fechamento do processo fique centralizado nesta classe.</p>
     *
     */

    public static void main(String[] args) {
        File arquivoLog = new File(caminhoLog);

        // Verifica se o arquivo existe antes de inicializar qualquer componente.
        if (!arquivoLog.exists()){
            System.out.println("arquivo não encontrado: " + arquivoLog.getAbsolutePath());
            return;
        }

        // Inicialização dos componentes com injeção de dependência via construtor.
        Scanner scanner = new Scanner(System.in);
        EscritorArquivo escritor = new EscritorArquivo();
        AnalisadorLog analisador = new AnalisadorLog(arquivoLog, escritor);
        Menu menu = new Menu(scanner, analisador);

        // Delega toda a execução ao Menu (a Main não conhece as opções do sistema).
        menu.iniciar();

        // Fecha o Scanner ao final para liberar o recurso de entrada.
        scanner.close();
    }

}
