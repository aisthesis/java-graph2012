package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.util.VertexResetter;

/**
 * Assuming that there is no cycle in the graph, this algorithm creates
 * a linked list representing an ordering that respects the edges in the graph.
 * That is, for any 2 distinct vertices u and v, if the graph contains the edge
 * (u, v), then u < v in the given ordering.
 * If the graph contains a cycle, then no such ordering exists.
 * Cf. <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 612ff. 
 * @author Marshall Farrier
 * @version Dec 8, 2012
 */
public class TopologicalSort {
	private DiGraph<? extends ColoredVertex> graph;
	private LinkedList<ColoredVertex> orderedVertices;
	
	/**
	 * This constructor modifies the colors of the vertices in the graph.
	 * @param graph graph whose vertices are to be topologically sorted
	 */
	public TopologicalSort(DiGraph<? extends ColoredVertex> graph) {
		this.graph = graph;
		sort();
	}
	
	public LinkedList<ColoredVertex> getSortedVertices() {
		return orderedVertices;
	}
	/**
	 * Returns true if earlier precedes later in the topologically sorted list.
	 * Returns false for equal vertices and for all other cases where earlier
	 * does not precede later in the list.
	 * @param earlier vertex tested for being earlier
	 * @param later vertex tested for being later
	 * @return true if earlier precedes later
	 */
	public boolean showsAsInOrder(ColoredVertex earlier, ColoredVertex later) {
		ListIterator<ColoredVertex> it = orderedVertices.listIterator();
		boolean earlierHasBeenFound = false;
		ColoredVertex tmp;
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
	 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, p. 613
	 */
	private void sort() {
		orderedVertices = new LinkedList<ColoredVertex>();
		VertexResetter.resetColors(graph);
		Iterator<? extends ColoredVertex> it = graph.vertexIterator();
		ColoredVertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.getColor() == Color.WHITE) {
				visit(u);
			}
		}
	}
	private void visit(ColoredVertex u) {
		u.setColor(Color.GRAY);
		Set<? extends Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			if (((ColoredVertex) v).getColor() == Color.WHITE) {
				visit((ColoredVertex) v);
			}
		}
		u.setColor(Color.BLACK);
		orderedVertices.addFirst(u);
	}
}
