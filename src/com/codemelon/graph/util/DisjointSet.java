package com.codemelon.graph.util;

import java.util.Collection;
import java.util.HashMap;

/**
 * Implementation of disjoint set following 
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 561ff.
 * Disjoint sets are needed for Kruskal's minimum spanning tree algorithm.
 * @author Marshall Farrier
 * @version Dec 1, 2012
 */
public class DisjointSet<T> {
	/*
	 * A node contains a pointer to a set and to a next node
	 */
	private class Node {
		T item;
		Node next;
		SetData setData;
		Node(T item) {
			this.item = item;
			next = null;
			setData = new SetData(this);
		}
	}
	/*
	 * 1 such object for each set. As union operations occur,
	 * 1 set is abandoned for garbage collection while the other
	 * set is updated to reflect the union.
	 */
	private class SetData {
		Node head;
		Node tail;
		int size;
		SetData(Node node) {
			head = node;
			tail = node;
			size = 1;
		}
	}
	private HashMap<T, Node> nodes;

	/**
	 * This performs the make-set operation described in 
	 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a> on all items
	 * in the collection
	 * @param items
	 */
	public DisjointSet(Collection<T> items) {
		nodes = new HashMap<T, Node>(items.size());
		for (T item : items) {
			nodes.put(item, new Node(item));
		}
	}
	
	public T findSet(T item) {
		return nodes.get(item).setData.head.item;
	}
	
	/**
	 * Perform a union on the sets to which x and y belong.
	 * After this method is called, x and y will belong to the same
	 * disjoint set.
	 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, p. 562
	 * @param x member of a disjoint set (unknown whether or not the same set as y)
	 * @param y member of a disjoint set (unknown whether or not the same set as x)
	 * @return true iff the sets to which x and y belong were distinct
	 * prior to the call
	 */
	public boolean union(T x, T y) {
		Node bigSetHead = nodes.get(x).setData.head;
		Node smallSetHead = nodes.get(x).setData.head;
		Node tmp;
		if (bigSetHead == smallSetHead) { return false; }
		// swap if necessary so that bigSetHead really points to the larger set
		if (bigSetHead.setData.size < smallSetHead.setData.size) {
			tmp = bigSetHead;
			bigSetHead = smallSetHead;
			smallSetHead = tmp;
		}
		// update the size of the new set
		bigSetHead.setData.size += smallSetHead.setData.size;
		// update the next pointer for tail of big set
		bigSetHead.setData.tail.next = smallSetHead;
		// update the tail pointer for the big set
		bigSetHead.setData.tail = smallSetHead.setData.tail;
		// point all nodes in the small set to the big set setData
		tmp = smallSetHead;
		while (tmp != null) {
			tmp.setData = bigSetHead.setData;
			tmp = tmp.next;
		}
		return true;
	}
}