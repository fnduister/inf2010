package POO;

public class CadreDiplome extends Article {
	private String categorie;
	private String couleur;
	public CadreDiplome(String NumProduit, String nom, double prix, String couleur,String categorie){
		//completer
		super(NumProduit,nom,prix);//appel du constructeur par parametre
		this.couleur=couleur;
		this.categorie=categorie;
		
	}
	
	public CadreDiplome(String NumProduit, String nom, double prix,String categorie){
		//completer
		super(NumProduit,nom,prix);//appel du constructeur par parametre
		this.couleur="inconnu";
		this.categorie=categorie;
		
	}
	
	@Override
	public String getArticleType() {
		//completer
		String str=super.getArticleType()+"CadreDiplome";
		return str;
	}

	public String getCategorie() {//la categorie est de type String et non un int 
		//completer
		return categorie;
	}

	public void setCategorie(String categorie) {
                   //completer
		this.categorie=categorie;
	}
	
}
