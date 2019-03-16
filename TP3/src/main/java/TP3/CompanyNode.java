package TP3;

import java.util.List;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
        childs = new BinarySearchTree<CompanyNode>(this);
        worstChild = null;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
        childs.insert(item);// ajout de l'item � la compagnie ?? a v�rifier
        money += item.getMoney();// mise a jour du montant en banque

        // determiner le worst child
        if (worstChild == null) {
            if (item.worstChild != null) {
                worstChild = item.worstChild;
            } else {
                worstChild = item;
            }
        } else {
            if (item.worstChild != null) {
                if (worstChild.getMoney() > item.worstChild.getMoney())
                    worstChild = item.worstChild;
            } else if (worstChild.getMoney() > item.getMoney()) {
                worstChild = item;
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

        // childs.getItemsInOrder();

        // }

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
