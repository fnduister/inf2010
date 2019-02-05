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
	   
      int hashVal = x.hashCode( );//trouver  le code hash code de l'objet .
      hashVal=((a*hashVal+b)%p)%n; //calculer la position de l'objet
      if( hashVal < 0 ) hashVal += n;
      return hashVal;
   }
   private int findPos(AnyType x)
   {
      // completer
      if (x!=null){

         generator=new Random(); //instancier un objet random dont le seed est alÃ©atoire 
         a=generator.nextInt(p);//gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu
         b=generator.nextInt(p);//gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu
        // n=generator.nextInt(p);//gÃ©nÃ©rer un nombre alÃ©atoire entre 0 et p exclu

      

         int currentPos=myhash(x);// la position courante currentPos
         
         
          
         int offset=1;

         //vÃ©rifier que la position courante est comprise entre 0 et n exclus
          while (data[currentPos]!=null && !data[currentPos].equals(x)){
            currentPos+=offset; 
            

            if (currentPos>=n)
               currentPos-=n;
         }

         return  currentPos;

      }
        

      return 0;
   }
   
   public boolean contains(AnyType x)
   {      
      // completer
    int currentPos=findPos(x);
    
    if(data[currentPos]!=null) {
    	if(data[currentPos].contains(x)) //si la position de l'objet dans le tableau est trouvÃ© alors l'objet existe
	        return true;
    }
	
      return false;
   }
      
   @SuppressWarnings("unchecked")
   private void allocateMemory(ArrayList<AnyType> array)
   {
      clear();
      
      if(array == null || array.size() == 0) return;

      n    = array.size();
      data = new QuadraticSpacePerfectHashing[n];
      memorySize=1;//la taille de la mémoire alloué est égale à 1 
      if(n == 1)
      {
         // Completer
    	  data[n].setArray(array);
    	 


         return;
      }
      else {
    	  // enleer
    	  int index=0;
    	  for (int i=0;i<n;i++) {//on parcourt le tableau
    		  //on cree un tableau de quadraticspace qui contient tout les objets a la meme position
    		  ////QuadraticSpacePerfectHashing tableau= new QuadraticSpacePerfectHashing();
    		  ArrayList<AnyType> table=new ArrayList<AnyType>();
    		  for(int j=0;j<n;j++) {// on parcourt a nouveau le tableau pour pouvoir comparer chaque element i avec tout les elements j 
    			 
    			  if(findPos(array.get(i))==findPos(array.get(j))) { //si deux elements different du tableau array sont en enregistrement synonyme
    				 table.add(array.get(j));//ajoute l'element en fin de liste 
    				 memorySize++;//on met à jour la taille de la mémoire alloué777b
    				 ////System.out.println(array.get(i));test
    			 }
    			
    		  }
    		  for(AnyType item:table)
    			  System.out.println(item+",");
    		  
    		  data[i]=new QuadraticSpacePerfectHashing(table);
    		 for(AnyType item:table)
    		  System.out.println(item+",");
    		///  //data[i].setArray(table);//on met a jour le tableau data//on met dans un tableau quadratic a la fin de la boucle 
    		
    		 System.out.println("position "+ index++);
    	  }
    	  
    	  
    	  
    	  
      }
    	  
    
   }
   
   public int memorySize() 
   {
      return memorySize;
   }
   
   public String toString(){
      StringBuilder sb = new StringBuilder();
      
      // completer
      int indice=0;
      for(QuadraticSpacePerfectHashing<AnyType> item : data) 
          if( item != null ) 
             sb.append("-"+ (indice++) +"->"+item.toString() + "/n");
      return sb.toString();
   }
}
