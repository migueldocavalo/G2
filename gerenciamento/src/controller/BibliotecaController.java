package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {
    private List<ItemBiblioteca> itens = new ArrayList<>();

    public void adicionarItem(ItemBiblioteca item) throws CampoVazioException {
        if (item.getTitulo().isEmpty()) {
            throw new CampoVazioException("O título não pode ser vazio.");
        }
        itens.add(item);
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
        } else {
            for (ItemBiblioteca item : itens) {
                item.exibirDetalhes();
            }
        }
    }

    public ItemBiblioteca buscarPorTitulo(String titulo) throws ItemNaoEncontradoException {
        for (ItemBiblioteca item : itens) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Item com título '" + titulo + "' não encontrado.");
    }

    public void removerItem(String titulo) throws ItemNaoEncontradoException {
        ItemBiblioteca item = buscarPorTitulo(titulo);
        itens.remove(item);
        System.out.println("Item removido com sucesso!");
    }
}