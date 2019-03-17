package TP3;

import java.util.List;
import java.util.ListIterator;;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
        childs = null;
        worstChild = null;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
        if (childs == null)
            childs = new BinarySearchTree<CompanyNode>(item);
        else {
            childs.insert(item);// ajout de l'item ï¿½ la compagnie ?? a vï¿½rifier
        }

        money += item.getMoney();// mise a jour du montant en banque
        // determiner le worst child

        if (worstChild == null) { // si le pire enfant n'existe pas
            worstChild = item; // on assigne automatiquement le parametre item comme pire enfant
            if (item.worstChild != null) { // si le pire enfant d'item existe
                if (item.compareTo(item.worstChild) == -1) { // si item a une valeur inférieur a celle de son pire
                                                             // enfant
                    worstChild = item.worstChild; // le pire enfant devient celui de item
                }
            }
        } else {
            if (item.compareTo(worstChild) == -1) { // si item a une valeur inférieur a celle du pire enfant existant
                worstChild = item;// le pire enfant devient item
            }

            if (item.worstChild != null) {// si le pire enfant d'item existe
                if (item.worstChild.compareTo(worstChild) == -1) { // et si ce dernier a une valeur inférieur a celle du
                                                                   // pire enfant courant
                    worstChild = item.worstChild; // le pire enfant devient le pire enfant d'item
                }
            }
        }
    }

    // TODO: on retourne le montant en banque de la compagnie
    // O(1)
    public Integer getMoney() {
        return money;
    }

    // TODO: on rempli le builder de la compagnie et de ses enfants avec le format
    // A
    // > A1
    // > A2
    // > > A21...
    // les enfants sont afficher du plus grand au plus petit (voir
    // TestCompany.testPrint)
    // O(n)
    public void fillStringBuilderInOrder(StringBuilder builder, String prefix) {

        // ajouter le pere
        builder.append(prefix + this.getMoney() + "\n");

        // augmente le prefix
        prefix += " > ";
        if (childs == null) {
            return;
        }

        // reverse la liste obtenu avec binarySearchTree
        List<BinaryNode<CompanyNode>> listOfChild = childs.getItemsInOrder();

        // ajouter tous ses fils
        for (int i = listOfChild.size(); i-- > 0;) {
            listOfChild.get(i).getData().fillStringBuilderInOrder(builder, prefix);
        }
    }

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
        if (item.getMoney() > getMoney())
            return -1;
        else if (item.getMoney() < getMoney())
            return 1;
        else
            return 0;
    }
}
