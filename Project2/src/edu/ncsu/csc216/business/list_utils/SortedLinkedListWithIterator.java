/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

import java.util.NoSuchElementException;

/**
 * Implements the SortedList interface with a data structure of linked Nodes.
 * 
 * @author Alex Raum, Walker Clem
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {

	/** Node object */
	private Node<E> head;
	
	/**
	 * The Constructor
	 */
	public SortedLinkedListWithIterator() {
		this.head = null;
	}
	
	/** 
	 * Returns the size
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		int count = 0;
		Node<E> current = this.head;
		while (current != null) {
			current = current.next;
			count++;
		}
		return count;
	}

	/**
	 * Returns whether the list is empty
	 * 
	 * @return if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns whether the list contains object e
	 * 
	 * @param e the object to check
	 * @return whether the object contains
	 */
	@Override
	public boolean contains(E e) {
		return indexOf(e) != -1;
	}

	/**
	 * Adds an item to the list
	 * 
	 * @param e the object to add
	 * @return if the object is added
	 * @throws NullPointerException if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	@Override
	public boolean add(E e) {
//		if (e == null) {
//			throw new NullPointerException();
//		}
//		if (contains(e)) {
//			throw new IllegalArgumentException();
//		}
//		if (head == null || head.value.compareTo(e) > 0) {
//			head = new Node<E>(e, head);
//		} else {
//			Node<E> current = head;
//			while (current.next != null && current.next.value.compareTo(e) <= 0) {
//				current = current.next;
//			}
//			current.next = new Node<E>(e, current.next);
//		}
//		return true;
		
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (head == null || head.value.compareTo(e) > 0) {
			head = new Node<E>(e, head);
		} else {
			Node<E> current = head;
			while (current.next != null && current.next.value.compareTo(e) <= 0) {
				current = current.next;
			}
			current.next = new Node<E>(e, current.next);
		}
		return true;
	}

	/**
	 * Clears the list
	 */
	@Override
	public void clear() {
		this.head = null;

	}

	/**
	 * Gets the item from an index
	 * 
	 * @param index the index of the item
	 * @return the element
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.value;
	}

	/**
	 * Remove an item at index
	 * 
	 * @param index the index of the element to remove
	 * @return if the element is removed
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E elem;
		if (index == 0) {
			elem = head.value;
			head = head.next;
		} else {
			Node<E> current = head;
			for (int i = 0; i < (index - 1); i++) {
				current = current.next;
			}
			elem = current.next.value;
			current.next = current.next.next;
		}
		return elem;
	}

	/**
	 * Shorten the list
	 * 
	 * @param start of the truncate
	 * @return the truncated sorted list
	 * @throws IllegalArgumentException if the index is out of range
	 */
	@Override
	public SortedList<E> truncate(int start) {
		if (start < 0 || start > size()) {
			throw new IllegalArgumentException();
		}
		Node<E> tail;
		if (start == 0) {
			tail = head;
			this.head = null;
		} else {
			Node<E> current = head;
			for (int i = 0; i < (start - 1); i++) {
				current = current.next;
			}
			tail = current.next;
			current.next = null;
		}
		SortedLinkedListWithIterator<E> tailList = new SortedLinkedListWithIterator<E>();
		tailList.head = tail;
		return tailList;
	}

	/**
	 * Returns the index of an element
	 * 
	 * @param the element
	 * @return the index of the element
	 */
	@Override
	public int indexOf(E e) {
		Node<E> current = head;
		for (int i = 0; i < size(); i++) {
			// TODO may need to use ==
			if (current.value.equals(e)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}
	
	/**
	 * Translates the list to a string
	 * 
	 * @return a String representation of the list
	 */
	@Override
	public String toString() {
		if (head == null) {
			return "[]";
		} else {
			String list = "[" + head.value;
			Node<E> current = head.next;
			while (current != null) {
				list += ", " + current.value;
				current = current.next;
			}
			list += "]";
			return list;
		}
	}

	/**
	 * The iterator
	 * 
	 * @return the new iterator
	 */
	public SimpleListIterator<E> iterator() {
		Cursor iterator = new Cursor();
		return iterator;
	}
	
	/**
	 * The Node class is a private inner class and is used
	 * to define all state and behavior for a Node object.
	 * 
	 * @author Alex Raum, Walker Clem
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
			this.value = value;
			this.next = next;
		}
	}
	
	/**
	 * The Cursor class is a private inner class and is used
	 * to define all state and behavior for a Cursor object.
	 * 
	 * @author Alex Raum, Walker Clem
	 */
	private class Cursor implements SimpleListIterator<E> {
		
		/** the traveler node */
		private Node<E> traveler;
		
		/**
		 * the cursor constructor
		 */
		public Cursor() {
			traveler = head;
		}
		
		/**
		 * if the cursor has a next element
		 * 
		 * @return if the cursor has a next element
		 */
		@Override
		public boolean hasNext() {
			return traveler != null;
		}
		
		/**
		 * the next element
		 * 
		 * @return the next element
		 * @throws NoSuchElementException if the list has already been traversed
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E value = traveler.value;
			traveler = traveler.next;
			return value;
		}
	}
}
