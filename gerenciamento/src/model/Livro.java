package model;

public class Livro extends ItemBiblioteca {
    private int numeroPaginas;

    public Livro(String titulo, String autor, int numeroPaginas) {
        super(titulo, autor);
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Livro: " + titulo + " | Autor: " + autor + " | Páginas: " + numeroPaginas);
    }
}