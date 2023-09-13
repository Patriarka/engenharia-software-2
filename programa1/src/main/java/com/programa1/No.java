package com.programa1;

public class No {
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
