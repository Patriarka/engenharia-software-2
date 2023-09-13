package com.programa1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private ListaEncadeada lista;

    @Before
    public void setUp() {
        lista = new ListaEncadeada();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdicionarElementoPosicaoNegativa() {
        lista.adicionarNo(10.0, -1);
    }

    @Test
    public void testMediaListaVazia() {
        assertEquals(0.0, lista.mediaLista(), 0.001); 
    }

    @Test
    public void testDesvioPadraoAmostralListaVazia() {
        assertEquals(0.0, lista.desvioPadraoAmostralLista(), 0.001);
    }

    @Test
    public void testAdicionarNoInicio() {
        lista.adicionarNo(10, 0);
        assertEquals(10, lista.obterElemento(0), 0.001);
    }

    @Test
    public void testAdicionarNoMeio() {
        lista.adicionarNo(10, 0);
        lista.adicionarNo(30, 1);
        lista.adicionarNo(20, 1);

        assertEquals(20, lista.obterElemento(1), 0.001);
    }

    @Test
    public void testAdicionarNoFim() {
        lista.adicionarNo(10, 0);
        lista.adicionarNo(20, 1);
        lista.adicionarNo(30, 2);

        assertEquals(30, lista.obterElemento(2), 0.001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void obterElementoPosicaoMaiorQueTamanho() {
        lista.obterElemento(1);
    }

    @Test
    public void testCalculoMediaEDesvioPadrao_Caso1 () {
        ListaEncadeada lista = new ListaEncadeada();
        lista.adicionarNo(160, 0);
        lista.adicionarNo(591, 1);
        lista.adicionarNo(114, 2);
        lista.adicionarNo(229, 3);
        lista.adicionarNo(230, 4);
        lista.adicionarNo(270, 5);
        lista.adicionarNo(128, 6);
        lista.adicionarNo(1657, 7);
        lista.adicionarNo(624, 8);
        lista.adicionarNo(1503, 9);

        double mediaEsperada = 550.6; 
        double mediaCalculada = lista.mediaLista();

        assertEquals(mediaEsperada, mediaCalculada, 0.01);

        double desvioPadraoAmostralListaEsperado = 572.03;
        double desvioPadraoAmostralListaCalculado = lista.desvioPadraoAmostralLista();

        assertEquals(desvioPadraoAmostralListaEsperado, desvioPadraoAmostralListaCalculado, 0.01);
    }

    @Test
    public void testCalculoMediaEDesvioPadrao_Caso2() {
        ListaEncadeada lista = new ListaEncadeada();
        
        lista.adicionarNo(15.0, 0);
        lista.adicionarNo(69.9, 1);
        lista.adicionarNo(6.5, 2);
        lista.adicionarNo(22.4, 3);
        lista.adicionarNo(28.4, 4);
        lista.adicionarNo(65.9, 5);
        lista.adicionarNo(19.4, 6);
        lista.adicionarNo(198.7, 7);
        lista.adicionarNo(38.8, 8);
        lista.adicionarNo(138.2, 9);

        double mediaEsperada = 60.32;
        double mediaCalculada = lista.mediaLista();

        assertEquals(mediaEsperada, mediaCalculada, 0.01);

        double desvioPadraoAmostralListaEsperado = 62.26;
        double desvioPadraoAmostralListaCalculado = lista.desvioPadraoAmostralLista();

        assertEquals(desvioPadraoAmostralListaEsperado, desvioPadraoAmostralListaCalculado, 0.01);
    }
}
