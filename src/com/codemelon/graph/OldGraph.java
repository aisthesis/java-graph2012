/**
 * Undirected graph with no self edges.
 */
package com.codemelon.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.edge.types.WeightedEdge;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class OldGraph extends OldDiGraph{
	public OldGraph() { super(); }
	public OldGraph(int initialCapacity) { super(initialCapacity); }
	public OldGraph(Collection<CompleteVertex> initialVertices) { super(initialVertices); }
	/**
	 * Inserts the given Edge if not already present unless
	 * from and to are the same.
	 * @param from one end of the edge to be inserted
	 * @param to the other end of the edge to be inserted
	 * @return true if the edge can be inserted
	 */
	@Override
	public boolean addEdge(CompleteVertex from, CompleteVertex to) {
		if (from == to) { return false; }
		if (!super.addEdge(from, to)) { return false; }
		return super.addEdge(to, from);
	}
	@Override
	public boolean removeEdge(CompleteVertex from, CompleteVertex to) {
		if (!super.removeEdge(from, to)) { return false; }
		return super.removeEdge(to, from);
	}
	@Override
	public int edgeCount() { return super.edgeCount() / 2; }
	@Override
	public void setEdgeColor(CompleteVertex from, CompleteVertex to, Color color) {
		super.setEdgeColor(from, to, color);
		super.setEdgeColor(to, from, color);
	}
	@Override
	public void setEdgeType(CompleteVertex from, CompleteVertex to, EdgeType edgeType) {
		super.setEdgeType(from, to, edgeType);
		super.setEdgeType(to, from, edgeType);
	}
	@Override
	public void setEdgeWeight(CompleteVertex from, CompleteVertex to, double weight) {
		super.setEdgeWeight(from, to, weight);
		super.setEdgeWeight(to, from, weight);
	}
	public Set<WeightedEdge> getWeightedEdges() {
		HashSet<WeightedEdge> result = new HashSet<WeightedEdge>(edgeCount());
		Iterator<CompleteVertex> vertexIterator = this.vertexIterator();
		CompleteVertex from;
		Set<CompleteVertex> adjacencySet;
		while (vertexIterator.hasNext()) {
			from = vertexIterator.next();
			adjacencySet = from.getAdjacencies();
			for (CompleteVertex to : adjacencySet) {
				//result.add(new WeightedEdge(from, to));
			}
		}
		return result;
	}
}
