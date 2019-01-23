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
     * Initialise une File vide.
     */
    public File() {
        premierElement = null;
        dernierElement = premierElement;
        nbrElements = 0;
    }

    /**
     * retourne vrai si la file est vide
     */
    public boolean estVide() {
        return premierElement == null;
    }

    /**
     * @return retourne le nombre d'elements dans la file.
     */
    public int size() {
        return nbrElements;
    }

    /**
     * Ajoute un element dans la file.
     */
    public void enfile(AnyType donnee) {
        // dans le cas our la file est vide on doit initiliser le premier et dernier
        // element avec la nouvelle valeur
        if (premierElement == null) {
            premierElement = new Noeud<AnyType>(donnee);
            dernierElement = premierElement;
        } else {
            Noeud<AnyType> nouveauNoeud = new Noeud<AnyType>(donnee);
            dernierElement.suivant = nouveauNoeud;
            dernierElement = nouveauNoeud;
            // dans le cas ou c'est le deuxieme element on link avec premierElement.
            if (premierElement.suivant == null) {
                premierElement.suivant = dernierElement;
            }
        }
        nbrElements++;
    }

    /**
     * Voir les donnée du premier élément.
     */
    public AnyType voirPremierElement() {
        return premierElement.donnee;
    }

    /**
     * retire le premiere élément entrée dans la file.
     * 
     * @return donnée de l'élément qu'on defile
     */
    public AnyType defile() {
        if (premierElement == null) {
            throw new NoSuchElementException("votre file est vide");
        }
        AnyType ancienneDonnee = premierElement.donnee;
        premierElement = premierElement.suivant;
        nbrElements--;
        return ancienneDonnee;
    }

    /**
     * implementation de la fonction iterator.
     */
    public Iterator<AnyType> iterator() {
        return new ListIterator<AnyType>(premierElement);
    }

    private class ListIterator<AnyType> implements Iterator<AnyType> {
        private Noeud<AnyType> courant;

        public ListIterator(Noeud<AnyType> premierElement) {
            courant = premierElement;
        }

        public boolean hasNext() {
            return courant != null;
        }

        /**
         * suppression d'un element dans la file n'est psa supportée.
         */
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
            System.out.println("le sac est vide");
        }
        // try {
        // nombres.defile();
        // } catch (Error err) {
        // System.out.print("votre sac est vide: " + err);
        // }
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
        System.out.println("nouveau premier element: " + nombres.voirPremierElement());
    }
}
