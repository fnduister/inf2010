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
    	
    		return data;
    }

    // TODO: on ajoute une nouvelle donnee au bon endroit
    // O(log(n))
    public void insert(T item) {
    	
    	//une fois qu'on arrive a la feuille
    	if(item.compareTo(data) <=0){
    		//si on est sur un noeud ayant des descendants
    		if(left==null) {
    			left=new BinaryNode<T>(item);
    			left.data=item;
    			
    		}
    		// si on arrive a la feuille
    		else {
    			left.insert(item);
    			return;//on sort de la méthode une fois l'insertion effectué
    		}
    		
    	}
    	else
    	{
    		//on rappelle la methode insert si on est sur un noeud ayant des descendants
    		if(right==null) {
    			right=new BinaryNode<T>(item);
    			right.data=item;
    		}
    		// si on arrive a la feuille , on insert
    		else {
    			right.insert(item);
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
        if(item.compareTo(data)>0) { //parcourir le noeud droit si celui est supérieur a l'item
    		if(right !=null && right.contains(item))
    			return true; //renvoyer vrai si le noeud droit contient l'item
        
    	}
    	if (item.compareTo(data)<0 ) {//parcourir le noeud droit si celui est supérieur a l'item 
        	if(left!=null && left.contains(item))
        		return true;//renvoyer vrai si le noeud gauche contient l'item
        	
        }	
    	

    	return false;
    }

    // TODO: on retourne la maximale de l'arbre
    // O(n)
    public int getHeight() {

    	if(data==null ) {
    		return -1;
    	}
    	if(right!=null && left !=null) {
    			return 1+Math.max(right.getHeight(),left.getHeight());
    		}
    	
    	else if (right==null && left!=null) {
    			return 1+left.getHeight();		
    		}
    	else if (left==null && right !=null) {
    			return 1+right.getHeight();	
    		
    	}
    	
    	return 0;
    		
    	
    }   

    // TODO: l'ordre d'insertion dans la liste est l'ordre logique
    // de maniÃ¨re que le plus petit item sera le premier inserÃ©
    // O(n)
    public void fillListInOrder(List<BinaryNode<T>> result) {
    	
    	    if (data==null)
    	    	return;
    	
    		if(right==null && left ==null) {//si le noeud courant n'a pas d'enfants
    			
    			result.add(this);//ajouter directement le noeud courant
    		}
    		
    		if(left!=null &&left.getHeight()==0){// si le noeud a exactement un enfant gauche
    			result.add(left);//ajouter l'enfant
    			result.add(this);//puis ajouter le noeud courant
    		
   
    		
    		if (left!=null && left.getHeight()!=0)
    			left.fillListInOrder(result); //si le noeud courant a un sous arbre d'enfant gauche appelle recursivement la fonction listFillInOrder
    			
		
    		if(right!=null && right.getHeight()==0) {// si le noeud a exactement un enfant droit
    			
    			result.add(right);//ajouter l'enfant
    		}
    		if (right!=null && right.getHeight()!=0) {
	    			right.fillListInOrder(result);//sinon appeler a nouveau la fonction filllistInOrder pour cet sous-arbre
	    		}
    		}
    			
    		
    	
    }
}
