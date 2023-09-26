package com.utfpr;

public class ListaEncadeada {
        private Cabeca cabeca;

        private final String mensagemExcecao = "Posição maior ou igual ao tamanho da lista";

        public ListaEncadeada() {
                cabeca = new Cabeca();
        }

        private class No {
                private double dadoX;

                private double dadoY;

                private No proximo;

                public No(double dadoX, double dadoY) {
                        this.dadoX = dadoX;
                        this.dadoY = dadoY;
                        this.proximo = null;
                }

                public double getX() {
                        return dadoX;
                }

                public double getY() {
                        return dadoY;
                }

                public No getProximo() {
                        return proximo;
                }

                public void setProximo(No proximo) {
                        this.proximo = proximo;
                }
        }

        private class Cabeca {
                private No primeiro;

                private No ultimo;

                public Cabeca() {
                        this.primeiro = null;
                        this.ultimo = null;
                }

                public No getPrimeiro() {
                        return primeiro;
                }

                public void setPrimeiro(No proximo) {
                        this.primeiro = proximo;
                }

                public No getUltimo() {
                        return ultimo;
                }

                public void setUltimo(No proximo) {
                        this.ultimo = proximo;
                }
        }

        public void adicionarNo(double dadoX, double dadoY, int posicao) {
                No novo = new No(dadoX, dadoY); // criar novo no
                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

                int posicao_atual = 0;

                // executa enquanto a posicao nao for a posicao passada e for diferente do fim
                // da lista
                while (posicao_atual < posicao - 1 && no_atual.getProximo() != null) {
                        posicao_atual++;
                        no_atual = no_atual.getProximo();
                }

                // insercao no inicio da lista quando a lista está vazia
                if (posicao == 0 && cabeca.getPrimeiro() == null) {
                        cabeca.setPrimeiro(novo);
                        cabeca.setUltimo(novo);

                        // insercao no inicio da lista quando ela já tá ocupada
                } else if (posicao == 0 && cabeca.getPrimeiro() != null) {
                        novo.setProximo(cabeca.getPrimeiro());
                        cabeca.setPrimeiro(novo);

                        // insercao no fim da lista
                } else if (no_atual.getProximo() == null) {
                        no_atual.setProximo(novo);
                        cabeca.setUltimo(novo);

                        // insercao no meio da lista
                } else {
                        novo.setProximo(no_atual.getProximo());
                        no_atual.setProximo(novo);
                }
        }

        public double obterElementoX(int posicao) {
                if (posicao >= tamanho()) {
                        throw new IndexOutOfBoundsException(mensagemExcecao);
                }

                No no_atual = cabeca.getPrimeiro();
                int posicao_atual = 0;

                while (posicao_atual < posicao) {
                        no_atual = no_atual.getProximo();
                        posicao_atual++;
                }

                return no_atual.getX();
        }

        public double obterElementoY(int posicao) {
                if (posicao >= tamanho()) {
                        throw new IndexOutOfBoundsException(mensagemExcecao);
                }

                No no_atual = cabeca.getPrimeiro();
                int posicao_atual = 0;

                while (posicao_atual < posicao) {
                        no_atual = no_atual.getProximo();
                        posicao_atual++;
                }

                return no_atual.getY();
        }

        public void adicionarNoFinalLista(double dadoX, double dadoY) {
                adicionarNo(dadoX, dadoY, tamanho());
        }

        public void imprimirLista() {
                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no
                boolean primeiroElemento = true;

                String separador = ", ";
                String inicioLista = "[";

                System.out.print(inicioLista);

                // executa enquanto for diferente do fim da lista
                while (no_atual != null) {
                        if (!primeiroElemento) {
                                System.out.print(separador);
                        }

                        System.out.print(inicioLista + no_atual.getX() + separador + no_atual.getY() + "] ");

                        no_atual = no_atual.getProximo();

                        primeiroElemento = false;
                }

                System.out.println("]");
        }

        public double mediaListaX() {
                if (cabeca.getPrimeiro() == null) { // Lista vazia
                        return 0;
                }

                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

                double soma = 0;
                int qtde_elementos = 0;

                // enquanto for diferente de fim
                while (no_atual != null) {
                        soma += no_atual.getX();
                        qtde_elementos++;
                        no_atual = no_atual.getProximo();
                }

                return (double) soma / qtde_elementos;
        }

        public double mediaListaY() {
                if (cabeca.getPrimeiro() == null) { // Lista vazia
                        return 0;
                }

                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

                double soma = 0;
                int qtde_elementos = 0;

                // enquanto for diferente de fim
                while (no_atual != null) {
                        soma += no_atual.getY();
                        qtde_elementos++;
                        no_atual = no_atual.getProximo();
                }

                return (double) soma / qtde_elementos;
        }

        public double desvioPadraoAmostralListaX() {
                if (cabeca.getPrimeiro() == null) { // Lista vazia
                        return 0;
                }

                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

                double media = mediaListaX();

                double soma = 0;
                int qtde_elementos = 0;

                // enquanto for diferente de fim
                while (no_atual != null) {
                        soma += Math.pow((no_atual.getX() - media), 2);

                        qtde_elementos++;
                        no_atual = no_atual.getProximo();
                }

                return (double) Math.sqrt(soma / (qtde_elementos - 1));
        }

        public double desvioPadraoAmostralListaY() {
                if (cabeca.getPrimeiro() == null) { // Lista vazia
                        return 0;
                }

                No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

                double media = mediaListaY();

                double soma = 0;

                int qtde_elementos = 0;

                // enquanto for diferente de fim
                while (no_atual != null) {
                        soma += Math.pow((no_atual.getY() - media), 2);

                        qtde_elementos++;
                        no_atual = no_atual.getProximo();
                }

                return (double) Math.sqrt(soma / (qtde_elementos - 1));
        }

        public int tamanho() {
                No no_atual = cabeca.getPrimeiro(); // pegar primeiro nó
                int tamanho = 0;

                // enquanto for diferente de fim
                while (no_atual != null) {
                        tamanho++;
                        no_atual = no_atual.getProximo();
                }
                return tamanho;
        }
}
