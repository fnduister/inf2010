package tp2;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
   static int p = 46337;
   
   int a, b, n, memorySize;
   QuadraticSpacePerfectHashing<AnyType>[] data;
   Random generator;
   
   LinearSpacePerfectHashing()
   {
      clear();
   }
   
   LinearSpacePerfectHashing(ArrayList<AnyType> array)
   {
      allocateMemory(array);
   }
   
   public void setArray(ArrayList<AnyType> array)
   {
      allocateMemory(array);
   }
   
   public int size()
   {
      return n;
   }

   public void clear()
   {
      generator = new Random( System.nanoTime() );
      a = b = n = memorySize = 0; 
      data = null;
   }

   private int findPos(AnyType x)
   {
      // completer
      return 0;
   }
   
   public boolean contains(AnyType x)
   {      
      // completer
      return false;
   }
      
   @SuppressWarnings("unchecked")
   private void allocateMemory(ArrayList<AnyType> array)
   {
      clear();
      
      if(array == null || array.size() == 0) return;

      n    = array.size();
      data = new QuadraticSpacePerfectHashing[n];
      
      if(n == 1)
      {
         // Completer
         return;
      }
      
      // A completer
   }
   
   public int memorySize() 
   {
      return memorySize;
   }
   
   public String toString(){
      StringBuilder sb = new StringBuilder();
      
      // completer
      
      return sb.toString();
   }
}
