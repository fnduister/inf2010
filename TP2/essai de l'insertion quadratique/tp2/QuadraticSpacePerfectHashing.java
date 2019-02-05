package tp2;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
   static int p = 46337;

   int a, b, m, n;
   AnyType[] items;
   Random generator;

   QuadraticSpacePerfectHashing()
   {
      clear();
   }

   QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
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
      a = b = m = n = 0; 
      items = null;
   }

   private int myhash( AnyType x ) { 
	   
      int hashVal = x.hashCode( );//trouver  le code hash code de l'objet .
      hashVal=((a*hashVal+b)%p)%m; //calculer la position de l'objet
      if( hashVal < 0 ) hashVal += m;
      return hashVal;
      }
   private int findPos(AnyType x)
   {
      // Completer
         // completer
         if (x!=null){

            
            //la position courante currentPos
            int currentPos =myhash(x);
             
            int offset=1;
   
            //vérifier que la position courante est comprise entre 0 et m exclus
             while (items[currentPos]!=null && !items[currentPos].equals(x)){
               currentPos+=offset; 
               offset +=2;
   
               if (currentPos>=m)
                  currentPos-=m;
              
            }
   
            return  currentPos;
   
         }
           
   
         
      return 0;
   }

   public boolean contains(AnyType x )
   {
      if( n == 0 ) return false; 

      int index = findPos(x);

      return ( ( items[index] != null ) && ( items[index].equals(x) ) );
   }

   @SuppressWarnings("unchecked")
   private void allocateMemory(ArrayList<AnyType> array)
   {
      clear();

      if(array == null || array.size() == 0) return;

      n = array.size();
      m = n*n;

      if(n == 1)
      {
         items = (AnyType[]) new Object[m];
         items[0]	= array.get(0);
         return;
      }

      while( unsuccessfulMemoryAllocation( array ) ); // les elements doivent pouvoir tous etre inserer pour un a et un b différents 
   }

   @SuppressWarnings("unchecked")
   private boolean unsuccessfulMemoryAllocation(ArrayList<AnyType> array)
   {
      // A completer
	   if(a==0 && b==0  ) {// si le tableau non initialis�
      generator=new Random(); //instancier un objet random dont le seed est aléatoire 
      a=generator.nextInt(p);//générer un nombre aléatoire entre 0 et p exclu
      b=generator.nextInt(p);//générer un nombre aléatoire entre 0 et p exclu
          
      
	   }

      //allouer le  items de la taille m
      items = (AnyType[]) new Object[m];

      for (int i=0;i<array.size();i++){

         int index =findPos(array.get(i));
        
         if(items[index]!=null) //si le tableau item contient deja un element ,il y a collision 
            return true; // cet echec n"aura pas lieu car findpos trouvera toujours une position ideale
         else
            items[index]=array.get(i);

      }

      return false;
   }
   
   public int memorySize() 
   {
      return m;
   }
   
   public String toString(){
      if(n == 0) 
         return "";
      
      StringBuilder sb = new StringBuilder();
      
      for(AnyType item : items) 
         if( item != null ) 
            sb.append(item + ", ");
      
      return sb.toString();
   }
}
