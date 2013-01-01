package com.codemelon.graph.vertex.types;

import java.util.IdentityHashMap;
import java.util.Set;

import com.codemelon.graph.edge.interfaces.EdgeDataFactory;
import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * In contrast to SimpleVertex, an EdgeDataVertex supports edge data, represented by
 * the generic type T.
 * @author Marshall Farrier
 * @my.created Dec 12, 2012
 * @my.edited Dec 12, 2012
 */
public class EdgeDataVertex<T, U extends EdgeDataFactory<T>> implements Vertex {
	private DiGraph<? extends Vertex> graph;
	private U edgeDataFactory;
	private IdentityHashMap<Vertex, T> adjacencies;
	
	public EdgeDataVertex(U edgeDataFactory) {
		this.edgeDataFactory = edgeDataFactory;
		adjacencies = new IdentityHashMap<Vertex, T>();
		graph = null;
	}
	
	/**
	 * Get data for a particular edge.
	 * @param to head of the edge for which to retrieve data
	 * @return the data for the given edge
	 */
	public T getEdgeData(Vertex to) {
		return adjacencies.get(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#setGraph(com.codemelon.graph.graph.types.DiGraph)
	 */
	@Override
	public void setGraph(DiGraph<? extends Vertex> graph) {
		this.graph = graph;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#getGraph()
	 */
	@Override
	public DiGraph<? extends Vertex> getGraph() {
		return graph;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#addAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public <V extends Vertex> boolean addAdjacency(V to) {
		if (this.graph == null || this.graph != to.getGraph()) {
			throw new IllegalArgumentException("Adjacency must belong to the same graph!");
		}
		if (adjacencies.containsKey(to)) { return false; }
		adjacencies.put(to, edgeDataFactory.newEdgeData());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#removeAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean removeAdjacency(Vertex to) {
		if (adjacencies.containsKey(to)) {
			adjacencies.remove(to);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#clearAdjacencies()
	 */
	@Override
	public void clearAdjacencies() {
		adjacencies.clear();
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#containsAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean containsAdjacency(Vertex to) {
		return adjacencies.containsKey(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#adjacencyCount()
	 */
	@Override
	public int adjacencyCount() {
		return adjacencies.size();
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#getAdjacencies()
	 */
	@Override
	public Set<? extends Vertex> getAdjacencies() {
		return adjacencies.keySet();
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#hasAdjacencies()
	 */
	@Override
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
}