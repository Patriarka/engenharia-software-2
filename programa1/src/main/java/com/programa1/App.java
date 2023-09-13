package com.programa1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main( String[] args ) {
        ListaEncadeada lista = new ListaEncadeada();

        // lê os dados de entrada de um arquivo
        try {
            BufferedReader br = new BufferedReader(new FileReader("numeros.txt"));

            String linha;

            while ((linha = br.readLine()) != null) {
                double numero = Double.parseDouble(linha);
                lista.adicionarNo(numero, lista.tamanho()); 
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        lista.imprimirLista();

        System.out.println(lista.mediaLista());
        System.out.println(lista.desvioPadraoAmostralLista());
    }
}
