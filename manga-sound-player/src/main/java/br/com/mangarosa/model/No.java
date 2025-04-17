package br.com.mangarosa.model;

public class No {
    private Musica musica;
    private No proximo;

    public No(Musica musica){
        this.musica = musica;
        this.proximo = null;
    }

    public Musica getMusica() {
        return musica;
    }

    public No getProximo(){
        return proximo;
    }

    public void setProximo(No proximo){
        this.proximo = proximo;
    }
}
