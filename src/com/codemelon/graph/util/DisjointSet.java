package com.codemelon.graph.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Implementation of disjoint set following 
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 561ff.
 * Disjoint sets are needed for Kruskal's algorithm for finding a minimum
 * spanning tree.
 * @author Marshall Farrier
 * @version Dec 1, 2012
 */
public class DisjointSet<T> {
	private class LinkedItem {
		T next;
		T head;
		T tail;
		int size;
		LinkedItem(T item) {
			next = null;
			head = item;
			tail = item;
			size = 1;
		}
	}
	private HashMap<T, LinkedItem> linkedItems;
	/**
	 * This performs the make-set operation of CLRS on all items
	 * in the collection
	 * @param items
	 */
	public DisjointSet(Collection<T> items) {
		linkedItems = new HashMap<T, LinkedItem>(items.size());
		for (T item : items) {
			linkedItems.put(item, new LinkedItem(item));
		}
	}
	
	public T findSet(T item) {
		return linkedItems.get(item).head;
	}
	
	/**
	 * CLRS, p. 562
	 * @param x
	 * @param y
	 */
	public void union(T x, T y) {
		
	}
}
