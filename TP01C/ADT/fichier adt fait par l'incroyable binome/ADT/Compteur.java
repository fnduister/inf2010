package ADT;

import java.util.Random;

public class Compteur implements Comparable<Compteur> {

    private final String nom;        // nom du compteur
    private int compteur = 0;        // initialisation la valeur courant est 0

    
    public Compteur(String id) {
         // completer
    		nom=id;
    } 

   
    public void increment() {
         // completer
    	compteur++;
    } 
    

   
    public int score() {
         // completer
    	return compteur;
    } 

   
    public String toString() {
         // completer
    	String description ="le compteur a pour identifiant "+ nom + " et pour valeur "+compteur;
    	return description;
    } 

  
    public int compareTo(Compteur x) {
                 // completer
    	
    	return compteur- x.compteur; 
    }
    
	
      
        private static Random random=new Random(10000);
    
      //Retourne un nombre entier aléatoire uniformément dans [0,n[
	 public static int uniform(int n) {
	                         // completer
		 
		 return random.nextInt(n); //genere un nombre aleatoire entre 0 et n 
		 
		
	    }


   
    public static void main(String[] args) { 
        int n = 6;
        int essais = 60000;

        // Creation n compteurs
       
       Compteur compteur[]=new Compteur[n];//tableau vide pouvant contenir 6 objets compteurs
        
       //  le nom des compteurs 
       
       for (int i=0;i<6;i++) {
    	   compteur[i]=new Compteur("Compteur"+i);
    	   
       }
       
       
      /* compteur[0]=new Compteur("1");
       compteur[1]=new Compteur("2");
       compteur[2]=new Compteur("3");
       compteur[3]=new Compteur("4");
       compteur[4]=new Compteur("5");
       compteur[5]=new Compteur("6");*/
       
 
         // incrémente les compteurs d'essais au hasard
        
       for (int index=0;index<essais;index++) {
    	   
    	 //retourner un nombre entre 0 et 6 
           int nombrealeatoire=uniform(n);
      
            //incrementer le compteur qui se rapporte � ce nombre 
      
     
           
           
           compteur[nombrealeatoire].increment();
    	   
    	   
    	   
    	   
    	   
       }
       		
       
       
        // Affichage des resultat
       
	       for (int indice=0;indice<n;indice++){
	    	   
	    	   System.out.println("le compteur "+ (indice+1) + " a pour valeur "+compteur[indice].score());
	    	   
	    	   
	       }
       
        
        }
    } 

