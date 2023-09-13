package com.contadorloc;

import java.io.IOException;

public final class App {
        private App() {
                throw new AssertionError("Esta classe não deve ser instanciada.");
        }

        public static void main(String[] args) throws IOException {

                if (args.length != 1) {
                        System.err.println("Especifique o caminho do Projeto");
                        System.exit(1);
                }

                String caminhoProjeto = args[0];

                ContadorLoc contadorLoc = new ContadorLoc(caminhoProjeto);
                contadorLoc.contarLinhasPrograma();
        }
}
