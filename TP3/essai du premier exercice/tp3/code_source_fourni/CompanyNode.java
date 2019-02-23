package tp3.code_source_fourni;

import java.util.List;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
    		//money=data;
    		//childs=new BinarySearchTree<CompanyNode>();
    		//comment implementer worstChild;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
    	//	childs.insert(item);//ajout de l'item à la compagnie ?? a vérifier
    	//	money+=item.getMoney();//mise a jour du montant en banque
    }

    // TODO: on retourne le montant en banque de la compagnie
    // O(1)
    public Integer getMoney() {
        return Integer.MIN_VALUE;
    }

    // TODO: on rempli le builder de la compagnie et de ses enfants avec le format
    //A
    // > A1
    // > A2
    // >  > A21...
    // les enfants sont afficher du plus grand au plus petit (voir TestCompany.testPrint)
    // O(n)
    public void fillStringBuilderInOrder(StringBuilder builder, String prefix) {
    	 
    	//childs.getItemsInOrder();
    		
    		
    		
    	//}
    	
    }

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
    	if(item.getMoney()>getMoney())
    		return -1;
    	else if(item.getMoney()<getMoney())
    		return 1;
    	else
    		return 0;
    }
}
