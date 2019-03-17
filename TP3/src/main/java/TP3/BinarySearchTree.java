package TP3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
    }

    // TODO: initialisation
    public BinarySearchTree(T item) {
        root = new BinaryNode<T>(item);
    }

    // TODO: on insere un nouvel item a partir de la racine
    // O(log(n))
    public void insert(T item) {
        root.insert(item);
    }

    // TODO: est-ce qu'un item fais partie de l'arbre
    // O(log(n))
    public boolean contains(T item) {
        if (root != null)
            return root.contains(item);
        return false;
    }

    // TODO: trouver la hauteur de l'arbre
    // O(n)
    public int getHeight() {
        return root.getHeight();
    }

    // TODO: placer dans une liste les items de l'arbre en ordre
    // O(n)
    public List<BinaryNode<T>> getItemsInOrder() {
        if (root == null)
            return null;
        List<BinaryNode<T>> liste = new ArrayList<BinaryNode<T>>();
        root.fillListInOrder(liste);
        return liste;
    }

    // TODO: retourner la liste d'item en String selon le bon format
    // O(n)
    public String toStringInOrder() {
        List<BinaryNode<T>> liste = new ArrayList<BinaryNode<T>>();
        liste = getItemsInOrder();

        // debute le string
        StringBuilder stringInOrder = new StringBuilder("[");

        // remplie le string builder des enfants
        for (BinaryNode<T> item : liste) {
            stringInOrder.append(item.getData() + ", ");
        }

        String realString = stringInOrder.substring(0, stringInOrder.length() - 2);
        realString += "]";
        return realString;
    }
}
