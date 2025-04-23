package br.com.mangarosa.repositoriomusical;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

public class ListaReproducao {
    private List<Clip> clipes;
    String nome; // string nome pra poder ver o nome da playlist


    public ListaReproducao(String nome) {
        this.nome = nome; // inicializar a string
        clipes = new ArrayList<>();
    }


    public void adicionarClip(Clip clip) {
        clipes.add(clip);
    }


    public void removerClip(Clip clip) {
        clipes.remove(clip);
    }


    public boolean isEmpty() {
        return clipes.isEmpty();
    }


    public int size() {
        return clipes.size();
    }


    public Clip getClip(int index) {
        if (index >= 0 && index < clipes.size()) {
            return clipes.get(index);
        } else {
            return null;
        }
    }


    public void listarClipes() {
        for (Clip clip : clipes) {
            System.out.println(clip);
        }
    }

    // metodo pra puxar o nome da playlist
    public String getNome() {
        return nome;
    }
}
