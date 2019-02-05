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
      if (hashVal>n) hashVal-=n;
      return hashVal;
   }
   private int findPos(AnyType x)
   {
      // completer
      if (x!=null){
    	  if(a==0 && b==0  ) { 
	         generator=new Random(); //instancier un objet random dont le seed est al√©atoire 
	         a=generator.nextInt(p);//g√©n√©rer un nombre al√©atoire entre 0 et p exclu
	         b=generator.nextInt(p);//g√©n√©rer un nombre al√©atoire entre 0 et p exclu
	        // n=generator.nextInt(p);//g√©n√©rer un nombre al√©atoire entre 0 et p exclu

    	  }

         int currentPos=myhash(x);// la position courante currentPos
         
         int offset=1;
         while(currentPos <0 && currentPos >n ) {
             currentPos+=offset; 
            
 
            // while(currentPos <0 && currentPos >m) {
                 if( currentPos < 0 )  currentPos += n;
                 if( currentPos > n ) currentPos -= n;
              
          }
        
     

         return  currentPos;

      }
        

      return 0;
   }
   
   public boolean contains(AnyType x)
   {      
      // completer
    int currentPos=findPos(x);
    if( n == 0 ) return false; 

    if(data[currentPos]!=null) {
    	if(data[currentPos].contains(x)) //si la position de l'objet dans le tableau est trouv√© alors l'objet existe
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
      memorySize=1;//la taille de la mÈmoire allouÈ est Ègale ‡ 1 
      if(n == 1)
      {
         // Completer
    	  data[0].setArray(array);
   	     //data[n]=new QuadraticSpacePerfectHashing(array);
    	
         return;
      }
      else {
    	
    	  
    	  for (int i=0;i<array.size();i++) {//on parcourt le tableau
    		  
    		  //on cree un tableau de quadraticspace qui contient tout les objets a la meme position
 
    		  ArrayList<AnyType> table=new ArrayList<AnyType>();
    		  for(int j=0;j<array.size();j++) {// on parcourt a nouveau le tableau pour pouvoir comparer chaque element i avec tout les elements j 
    			 
    			  if(i==findPos(array.get(j))) { //si deux elements different du tableau array sont en enregistrement synonyme
    				 
    				  table.add(array.get(j));//ajoute l'element en fin de liste 
    				  
    				 memorySize++;//on met ‡ jour la taille de la mÈmoire allouÈ777b
    				
    			 }
    			
    		  } 
    		
    		   data[i]=new QuadraticSpacePerfectHashing(table);
    		  
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
             sb.append("-"+ (indice++) +"->"+item.toString() + System.lineSeparator());
      return sb.toString();
   }
}
