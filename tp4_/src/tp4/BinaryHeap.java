package tp4 ;
import java.util.*; 


public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType [ ] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( boolean min )
    {
	this.min = min;
	currentSize = 0;
	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( AnyType[] items, boolean min )
    {
	this.min = min;
	
	// COMPLETEZ
	// invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
	
	array=items;
	currentSize=array.length;//mise a jour de la taille du tableau
		if(min) {
			
			buildMinHeap();
			
		}
		else {
			
			buildMaxHeap();
		}
    }
    
    public boolean offer( AnyType x )
    {
	if (x == null)
	    throw new NullPointerException("Cannot insert null in a BinaryHeap");
	
	if( currentSize + 1 == array.length )
	    doubleArray();
	
	
	// COMPLETEZ
	if(array[currentSize+1]==null) {//tjrs vrai // mettre l'�lement a la derniere position du tableau --revoir
		array[currentSize+1]=x;
		currentSize++;//enlevable
		
		
		
	}
	
	if(min) {
		
		int position=currentSize;
		while(array[position/2]!=null && array[position/2].compareTo(x)>=0 && position>1) {//si le parent de x a une valeur qui lui est sup�rieur
			
			//if(array[position/2]!=null && array[position/2].compareTo(x)>0)
				position=position/2;//chercher la position  tel que l'�lement a une valeur plus petite que x
		}
		
	

		swapReferences( array, position, currentSize );
		percolateDownMinHeap(position,currentSize );
		//percoler ses enfants
		position=currentSize;
		while(array[position/2]!= null && array[position/2]!=x) {
		 
			
			if(array[position].compareTo(array[position/2])<0) {
				
				swapReferences( array, position/2, currentSize );// percoler les enfants 
				percolateDownMinHeap(position/2,currentSize );
			}
			position=position/2;
			
		}
			//currentSize++;
	}
	else {
		
		int position=currentSize;
		while(array[position/2]!=null && array[position/2].compareTo(x)<=0 && position>1) {//si le parent de x a une valeur qui lui est sup�rieur
			
			//if(array[position/2]!=null && array[position/2].compareTo(x)>0)
				position=position/2;//chercher la position  tel que l'�lement a une valeur plus petite que x
		}
		
	

		swapReferences( array, position, currentSize );
		percolateDownMaxHeap(position,currentSize );
		//percoler ses enfants
		position=currentSize;
		while(array[position/2]!= null && array[position/2]!=x) {
		 
			
			if(array[position].compareTo(array[position/2])>0) {
				
				swapReferences( array, position/2, currentSize );// percoler les enfants 
				percolateDownMaxHeap(position/2,currentSize );
			}
			position=position/2;
			
		}
	}
	
	return true;
    }
    
    public AnyType peek()
    {
	if(!isEmpty())
	    return array[1];
	
	return null;
    }
    
    public AnyType poll()
    {
	//COMPLETEZ
    	if(!isEmpty()) { // si le tableau est vide 
    		//on prend le dernier element du tableau 
    		swapReferences(array,1,currentSize);
    		if(min) {
    			percolateDownMinHeap(1,array.length -1 ); //array.length -1 afin de ne pas travailler sur tout le tableau
    		}
    		else {
    			percolateDownMaxHeap(1,array.length -1 );
    		}
    		
    		
    		
    	}
    	return null;
    }
    
    public Iterator<AnyType> iterator()
    {
	return new HeapIterator();
    }
    
    private void buildMinHeap()
    {
	//COMPLETEZ
    	int position=currentSize;
    	if(position>0) {
	    	for(int i=position/2;i>=1;i--) {
	    		
	    		percolateDownMinHeap(i,array.length );
	    	}
    	}
    	//si le tableau commence a l'index 0 on le met a l'index 1 
    	
    	if(array[0]!=null) { //decaler les �l�ments afin qu'ils commencent a 1
		
		
			//doubler le tableau
			AnyType [ ] newArray;
			
			newArray = (AnyType []) new Comparable[ array.length * 2  ];
			//newArray = (AnyType []) new Comparable[ array.length +1  ];
			for( int i = 0; i < array.length; i++ )
			    newArray[ i+1 ] = array[ i ];
			array = newArray;
			
    	}
		
	
    }
    
    private void buildMaxHeap()
    {
	//COMPLETEZ
    	
    	int position=array.length;
    	if(position>0) {
	    	for(int i=position/2;i>=0 && array[i]!=null;i--) {
	    		
	    		percolateDownMaxHeap(i,array.length );
	    	}
    	}
    	
    	//si le tableau commence a l'index 0 on le met a l'index 1 
    	
    	if(array[0]!=null) { //decaler les �l�ments afin qu'ils commencent a 1
		
		
			//doubler le tableau
			AnyType [ ] newArray;
			
			newArray = (AnyType []) new Comparable[ array.length * 2  ];
			//newArray = (AnyType []) new Comparable[ array.length +1  ];
			for( int i = 0; i < array.length; i++ )
			    newArray[ i ] = array[ i ];///  newArray[ i+1 ] = array[ i ];
			array = newArray;
			
		
		
		for(int i=array.length-1;i>0  ;i--) {
			
			
			
		
			if( array[i]!=null)
				array[i]=array[i-1];
		}
		array[0]=null;
		}
	
    }
    
    public boolean isEmpty()
    {
	return currentSize == 0;
    }
    
    public int size()
    {
	return currentSize;
    }
    
    public void clear()
    {
	currentSize = 0;
	modifications = 0;
	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    private static int leftChild( int i, boolean heapIndexing )
    {
	return ( heapIndexing ? 2*i : 2*i+1 );
    }
    
    private void swapReferences( int index1, int index2 )
    {
	swapReferences(array, index1, index2);
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
				    void swapReferences( AnyType[] array, int index1, int index2 )
    {
	AnyType tmp = array[ index1 ];
	array[ index1 ] = array[ index2 ];
	array[ index2 ] = tmp;
    }
    
    @SuppressWarnings("unchecked")
	private void doubleArray()
    {
	AnyType [ ] newArray;
	
	newArray = (AnyType []) new Comparable[ array.length * 2 ];
	for( int i = 0; i < array.length; i++ )
	    newArray[ i ] = array[ i ];
	array = newArray;
    }
    
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMinHeap( int hole, int size )
    {
    	if(array[0]!=null) //si les �lements commence � l'index 0
    		percolateDownMinHeap(array, hole, size,false);
    	else
    		percolateDownMinHeap(array,hole,size,true);
	
    }
    
    /**
     * @param array   Tableau d'element
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
				    void percolateDownMinHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ
    	
    	/*
    	if(heapIndexing==false) { //decaler les �l�ments afin qu'ils commencent a 1
    		
    		if(array.length<=size) {
    			
    			
    			//doubler le tableau
    			AnyType [ ] newArray;
    			
    			newArray = (AnyType []) new Comparable[ array.length * 2 ];
    			for( int i = 0; i < array.length; i++ )
    			    newArray[ i ] = array[ i ];
    			array = newArray;
    		}
    		
    		for(int i=0;i<array.length && array[i]!=null && array[i+1]!=null;i++) {
    			
    			
    			swapReferences(array,i,i+1);//c'est le cote au bout qui prend la valeur 1
    			
    		}
    		heapIndexing=true;
    	}
    	if(heapIndexing==true) {
    		
    		 int child=leftChild(hole,true);       
    		 AnyType tmp = array[ hole ]; 
    		 
    	        for( ; hole * 2 <= size; hole = child ) { 
    	        	child = hole * 2;             
	    	        if( child != size && array[ child + 1 ].compareTo( array[ child ] ) < 0 )        
	    	        	child++;            
	    	        if( array[ child ].compareTo( tmp ) < 0 )           
	    	        	array[ hole ] = array[ child ];             
	    	        else                
	    	        	break;       
    	        }      
    	        array[ hole ] = tmp;   
    			
    	
    	}
    	*/
        int child=leftChild(hole,heapIndexing);   
        
		 AnyType tmp = array[ hole ]; 
		 if(child>size) {return;}//sortir de la fonction si l'enfant en question deborde du tableau
		 
	        for( ; hole * 2 <= size; hole = child ) { 
	        	child = hole * 2;             
  	        if( child != size && array[ child + 1 ].compareTo( array[ child ] ) < 0 )        
  	        	child++;            
  	        if( array[ child ].compareTo( tmp ) < 0 )           
  	        	array[ hole ] = array[ child ];             
  	        else                
  	        	break;       
	        }      
	        array[ hole ] = tmp; 
			
		
    }
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMaxHeap( int hole, int size )
    {
    	//
    	if(array[0]!=null) //si les �lements commence � l'index 0
    		percolateDownMaxHeap(array, hole, size,false);
    	else
    		percolateDownMaxHeap(array,hole,size,true);
	
    }
    
    /**
     * @param array         Tableau d'element
     * @param hole          Position a percoler
     * @param size          Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>> 
				    void percolateDownMaxHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ

    	/*if(heapIndexing==false) { //decaler les �l�ments afin qu'ils commencent a 1
    		
    		if(array.length<=size) {
    			
    			
    			//doubler le tableau
    			AnyType [ ] newArray;
    			
    			newArray = (AnyType []) new Comparable[ array.length * 2  ];
    			//newArray = (AnyType []) new Comparable[ array.length +1  ];
    			for( int i = 0; i < array.length; i++ )
    			    newArray[ i ] = array[ i ];
    			array = newArray;
    			
    		}
    		
    		for(int i=0;i<size && array[i]!=null ;i++) {
    			
    			
    			//swapReferences(array,i,i+1);//c'est le cote au bout qui prend la valeur 1
    		
    			
    			array[size -i]=array[size -i-1];
    		}
    		array[0]=null;
    		heapIndexing=true;
    	}
    	if(heapIndexing==true) {
    		
    		
    		
    		 int child=leftChild(hole,true);       
    		 AnyType tmp = array[ hole ]; 
    		 
    	        for( ; hole * 2 <= size; hole = child ) { 
    	        	child = hole * 2;             
	    	        if( child != size && array[ child + 1 ].compareTo( array[ child ] ) > 0 )        
	    	        	child++;            
	    	        if( array[ child ].compareTo( tmp ) > 0 )           
	    	        	array[ hole ] = array[ child ];             
	    	        else                
	    	        	break;       
    	        }      
    	        array[ hole ] = tmp;   
    			
    		
    		
    		
    		
    		
    		
    	}*/
  		
	          int child=leftChild(hole,heapIndexing);   
          
    		 AnyType tmp = array[ hole ]; 
    		 if(child>size) {return;}//sortir de la fonction si l'enfant en question deborde du tableau
    		 
    	        for( ; hole * 2 <= size; hole = child ) { 
    	        	child = hole * 2;             
	    	        if( child != size && array[ child + 1 ].compareTo( array[ child ] ) > 0 )        
	    	        	child++;            
	    	        if( array[ child ].compareTo( tmp ) > 0 )           
	    	        	array[ hole ] = array[ child ];             
	    	        else                
	    	        	break;       
    	        }      
    	        array[ hole ] = tmp; 
    			
    		
    		
    	
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSort( AnyType[] a )
    {
	//COMPLETEZ
    	
    	//si le tableau ne commence pas a l'indice 0 , on peut pr�sumer qu'il commence a l'index 1
    	if(a[0]==null) {
    		for(int i=0;i<a.length+1;i++) {
    			//on fait reculer tous les �lements 
    			a[i] = a[i+1 ];
    		}
    	}
    	//mettre la derniere valeur du tableau au sommet et faire une percolation du monceau allant de l'index 1 a n
    	
    	for(int i=0;i<a.length;i++) {
    		
    		//on prend le dernier element du tableau a[a.length-1]
    		
    		AnyType tmp = a[0];
    		a[0] = a[(a.length-1)-i];
    		a[(a.length-1)-i] = tmp;
    		percolateDownMinHeap(a,0,a.length-i,true);
    	}
    	
    	
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSortReverse( AnyType[] a )
    {
	//COMPLETEZ
    	//si le tableau ne commence pas a l'indice 0 , on peut pr�sumer qu'il commence a l'index 1
    	if(a[0]==null) {
    		for(int i=0;i<a.length+1;i++) {
    			//on fait reculer tous les �lements 
    			a[i] = a[i+1 ];
    		}
    	}
    	//mettre la derniere valeur du tableau au sommet et faire une percolation du monceau allant de l'index 1 a n
    	
    	for(int i=0;i<a.length;i++) {
    		
    		//on prend le dernier element du tableau a[a.length-1]
    		/*swapreference entre l'index 0 et la taille du tableau*/
    		AnyType tmp = a[0];
    		a[0] = a[a.length-1];
    		a[a.length-1] = tmp;
    		percolateDownMaxHeap(a,0,a.length,true);
    	}
    	
    }
    
    public String nonRecursivePrintFancyTree()
    {
	String outputString = "";
	
	//COMPLETEZ
	//string temporaire
	for(int i=0;i<array.length;i++) {
	  outputString.concat(array[i].toString());
	}
	return outputString;
    }
    
    public String printFancyTree()
    {
	return printFancyTree(1, "");
    }
    
    private String printFancyTree( int index, String prefix)
    {
	String outputString = "";
	
	outputString = prefix + "|__";
	
	if( index <= currentSize )
	    {
		boolean isLeaf = index > currentSize/2;
		
		outputString += array[ index ] + "\n";
		
		String _prefix = prefix;
		
		if( index%2 == 0 )
		    _prefix += "|  "; // un | et trois espace
		else
		    _prefix += "   " ; // quatre espaces
		
		if( !isLeaf ) {
		    outputString += printFancyTree( 2*index, _prefix);
		    outputString += printFancyTree( 2*index + 1, _prefix);
		}
	    }
	else
	    outputString += "null\n";
	
	return outputString;
    }
    
    
    private class HeapIterator implements Iterator {
	
    	//creer un conteneur arrayList afin de pouvoir utiliser Iterator
		ArrayList<?> arrayList=new ArrayList<>(Arrays.asList(array));
		//creation d'un iterateur
		Iterator<?> it=arrayList.iterator();
		public boolean hasNext() {
		    //COMPLETEZ
	
			if(next()==null) {
				
				return false;
			}
			return true;
		}
		
		public Object next()  {
	//COMPLETEZ
			
			if(modifications!=0)
				throw new ConcurrentModificationException();
			
		//	return this.next();
			
			if(modifications!=0)
				throw new ConcurrentModificationException();
			// retourner l'objet sur lequel pointe le prochain it�rateur
			
			return it.next();
			
			
		}
	
	
	
		/*public Object next() throws NoSuchElementException, 
					    ConcurrentModificationException, 
					    UnsupportedOperationException {
		    //COMPLETEZ
		}*/
		
		public void remove() {
		    throw new UnsupportedOperationException();
		}
		
    }
    
}
