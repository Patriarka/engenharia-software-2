package com.utfpr;

public class RegressaoLinear {
        private ListaEncadeada listaDePares;

        public RegressaoLinear(ListaEncadeada listaDePares) {
                this.listaDePares = listaDePares;
        }

        public double calcularBum() {
                int tamanhoLista = listaDePares.tamanho();

                if (tamanhoLista == 0) {
                        throw new IllegalStateException("A lista está vazia. Não é possível calcular b1.");
                }

                double numerador = 0;
                double denominador = 0;
                double dadoX = 0;
                double dadoY = 0;

                for (int i = 0; i < tamanhoLista; i++) {
                        dadoX = listaDePares.obterElementoX(i);
                        dadoY = listaDePares.obterElementoY(i);
                        numerador += dadoX * dadoY;
                }

                numerador -= tamanhoLista * listaDePares.mediaListaX() * listaDePares.mediaListaY();

                for (int i = 0; i < tamanhoLista; i++) {
                        dadoX = listaDePares.obterElementoX(i);
                        denominador += dadoX * dadoX;
                }

                denominador -= tamanhoLista * (listaDePares.mediaListaX() * listaDePares.mediaListaX());

                return numerador / denominador;
        }

        public double calcularBzero() {
                int tamanhoLista = listaDePares.tamanho();

                if (tamanhoLista == 0) {
                        throw new IllegalStateException("A lista está vazia. Não é possível calcular b0.");
                }

                double bUm = calcularBum();
                double mediaX = listaDePares.mediaListaX();
                double mediaY = listaDePares.mediaListaY();
                return mediaY - (bUm * mediaX);
        }

        public double calcularPrevisaoMelhorada(double dadoXk) {
                int tamanhoLista = listaDePares.tamanho();

                if (tamanhoLista == 0) {
                        throw new IllegalStateException("A lista está vazia. Não é possível calcular previsão melhorada.");
                }

                double bZero = calcularBzero();
                double bUm = calcularBum();
                return bZero + (bUm * dadoXk);
        }

        private double calcularCoeficienteCorrelacaoDenominador(int tamanho) {
                double denominadorSomatoria1 = 0;
                double denominadorSomatoria2 = 0;
                double denominadorSomatoria3 = 0;
                double denominadorSomatoria4 = 0;
                double dadoX;
                double dadoY;

                for (int i = 0; i < tamanho; i++) {
                        dadoX = listaDePares.obterElementoX(i);
                        dadoY = listaDePares.obterElementoY(i);

                        denominadorSomatoria1 += Math.pow(dadoX, 2);
                        denominadorSomatoria2 += dadoX;

                        denominadorSomatoria3 += Math.pow(dadoY, 2);
                        denominadorSomatoria4 += dadoY;
                }

                denominadorSomatoria2 = Math.pow(denominadorSomatoria2, 2);
                denominadorSomatoria4 = Math.pow(denominadorSomatoria4, 2);

                return Math.sqrt((tamanho * denominadorSomatoria1 - denominadorSomatoria2) * (tamanho * denominadorSomatoria3 - denominadorSomatoria4));
        }

        private double calcularCoeficienteCorrelacaoNumerador(int tamanho) {
                double numeradorSomatoria1 = 0;
                double numeradorSomatoria2 = 0;
                double numeradorSomatoria3 = 0;
                double dadoX;
                double dadoY;

                for (int i = 0; i < tamanho; i++) {
                        dadoX = listaDePares.obterElementoX(i);
                        dadoY = listaDePares.obterElementoY(i);

                        numeradorSomatoria1 += dadoX * dadoY;
                        numeradorSomatoria2 += dadoX;
                        numeradorSomatoria3 += dadoY;
                }

                return tamanho * numeradorSomatoria1 - numeradorSomatoria2 * numeradorSomatoria3;
        }

        public double calcularCoeficienteCorrelacao() {
                int tamanhoLista = listaDePares.tamanho();

                if (tamanhoLista == 0) {
                        throw new IllegalStateException("A lista está vazia. Não é possível calcular coeficiente de correlação.");
                }

                double numerador = 0;
                double denominador = 0;

                int tamanho = listaDePares.tamanho();
                numerador = calcularCoeficienteCorrelacaoNumerador(tamanho);
                denominador = calcularCoeficienteCorrelacaoDenominador(tamanho);

                return numerador / denominador;
        }

        public double calcularCoeficienteCorrelacaoAoQuadrado() {
                int tamanhoLista = listaDePares.tamanho();

                if (tamanhoLista == 0) {
                        throw new IllegalStateException("A lista está vazia. Não é possível calcular coeficiente de correlação ao quadrado.");
                }

                return Math.pow(calcularCoeficienteCorrelacao(), 2);
        }
}
