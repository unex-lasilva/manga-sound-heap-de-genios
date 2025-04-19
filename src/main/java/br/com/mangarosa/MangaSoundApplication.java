package br.com.mangarosa;

import br.com.mangarosa.model.MangaController;

public class MangaSoundApplication
{
    public static void main( String[] args ) {
        MangaController controller = new MangaController();
        System.out.println( "Welcome to Manga Sound Player" );

        // descobri || int opcaoEscolhida = 1; || temporario ate eu descobrir como puxar da outra classe

        // esse switch vai pegar o numero que foi escolhido na funcao de mostrar o menu, criado na classe MangaController
        // ja o while vai fazer isso ficar rodando o tempo inteiro
        // tambem falta criar os metodos que vao fazer cada coisa, mas ja dei um nome pra cada
        while (true){
            int opcaoEscolhida = controller.mostrarMenu();
            switch (opcaoEscolhida){
                case 1:
                    System.out.println("Adicionando musica!");
                    // controller.adicionarMusica();
                    break;
                case 2:
                    System.out.println("Criando Playlist!");
                    // controller.criarPlaylist();
                    break;
                case 3:
                    System.out.println("Alterando Playlist!");
                    // controller.alterarPlaylist();
                    break;
                case 4:
                    System.out.println("Tocando Playlist!");
                    // controller.tocarPlaylist();
                    break;
                case 5:
                    System.out.println("Saindo do MangaSound! Ate a proxima!");
                    return;
                default:
                    System.out.println("Numero invalido, tente novamente!");

            }
        }
    }
}
