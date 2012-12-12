package com.codemelon.graph.vertex.types;

import java.util.HashSet;
import java.util.Set;

import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * Basic vertex functionality when no edge data is required.
 * @author Marshall Farrier
 * @my.created Dec 12, 2012
 * @my.edited Dec 12, 2012
 */
public class SimpleVertex implements Vertex {
	private DiGraph<? extends Vertex> graph;
	private Set<Vertex> adjacencies;
	
	/**
	 * Create a vertex belonging to no graph (graph is null) and no adjacencies.
	 */
	public SimpleVertex() {
		graph = null;
		adjacencies = new HashSet<Vertex>();
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
	public <T extends Vertex> boolean addAdjacency(T to) {
		if (this.graph == null || this.graph != to.getGraph()) {
			throw new IllegalArgumentException("Adjacency must belong to the same graph!");
		}
		if (adjacencies.contains(to)) { return false; }
		adjacencies.add(to);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#removeAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean removeAdjacency(Vertex to) {
		return adjacencies.remove(to);
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
		return adjacencies.contains(to);
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
		return adjacencies;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#hasAdjacencies()
	 */
	@Override
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
	/**
	 * Don't allow subclasses to override the equals method
	 * @return true iff o points to exactly the same objects
	 */
	@Override
	public final boolean equals(Object o) {
		return (this == o);
	}
}