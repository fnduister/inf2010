package POO;

import java.util.Comparator;

public class Etudiant  {
	final int N = 10;
	private String Matr;
	private String nom;
	private String prenom;	
	private String email; 
	private int section; 
	
        private int n_des_notes;
	private NoteCours [] notes;
	

	//ils l'avaient appelle Student le constructeur a mon avis
	 public Etudiant(String Matr,String name, int section) {
              //completer
		 this.Matr=Matr;
		 this.nom=name;
		 this.section=section;
		 this.n_des_notes=0;
		 notes=new NoteCours[N];//initialisation du tableau 
		 
	    }
        
         
	//le sigle est un int: int sigle
	public void AjouterNote(String sigle, String titre, int note){ 
		//completer
		
	
		NoteCours une_note=new NoteCours();
		//mise manuellement des parametre 
		
			if (n_des_notes<N) {
			une_note.note=note;
			une_note.sigle=sigle;
			une_note.titre=titre;
			
			//ajout de la note au tableau a la position restante 
			
		
			notes[n_des_notes]=une_note; // lenght envoie la taille du tableau??? 
			n_des_notes++;
			}
			
	}
	
	public double NoteMoyenne(){
		//completer
		int somme =0;
		// comment le faire avec ce for for (NoteCours element:[]NoteCours tableau) 
		for (int index=0;index<this.n_des_notes;index++) {
			somme+=notes[index].note;
			
		}
		return somme/n_des_notes; 
		
	}

	public String getMatr() {
		//completer
		return this.Matr;
	}

	public void setMatr(String matr) {
		//completer
		this.Matr=matr;
	}

        public String getEmail() {
                   //completer
        	return this.email;
	}

	public void setEmail(String matr) {
                      //completer
		this.email=matr;
	}

	public String getNom() {
		//completer
		return nom;
	}
    
	
	public void setNom(String nom) {
		//completer
		this.nom =nom;
	}

	public String getPrenom() {
		//completer
		return prenom;
	}

	public void setPrenom(String prenom) {
		//completer
		this.prenom=prenom;
	}
	
	public int getSection() {
		return section  ;
	}
	
    public String toString() {
        // completer
    	//decrit l'objet 
    	
    	String description=("L'etudiant "+nom+" a pour section "+section+ " et a pour matricule"+ Matr);
    	return description;
    }
    
    
    public boolean equals(Object etudiant_x) {
            // completer
    
    	//verifier si les r�fernces de l'objet sont identiques
    	if (this== etudiant_x) {
    		return true;
    	}
    	
    	//on verifie si l'objet passe en parametre est null
    	if(etudiant_x==null) {
    		return false;
    	}
    	
    	//on verifie que les objets sont du meme type
    	if (this.getClass()!=etudiant_x.getClass()) {
    		return false;
    	}
    	
    	Etudiant etudiant=(Etudiant)etudiant_x;
    	
    	
    	//comparer les attributs 
    	///faire un if composite !!
    	if (this.n_des_notes != etudiant.n_des_notes) {
    		return false;
    	}
    	
    	if (this.nom !=etudiant.nom) {
    		//les string sont comparables
    		return false;
    	}
    
    	if (this.prenom !=etudiant.prenom) {
    		//les string sont comparables
    		return false;
    	}
    	if(this.section!=etudiant.section)
    		return false;
    	
    	if (this.Matr!=etudiant.Matr)
    		return false;
    	
    	return true;
    
    }
    
 
}

