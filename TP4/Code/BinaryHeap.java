package tp4;

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
			// on prend le premier element du tableau
			AnyType valeur_retirer;
			valeur_retirer=array[1];
			swapReferences(array, 1, currentSize);
			if (min) {
				percolateDownMinHeap(1, currentSize - 1); // array.length -1 afin de ne pas travailler sur tout le
															// tableau
			} else {
				percolateDownMaxHeap(1, currentSize - 1);
			}
			return valeur_retirer;
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

		/*
		 * AnyType[] arraytemp = (AnyType[]) new Comparable[ currentSize];
		 * 
		 * //faire une copie par valeur for(int i=0;i<array.length;i++) {
		 * arraytemp[i]=array[i];
		 * 
		 * 
		 * } //vider le tableau array AnyType[] newArray = (AnyType[]) new
		 * Comparable[1]; array=newArray; currentSize=0; this.min=false;
		 * 
		 * //arraytemp=array; for( int i = 0 ;i<arraytemp.length;i++ ){
		 * 
		 * this.offer(arraytemp[array.length-i-1]); }
		 */

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
			if (smallestChildIndex < size && smallestChildIndex< (size-1) &&array[smallestChildIndex]!=null &&array[smallestChildIndex + 1].compareTo(array[smallestChildIndex]) <= 0)
				smallestChildIndex++;// on recupere l'enfant de droite
			if (array[smallestChildIndex]!=null && array[smallestChildIndex].compareTo(array[hole]) < 0)
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

		// on fait un monceau max 
		
		//on trie le tableau 
		AnyType[] arraytemp = (AnyType[]) new Comparable[a.length ];
		for (int i = 0; i < a.length; i++) {

			// on prend le dernier element du tableau a[a.length-1]
			int position = a.length -i;
			if (position >= 0) {
				for (int j = (position / 2)-1; j >= 0; j--) {
					percolateDownMinHeap(a, j, position , false);
					
				}
				
			}
			
			
			arraytemp[i]=a[0];
			AnyType tmp = a[0];
			a[0] = a[(a.length - 1) - i];
		
			a[(a.length - 1) - i] = null;
		
			
			
		}
		
			
			for(int i=0;i<arraytemp.length;i++) {
				
				
				a[i]=arraytemp[i];
				
			}
	
	}

	public static <AnyType extends Comparable<? super AnyType>> void heapSortReverse(AnyType[] a) {
		// COMPLETEZ
		// si le tableau ne commence pas a l'indice 0 , on peut pr�sumer qu'il commence
		// a l'index 1
		AnyType[] arraytemp = (AnyType[]) new Comparable[a.length ];
		for (int i = 0; i < a.length; i++) {

			// on prend le dernier element du tableau a[a.length-1]
			int position = a.length -i;
			if (position >= 0) {
				for (int j = (position / 2)-1; j >= 0; j--) {
					percolateDownMinHeap(a, j, position , false);
					
				}
				
			}
			
			
			arraytemp[i]=a[0];
			AnyType tmp = a[0];
			a[0] = a[(a.length - 1) - i];
		
			a[(a.length - 1) - i] = null;
		
			
			
		}
		
			
			for(int i=0;i<arraytemp.length;i++) {
				
				
				a[i]=arraytemp[(arraytemp.length-i-1)];
				
			}

	}

	public String nonRecursivePrintFancyTree() {
		String outputString = "";

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

		//declaration de variables temoin des changements 
		
		int currentSize=0;
		

		public boolean hasNext() {
			// COMPLETEZ

			if (this.currentSize < currentSize) {

				return false;
			}
			else
				return true;
		}

		public Object next() throws NoSuchElementException, 
	    ConcurrentModificationException, 
	    UnsupportedOperationException {
              //COMPLETEZ
				
				if(hasNext() ) {
					
					this.currentSize ++;
					if(array[this.currentSize]==null)
						modifications++;
					
					return (Object) array[this.currentSize];
				} 
				else if( modifications !=0) {
					throw new ConcurrentModificationException();
				}
				else if(!hasNext()){
					throw new NoSuchElementException();
				}
				else {
					throw new UnsupportedOperationException();
				}
				
		}
		

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}