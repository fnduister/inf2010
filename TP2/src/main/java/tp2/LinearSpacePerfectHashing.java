package tp2;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType> {
   static int p = 46337;

   int a, b, n, memorySize;
   QuadraticSpacePerfectHashing<AnyType>[] data;
   Random generator;

   LinearSpacePerfectHashing() {
      clear();
   }

   LinearSpacePerfectHashing(ArrayList<AnyType> array) {
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
      a = b = n = memorySize = 0;
      data = null;
   }

   private int findPos(AnyType x) {
      int pos = ((a * x.hashCode() + b) % p) % n;
      if (pos < 0) {
         pos += n;
      }
      return pos;
   }

   public boolean contains(AnyType x) {
      // completer
      int currentPos = findPos(x);
      if (n == 0)
         return false;

      if (data[currentPos] != null) {
         if (data[currentPos].contains(x)) // si la position de l'objet dans le tableau est trouvÃ© alors l'objet existe
            return true;
      }

      return false;
   }

   @SuppressWarnings("unchecked")
   private void allocateMemory(ArrayList<AnyType> array) {
      clear();

      if (array == null || array.size() == 0)
         return;

      generator = new Random(); // instancier un objet random dont le seed est alÃ©atoire
      a = generator.nextInt(p);// gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu
      b = generator.nextInt(p);// gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu

      n = array.size();
      data = new QuadraticSpacePerfectHashing[n];

      if (n == 1) {
         // Completer
         data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
         return;
      }

      ArrayList<AnyType>[] dataCopy = new ArrayList[n];
      for (AnyType value : array) {
         int position = findPos(value);
         if (dataCopy[position] == null) {
            dataCopy[position] = new ArrayList<AnyType>();
         }
         dataCopy[position].add(value);
      }

      for (int i = 0; i < dataCopy.length; i++) {
         data[i] = new QuadraticSpacePerfectHashing<AnyType>(dataCopy[i]);
         memorySize += data[i].memorySize();
      }
   }

   public int memorySize() {
      return memorySize;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      // completer
      int indice = 0;
      for (QuadraticSpacePerfectHashing<AnyType> item : data)
         if (item != null)
            sb.append("- " + (indice++) + " -> " + item.toString() + System.lineSeparator());
      return sb.toString();
   }
}
