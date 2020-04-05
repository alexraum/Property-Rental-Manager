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
	private static final int RESIZE = 12;
	/**  List object */
	private Object[] list;
	/** List size */
	private int size;
	
	/*
	 * Constructor for the ArrayList
	 */
	public SimpleArrayList() {
		this(RESIZE);
	}
	
	/**
	 * Constructor with a capacity set
	 */
	@SuppressWarnings("unchecked")
	public SimpleArrayList(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		list = (E[])(new Object[capacity]);
		this.size = 0;
	}
	
	/** 
	 * Returns the size
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		if (this.size > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return this.size;
	}
	
	/**
	 * Returns whether the list is empty
	 * 
	 * @return if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
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
		return indexOf(e) != -1;
	}
	
	/**
	 * Adds an item to the list
	 * 
	 * @param e the object to add
	 * 
	 * @return if the object is added
	 * 
	 * @throws NullPointerException If the specified element is null
	 * @throws IllegalArgumentException if the list already contains e
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (size() == list.length) {
			growArray();
		}
		this.list[size] = e;
		this.size++;
		return true;
	}
	
	/**
	 * Gets the item from an index
	 * 
	 * @param idx the index of the item
	 * 
	 * @return the element
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return (E)(list[idx]);
	}
	
	/**
	 * Adds an item at position e
	 * 
	 * @param pos the position of the elemeent
	 * @param e the element
	 * 
	 * @return if the element was added
	 * 
	 * @throws NullPointerException If the specified element is null
	 * @throws IllegalArgumentException If the specified element is already in the list
	 * @throws IndexOutOfBoundsException If the index is out of range
	 */
	@Override
	public void add(int pos, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (pos < 0 || pos > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size() == list.length) {
			growArray();
		}
		for (int i = size(); i > pos; i--) {
			list[i] = list[i - 1];
		}
		list[pos] = e;
		this.size++;
	}
	
	/**
	 * Removes an item at index
	 * 
	 * @param index the index of the element to remove
	 * 
	 * @return if the element is removed
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E elem = (E)(list[index]);
		for (int i = index; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = null;
		this.size--;
		return elem;
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
		for (int i = 0; i < this.size; i++) {
			if (list[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * Resizes the array to create more storage
	 */
	@SuppressWarnings("unchecked")
	public void growArray() {
		int newSize = size + RESIZE;
		E[] newList = (E[])(new Object[newSize]);
		for (int i = 0; i < size; i++) {
			newList[i] = (E)(list[i]);
		}
		list = newList;
	}
}
