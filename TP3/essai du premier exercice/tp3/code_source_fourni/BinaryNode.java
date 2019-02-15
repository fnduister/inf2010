package tp3.code_source_fourni;

import java.util.List;

public class BinaryNode<T extends Comparable<? super T> > {
    private T data;
    private BinaryNode<T> right;
    private BinaryNode<T> left;

    // TODO: initialisation
    // O(1)
    public BinaryNode(T data) {
    	this.data=data;//objet courant pointé par this=data

    }

    // TODO: on retourne la donnee voulue
    // O(1)
    public T getData() {
    	if(this.data==null)//pertinence?
    		return null;
    	else 
    		return data;
    }

    // TODO: on ajoute une nouvelle donnee au bon endroit
    // O(log(n))
    public void insert(T item) {
    	
    	//une fois qu'on arrive a la feuille
    	if(item.compareTo(data) <=0){
    		//si on est sur un noeud ayant des descendants
    		if(left.data!=null)
    			left.insert(item);
    		// si on arrive a la feuille
    		else {
    			left.data=item;
    			return;//on sort de la méthode une fois l'insertion effectué
    		}
    		
    	}
    	else
    	{
    		//on rapelle la methode insert si on est sur un noeud ayant des descendants
    		if(right.data!=null)
    			right.insert(item);
    		// si on arrive a la feuille , on insert
    		else {
    			right.data=item;
    			return;//on sort de la méthode une fois l'insertion effectué
    		}
    	}

    }

    // TODO: est-ce que l'item fais partie du noeuds courant
    // O(log(n))
    public boolean contains(T item) {
        	
    	//comparer l'item avec le noeud courant 
    	if(item.compareTo(data)==0)
    		return true;//renvoyer vrai si le noeud actuel est egale l'item
        if(right.data!=null && right.data.compareTo(item)>0) { //parcourir le noeud droit si celui est supérieur a l'item
    		if(right.contains(item))
    			return true; //renvoyer vrai si le noeud droit contient l'item
        
    	}
    	if (left.data!=null && left.data.compareTo(item)<0) {//parcourir le noeud droit si celui est supérieur a l'item 
        	if(left.contains(item))
        		return true;//renvoyer vrai si le noeud gauche contient l'item
        	
        }	
    	

    	return false;
    }

    // TODO: on retourne la maximale de l'arbre
    // O(n)
    public int getHeight() {

    	if(data!=null)
    		return 1+Math.max(right.getHeight(),left.getHeight());
    	return -1;
    }   

    // TODO: l'ordre d'insertion dans la liste est l'ordre logique
    // de maniÃ¨re que le plus petit item sera le premier inserÃ©
    // O(n)
    public void fillListInOrder(List<BinaryNode<T>> result) {
    	//determiner l'élément le plus a gauche
    	BinaryNode<T> datamin=null;
    	BinaryNode<T> variable = null;
    	if(left.data!=null)
    		variable=left;//new BinaryNode(left.data);//revoir la copie
    	
    		
    	while(variable.left!=null) {	
    		if(variable.left==null) {
    			datamin=variable;//on a la valeur la plus petite 
    			//remonter l,arbre 
    			result.add(datamin);
    			
    			break;
    		}
    		variable=left;
    	
    	}
    		
    	
    }
}
