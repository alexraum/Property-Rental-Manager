/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

/**
 * @author Alex Raum
 *
 */
public class SimpleArrayList<E> implements SimpleList<E> {

	/** */
	private static final int RESIZE = 0; // need to double check this value with TA
	/** */
	private Object[] list;
	/** */
	private int size;
	
	/*
	 * 
	 */
	public SimpleArrayList() {
		
	}
	
	/**
	 * 
	 */
	public SimpleArrayList(int capacity) {

	}
	
	/** 
	 * 
	 */
	@Override
	public int size() {
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean contains(E e) {
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean add(E e) {
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public E get(int idx) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void add(int pos, E e) {
		
	}
	
	/**
	 * 
	 */
	@Override
	public E remove(int index) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public int indexOf(E e) {
		return 0;
	}
	
}
