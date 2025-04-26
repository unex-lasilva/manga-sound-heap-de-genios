package br.com.mangarosa.model;

public class ListaEncadeada {

    private No cabeca;
    private int tamanho;

    public void append(Object value) {
        No novo = new No(value);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.getProx() != null) {
                atual = atual.getProx();
            }
            atual.setProx(novo);
        }
        tamanho++;
    }

    public boolean remove(int position) {
        if (position < 0 || position >= tamanho || cabeca == null) return false;

        if (position == 0) {
            cabeca = cabeca.getProx();
        } else {
            No anterior = cabeca;
            for (int i = 0; i < position - 1; i++) {
                anterior = anterior.getProx();
            }
            anterior.setProx(anterior.getProx().getProx());
        }
        tamanho--;
        return true;
    }

    public boolean insertAt(int position, Object value) {
        if (position < 0 || position > tamanho) return false;

        No novo = new No(value);
        if (position == 0) {
            novo.setProx(cabeca);
            cabeca = novo;
        } else {
            No anterior = cabeca;
            for (int i = 0; i < position - 1; i++) {
                anterior = anterior.getProx();
            }
            novo.setProx(anterior.getProx());
            anterior.setProx(novo);
        }
        tamanho++;
        return true;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }

    public void addAll(ListaEncadeada lista) {
        for (int i = 0; i < lista.size(); i++) {
            this.append(lista.get(i));
        }
    }

    public int indexOf(Object value) {
        No atual = cabeca;
        int index = 0;
        while (atual != null) {
            if (atual.getValor().equals(value)) return index;
            atual = atual.getProx();
            index++;
        }
        return -1;
    }

    public boolean containsValue(Object value) {
        return indexOf(value) != -1;
    }

    public void clear() {
        cabeca = null;
        tamanho = 0;
    }

    public Object get(int position) {
        if (position < 0 || position >= tamanho) return null;

        No atual = cabeca;
        for (int i = 0; i < position; i++) {
            atual = atual.getProx();
        }
        return atual.getValor();
    }
}