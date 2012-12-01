/**
 * 
 */
package com.codemelon.graph.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marshall Farrier
 * @version Dec 1, 2012
 *
 */
public class DisjointSetTest {
	private HashSet<Character> items;
	private DisjointSet<Character> disjointSet;

	/**
	 * set up a collection of characters a - h
	 * Cf. <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, p. 565
	 * (adding the character 'a' to the example)
	 */
	@Before
	public void setUp() {
		items = new HashSet<Character>();
		for (char i = 'a'; i <= 'h'; i++) {
			items.add(i);
		}
		disjointSet = new DisjointSet<Character>(items);
	}

	/**
	 * destroy the collection
	 */
	@After
	public void tearDown() {
		items = null;
		disjointSet = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.DisjointSet#findSet(java.lang.Object)}.
	 */
	@Test
	public void testFindSet() {
		// items are initially in different sets
		for (char i = 'a'; i <= 'g'; i++) {
			for (char j = (char)(i + 1); j <= 'h'; j++) {
				assertThat(disjointSet.findSet(i), is(not(disjointSet.findSet(j))));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.DisjointSet#union(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testUnion() {
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('f', 'g'));
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('f', 'd'));
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('c', 'h'));
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('h', 'e'));
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('c', 'b'));
		assertFalse("Union returns false when sets are same", disjointSet.union('d', 'g'));
		// 'a' belongs to a separate disjoint set
		for (char i = 'b'; i <= 'h'; i++) {
			assertThat(disjointSet.findSet('a'), is(not(disjointSet.findSet(i))));
		}
		assertEquals(disjointSet.findSet('f'), disjointSet.findSet('g'));
		assertEquals(disjointSet.findSet('c'), disjointSet.findSet('e'));
		assertEquals(disjointSet.findSet('b'), disjointSet.findSet('h'));
		assertEquals(disjointSet.findSet('e'), disjointSet.findSet('b'));
		assertThat(disjointSet.findSet('f'), is(not(disjointSet.findSet('c'))));
		// now join the 2 sets
		assertTrue("Union returns true when sets are disjoint", disjointSet.union('d', 'e'));
		assertEquals(disjointSet.findSet('f'), disjointSet.findSet('c'));
	}
}