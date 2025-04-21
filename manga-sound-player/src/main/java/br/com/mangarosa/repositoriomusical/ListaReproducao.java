package br.com.mangarosa.repositoriomusical;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

public class ListaReproducao {

    private List<Clip> clipes;


    public ListaReproducao() {
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
}
