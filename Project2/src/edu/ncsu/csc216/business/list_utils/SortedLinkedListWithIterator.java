/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

/**
 * @author Alex Raum
 *
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {

	/** */
	private Node<E> head;
	
	/**
	 * 
	 */
	public SortedLinkedListWithIterator() {
		
	}
	
	/**
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public SortedList<E> truncate(int start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return null;
	}

	/**
	 * 
	 */
	public SimpleListIterator<E> iterator() {
		return null;
	}
	
	/**
	 * 
	 * @author Alex Raum
	 *
	 */
	private static class Node<E> {
		
		/** */
		E value;
		/** */
		private Node<E> next;
		
		/**
		 * 
		 */
		public Node(E value, Node<E> next) {
			
		}
	}
	
	/**
	 * 
	 * @author Alex Raum
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {
		
		/** */
		private Node<E> traveler;
		
		/**
		 * 
		 */
		public Cursor() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public boolean hasNext() {
			return false;
		}
		
		/**
		 * 
		 */
		@Override
		public E next() {
			return null;
		}
	}
}
