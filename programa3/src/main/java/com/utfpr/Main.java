package com.utfpr;

public final class Main {
        private Main() {
                throw new AssertionError("Esta classe não deve ser instanciada.");
        }

        public static void main(String[] args) {
                ListaEncadeada lista = new ListaEncadeada();

                lista.adicionarNo(130, 186, 0);
                lista.adicionarNo(650, 699, 0);
                lista.adicionarNo(99, 132, 0);
                lista.adicionarNo(150, 272, 0);
                lista.adicionarNo(128, 291, 0);
                lista.adicionarNo(302, 331, 0);
                lista.adicionarNo(95, 199, 0);
                lista.adicionarNo(945, 1890, 0);
                lista.adicionarNo(368, 788, 0);
                lista.adicionarNo(961, 1601, 0);

                RegressaoLinear regressao = new RegressaoLinear(lista);

                System.out.println(regressao.calcularBzero());
                System.out.println(regressao.calcularBum());
                System.out.println(regressao.calcularPrevisaoMelhorada(386));
                System.out.println(regressao.calcularCoeficienteCorrelacao());
                System.out.println(regressao.calcularCoeficienteCorrelacaoAoQuadrado());
        }
}
