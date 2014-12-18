package linkedList;

import java.util.Iterator;

import structures.CollectionI;

public interface LinkedListI<E> extends CollectionI<E>{
	/**
	 * Used by the test classes only 
	 * @return
	 */
	public ListNodeI<E> getHead();
	
	
	public void append(E value);
	
	public void prepend(E value);
	
}