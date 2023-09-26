package com.utfpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegressaoLinearTest {
        private ListaEncadeada lista;

        @BeforeEach
        void setUp() {
                lista = new ListaEncadeada();
        }

        @Test
        public void testePrimeiroCaso() {
                lista.adicionarNoFinalLista(130, 186);
                lista.adicionarNoFinalLista(650, 699);
                lista.adicionarNoFinalLista(99, 132);
                lista.adicionarNoFinalLista(150, 272);
                lista.adicionarNoFinalLista(128, 291);
                lista.adicionarNoFinalLista(302, 331);
                lista.adicionarNoFinalLista(95, 199);
                lista.adicionarNoFinalLista(945, 1890);
                lista.adicionarNoFinalLista(368, 788);
                lista.adicionarNoFinalLista(961, 1601);

                RegressaoLinear regressao = new RegressaoLinear(lista);

                double bZero = regressao.calcularBzero();
                bZero = Math.round(bZero * 100.0) / 100.0;
                assertEquals(-22.55, bZero, 0.001);

                double bUm = regressao.calcularBum();
                bUm = Math.round(bUm * 1000.0) / 1000.0;
                assertEquals(1.7279, bUm, 0.001);

                double previsaoMelhorada = regressao.calcularPrevisaoMelhorada(386);
                previsaoMelhorada = Math.round(previsaoMelhorada * 100.0) / 100.0;
                assertEquals(644.429, previsaoMelhorada, 0.001);

                double coeficienteCorrelacao = regressao.calcularCoeficienteCorrelacao();
                coeficienteCorrelacao = Math.round(coeficienteCorrelacao * 1000.0) / 1000.0;
                assertEquals(0.9545, coeficienteCorrelacao, 0.001);

                double coeficienteCorrelacaoAoQuadrado = regressao.calcularCoeficienteCorrelacaoAoQuadrado();
                coeficienteCorrelacaoAoQuadrado = Math.round(coeficienteCorrelacaoAoQuadrado * 1000.0) / 1000.0;
                assertEquals(0.9111, coeficienteCorrelacaoAoQuadrado, 0.001);
        }

        @Test
        public void testeSegundoCaso() {
                lista.adicionarNoFinalLista(130, 15);
                lista.adicionarNoFinalLista(650, 69.9);
                lista.adicionarNoFinalLista(99, 6.5);
                lista.adicionarNoFinalLista(150, 22.4);
                lista.adicionarNoFinalLista(128, 28.4);
                lista.adicionarNoFinalLista(302, 65.9);
                lista.adicionarNoFinalLista(95, 19.4);
                lista.adicionarNoFinalLista(945, 198.7);
                lista.adicionarNoFinalLista(368, 38.8);
                lista.adicionarNoFinalLista(961, 138.2);

                RegressaoLinear regressao = new RegressaoLinear(lista);

                double bZero = regressao.calcularBzero();
                assertEquals(-4.039, bZero, 0.001);

                double bUm = regressao.calcularBum();
                assertEquals(0.1681, bUm, 0.001);

                double previsaoMelhorada = regressao.calcularPrevisaoMelhorada(386);
                assertEquals(60.858, previsaoMelhorada, 0.001);

                double coeficienteCorrelacao = regressao.calcularCoeficienteCorrelacao();

                assertEquals(0.9333, coeficienteCorrelacao, 0.001);

                double coeficienteCorrelacaoAoQuadrado = regressao.calcularCoeficienteCorrelacaoAoQuadrado();
                assertEquals(0.8710, coeficienteCorrelacaoAoQuadrado, 0.001);
        }

        @Test
        public void testeTerceiroCaso() {
                lista.adicionarNoFinalLista(163, 186);
                lista.adicionarNoFinalLista(765, 699);
                lista.adicionarNoFinalLista(141, 132);
                lista.adicionarNoFinalLista(166, 272);
                lista.adicionarNoFinalLista(137, 291);
                lista.adicionarNoFinalLista(355, 331);
                lista.adicionarNoFinalLista(136, 199);
                lista.adicionarNoFinalLista(1206, 1890);
                lista.adicionarNoFinalLista(433, 788);
                lista.adicionarNoFinalLista(1130, 1601);

                RegressaoLinear regressao = new RegressaoLinear(lista);

                double bZero = regressao.calcularBzero();
                bZero = Math.round(bZero * 100.0) / 100.0;
                assertEquals(-23.92, bZero, 0.001);

                double bUm = regressao.calcularBum();
                assertEquals(1.43097, bUm, 0.001);

                double previsaoMelhorada = regressao.calcularPrevisaoMelhorada(386);
                assertEquals(528.4294, previsaoMelhorada, 0.001);

                double coeficienteCorrelacao = regressao.calcularCoeficienteCorrelacao();
                assertEquals(0.9631, coeficienteCorrelacao, 0.001);

                double coeficienteCorrelacaoAoQuadrado = regressao.calcularCoeficienteCorrelacaoAoQuadrado();
                assertEquals(0.9276, coeficienteCorrelacaoAoQuadrado, 0.001);
        }

        @Test
        public void testeQuartoCaso() {
                lista.adicionarNoFinalLista(163, 15.0);
                lista.adicionarNoFinalLista(765, 69.9);
                lista.adicionarNoFinalLista(141, 6.5);
                lista.adicionarNoFinalLista(166, 22.4);
                lista.adicionarNoFinalLista(137, 28.4);
                lista.adicionarNoFinalLista(355, 65.9);
                lista.adicionarNoFinalLista(136, 19.4);
                lista.adicionarNoFinalLista(1206, 198.7);
                lista.adicionarNoFinalLista(433, 38.8);
                lista.adicionarNoFinalLista(1130, 138.2);

                RegressaoLinear regressao = new RegressaoLinear(lista);

                double bZero = regressao.calcularBzero();
                assertEquals(-4.604, bZero, 0.001);

                double bUm = regressao.calcularBum();
                assertEquals(0.140164, bUm, 0.001);

                double previsaoMelhorada = regressao.calcularPrevisaoMelhorada(386);
                assertEquals(49.4994, previsaoMelhorada, 0.001);

                double coeficienteCorrelacao = regressao.calcularCoeficienteCorrelacao();
                assertEquals(0.9480, coeficienteCorrelacao, 0.001);

                double coeficienteCorrelacaoAoQuadrado = regressao.calcularCoeficienteCorrelacaoAoQuadrado();
                assertEquals(0.8988, coeficienteCorrelacaoAoQuadrado, 0.001);
        }

        @Test
        public void testeCalcularRegressaoComListaVazia() {
                RegressaoLinear regressao = new RegressaoLinear(lista);

                assertThrows(IllegalStateException.class, () -> {
                        regressao.calcularBzero();
                });

                assertThrows(IllegalStateException.class, () -> {
                        regressao.calcularBum();
                });

                assertThrows(IllegalStateException.class, () -> {
                        regressao.calcularCoeficienteCorrelacao();
                });

                assertThrows(IllegalStateException.class, () -> {
                        regressao.calcularCoeficienteCorrelacaoAoQuadrado();
                });

                assertThrows(IllegalStateException.class, () -> {
                        regressao.calcularPrevisaoMelhorada(386);
                });
        }
}
