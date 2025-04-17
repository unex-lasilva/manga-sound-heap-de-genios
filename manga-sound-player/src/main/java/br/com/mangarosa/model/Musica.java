package br.com.mangarosa.model;

public class Musica {
    private String nome;
    private String caminhoArquivo;

    public Musica(String nome, String CaminhoArquivo) {
        this.nome = nome;
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    @Override
    public String toString(){
        return "Música: "+ nome + " | Caminho: " + caminhoArquivo;
    }
}
