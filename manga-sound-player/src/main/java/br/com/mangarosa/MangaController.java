package br.com.mangarosa;

import br.com.mangarosa.model.ListaEncadeada;
import br.com.mangarosa.model.Musica;
import br.com.mangarosa.repositoriomusical.ListaReproducao;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class MangaController {
    private ListaEncadeada musicas; // Guardar as músicas
    private ListaEncadeada clips;   // Guardar os clips correspondentes
    private ListaEncadeada playlists;
    private Scanner scanner;
    private Clip currentClip; // Clip atualmente em reprodução
    private long startTime;   // Tempo de início da reprodução

    public MangaController() {
        scanner = new Scanner(System.in);
        musicas = new ListaEncadeada();
        clips = new ListaEncadeada();
        playlists = new ListaEncadeada();
        currentClip = null;
    }

    public int mostrarMenu() {
        System.out.println("\nMenu do MangaSound:");
        System.out.println("1 - Adicionar música");
        System.out.println("2 - Criar playlist");
        System.out.println("3 - Editar playlist");
        System.out.println("4 - Tocar playlist");
        System.out.println("5 - Sair");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void adicionarMusica() throws IOException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        scanner.nextLine();
        System.out.print("Digite o caminho do arquivo .wav: ");
        String caminho = scanner.nextLine();
        System.out.print("Digite o título da música: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o nome do artista: ");
        String artista = scanner.nextLine();
        System.out.print("Digite a duração da música (em segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        // Criar uma música nova
        Musica musica = new Musica(titulo, artista, caminho, duracao);
        musicas.append(musica);

        // Criar um clip para essa música
        File arquivo = new File(caminho);
        AudioInputStream audio = AudioSystem.getAudioInputStream(arquivo);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clips.append(clip);
        System.out.println("Música adicionada: " + musica.getTitulo());
    }

    public void criarPlaylist() {
        scanner.nextLine();
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        ListaReproducao playlist = new ListaReproducao(nome);
        playlists.append(playlist);
        System.out.println("Playlist criada: " + nome);

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música disponível para adicionar!");
            return;
        }

        // Mostrar as músicas disponíveis
        System.out.println("Músicas disponíveis:");
        for (int i = 0; i < musicas.size(); i++) {
            Musica musica = (Musica) musicas.get(i);
            System.out.println(i + ": " + musica.getTitulo() + " - " + musica.getArtista());
        }
        System.out.print("Escolha uma música para adicionar (número, ou -1 para parar): ");
        int musicaEscolhida = scanner.nextInt();
        while (musicaEscolhida != -1) {
            if (musicaEscolhida >= 0 && musicaEscolhida < musicas.size()) {
                Clip clip = (Clip) clips.get(musicaEscolhida);
                playlist.adicionarClip(clip);
                System.out.println("Música adicionada à playlist!");
            } else {
                System.out.println("Número inválido!");
            }
            System.out.print("Escolha outra música para adicionar (número, ou -1 para parar): ");
            musicaEscolhida = scanner.nextInt();
        }
    }

    public void editarPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist para editar!");
            return;
        }

        // Mostrar as playlists
        System.out.println("Playlists disponíveis:");
        for (int i = 0; i < playlists.size(); i++) {
            ListaReproducao playlist = (ListaReproducao) playlists.get(i);
            System.out.println(i + ": " + playlist.getNome());
        }
        System.out.print("Escolha uma playlist (número): ");
        int playlistEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (playlistEscolhida < 0 || playlistEscolhida >= playlists.size()) {
            System.out.println("Número inválido!");
            return;
        }

        ListaReproducao playlist = (ListaReproducao) playlists.get(playlistEscolhida);
        System.out.println("O que você quer fazer?");
        System.out.println("1 - Adicionar música");
        System.out.println("2 - Remover música");
        System.out.println("3 - Mudar posição de música");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            // Adicionar música
            if (musicas.isEmpty()) {
                System.out.println("Nenhuma música disponível para adicionar!");
                return;
            }
            System.out.println("Músicas disponíveis:");
            for (int i = 0; i < musicas.size(); i++) {
                Musica musica = (Musica) musicas.get(i);
                System.out.println(i + ": " + musica.getTitulo());
            }
            System.out.print("Escolha uma música para adicionar (número): ");
            int musicaEscolhida = scanner.nextInt();
            if (musicaEscolhida >= 0 && musicaEscolhida < musicas.size()) {
                Clip clip = (Clip) clips.get(musicaEscolhida);
                playlist.adicionarClip(clip);
                System.out.println("Música adicionada!");
            } else {
                System.out.println("Número inválido!");
            }
        } else if (opcao == 2) {
            // Remover música
            if (playlist.size() == 0) {
                System.out.println("Playlist vazia, não há o que remover!");
                return;
            }
            System.out.println("Músicas na playlist:");
            for (int i = 0; i < playlist.size(); i++) {
                Clip clip = playlist.getClip(i);
                int clipIndex = clips.indexOf(clip);
                Musica musica = (Musica) musicas.get(clipIndex);
                System.out.println(i + ": " + musica.getTitulo());
            }
            System.out.print("Escolha uma música para remover (número): ");
            int musicaRemover = scanner.nextInt();
            if (musicaRemover >= 0 && musicaRemover < playlist.size()) {
                Clip clip = playlist.getClip(musicaRemover);
                playlist.removerClip(clip);
                System.out.println("Música removida!");
            } else {
                System.out.println("Número inválido!");
            }
        } else if (opcao == 3) {
            // Mudar posição
            if (playlist.size() < 2) {
                System.out.println("A playlist precisa ter pelo menos 2 músicas para mudar a posição!");
                return;
            }
            System.out.println("Músicas na playlist:");
            for (int i = 0; i < playlist.size(); i++) {
                Clip clip = playlist.getClip(i);
                int clipIndex = clips.indexOf(clip);
                Musica musica = (Musica) musicas.get(clipIndex);
                System.out.println(i + ": " + musica.getTitulo());
            }
            System.out.print("Escolha a música para mover (número): ");
            int musicaMover = scanner.nextInt();
            System.out.print("Nova posição (número): ");
            int novaPosicao = scanner.nextInt();
            if (musicaMover >= 0 && musicaMover < playlist.size() && novaPosicao >= 0 && novaPosicao < playlist.size()) {
                Clip clip = playlist.getClip(musicaMover);
                playlist.removerClip(clip);
                // Criar uma nova lista encadeada para reorganizar
                ListaEncadeada novaLista = new ListaEncadeada();
                int originalSize = playlist.size();
                for (int i = 0; i <= originalSize; i++) {
                    if (i == novaPosicao) {
                        novaLista.append(clip);
                    }
                    if (i < originalSize) {
                        Clip current = playlist.getClip(0);
                        novaLista.append(current);
                        playlist.removerClip(current);
                    }
                }
                // Atualizar a playlist com a nova ordem
                for (int i = 0; i < novaLista.size(); i++) {
                    playlist.adicionarClip((Clip) novaLista.get(i));
                }
                System.out.println("Música movida para a posição " + novaPosicao + "!");
            } else {
                System.out.println("Posição inválida!");
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void tocarPlaylist() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist para tocar!");
            return;
        }

        System.out.println("Playlists disponíveis:");
        for (int i = 0; i < playlists.size(); i++) {
            ListaReproducao playlist = (ListaReproducao) playlists.get(i);
            System.out.println(i + ": " + playlist.getNome());
        }
        System.out.print("Escolha uma playlist (número): ");
        int playlistEscolhida = scanner.nextInt();

        if (playlistEscolhida < 0 || playlistEscolhida >= playlists.size()) {
            System.out.println("Número inválido!");
            return;
        }

        ListaReproducao playlist = (ListaReproducao) playlists.get(playlistEscolhida);
        if (playlist.size() == 0) {
            System.out.println("Playlist vazia!");
            return;
        }

        int currentIndex = 0;
        while (currentIndex < playlist.size()) {
            currentClip = playlist.getClip(currentIndex);
            int clipIndex = clips.indexOf(currentClip);
            Musica musica = (Musica) musicas.get(clipIndex);
            System.out.println("Tocando: " + musica.getTitulo() + " - " + musica.getArtista());
            currentClip.setFramePosition(0);
            startTime = System.currentTimeMillis();
            currentClip.start();

            // Controles de reprodução
            boolean playing = true;
            while (playing) {
                System.out.println("1 - Parar | 2 - Próxima | 3 - Anterior | 4 - Continuar tocando");
                System.out.print("Escolha: ");
                int opcao = scanner.nextInt();
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tempo em segundos

                switch (opcao) {
                    case 1: // Parar
                        currentClip.stop();
                        playing = false;
                        currentIndex = playlist.size(); // Sair do loop
                        break;
                    case 2: // Próxima
                        currentClip.stop();
                        currentIndex++;
                        playing = false;
                        break;
                    case 3: // Anterior
                        currentClip.stop();
                        if (elapsedTime > 10) {
                            // Reiniciar a música atual
                            currentClip.setFramePosition(0);
                            currentClip.start();
                            startTime = System.currentTimeMillis();
                        } else {
                            // Tocar a música anterior
                            currentIndex = Math.max(0, currentIndex - 1);
                            playing = false;
                        }
                        break;
                    case 4: // Continuar
                        if (!currentClip.isRunning()) {
                            currentClip.start();
                            startTime = System.currentTimeMillis();
                        }
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
        System.out.println("Fim da playlist!");
    }
}