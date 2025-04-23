package br.com.mangarosa;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MangaSoundApplication {
    public static void main(String[] args) {
        MangaController controller = new MangaController(); // criar o controlador

    // loop do menu
    while (true) {
        int choice = controller.mostrarMenu();
        try {
            switch (choice) {
                case 1:
                    controller.adicionarMusica(); // adicionar musica
                    break;
                case 2:
                    controller.criarPlaylist(); // criar playlist
                    break;
                case 3:
                    controller.editarPlaylist(); // editar playlist, vamos adicionar esse metodo
                    break;
                case 4:
                    controller.tocarPlaylist(); // tocar playlist
                    break;
                case 5:
                    System.out.println("Saindo do MangaSound. Tchau!");
                    return; // sair do programa
                default:
                    System.out.println("Opcao invalida. Tenta de novo!");
            }
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println("Deu erro: " + e);
            // pesquisar como fazer isso pra implementar depos
        }
    }
}

}