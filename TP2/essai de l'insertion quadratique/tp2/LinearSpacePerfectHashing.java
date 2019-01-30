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

   private int myhash( AnyType x ) { 
      int hashVal = x.hashCode( );
      hashVal %= n;
      if( hashVal < 0 ) hashVal += n;
      return hashVal;
      }
      
   private int findPos(AnyType x)
   {
      // completer
      if (x!=null){

         generator=new Random(); //instancier un objet random dont le seed est aléatoire 
         a=generator.nextInt(p);//générer un nombre aléatoire entre 0 et p exclu
         b=generator.nextInt(p);//générer un nombre aléatoire entre 0 et p exclu
        // n=generator.nextInt(p);//générer un nombre aléatoire entre 0 et p exclu

         
         //trouver  le code hash code de l'objet .
         int x_int=myhash(x);
         //calculer la position courante currentPos
         int currentPos =((a*x_int+b)%p)%n;
          
         int offset=1;

         //vérifier que la position courante est comprise entre 0 et m exclus
          while (data[currentPos]!=null && !data[currentPos].equals(x)){
            currentPos+=offset; 
            offset +=2;

            if (currentPos>=m)
               currentPos-=m;
         }

         return  currentPos;

      }
        

      return 0;
   }
   
   public boolean contains(AnyType x)
   {      
      // completer
    

      if(findPos(x)) //si la position de l'objet dans le tableau est trouvé alors l'objet existe
         return true
      
      
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
