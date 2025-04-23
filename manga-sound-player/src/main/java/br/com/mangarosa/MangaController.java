package br.com.mangarosa;
import br.com.mangarosa.collections.ListaEncadeada;
import br.com.mangarosa.model.Musica;
import br.com.mangarosa.repositoriomusical.ListaReproducao;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class MangaController {
    private ListaEncadeada musicas; // guardar as musicas pra mostrar os titulos
    private ListaEncadeada clips; // guardar os clips pra usar na playlist
    private ListaEncadeada playlists;
    private Scanner scanner;

    public MangaController() {
        scanner = new Scanner(System.in);
        musicas = new ListaEncadeada(); // lista pra guardar musicas
        clips = new ListaEncadeada(); // lista pra guardar os clips
        playlists = new ListaEncadeada(); // lista pra guardar playlists
    }

    public int mostrarMenu() {
        System.out.println("Menu do MangaSound:");
        System.out.println("1 - Adicionar musica");
        System.out.println("2 - Criar playlist");
        System.out.println("3 - Editar playlist");
        System.out.println("4 - Tocar playlist");
        System.out.println("5 - Sair");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void adicionarMusica() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        scanner.nextLine();
        System.out.print("Digita o caminho do arquivo .wav: ");
        String caminho = scanner.nextLine();

        // criar uma musica nova
        Musica musica = new Musica(caminho, 0, caminho, "Nenhum artista");
        musicas.append(musica); // adicionar na lista de musicas

        // criar um clip pra essa musica
        File arquivo = new File(caminho);
        AudioInputStream audio = AudioSystem.getAudioInputStream(arquivo);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clips.append(clip); // guardar o clip
        System.out.println("Musica adicionada: " + musica.getTitulo());
    }

    public void criarPlaylist() {
        scanner.nextLine();
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        ListaReproducao playlist = new ListaReproducao(nome);
        playlists.append(playlist); // adicionar na lista de playlists
        System.out.println("Playlist criada: " + nome);

        // mostrar as musicas disponiveis
        System.out.println("Musicas disponiveis:");
        for (int i = 0; i < musicas.size(); i++) {
            Musica musica = (Musica) musicas.get(i);
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

    public void editarPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist pra editar!");
            return;
        }

        // mostrar as playlists
        System.out.println("Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            ListaReproducao playlist = (ListaReproducao) playlists.get(i);
            System.out.println(i + ": " + playlist.getNome());
        }
        System.out.print("Escolha uma playlist (numero): ");
        int playlistEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (playlistEscolhida < 0 || playlistEscolhida >= playlists.size()) {
            System.out.println("Numero errado!");
            return;
        }

        ListaReproducao playlist = (ListaReproducao) playlists.get(playlistEscolhida);
        System.out.println("O que voce quer fazer?");
        System.out.println("1 - Adicionar musica");
        System.out.println("2 - Remover musica");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            // adicionar musica
            System.out.println("Musicas disponiveis:");
            for (int i = 0; i < musicas.size(); i++) {
                Musica musica = (Musica) musicas.get(i);
                System.out.println(i + ": " + musica.getTitulo());
            }
            System.out.print("Escolha uma musica pra adicionar (numero): ");
            int musicaEscolhida = scanner.nextInt();
            if (musicaEscolhida >= 0 && musicaEscolhida < musicas.size()) {
                Clip clip = (Clip) clips.get(musicaEscolhida);
                playlist.adicionarClip(clip);
                System.out.println("Musica adicionada!");
            } else {
                System.out.println("Numero errado!");
            }
        } else if (opcao == 2) {
            // remover musica
            if (playlist.size() == 0) {
                System.out.println("Playlist vazia, nao tem o que remover!");
                return;
            }
            System.out.println("Musicas na playlist:");
            for (int i = 0; i < playlist.size(); i++) {
                Musica musica = (Musica) musicas.get(i);
                System.out.println(i + ": " + musica.getTitulo());
            }
            System.out.print("Escolha uma musica pra remover (numero): ");
            int musicaRemover = scanner.nextInt();
            if (musicaRemover >= 0 && musicaRemover < playlist.size()) {
                Clip clip = playlist.getClip(musicaRemover);
                playlist.removerClip(clip);
                System.out.println("Musica removida!");
            } else {
                System.out.println("Numero errado!");
            }
        } else {
            System.out.println("Opcao errada!");
        }
    }

    public void tocarPlaylist() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
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
            Clip clip = playlist.getClip(i);
            if (clip != null) {
                Musica musica = (Musica) musicas.get(i);
                System.out.println("Tocando: " + musica.getTitulo());
                clip.setFramePosition(0);
                clip.start();
                try {
                    Thread.sleep(5000);
                    clip.stop();
                } catch (Exception e) {
                    System.out.println("Deu erro ao tocar: " + e);
                }
            }
        }
    }

}