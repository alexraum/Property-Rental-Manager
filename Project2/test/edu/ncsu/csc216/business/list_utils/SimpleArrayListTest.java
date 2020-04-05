/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The SimpleArrayListTest class checks the functionality of methods in the
 * SimpleArrayListClass.
 * 
 * @author Alex Raum, Walker Clem
 */
public class SimpleArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#size()}.
	 */
	@Test
	public void testSize() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		list.add("peach");
		assertTrue(list.contains("peach"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertTrue(list.add("papaya"));
		assertTrue(list.add("kiwi"));
		assertTrue(list.add("mango"));
		
		try {
			list.add("kiwi");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#get(int)}.
	 */
	@Test
	public void testGet() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertTrue(list.add("papaya"));
		assertTrue(list.add("kiwi"));
		assertTrue(list.add("mango"));
		assertEquals("papaya", list.get(0));
		
		try {
			list.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("papaya", list.get(0));
			assertEquals("kiwi", list.get(1));
			assertEquals("mango", list.get(2));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		list.add(0, "apple");
		list.add(0, "pear");
		list.add(1, "orange");
		assertEquals("pear", list.get(0));
		assertEquals("orange", list.get(1));		
		assertEquals("apple", list.get(2));
		
		try {
			list.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("pear", list.get(0));
			assertEquals("orange", list.get(1));		
			assertEquals("apple", list.get(2));
		}
		
		try {
			list.add(2, "pear");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("pear", list.get(0));
			assertEquals("orange", list.get(1));		
			assertEquals("apple", list.get(2));
		}
		
		try {
			list.add(5, "peach");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("pear", list.get(0));
			assertEquals("orange", list.get(1));		
			assertEquals("apple", list.get(2));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertTrue(list.add("blueberry"));
		assertTrue(list.add("blackberry"));
		assertTrue(list.add("raspberry"));
		assertEquals("blueberry", list.remove(0));
		assertEquals("blackberry", list.remove(0)); 
		assertEquals("raspberry", list.remove(0));
		list.add("strawberry");
		
		try {
			assertEquals("strawberry", list.remove(1));
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("strawberry", list.get(0));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SimpleArrayList#indexOf(java.lang.Object)}.
	 */
	@Test
	public void testIndexOf() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertEquals(-1, list.indexOf("watermelon"));
		list.add("plum");
		list.add("nectarine");
		list.add("apricot");
		assertEquals(2, list.indexOf("apricot"));
	}

}
