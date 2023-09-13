package com.contadorloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContadorLoc {
        private AnalisadorFuncionamentoPrograma analisador;

        private String caminhoProjeto;

        private List<MetricaNo> listaMetrica = new ArrayList<>();

        public ContadorLoc(String caminhoProjeto) {
                this.analisador = new AnalisadorFuncionamentoPrograma(caminhoProjeto);
                this.caminhoProjeto = caminhoProjeto;
        }

        private class MetricaNo {
                private int qtdeLinhas;

                private String nomeMetodo;

                private String nomeClasse;

                private String nomeArquivo;

                public MetricaNo(int qtdeLinhas, String nomeMetodo, String nomeClasse, String nomeArquivo) {
                        this.qtdeLinhas = qtdeLinhas;
                        this.nomeMetodo = nomeMetodo;
                        this.nomeClasse = nomeClasse;
                        this.nomeArquivo = nomeArquivo;
                }

                public int getQtdeLinhas() {
                        return qtdeLinhas;
                }

                public void setQtdeLinhas(int qtdeLinhas) {
                        this.qtdeLinhas = qtdeLinhas;
                }

                public String getNomeMetodo() {
                        return nomeMetodo;
                }

                public String getNomeClasse() {
                        return nomeClasse;
                }

                public String getNomeArquivo() {
                        return nomeArquivo;
                }
        }

        private void incrementarLinhasExternasDasClassesParaClasses(int somaClasse, String nomePrograma) {
                int qtdeLinhas;

                int resultadoSoma;

                for (MetricaNo metrica : listaMetrica) {
                        if (metrica.getNomeMetodo().isEmpty() && !metrica.getNomeClasse().isEmpty()
                                        && metrica.getNomeArquivo().equals(nomePrograma)) {
                                qtdeLinhas = metrica.getQtdeLinhas();
                                resultadoSoma = qtdeLinhas + somaClasse;
                                metrica.setQtdeLinhas(resultadoSoma);
                        }
                }
        }

        private int contarLinhasProgramaPorArquivo(File arquivoAtual, String sufixo) throws IOException {

                BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoAtual));
                String linha;

                String fechoEstrutura = "}";
                String identificadorClasse = "class";

                int qtdeLinhasArquivo = 0;

                int qtdeLinhasForaClasse = 0;
                int qtdeLinhasClasse = 0;
                int qtdeLinhasMetodo = 0;
                int qtdeChavesClasseAninhada = 0;
                int qtdeEstruturaAuxiliar = 0;

                boolean foraClasseAninhada = true;

                List<String> classes = new ArrayList<>();
                List<String> metodos = new ArrayList<>();

                Pattern classePattern = Pattern.compile("class\\s+(\\w+)");

                Pattern metodoPattern = Pattern.compile("\\b\\w+\\s+(\\w+)\\s*\\([^)]*\\)\\s*\\{");

                String nomePrograma = arquivoAtual.getName().replace(sufixo, "");

                while ((linha = bufferedReader.readLine()) != null) {
                        linha = linha.trim();

                        if (linha.startsWith("//") || linha.isEmpty() || linha.startsWith("/*")) {
                                continue;
                        } else {
                                /* verifica e contabiliza as linhas referentes a classes aninhadas */
                                if (linha.contains(identificadorClasse) && classes.size() == 1) {
                                        qtdeChavesClasseAninhada++;
                                        qtdeLinhasClasse++;
                                        foraClasseAninhada = false;
                                } else if (linha.contains(fechoEstrutura) && qtdeChavesClasseAninhada > 0) {
                                        qtdeChavesClasseAninhada++;
                                        qtdeLinhasClasse++;
                                } else if (linha.contains(fechoEstrutura) && qtdeChavesClasseAninhada > 0) {
                                        qtdeChavesClasseAninhada--;
                                        qtdeLinhasClasse++;
                                } else if (qtdeChavesClasseAninhada > 0) {
                                        qtdeLinhasClasse++;
                                }

                                /* se não for uma classe aninhada */
                                if (foraClasseAninhada) {
                                        if (linha.contains(identificadorClasse)) {
                                                Matcher matcher = classePattern.matcher(linha);

                                                if (matcher.find()) {
                                                        String className = matcher.group(1);
                                                        classes.add(className);
                                                }

                                                qtdeLinhasClasse++;

                                        } else if ((linha.contains("private") || linha.contains("public"))
                                                        && linha.contains("(") && linha.contains(")")) {

                                                Matcher methodMatcher = metodoPattern.matcher(linha);
                                                if (methodMatcher.find()) {
                                                        String methodName = methodMatcher.group(1);
                                                        metodos.add(methodName);
                                                }

                                                qtdeLinhasMetodo++;
                                        } else if (linha.matches(".*\\b(try|if|for|while)\\b.*\\{.*")
                                                        && !linha.matches(".*\\belse\\s+if\\b.*\\{.*")) {
                                                qtdeEstruturaAuxiliar++;
                                                qtdeLinhasMetodo++;
                                        } else if (linha.matches(".*\\b(catch|else|else// if)\\b.*\\{.*")) {
                                                qtdeEstruturaAuxiliar++;
                                        } else if (!linha.contains(fechoEstrutura) && metodos.size() > 0) {
                                                qtdeLinhasMetodo++;
                                        } else if (!linha.contains(fechoEstrutura) && classes.size() > 0
                                                        && metodos.size() == 0) {
                                                qtdeLinhasClasse++;
                                        } else if (!linha.contains(fechoEstrutura) && metodos.size() == 0
                                                        && classes.size() == 0) {
                                                qtdeLinhasForaClasse++;
                                        }
                                        /* verifica o fim das classes, métodos e estruturas (if, for...) */
                                        if (qtdeEstruturaAuxiliar > 0 && linha.contains(fechoEstrutura)) {
                                                qtdeEstruturaAuxiliar--;
                                                qtdeLinhasMetodo++;
                                        } else if (linha.contains(fechoEstrutura) && metodos.size() > 0
                                                        && qtdeEstruturaAuxiliar == 0) {
                                                String nomeMetodo = metodos.get(metodos.size() - 1);
                                                String nomeClasse = classes.get(classes.size() - 1);
                                                qtdeLinhasMetodo++;
                                                listaMetrica.add(new MetricaNo(qtdeLinhasMetodo, nomeMetodo, nomeClasse,
                                                                nomePrograma));

                                                if (!metodos.isEmpty()) {
                                                        metodos.remove(metodos.size() - 1);
                                                }

                                                qtdeLinhasMetodo = 0;
                                        } else if (linha.contains(fechoEstrutura) && classes.size() > 0
                                                        && metodos.size() == 0
                                                        && qtdeEstruturaAuxiliar == 0) {
                                                String nomeClasse = classes.get(classes.size() - 1);
                                                qtdeLinhasClasse++;
                                                listaMetrica.add(new MetricaNo(qtdeLinhasClasse, "", nomeClasse,
                                                                nomePrograma));
                                                qtdeLinhasClasse = 0;
                                                if (!classes.isEmpty()) {
                                                        classes.remove(classes.size() - 1);
                                                }
                                        }
                                }

                                qtdeLinhasArquivo++;

                                if (qtdeChavesClasseAninhada == 0) {
                                        foraClasseAninhada = true;
                                }
                        }
                }

                listaMetrica.add(new MetricaNo(qtdeLinhasArquivo, "", "", nomePrograma));

                incrementarLinhasExternasDasClassesParaClasses(qtdeLinhasForaClasse,
                                nomePrograma);

                bufferedReader.close();

                return qtdeLinhasArquivo;
        }

        private void converterMetricasCSV(String nomeArquivoCSV) {
                String separador = ",";

                try (FileWriter writer = new FileWriter(nomeArquivoCSV)) {
                        writer.write("Arquivo,Classe,Método,Linhas\n");

                        for (MetricaNo metrica : listaMetrica) {
                                writer.write(metrica.getNomeArquivo()
                                                + separador
                                                + metrica.getNomeClasse()
                                                + separador
                                                + metrica.getNomeMetodo()
                                                + separador
                                                + metrica.getQtdeLinhas() + "\n");
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void contarLinhasPrograma() throws IOException {
                boolean foi_compilado = analisador.verificarCompilacao();

                if (!foi_compilado) {
                        System.exit(1);
                }

                String extensaoArquivosAnalise = ".java";

                File arquivo = new File(caminhoProjeto);

                Stack<File> pilha = new Stack<>();

                pilha.push(arquivo);

                int qtdeLinhasTotalPrograma = 0;

                while (!pilha.isEmpty()) {
                        File arquivoAtual = pilha.pop();

                        if (arquivoAtual.isDirectory()) {
                                File[] files = arquivoAtual.listFiles();

                                for (File f : files) {
                                        pilha.push(f);
                                }

                        } else if (arquivoAtual.getName().endsWith(extensaoArquivosAnalise)) {
                                qtdeLinhasTotalPrograma += contarLinhasProgramaPorArquivo(arquivoAtual, extensaoArquivosAnalise);
                        }
                }

                listaMetrica.add(new MetricaNo(qtdeLinhasTotalPrograma, "", "", ""));

                converterMetricasCSV("metricas.csv");
        }
}
