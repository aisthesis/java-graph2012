/**
 * 
 */
package com.codemelon.graph.util.search.depthfirst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSort {
	private DiGraph graph;
	private LinkedList<Vertex> sortedVertices;
	
	public TopologicalSort(DiGraph graph) {
		this.graph = graph;
		sort();
	}
	
	public LinkedList<Vertex> getSortedVertices() {
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
	public boolean showsAsInOrder(Vertex earlier, Vertex later) {
		ListIterator<Vertex> it = sortedVertices.listIterator();
		boolean earlierHasBeenFound = false;
		Vertex tmp;
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
		sortedVertices = new LinkedList<Vertex>();
		new VertexResetter(graph).resetColors();
		Iterator<Vertex> it = graph.vertexIterator();
		Vertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.color == Color.WHITE) {
				visit(u);
			}
		}
	}
	private void visit(Vertex u) {
		u.color = Color.GRAY;
		Set<Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				visit(v);
			}		
		}
		u.color = Color.BLACK;
		sortedVertices.addFirst(u);	
	}
}
