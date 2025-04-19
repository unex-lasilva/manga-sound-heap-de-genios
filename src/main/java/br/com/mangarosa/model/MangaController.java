package br.com.mangarosa.model;
import java.util.Scanner;

public class MangaController {
    Scanner scanner = new Scanner(System.in);

    public int mostrarMenu(){
        System.out.println("Escolha uma opcao: ");
        System.out.println("1. Adicionar musica");
        System.out.println("2. Criar Playlist");
        System.out.println("3. Alterar Playlist");
        System.out.println("4. Tocar Playlist");
        System.out.println("5. Sair do menu");
        System.out.println("Digite o respectivo numero da opcao: ");
        int opcaoEscolhida = scanner.nextInt();
        return opcaoEscolhida;
    }
}
