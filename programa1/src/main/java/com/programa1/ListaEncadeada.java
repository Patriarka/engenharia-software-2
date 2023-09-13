package com.programa1;

public class ListaEncadeada {
    private Cabeca cabeca;

    public ListaEncadeada() {
        cabeca = new Cabeca();
    }

    private class No {
        private double dado;
        private No proximo;
        
        public No(double dado) {
            this.dado = dado;
            this.proximo = null; 
        }
    
        public double getDado() {
            return dado;
        }
    
        public void setDado(double dado) {
            this.dado = dado;
        }
    
        public No getProximo() {
            return proximo;
        }
    
        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

    private class Cabeca {
        private No primeiro = null;
        private No ultimo = null;
    
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

    public void adicionarNo(double dado, int posicao) {
        if (posicao < 0) {
            throw new IllegalArgumentException("Posição não pode ser negativa.");
        }

        No novo = new No(dado); // criar novo no
        No no_atual = cabeca.getPrimeiro(); // pegar primeiro no

        int posicao_atual = 0;

        // executa enquanto a posicao nao for a posicao passada e for diferente do fim da lista
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

    public void adicionarNoFinalLista(double dado) {
        adicionarNo(dado, tamanho()); 
    }

    public void imprimirLista() {
        No no_atual = cabeca.getPrimeiro(); // pegar primeiro no
        boolean primeiroElemento = true;

        System.out.print("[");

        // executa enquanto for diferente do fim da lista
        while (no_atual != null) {
            if (!primeiroElemento) {
                System.out.print(", "); 
            }

            System.out.print(no_atual.getDado());
            
            no_atual = no_atual.getProximo();

            primeiroElemento = false;
        }

        System.out.println("]");
    }

    public double mediaLista() {
        if (cabeca.getPrimeiro() == null) // Lista vazia
            return 0;

        No no_atual = cabeca.getPrimeiro(); // pegar primeiro no
        
        double soma = 0;
        int qtde_elementos = 0;

        // enquanto for diferente de fim
        while (no_atual != null) {
            soma += no_atual.getDado();
            qtde_elementos++;
            no_atual = no_atual.getProximo();
        }

        return (double) soma / qtde_elementos;
    }

    public double desvioPadraoAmostralLista() {
        if (cabeca.getPrimeiro() == null) // Lista vazia
            return 0;

        No no_atual = cabeca.getPrimeiro(); // pegar primeiro no
        
        double media = mediaLista();

        double soma = 0;
        
        int qtde_elementos = 0;

        // enquanto for diferente de fim
        while (no_atual != null) {
            soma += Math.pow((no_atual.getDado() - media), 2);

            qtde_elementos++;
            no_atual = no_atual.getProximo();
        }

        return (double) Math.sqrt(soma / (qtde_elementos - 1));
    }

    public double obterElemento(int posicao) {
        if (posicao >= tamanho()) {
            throw new IndexOutOfBoundsException("Posição maior ou igual ao tamanho da lista");
        }

        No no_atual = cabeca.getPrimeiro(); 
        int posicao_atual = 0;

        while (posicao_atual < posicao) {
            no_atual = no_atual.getProximo();
            posicao_atual++;
        }

        return no_atual.dado;
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