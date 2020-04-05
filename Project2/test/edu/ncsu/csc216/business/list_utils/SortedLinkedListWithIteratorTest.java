/**
 * 
 */
package edu.ncsu.csc216.business.list_utils;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * The SortedLinkedListWithIteratorTest class checks the functionality 
 * of methods in the SortedLinkedListWithIterator class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class SortedLinkedListWithIteratorTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#size()}.
	 */
	@Test
	public void testSize() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContains() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("peach"));
		assertTrue(list.contains("peach"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAdd() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("grapefruit"));
		assertTrue(list.add("lemon"));
		assertTrue(list.add("lime"));
		assertEquals(3, list.size());
		
		try {
			assertTrue(list.add(null));
			fail();
		} catch (NullPointerException e) {
			assertEquals(3, list.size());
		}
		
		try {
			assertTrue(list.add("grapefruit"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#clear()}.
	 */
	@Test
	public void testClear() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("grapefruit"));
		assertTrue(list.add("lemon"));
		assertTrue(list.add("lime"));
		
		list.clear();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#get(int)}.
	 */
	@Test
	public void testGet() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("grapefruit"));
		assertTrue(list.add("lemon"));
		assertTrue(list.add("lime"));
		
		assertEquals("grapefruit", list.get(0));
		assertEquals("lemon", list.get(1));
		assertEquals("lime", list.get(2));
		
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("grapefruit", list.get(0));
			assertEquals("lemon", list.get(1));
			assertEquals("lime", list.get(2));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#remove(int)}.
	 */
	@Test
	public void testRemove() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("grapefruit"));
		assertTrue(list.add("lemon"));
		assertTrue(list.add("lime"));
		
		assertEquals("grapefruit", list.remove(0));
		assertEquals(2, list.size());
		assertEquals("lime", list.remove(1));
		assertEquals(1, list.size());
		assertEquals("lemon", list.remove(0));
		assertEquals(0, list.size());
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#truncate(int)}.
	 */
	@Test
	public void testTruncate() {
		//fail();
//		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
//		assertTrue(list.add("grapefruit"));
//		assertTrue(list.add("lemon"));
//		assertTrue(list.add("lime"));
//		assertTrue(list.add("orange"));
//		assertTrue(list.add("strawberry"));
//		assertTrue(list.add("blueberry"));
//		assertTrue(list.add("huckleberry"));
//		assertTrue(list.add("thimbleberry"));
//				
//		list.truncate(4);
//		assertEquals(4, list.size());
//		assertEquals("grapefruit", list.get(0));
//		assertEquals("lemon", list.get(1));
//		assertEquals("lime", list.get(2));
//		assertEquals("orange", list.get(3));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#indexOf(java.lang.Comparable)}.
	 */
	@Test
	public void testIndexOf() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("grapefruit"));
		assertTrue(list.add("lemon"));
		assertTrue(list.add("lime"));
		assertTrue(list.add("orange"));
		assertTrue(list.add("strawberry"));
		assertTrue(list.add("blueberry"));
		assertTrue(list.add("huckleberry"));
		assertTrue(list.add("thimbleberry"));
		
		assertEquals(1, list.indexOf("lemon"));
		assertEquals(3, list.indexOf("orange"));
		assertEquals(5, list.indexOf("blueberry"));
		assertEquals(7, list.indexOf("thimbleberry"));
		assertEquals(-1, list.indexOf("pear"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#toString()}.
	 */
	@Test
	public void testToString() {
		SortedLinkedListWithIterator<String> list1 = new SortedLinkedListWithIterator<String>();
		SortedLinkedListWithIterator<String> list2 = new SortedLinkedListWithIterator<String>();
		assertTrue(list2.add("strawberry"));
		assertTrue(list2.add("blueberry"));
		assertTrue(list2.add("huckleberry"));
		assertTrue(list2.add("thimbleberry"));
		
		String s1 = "[]";
		String s2 = "[strawberry, blueberry, huckleberry, thimbleberry]";
		assertEquals(s1, list1.toString());
		assertEquals(s2, list2.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator#iterator()}.
	 */
	@Test
	public void testIterator() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.add("strawberry"));
		assertTrue(list.add("blueberry"));
		assertTrue(list.add("huckleberry"));
		assertTrue(list.add("thimbleberry"));
		
		SimpleListIterator<String> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("strawberry", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("blueberry", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("huckleberry", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("thimbleberry", iterator.next());
		assertFalse(iterator.hasNext());
		
		try {
			iterator.next();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(4, list.size());
		}
	}

}
