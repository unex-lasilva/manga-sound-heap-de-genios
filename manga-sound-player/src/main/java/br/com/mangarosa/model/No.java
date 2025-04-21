package br.com.mangarosa.model;

public class No {

    private Object valor;
    private No prox;

    public No(Object valor, No prox) {
        this.valor = valor;
        this.prox = prox;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;

    private Musica musica;
    private No proximo;

    public No(Musica musica) {
        this.musica = musica;
        this.proximo = null;
    }

    public Musica getMusica() {
        return musica;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;

    }
}
