package POO;

public class Article {	
	private final String NumProduit; 
	private String nom;
	private double Prix_net;


        public Article(String NumProduit, String nom){
                    //completer
        	this.NumProduit=NumProduit;
        	this.nom=nom;
        	//par defaut on mettra le prix net a 0
        	this.Prix_net=0;
	}
	
	public Article(String np, String nom, double prix){		
		//completer
		this.NumProduit=np;
    	this.nom=nom;
    	
    	this.Prix_net=prix;
	}
	

	public String getNumProduit() {
		//completer
		
		return NumProduit;
	}	
	public String getNom() {
		//completer
		return nom;
	}
	public void setNom(String nom) {
		//completer
		this.nom=nom;
	}
	public double getPrixNet() {
		//completer
		
		return Prix_net;
	}
	public void setPrixNet(double prix_net) {
		//completer
		this.Prix_net=prix_net;
	}
	public double getVAT(){ //tva //sera implementer par chaque elelmetn 
		//completer
		return 0;
	}
	public double getPrix(int count){
		//completer
		return count*Prix_net;
	}
	public String getArticleType(){
		//completer
		String str= "C'est un article de type";
		return str;
	}
	public String toString() {
		//completer
		String description =" L'article" +this.getNom()+ "a pour numero "+this.getNumProduit()  +" et pour prix net"+this.getPrixNet();
		return description;
	}
}
