package br.com.mangarosa;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MangaSoundApplication {
    public static void main(String[] args) {
        MangaController controller = new MangaController();

        // Loop do menu
        while (true) {
            int choice = controller.mostrarMenu();
            try {
                switch (choice) {
                    case 1:
                        controller.adicionarMusica();
                        break;
                    case 2:
                        controller.criarPlaylist();
                        break;
                    case 3:
                        controller.editarPlaylist();
                        break;
                    case 4:
                        controller.tocarPlaylist();
                        break;
                    case 5:
                        System.out.println("Saindo do MangaSound. Até logo!");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                }
            } catch (UnsupportedAudioFileException e) {
                System.out.println("Formato de áudio não suportado: " + e.getMessage());
            } catch (LineUnavailableException e) {
                System.out.println("Linha de áudio indisponível: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}