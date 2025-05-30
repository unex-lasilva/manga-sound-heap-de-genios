package br.com.mangarosa.model;

public class Musica {

    private String titulo;
    private int duracao;
    private String path;
    private String artista;

    public Musica() {
    }

    public Musica(String titulo, String artista, String path, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.path = path;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}