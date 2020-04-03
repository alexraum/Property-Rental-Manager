/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

/**
 * Implements the SortedList interface with a data structure of linked Nodes.
 * @author Alex Raum, Walker Clem
 *
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {

	/** Node object */
	private Node<E> head;
	
	/**
	 * Constructor
	 */
	public SortedLinkedListWithIterator() {
		
	}
	
	/** 
	 * Returns the size
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returns whether the list is empty
	 * 
	 * @return if it is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns whether the list contains object e
	 * 
	 * @param e the object to check
	 * 
	 * @return whether the object contains
	 */
	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Adds an item to the list
	 * 
	 * @param e the object to add
	 * 
	 * @return if the object is added
	 */
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Clears the list
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the item from an index
	 * 
	 * @param index the index of the item
	 * 
	 * @return the element
	 */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Remove an item at index
	 * 
	 * @param index the index of the element to remove
	 * 
	 * @return if the element is removed
	 */
	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Shorten the list
	 * 
	 * @param start of the truncate
	 * 
	 * @return the truncated sorted list
	 */
	@Override
	public SortedList<E> truncate(int start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the index of an element
	 * 
	 * @param the element
	 * 
	 * @return the index of the element
	 */
	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Translates the list to a string
	 */
	@Override
	public String toString() {
		return null;
	}

	/**
	 * The iterator
	 * 
	 * @return the new iterator
	 */
	public SimpleListIterator<E> iterator() {
		return null;
	}
	
	/**
	 * The node object
	 * @author Alex Raum, Walker Clem
	 *
	 */
	private static class Node<E> {
		
		/** the e object */
		E value;
		/** the next node object */
		private Node<E> next;
		
		/**
		 * Creates a node
		 * 
		 * @param value the value of the node
		 * @param next the next node
		 */
		public Node(E value, Node<E> next) {
			
		}
	}
	
	/**
	 * The cursor object
	 * @author Alex Raum, Walker Clem
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {
		
		/** the travelor node */
		private Node<E> traveler;
		
		/**
		 * the cursor constructor
		 */
		public Cursor() {
			
		}
		
		/**
		 * if the cursor has a next
		 * 
		 * @return if the cursor has a next
		 */
		@Override
		public boolean hasNext() {
			return false;
		}
		
		/**
		 * the next object 
		 * 
		 * @return the next object
		 */
		@Override
		public E next() {
			return null;
		}
	}
}
