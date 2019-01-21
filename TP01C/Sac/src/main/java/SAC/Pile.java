package SAC;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pile<AnyType> implements Iterable<AnyType> {
    private Noeud<AnyType> dernierNoeud; // l'element qu'on garde comme reference dans le sac
    private int nbrElements; // nombre d'element dans le sac

    private static class Noeud<AnyType> {
    public Noeud(AnyType donnee, Noeud<AnyType> suivant) {
            this.suivant = suivant;
            this.donnee = donnee;
        }

        private AnyType donnee;
        private Noeud<AnyType> suivant;
    }

    /**
     * Initialise un Pile vide.
     */
    public Pile() {
        dernierNoeud = null;
        nbrElements = 0;
    }

    /**
     * retourne vrai si le sac est vide
     */
    public boolean estVide() {
        return dernierNoeud == null;
    }

    /**
     * @return retourne le nombre d'elements dans le sac
     */
    public int size() {
        return nbrElements;
    }

    /**
     * Ajoute un element dans le sac.
     */
    public void add(AnyType donnee) {
        dernierNoeud = new Noeud<AnyType>(donnee, dernierNoeud);
        nbrElements++;
    }

    /**
     * @return an iterator that iterates over the AnyTypes in this Pile in arbitrary
     *         order
     */
    public Iterator<AnyType> iterator() {
        return new ListIterator<AnyType>(dernierNoeud);
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
            System.out.print("le sac est vide" + System.lineSeparator());
        }
        nombres.add(32);
        System.out.print("nombre d'elements" + nombres.nbrElements + System.lineSeparator());
        nombres.add(45);
        nombres.add(409);
        nombres.add(4555);
        nombres.add(4509);
        for (Integer nombre : nombres) {
            System.out.print(nombre + System.lineSeparator());
        }
    }

}
