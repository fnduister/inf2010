package tp2;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> {
   static int p = 46337;

   int a, b, m, n;
   AnyType[] items;
   Random generator;

   QuadraticSpacePerfectHashing() {
      clear();
   }

   QuadraticSpacePerfectHashing(ArrayList<AnyType> array) {
      allocateMemory(array);
   }

   public void setArray(ArrayList<AnyType> array) {
      allocateMemory(array);
   }

   public int size() {
      return n;
   }

   public void clear() {
      generator = new Random(System.nanoTime());
      a = b = m = n = 0;
      items = null;
   }

   private int findPos(AnyType x) {
      // on trouve la position avec la formule
      int pos = ((a * x.hashCode() + b) % p) % m;

      // etant donne que le hashCode peut nous donner des valeurs negatives
      // on incremente de m pour etre sure que la position est entre [0, m]
      if (pos < 0) {
         pos += m;
      }
      return pos;
   }

   public boolean contains(AnyType x) {
      if (n == 0)
         return false;

      int index = findPos(x);

      return ((items[index] != null) && (items[index].equals(x)));
   }

   @SuppressWarnings("unchecked")
   private void allocateMemory(ArrayList<AnyType> array) {
      clear();

      if (array == null || array.size() == 0)
         return;

      n = array.size();
      m = n * n;

      if (n == 1) {
         items = (AnyType[]) new Object[m];
         items[0] = array.get(0);
         return;
      }

      while (unsuccessfulMemoryAllocation(array))
         ;
   }

   @SuppressWarnings("unchecked")
   private boolean unsuccessfulMemoryAllocation(ArrayList<AnyType> array) {
      // A completer
      // genere a et b random
      generator = new Random(); // instancier un objet random dont le seed est alÃ©atoire
      a = generator.nextInt(p);// gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu
      b = generator.nextInt(p);// gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu

      items = (AnyType[]) new Object[m];

      for (AnyType value : array) {
         int index = findPos(value);

         // si il y a collision on recommence, pour obtenir un a et b qui empeche la
         // collision
         if (items[index] != null) {
            return true;
         }
         // sinon on met la valeur dans le tableau
         items[index] = value;
      }
      return false;
   }

   public int memorySize() {
      return m;
   }

   public String toString() {
      if (n == 0)
         return "";

      StringBuilder sb = new StringBuilder();

      for (AnyType item : items)
         if (item != null)
            sb.append(item + ", ");

      return sb.toString();
   }
}
