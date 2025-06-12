package view;

import controller.BibliotecaController;
import model.*;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaController controller = new BibliotecaController();

        int opcao;
        do {
            System.out.println("\n--- Sistema Biblioteca ---");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Itens");
            System.out.println("3. Buscar por Título");
            System.out.println("4. Remover Item");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Número de páginas: ");
                        int paginas = Integer.parseInt(scanner.nextLine());

                        controller.adicionarItem(new Livro(titulo, autor, paginas));
                        System.out.println("Livro adicionado com sucesso!");
                        break;

                    case 2:
                        controller.listarItens();
                        break;

                    case 3:
                        System.out.print("Digite o título: ");
                        String busca = scanner.nextLine();
                        ItemBiblioteca encontrado = controller.buscarPorTitulo(busca);
                        encontrado.exibirDetalhes();
                        break;

                    case 4:
                        System.out.print("Digite o título para remover: ");
                        String removeTitulo = scanner.nextLine();
                        controller.removerItem(removeTitulo);
                        break;

                    case 0:
                        System.out.println("Encerrando...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }
}