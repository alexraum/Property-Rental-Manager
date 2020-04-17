/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

/**
 * Implements the SimpleList interface with an array data structure.
 * 
 * @param <E> the object in the list
 * 
 * @author Alex Raum, Walker Clem
 */
public class SimpleArrayList<E> implements SimpleList<E> {

	/** The initial size of the array that the list is built on */
	private static final int RESIZE = 12;
	/** An object of arrays used to store data in the list */
	private Object[] list;
	/** The size of the list */
	private int size;
	
	/**
	 * Constructor for the ArrayList
	 */
	public SimpleArrayList() {
		this(RESIZE);
	}
	
	/**
	 * Constructor with a capacity set
	 * 
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if the capacity
	 *         is less than or equal to 0
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
	 * Returns the size of the list
	 * 
	 * @return the size of the list
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
	 * @return if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns whether the list contains element e
	 * 
	 * @param e the element to check for
	 * @return whether the list contains the element
	 */
	@Override
	public boolean contains(E e) { 
		return indexOf(e) != -1;
	}
	
	/**
	 * Adds an element to the list
	 * 
	 * @param e the element to add
	 * @return if the element is successfully added
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
	 * Gets the element at a specified index
	 * 
	 * @param idx the index of the element
	 * @return the element at the specified index
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
	 * Adds an element at a specified position
	 * 
	 * @param pos the position of the element
	 * @param e the element to be added
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
	 * Removes an element at an index
	 * 
	 * @param index the index of the element to be removed
	 * @return if the element is successfully removed
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
	 * @param e the element to be located
	 * @return the index of the specified element
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
