package TP4;

import java.net.CacheResponse;
import java.util.*;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType> {
	private static final int DEFAULT_CAPACITY = 100;
	private int currentSize; // Nombre d'elements
	private AnyType[] array; // Tableau contenant les donnees (premier element a l'indice 1)
	private boolean min;
	private int modifications; // Nombre de modifications apportees a ce monceau

	@SuppressWarnings("unchecked")
	public BinaryHeap(boolean min) {
		this.min = min;
		currentSize = 0;
		array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(AnyType[] items, boolean min) {
		this.min = min;

		// COMPLETEZ
		// invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;

		array = items.clone();
		currentSize = array.length;// mise a jour de la taille du tableau
		if (min) {

			buildMinHeap();

		} else {

			buildMaxHeap();
		}
	}

	public boolean offer(AnyType x) {
		if (x == null)
			throw new NullPointerException("Cannot insert null in a BinaryHeap");

		if (currentSize + 1 == array.length)
			doubleArray();

		// COMPLETEZ
		if (array[currentSize + 1] == null) {// tjrs vrai // mettre l'�lement a la derniere position du tableau --revoir
			array[currentSize + 1] = x;
			currentSize++;// enlevable

		}

		if (min) {

			int position = currentSize;
			while (array[position / 2] != null && array[position / 2].compareTo(x) >= 0 && position > 1) {// si le
																											// parent de
																											// x a une
																											// valeur
																											// qui lui
																											// est
																											// sup�rieur

				// if(array[position/2]!=null && array[position/2].compareTo(x)>0)
				position = position / 2;// chercher la position tel que l'�lement a une valeur plus petite que x
			}

			swapReferences(array, position, currentSize);
			percolateDownMinHeap(position, currentSize);
			// percoler ses enfants
			position = currentSize;
			while (array[position / 2] != null && array[position / 2] != x) {

				if (array[position].compareTo(array[position / 2]) < 0) {

					swapReferences(array, position / 2, currentSize);// percoler les enfants
					percolateDownMinHeap(position / 2, currentSize);
				}
				position = position / 2;

			}
			// currentSize++;
		} else {

			int position = currentSize;
			while (array[position / 2] != null && array[position / 2].compareTo(x) <= 0 && position > 1) {// si le
																											// parent de
																											// x a une
																											// valeur
																											// qui lui
																											// est
																											// sup�rieur

				// if(array[position/2]!=null && array[position/2].compareTo(x)>0)
				position = position / 2;// chercher la position tel que l'�lement a une valeur plus petite que x
			}

			swapReferences(array, position, currentSize);
			percolateDownMaxHeap(position, currentSize);
			// percoler ses enfants
			position = currentSize;
			while (array[position / 2] != null && array[position / 2] != x) {

				if (array[position].compareTo(array[position / 2]) > 0) {

					swapReferences(array, position / 2, currentSize);// percoler les enfants
					percolateDownMaxHeap(position / 2, currentSize);
				}
				position = position / 2;

			}
		}

		return true;
	}

	public AnyType peek() {
		if (!isEmpty())
			return array[1];
		return null;
	}

	public AnyType poll() {
		// COMPLETEZ
		if (!isEmpty()) { // si le tableau est vide
			// on prend le dernier element du tableau
			swapReferences(array, 1, currentSize);
			if (min) {
				percolateDownMinHeap(1, array.length - 1); // array.length -1 afin de ne pas travailler sur tout le
															// tableau
			} else {
				percolateDownMaxHeap(1, array.length - 1);
			}

		}
		return null;
	}

	public Iterator<AnyType> iterator() {
		return new HeapIterator();
	}

	private void buildMinHeap() {
		// COMPLETEZ
		int position = currentSize;
		if (position > 0) {
			for (int i = position / 2; i >= 0; i--) {
				percolateDownMinHeap(i, array.length);
			}
		}
		// si le tableau commence a l'index 0 on le met a l'index 1
		if (array[0] != null) { // decaler les �l�ments afin qu'ils commencent a 1
			// doubler le tableau
			AnyType[] newArray = (AnyType[]) new Comparable[array.length + 1];
			// newArray = (AnyType []) new Comparable[ array.length +1 ];
			for (int i = 0; i < array.length; i++)
				newArray[i + 1] = array[i];
			array = newArray;
		}
	}

	private void buildMaxHeap() {
		// COMPLETEZ

		int position = array.length;
		if (position > 0) {
			for (int i = position / 2; i >= 0; i--) {

				percolateDownMaxHeap(i, array.length);
			}
		}

		// si le tableau commence a l'index 0 on le met a l'index 1

		if (array[0] != null) { // decaler les �l�ments afin qu'ils commencent a 1

			AnyType[] arraytemp = (AnyType[]) new Comparable[array.length + 1];
			for (int i = 0; i < array.length; i++) {

				arraytemp[i + 1] = array[i];
			}
			array = arraytemp;

		}

	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public int size() {
		return currentSize;
	}

	public void clear() {
		currentSize = 0;
		modifications = 0;
		array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
	}

	private static int leftChild(int i, boolean heapIndexing) {
		return (heapIndexing ? 2 * i : 2 * i + 1);
	}

	private void swapReferences(int index1, int index2) {
		swapReferences(array, index1, index2);
	}

	private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] array, int index1,
			int index2) {
		AnyType tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}

	@SuppressWarnings("unchecked")
	private void doubleArray() {
		AnyType[] newArray;

		newArray = (AnyType[]) new Comparable[array.length * 2];
		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];
		array = newArray;
	}

	/**
	 * @param hole Position a percoler
	 * @param size Indice max du tableau
	 */
	private void percolateDownMinHeap(int hole, int size) {
		if (array[0] != null) // si les �lements commence � l'index 0
			percolateDownMinHeap(array, hole, size - 1, false);
		else
			percolateDownMinHeap(array, hole, size, true);
	}

	/**
	 * @param array        Tableau d'element
	 * @param hole         Position a percoler
	 * @param size         Indice max du tableau
	 * @param heapIndexing True si les elements commencent a l'index 1, false sinon
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percolateDownMinHeap(AnyType[] array, int hole,
			int size, boolean heapIndexing) {
		// COMPLETEZ

		int smallestChildIndex;

		for (; hole * 2 <= size; hole = smallestChildIndex) {
			smallestChildIndex = leftChild(hole, heapIndexing);
			if (smallestChildIndex < size && array[smallestChildIndex + 1].compareTo(array[smallestChildIndex]) <= 0)
				smallestChildIndex++;// on recupere l'enfant de droite
			if (array[smallestChildIndex].compareTo(array[hole]) < 0)
				swapReferences(array, hole, smallestChildIndex);
			else
				break;
		}
	}

	/**
	 * @param hole Position a percoler
	 * @param size Indice max du tableau
	 */
	private void percolateDownMaxHeap(int hole, int size) {
		//
		if (array[0] != null) // si les �lements commence � l'index 0
			percolateDownMaxHeap(array, hole, size - 1, false);
		else
			percolateDownMaxHeap(array, hole, size, true);

	}

	/**
	 * @param array        Tableau d'element
	 * @param hole         Position a percoler
	 * @param size         Indice max du tableau
	 * @param heapIndexing True si les elements commencent a l'index 1, false sinon
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percolateDownMaxHeap(AnyType[] array, int hole,
			int size, boolean heapIndexing) {

		// COMPLETEZ
		int biggestChildIndex;

		for (; hole * 2 <= size; hole = biggestChildIndex) {
			biggestChildIndex = leftChild(hole, heapIndexing);
			if (biggestChildIndex < size && array[biggestChildIndex + 1].compareTo(array[biggestChildIndex]) >= 0)
				biggestChildIndex++;// on recupere l'enfant de droite
			if (array[biggestChildIndex].compareTo(array[hole]) > 0)
				swapReferences(array, hole, biggestChildIndex);
			else
				break;
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a) {
		// COMPLETEZ

		// si le tableau ne commence pas a l'indice 0 , on peut pr�sumer qu'il commence
		// a l'index 1
		if (a[0] == null) {
			for (int i = 0; i < a.length + 1; i++) {
				// on fait reculer tous les �lements
				a[i] = a[i + 1];
			}
		}
		// mettre la derniere valeur du tableau au sommet et faire une percolation du
		// monceau allant de l'index 1 a n

		for (int i = 0; i < a.length; i++) {

			// on prend le dernier element du tableau a[a.length-1]

			AnyType tmp = a[0];
			a[0] = a[(a.length - 1) - i];
			a[(a.length - 1) - i] = tmp;
			percolateDownMinHeap(a, 0, a.length - i - 1, false);
		}

	}

	public static <AnyType extends Comparable<? super AnyType>> void heapSortReverse(AnyType[] a) {
		// COMPLETEZ
		// si le tableau ne commence pas a l'indice 0 , on peut pr�sumer qu'il commence
		// a l'index 1
		if (a[0] == null) {
			for (int i = 0; i < a.length + 1; i++) {
				// on fait reculer tous les �lements
				a[i] = a[i + 1];
			}
		}
		// mettre la derniere valeur du tableau au sommet et faire une percolation du
		// monceau allant de l'index 1 a n

		for (int i = 0; i < a.length; i++) {

			// on prend le dernier element du tableau a[a.length-1]
			/* swapreference entre l'index 0 et la taille du tableau */
			AnyType tmp = a[0];
			a[0] = a[a.length - 1];
			a[a.length - 1] = tmp;
			percolateDownMaxHeap(a, 0, a.length, true);
		}

	}

	int parent(int childPosition) {
		return childPosition / 2;
	}

	public AnyType[] orderArrayWithLevels(int[] levels) {
		int size = currentSize + 1;
		AnyType[] orderedArray = (AnyType[]) new Comparable[size + 1];
		int[] frequence = new int[size];
		int[] newLevels = new int[size + 1];

		// initialisation du tableau
		for (int i = 1; i < size; i++)
			frequence[i] = 0;

		int position = 1;
		int nouvellePosition = 1;

		while (frequence[1] != 3) {
			switch (frequence[position]) {
			case 0:
				orderedArray[nouvellePosition] = array[position];
				newLevels[nouvellePosition++] = levels[position];
				frequence[position]++;
				if (leftChild(position, true) < size)
					position = leftChild(position, true);
				else {// on a pas d'enfant
					position = parent(position);
				}
				break;
			case 1:
				frequence[position]++;
				if (leftChild(position, true) < size - 1)
					position = leftChild(position, true) + 1;// right child
				else {// on a pas d'enfant
					nouvellePosition++;
					position = parent(position);
				}
				break;
			case 2:
				frequence[position]++;
				position = parent(position);
				break;
			default:
				break;
			}
		}
		levels = newLevels;
		return orderedArray;
	}

	public String nonRecursivePrintFancyTree() {
		String outputString = "";
		AnyType[] orderedArray = (AnyType[]) new Comparable[currentSize];

		int[] levels = new int[currentSize + 2];
		int valeur = 0; // le premier niveau
		int t = 1;

		while (t < currentSize) {
			for (int j = 0; j < Math.pow(2, valeur); j++) {
				levels[t++] = valeur;
				if (t > currentSize)
					break;
			}
			valeur++;
		}

		orderedArray = orderArrayWithLevels(levels);
		
		// COMPLETEZ
		// string temporaire
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				i++;
			}
			;
			outputString += (array[i].toString());
			// outputString.concat(array[i].toString());
		}
		return outputString;
	}

	public String printFancyTree() {
		return printFancyTree(1, "");
	}

	private String printFancyTree(int index, String prefix) {
		String outputString = "";

		outputString = prefix + "|__";

		if (index <= currentSize) {
			boolean isLeaf = index > currentSize / 2;

			outputString += array[index] + "\n";

			String _prefix = prefix;

			if (index % 2 == 0)
				_prefix += "|  "; // un | et trois espace
			else
				_prefix += "   "; // quatre espaces

			if (!isLeaf) {
				outputString += printFancyTree(2 * index, _prefix);
				outputString += printFancyTree(2 * index + 1, _prefix);
			}
		} else
			outputString += "null\n";

		return outputString;
	}

	private class HeapIterator implements Iterator {

		// creer un conteneur arrayList afin de pouvoir utiliser Iterator
		ArrayList<?> arrayList = new ArrayList<>(Arrays.asList(array));
		// creation d'un iterateur
		Iterator<?> it = arrayList.iterator();

		public boolean hasNext() {
			// COMPLETEZ

			if (next() == null) {

				return false;
			}
			return true;
		}

		public Object next() {
			// COMPLETEZ

			if (modifications != 0)
				throw new ConcurrentModificationException();

			// return this.next();

			if (modifications != 0)
				throw new ConcurrentModificationException();
			// retourner l'objet sur lequel pointe le prochain it�rateur

			return it.next();

		}

		/*
		 * public Object next() throws NoSuchElementException,
		 * ConcurrentModificationException, UnsupportedOperationException { //COMPLETEZ
		 * }
		 */

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
