package com.codemelon.graph.vertex.types;

import java.util.HashSet;
import java.util.Set;

import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.DistanceVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * Graph with components required for breadth-first search
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public class BfsVertex implements Vertex, ColoredVertex, DistanceVertex,
		ChildVertex {
	private DiGraph<Vertex> graph;
	private Set<Vertex> adjacencies;
	private ChildVertex parent;
	private int distance;
	private Color color;
	
	/**
	 * Create a vertex belonging to no graph (graph is null), with no adjacencies,
	 * no parent (parent is null) and default values for color and distance.
	 */
	public BfsVertex() {
		graph = null;
		adjacencies = new HashSet<Vertex>();
		parent = null;
		distance = VertexConstants.INITIAL_DISTANCE;
		color = VertexConstants.INITIAL_COLOR;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#setParent(com.codemelon.graph.vertex.interfaces.ChildVertex)
	 */
	/** 
	 * @throws IllegalArgumentException if the calling vertex does not belong to a graph
	 * @throws IllegalArgumentException if the parent to be set does not belong to the same graph
	 */
	@Override
	public void setParent(ChildVertex parent) {
		if (graph == null || parent.getGraph() != graph) {
			throw new IllegalArgumentException("Parent must belong to the same graph!");
		}
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#getParent()
	 */
	@Override
	public ChildVertex getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.DistanceVertex#setDistance(int)
	 */
	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.DistanceVertex#getDistance()
	 */
	@Override
	public int getDistance() {
		return distance;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#setColor(com.codemelon.graph.common.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#setGraph(com.codemelon.graph.DiGraph)
	 */
	@Override
	public void setGraph(DiGraph<Vertex> graph) {
		this.graph = graph;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#getGraph()
	 */
	@Override
	public DiGraph<Vertex> getGraph() {
		return graph;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#addAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	/** 
	 * @throws IllegalArgumentException if the calling vertex does not belong to a graph
	 * @throws IllegalArgumentException if the adjacency to be set does not belong to the same graph
	 */
	@Override
	public boolean addAdjacency(Vertex to) {
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
	public Set<Vertex> getAdjacencies() {
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
