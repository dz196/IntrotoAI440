package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    		if (size == 0)
    			throw new NoSuchElementException(); 
    		Node front = rear.next;
    		Node previous = rear;
    		Node newfront = rear.next.next;
    		previous.next = newfront;
    		size--;
    		return front.tree;
    		/* COMPLETE THIS METHOD */
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */
    	/*if (size == 0)
    		throw new NoSuchElementException();*/
    	try{
	    	Node previous = rear;
	    	for (Node tmp = rear.next; tmp != rear; tmp = tmp.next){
	    		//System.out.println("Reached Remove Method");
	    		System.out.println("Cursing through RemoveTreeMethod: " + tmp.tree.toString());
	    		if (vertex.getRoot().equals(tmp.tree.getRoot())){
	    			//System.out.println("Reached Remove Method2");
	    	    	System.out.println("Vertex: " + vertex.name + " " + vertex.parent);
	    	    	System.out.println("Compared: " + tmp.tree.getRoot());
	    			previous.next = tmp.next;
	    			for (Node tmp2 = rear.next; tmp2!=rear; tmp2 = tmp2.next){
	    				System.out.println("NEWTREE: " + tmp2.tree.toString());
	    			}
	    			System.out.println("NEWTREErear: " + rear.tree.toString());
	    			size--;
	    			//System.out.println("REAR: " + rear.tree.toString());
	    			return tmp.tree;
	    		}
	    		previous = previous.next;
	    	}
	    	//System.out.println("PREVIOUS: " + previous.tree.toString());
	    	System.out.println("Vertex: " + vertex.name + " " + vertex.parent);
	    	System.out.println("Compared: " + rear.tree.getRoot());
	    	if (vertex.getRoot().equals(rear.tree.getRoot())){
	    		//System.out.println("Reached Remove Method3");
	    		PartialTree tmp = rear.tree;
	    		previous.next = rear.next;
	    		rear = previous;
	    		for (Node tmp2 = rear.next; tmp2!=rear; tmp2 = tmp2.next){
    				System.out.println("NEWTREE: " + tmp2.tree.toString());
    			}
    			System.out.println("NEWTREErear: " + rear.tree.toString());
	    		size--;
	    		return tmp;
	    	}
	    	//System.out.println("Reached Remove Method4");
	    	throw new NoSuchElementException();
    	}
    	catch(Exception e){
	    	return null;
    	}
     }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


