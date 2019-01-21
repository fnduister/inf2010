package SAC;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class File<AnyType> implements Iterable<AnyType> {
    private Noeud<AnyType> premierElement; // l'element qu'on garde comme reference dans le sac
    private Noeud<AnyType> dernierElement; // l'element qu'on garde comme reference dans le sac
    private int nbrElements; // nombre d'element dans le sac

    private static class Noeud<AnyType> {
        public Noeud(AnyType donnee) {
            this.donnee = donnee;
            suivant = null;
        }

        private AnyType donnee;
        private Noeud<AnyType> suivant;
    }

    /**
     * Initialise un File vide.
     */
    public File() {
        premierElement = null;
        dernierElement = premierElement;
        nbrElements = 0;
    }

    /**
     * retourne vrai si le sac est vide
     */
    public boolean estVide() {
        return premierElement == null;
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
    public void enfile(AnyType donnee) {
        if (premierElement == null) {
            premierElement = new Noeud<AnyType>(donnee);
            dernierElement = premierElement;
        } else {
            Noeud<AnyType> nouveauNoeud = new Noeud<AnyType>(donnee);
            dernierElement.suivant = nouveauNoeud;
            dernierElement = nouveauNoeud;
            if (premierElement.suivant == null) {
                premierElement.suivant = dernierElement;
            }
        }
        nbrElements++;
    }

    public AnyType voirPremierElement() {
        return premierElement.donnee;
    }

    public AnyType defile() {
        if (premierElement == null) {
            throw new NoSuchElementException();
        }
        AnyType ancienneDonnee = premierElement.donnee;
        premierElement = premierElement.suivant;
        nbrElements--;
        return ancienneDonnee;
    }

    /**
     * @return an iterator that iterates over the AnyTypes in this File in arbitrary
     *         order
     */
    public Iterator<AnyType> iterator() {
        return new ListIterator<AnyType>(premierElement);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<AnyType> implements Iterator<AnyType> {
        private Noeud<AnyType> courant;

        public ListIterator(Noeud<AnyType> premierElement) {
            courant = premierElement;
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
        File<Integer> nombres = new File<Integer>();
        if (nombres.estVide()) {
            System.out.print("le sac est vide" + System.lineSeparator());
        }
        try {
            nombres.defile();
        } catch (Error err) {
            System.out.print("votre sac est vide: ");
        }
        nombres.enfile(32);
        System.out.print("nombre d'elements: " + nombres.nbrElements + System.lineSeparator());
        nombres.enfile(45);
        nombres.enfile(409);
        nombres.enfile(4555);
        nombres.enfile(4509);
        for (Integer nombre : nombres) {
            System.out.print(nombre + System.lineSeparator());
        }

        System.out.print("nombre qu'on vient de defiler: " + nombres.defile() + System.lineSeparator());
        System.out.print("nouveau premier element: " + nombres.voirPremierElement() + System.lineSeparator());
    }
}
