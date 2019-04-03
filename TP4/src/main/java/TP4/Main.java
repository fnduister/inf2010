package TP4;

import java.util.*;

public class Main {
   /**
    * Fonction principale
    */
   public static void main(String[] args) {
      // creer un monceau avec 22 elements et un tableau equivalent
      int numItems = 22;
      BinaryHeap<Integer> heap = new BinaryHeap<Integer>(true);

      Integer[] items = new Integer[numItems];

      int i;
      int j;

      // en inserant les elements un a un
      for (i = 11, j = 0; j != numItems; i = (i + 37), j++) {
         heap.offer(i);
         items[j] = i;
         i %= numItems;
      }

      // en construisant le monceau depuis le depart
      System.out.println("Monceau min contruit element par element:");
      System.out.println(heap.printFancyTree());

      heap = new BinaryHeap<Integer>(false);
      // en inserant les elements un a un
      for (Integer item : items)
         heap.offer(item);

      // en construisant le monceau depuis le depart
      System.out.println("Monceau max contruit element par element:");
      System.out.println(heap.printFancyTree());

      heap = new BinaryHeap<Integer>(items, false);
      System.out.println("Monceau max contruit a partir d'un tableau:");
      System.out.println(heap.printFancyTree());

      heap = new BinaryHeap<Integer>(items, true);
      System.out.println("Monceau min contruit a partir d'un tableau:");
      System.out.println(heap.printFancyTree());

      System.out.println();
      System.out.println("Affichage recursif:");
      System.out.println(heap.printFancyTree());

      System.out.println("Affichage non recursif:");
      System.out.println(heap.nonRecursivePrintFancyTree());

      System.out.println();
      System.out.println("Tableau d'origine:");
      System.out.println(printArray(items));

      BinaryHeap.heapSort(items);
      System.out.println("Tableau ordonne:");
      System.out.println(printArray(items));

      BinaryHeap.heapSortReverse(items);
      System.out.println("Tableau inversement ordonne:");
      System.out.println(printArray(items));

      /*
       * Ajouter appels pour repondre a la question
       **/

      Collections.sort(list);
      String inOrder = tree.toStringInOrder();
      String sortedArray = Arrays.toString(list.toArray());
      TestHelper.printTest(inOrder.equals(sortedArray));
   }

   private static <AnyType> String printArray(AnyType[] a) {
      String outputString = "";

      for (AnyType item : a) {
         outputString += item;
         outputString += ", ";
      }

      return outputString.substring(0, outputString.length() - 2);
   }
}
