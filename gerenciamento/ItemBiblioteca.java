package model;

public abstract class ItemBiblioteca implements Exibivel {
    protected String titulo;
    protected String autor;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }
}