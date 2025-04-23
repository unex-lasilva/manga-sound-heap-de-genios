package br.com.mangarosa.repositoriomusical;

import javax.sound.sampled.Clip;


public class ReprodutorLista {
        private ListaReproducao listaReproducao;
        private String status;
        private Clip clip;

    public ReprodutorLista() {
        String nome = "";
        this.listaReproducao = new ListaReproducao(nome);
         this.status = "Pausado";
    }
    public void adicionaClip(Clip clip) {
            listaReproducao.adicionarClip(clip);
    }
    public void play (){
            if (this.listaReproducao.isEmpty()){
            System.out.println("A lista de reprodução está sem nada");
            return;
        }
            this.status = "Tocando";
            this.clip= listaReproducao.getClip(0);
             System.out.println("Reproduzindo "+clip);
    }
    public void pause (){
            if (this.status.equals("Tocando")){
            this.status = "Pausado";
            System.out.println("Pausado");
        }

    }
    public void stop (){
            this.status = "Parou";
            System.out.println("Parou");
    }
    public void restartMusica() {
            if (this.status.equals("Pausado") || this.status.equals("Tocando")) {
            System.out.println("Reiniciando música");
        }
    }
    public void resetarLista () {
        System.out.println("Reiniciando lista de reprodução");
    }
}