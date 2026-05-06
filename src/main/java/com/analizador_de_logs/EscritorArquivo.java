package com.analizador_de_logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsável pela persistência dos resultados das análises no disco.
 *
 * <p>Responsabilidade única: criar a pasta de saída "Análise" e gravar
 * o conteúdo gerado pelas análises nos respectivos arquivos de texto.</p>
 *
 * <p>Decisão de design: a escrita em disco foi isolada em uma classe
 * própria seguindo o princípio da Responsabilidade Única (SRP). Dessa
 * forma, o {@link AnalisadorLog} foca exclusivamente na lógica de negócio
 * sem precisar conhecer detalhes de criação de pastas, caminhos de arquivo
 * ou manipulação de streams de escrita.</p>
 *
 * <p>Decisão de design: o uso de {@code new File("Análise")} cria um
 * caminho relativo a pasta de execução do programa. O {@link File}
 * utiliza automaticamente o separador de diretórios do SO
 * em uso, garantindo compatibilidade com Windows, Linux e Mac sem
 * necessidade de ajuste no código-fonte.</p>
 *
 */
public class EscritorArquivo {


    /**
     * Referência à pasta "Análise" onde todos os arquivos de saída são salvos.
     *
     * <p>Decisão de design: armazenar a pasta como atributo evita recriar
     * o objeto {@link File} a cada chamada de {@link #salvar}, além de
     * centralizar o nome da pasta em um único lugar para facilitar
     * manutenção futura.</p>
     */
    private final File pastaAnalise;

    /**
     * Construtor. Cria a pasta "Análise" caso ainda não exista.
     *
     * <p>Decisão de design: a criação da pasta ocorre no construtor para
     * garantir que ela exista antes de qualquer tentativa de escrita.
     * O método {@link File#mkdirs()} é utilizado em vez de {@link File#mkdir()}
     * pois cria todos os diretórios intermediários necessários, tornando
     * o comportamento mais robusto independente do diretório de execução.</p>
     */
    public EscritorArquivo(){
        this.pastaAnalise= new File("Analise");
        this.pastaAnalise.mkdirs();
    }

    /**
     * Salva o conteúdo fornecido em um arquivo dentro da pasta "Análise".
     *
     * <p>Decisão de design: o método recebe o conteúdo já formatado como
     * {@link String}, mantendo a responsabilidade de formatação no
     * {@link AnalisadorLog} e a responsabilidade de escrita aqui.
     * O {@link BufferedWriter} é utilizado para escrita eficiente com
     * buffer em memória antes de gravar no disco.</p>
     *
     * @param nomeArquivo nome do arquivo de saída, ex: "recursosGrandes.txt"
     * @param conteudo    texto completo a ser gravado no arquivo
     * @throws IOException se ocorrer falha ao criar ou escrever o arquivo
     */

    public void salvar(String nomeDoArquivo, String conteudo) throws IOException {
        // Monta o caminho completo: pasta Análise + nome do arquivo.
        // O construtor de File(File, String) usa o separador do SO automaticamente.
        File arquivo = new File(pastaAnalise, nomeDoArquivo);

        BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo));
        escritor.write(conteudo);
        escritor.close();

        System.out.println("arquivo salvo");
    }

}
