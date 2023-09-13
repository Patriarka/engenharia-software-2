package com.contadorloc;

import java.io.File;
import java.io.IOException;

public class AnalisadorFuncionamentoPrograma {
        String caminhoProjeto;

        public AnalisadorFuncionamentoPrograma(String caminhoProjeto) {
                this.caminhoProjeto = caminhoProjeto;
        }

        public boolean verificarCompilacao() {
                ProcessBuilder processBuilder = new ProcessBuilder("mvn.cmd", "compile");

                processBuilder.directory(new File(caminhoProjeto));
                try {
                        Process process = processBuilder.inheritIO().start();

                        int exitCode = process.waitFor();

                        if (exitCode == 0) {
                                System.out.println("Sucesso na Compilacao");
                                return true;
                        } else {
                                System.out.println("Erro na Compilacao");
                                return false;
                        }
                } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        return false;
                }
        }
}
