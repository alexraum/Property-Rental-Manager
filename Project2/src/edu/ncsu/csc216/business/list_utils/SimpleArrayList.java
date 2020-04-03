/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

/**
 * Implements the SimpleList interface with an array data structure.
 * @author Alex Raum, Walker Clem
 *
 */
public class SimpleArrayList<E> implements SimpleList<E> {

	/** List Resize */
	private static final int RESIZE = 0; // need to double check this value with TA
	/**  List object */
	private Object[] list;
	/** List size */
	private int size;
	
	/*
	 * Constructor for the ArrayList
	 */
	public SimpleArrayList() {
		
	}
	
	/**
	 * Constructor with a capactiy set
	 */
	public SimpleArrayList(int capacity) {

	}
	
	/** 
	 * Returns the size
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		return 0;
	}
	
	/**
	 * Returns whether the list is empty
	 * 
	 * @return if it is empty
	 */
	@Override
	public boolean isEmpty() {
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
		return false;
	}
	
	/**
	 * Gets the item from an index
	 * 
	 * @param idx the index of the item
	 * 
	 * @return the element
	 */
	@Override
	public E get(int idx) {
		return null;
	}
	
	/**
	 * Adds an item at position e
	 * 
	 * @param pos the position of the elemeent
	 * @param e the element
	 * 
	 * @return if the element was added
	 */
	@Override
	public void add(int pos, E e) {
		
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
		return 0;
	}
	
}
