package br.com.mangarosa;

import br.com.mangarosa.collections.ListaEncadeada;
import br.com.mangarosa.model.Musica;

public class MangaController {
    private ListaEncadeada<Musica> repositorioMusica;
    private ListaEncadeada<ListaReproducao> listasReproducao;
    private ListaEncadeada<String> artistas;
    private ReprodutorLista reprodutorLista;

}
