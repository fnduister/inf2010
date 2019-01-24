package ADT;

import java.util.Random;

public class Simulation {
	 private static Random random=new Random(10000) ; //ajout de la classe random  

	 //Retourne un nombre réel aléatoire uniformément dans [0,1[
	    public static double uniform() {
	        //completer
	    	
	    	return random.nextDouble(); 
	    	
	    }

	 
	 
	 //Retourne un nombre entier aléatoire uniformément dans [0,n[
	    public static int uniform(int n) {
	        //completer
	    	return random.nextInt(n);
	    	
	    }

	//Retourne un entier long aléatoire uniformément dans [0, n[.
        // Vous pouviez trouver le code https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
    public static long uniform(long n) {
        if (n <= 0L) throw new IllegalArgumentException("Argument doit etre positive: " + n);


        long r = random.nextLong();
        long m = n - 1;

        
        if ((n & m) == 0L) {
            return r & m;
        }

      
        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }
    
    //Retourne avec succès un booléen true si p suit d'une distribution de Bernoulli
    public static boolean bernoulli(double p) { //faire le calcul avec uniform()
       //completer
    	//comment v�rifier que p suit une distribution de bernouilli?
    	return true;
    }
    
    public static  Compteur max(Compteur x, Compteur y) {//ajout de type int 
      // completer
    	if( x.compareTo(y) > 0) // si l'entier n�gatif alors y >x
    		return x ;
    	else 
    		return y;
    }
	
	 public static void main(String[] args) {
	        int n = 10;
	        Compteur pile = new Compteur("pile");
	        Compteur face = new Compteur("face");
	       
              //Les instructions du simulation
                   //completer
	        // lancer n fois la piece 
	         for(int i=0;i<n;i++) {
	        
	        	 int nombre_aleatoire= uniform(n) %2;//trouver un nombre aleatoire entre 0 et 1
	        	 
	        	 //int nombre_aleatoire=uniform(2);
	        	 if (nombre_aleatoire==0)
	        		 pile.increment();
	        	 else 
	        		 face.increment();
	         }
	        
              //afficher la différence entre les score des compteur
	        System.out.println("La diff�rence des scores est " + (pile.score()-face.score()));
	        
	        Compteur pile_c = new Compteur("pile");
		Compteur face_c = new Compteur("face");
		        
	          //Les instructions du simulation
                   //completer
		for (int i=0;i<n;i++) {
			
			 int nombre_aleatoire=uniform(n) %2;//trouver un nombre aleatoire entre 0 et 1
        	 if (nombre_aleatoire==0)
        		 pile_c.increment();
        	 else 
        		 face_c.increment();
			
			
		}
                   //afficher le maximum entre les score des compteur
				
					 
					 System.out.println("le maximum entre les scores des compteurs est  "+(max((max(pile_c,face_c)),(max(pile,face)))).score());
					 
	        }
	    
	        
	    }



