package br.com.mangarosa.model;

public class Musica {

    private String titulo;
    private int duracao;
    private String path;
    private String artista;

    public Musica(String titulo, int duracao, String path, String artista) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.path = path;
        this.artista = artista;
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
      
    private String nome;
    private String caminho;

    public Musica(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public String toString() {
        return nome + " (" + caminho + ")";

    }
}
