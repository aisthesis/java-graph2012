/**
 * 
 */
package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSort {
	private DiGraph graph;
	private LinkedList<CompleteVertex> sortedVertices;
	
	public TopologicalSort(DiGraph graph) {
		this.graph = graph;
		sort();
	}
	
	public LinkedList<CompleteVertex> getSortedVertices() {
		return sortedVertices;
	}
	/**
	 * Returns true if earlier precedes later in the topologically sorted list.
	 * Returns false for equal vertices and for all other cases where earlier
	 * does not precede later in the list.
	 * @param earlier vertex tested for being earlier
	 * @param later vertex tested for being later
	 * @return true if earlier precedes later
	 */
	public boolean showsAsInOrder(CompleteVertex earlier, CompleteVertex later) {
		ListIterator<CompleteVertex> it = sortedVertices.listIterator();
		boolean earlierHasBeenFound = false;
		CompleteVertex tmp;
		while (it.hasNext()) {
			tmp = it.next();
			if (tmp == later) {
				if (earlierHasBeenFound) { return true; }
				return false;
			}
			else if (tmp == earlier) {
				earlierHasBeenFound = true;
			}
		}
		return false;
	}

	/**
	 * CLRS, p. 613
	 */
	private void sort() {
		sortedVertices = new LinkedList<CompleteVertex>();
		new VertexResetter(graph).resetColors();
		Iterator<CompleteVertex> it = graph.vertexIterator();
		CompleteVertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.color == Color.WHITE) {
				visit(u);
			}
		}
	}
	private void visit(CompleteVertex u) {
		u.color = Color.GRAY;
		Set<CompleteVertex> adjacentVertices = u.getAdjacencies();
		for (CompleteVertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				visit(v);
			}		
		}
		u.color = Color.BLACK;
		sortedVertices.addFirst(u);	
	}
}
