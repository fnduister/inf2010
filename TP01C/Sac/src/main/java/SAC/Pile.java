package SAC;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pile<AnyType> implements Iterable<AnyType> {
    private Noeud<AnyType> dernierNoeud; // l'element qu'on garde comme reference dans le sac
    private int nbrElements; // nombre d'element dans le sac

    /**
     * Noeud contient une reference au noeux suivant ainsi que la donné qu'il contient.
     */
    private static class Noeud<AnyType> {
        public Noeud(AnyType donnee, Noeud<AnyType> suivant) {
            this.suivant = suivant;
            this.donnee = donnee;
        }

        private AnyType donnee;
        private Noeud<AnyType> suivant;
    }

    /**
     * Initialise une Pile vide.
     */
    public Pile() {
        dernierNoeud = null;
        nbrElements = 0;
    }

    /**
     * retourne vrai si la pile est vide
     */
    public boolean estVide() {
        return dernierNoeud == null;
    }

    /**
     * @return retourne le nombre d'elements dans la pile
     */
    public int size() {
        return nbrElements;
    }

    /**
     * Ajoute un element dans le top de la pile.
     */
    public void enfile(AnyType donnee) {
        dernierNoeud = new Noeud<AnyType>(donnee, dernierNoeud);
        nbrElements++;
    }

    /**
     * retire le dernier element de la pile.
     */
    public AnyType defile() {
        if (dernierNoeud == null) {
            throw new NoSuchElementException();
        }
        AnyType ancienneDonnee = dernierNoeud.donnee;
        dernierNoeud = dernierNoeud.suivant;
        nbrElements--;
        return ancienneDonnee;
    }

    public Iterator<AnyType> iterator() {
        return new ListIterator<AnyType>(dernierNoeud);
    }

    public AnyType voirDernierElement() {
        return dernierNoeud.donnee;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<AnyType> implements Iterator<AnyType> {
        private Noeud<AnyType> courant;

        public ListIterator(Noeud<AnyType> dernierNoeud) {
            courant = dernierNoeud;
        }

        public boolean hasNext() {
            return courant != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();
            AnyType ancienneDonnee = courant.donnee; // on sauvegarde l'ancienne donnee pour la retourner
            courant = courant.suivant; // l'iterateur passe au node suivant
            return ancienneDonnee;
        }
    }

    // test class
    public static void main(String[] args) {
        Pile<Integer> nombres = new Pile<Integer>();
        if (nombres.estVide()) {
            System.out.println("le sac est vide");
        }
        nombres.enfile(32);
        System.out.println("nombre d'elements: " + nombres.nbrElements);
        nombres.enfile(45);
        nombres.enfile(409);
        nombres.enfile(4555);
        nombres.enfile(4509);
        for (Integer nombre : nombres) {
            System.out.println(nombre);
        }

        System.out.println("nombre qu'on vient de defiler: " + nombres.defile());
        System.out.println("nouveau dernier element: " + nombres.voirDernierElement());
    }

}
