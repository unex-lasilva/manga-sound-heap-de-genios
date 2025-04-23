package br.com.mangarosa;
import br.com.mangarosa.collections.ListaEncadeada;
import br.com.mangarosa.model.Musica;
import br.com.mangarosa.repositoriomusical.ListaReproducao;
import java.util.Scanner;
import javax.sound.sampled.*;

public class MangaController {
    // chamei tres listas e importei o scanner
    private ListaEncadeada musicas;
    private ListaEncadeada playlists;
    private ListaEncadeada clips;
    private Scanner scanner;

    public MangaController() {
        scanner = new Scanner(System.in);
        // listas pra guardar os atributos
        musicas = new ListaEncadeada();
        clips = new ListaEncadeada();
        playlists = new ListaEncadeada();
    }

    // mostra o menu e retorna o numero que o usuario colocou
    public int mostrarMenu() {
        System.out.println("Menu do MangaSound:");
        System.out.println("1 - Adicionar musica");
        System.out.println("2 - Criar playlist");
        System.out.println("3 - Tocar playlist");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    // funcao pra adicionar musica
    public void adicionarMusica() {
        scanner.nextLine();
        System.out.print("Digita o caminho do arquivo .wav: ");
        String caminho = scanner.nextLine();

        // criar uma musica nova
        Musica musica = new Musica(caminho, 0, caminho, "Nenhum artista");
        musicas.append(musica); // adicionar na lista de musicas
    }

    public void criarPlaylist() {
        scanner.nextLine();
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        ListaReproducao playlist = new ListaReproducao(nome); // cria uma nova lista de rproducao
        playlists.append(playlist); // adicionar na lista de playlists
        System.out.println("Playlist criada: " + nome);

        // mostrar as musicas disponiveis
        System.out.println("Musicas disponiveis:");
        for (int i = 0; i < musicas.size(); i++) {
            Musica musica = (Musica) musicas.get(i); // cria, chama e atribui o objeto a classe dele
            System.out.println(i + ": " + musica.getTitulo());
        }
        System.out.print("Escolha uma musica pra adicionar (numero): ");
        int musicaEscolhida = scanner.nextInt();
        if (musicaEscolhida >= 0 && musicaEscolhida < musicas.size()) {
            Clip clip = (Clip) clips.get(musicaEscolhida); // pegar o clip correspondente
            playlist.adicionarClip(clip); // adicionar o clip na playlist
            System.out.println("Musica adicionada na playlist!");
        } else {
            System.out.println("Numero errado!");
        }
    }

    public void tocarPlaylist() {
        // se a lista daplaylist estiver vazia, retorna o print
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist pra tocar!");
            return;
        }

        System.out.println("Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            ListaReproducao playlist = (ListaReproducao) playlists.get(i);
            System.out.println(i + ": " + playlist.getNome());
        }
        System.out.print("Escolha uma playlist (numero): ");
        int playlistEscolhida = scanner.nextInt();

        if (playlistEscolhida < 0 || playlistEscolhida >= playlists.size()) {
            System.out.println("Numero errado!");
            return;
        }

        ListaReproducao playlist = (ListaReproducao) playlists.get(playlistEscolhida);
        if (playlist.size() == 0) {
            System.out.println("Playlist vazia!");
            return;
        }

        // tocar cada clip da playlist
        for (int i = 0; i < playlist.size(); i++) {
            Clip clip = playlist.getClip(i); // usar getClip pra pegar o index
            if (clip != null) {
                Musica musica = (Musica) musicas.get(i);
                System.out.println(i + ": " + musica.getTitulo()); // usar a lista de musicas pra mostrar o titulo
                clip.setFramePosition(0); // voltar pro começo do clip
                clip.start();
            }
        }
    }
}