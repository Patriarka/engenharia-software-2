package com.programa1;

public class Cabeca {
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
