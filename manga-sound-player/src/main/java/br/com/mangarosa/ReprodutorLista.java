package br.com.mangarosa;

public class ReprodutorLista {
    private ListaReproducao listaReproducao;
    private int indiceAtual = 0;
    private boolean tocando = false;

    public void setListaReproducao(ListaReproducao lista){
        this.listaReproducao = lista;
        this.indiceAtual = 0;

    }

    public void play(){
        if (listaReproducao == null || listaReproducao.getMusicas().estaVazia()){
            System.out.println("Nenhuma musica pra tocar");
            return;
        }
        tocando = true;
        Musica atual = listaReproducao.getMusicas().get(indiceAtual);
        System.out.println("Tocando agora: " + atual.getTitulo() + " - " + atual.getArtista());
    }
    public void pause(){
        if (listaReproducao == null) return;

        if (indiceAtual < listaReproducao.getMusicas().tamanho() - 1){
            indiceAtual++;
        }
        else {
            indiceAtual = 0;
        }
        play();
    }

    public void passar(){
        if (listaReproducao == null) return;

        if (indiceAtual < listaReproducao.getMusicas().tamanho() - 1){
            indiceAtual++;
        }
        else {
            indiceAtual = 0;
        }
        play();
    }

    public void voltar(){
        if(indiceAtual > 0) {
            indiceAtual--;
        }
        else {
            indiceAtual = listaReproducao.getMusicas().tamanho() - 1;
        }
        play();
    }

    public void stop(){
        tocando = false;
        indiceAtual = 0;
        System.out.println("Reproduçao Pausada.");
    }

    public void restarLista(){
        indiceAtual = 0;
        play();S
    }
}
