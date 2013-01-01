package com.codemelon.graph.vertex.types;

import java.util.IdentityHashMap;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.EdgeTypeVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.VisitedVertex;

/**
 * @author Marshall Farrier
 * @version Dec 7, 2012
 */
public class DfsVertex implements Vertex, ColoredVertex, ChildVertex,
		VisitedVertex, EdgeTypeVertex {
	private DiGraph<? extends Vertex> graph;
	private IdentityHashMap<Vertex, EdgeType> adjacencies;
	private ChildVertex parent;
	private Color color;
	private int discoveryTime;
	private int finishTime;
	
	public DfsVertex() {
		graph = null;
		adjacencies = new IdentityHashMap<Vertex, EdgeType>();
		parent = null;
		color = Color.WHITE;
		discoveryTime = VertexConstants.INITIAL_DISCOVERY_TIME;
		finishTime = VertexConstants.INITIAL_FINISH_TIME;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#setEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex, com.codemelon.graph.common.EdgeType)
	 */
	@Override
	public void setEdgeType(EdgeTypeVertex to, EdgeType edgeType) {
		if (!adjacencies.containsKey(to)) {
			throw new IllegalArgumentException("Edge does not exist!");
		}
		adjacencies.put(to, edgeType);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#getEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex)
	 */
	@Override
	public EdgeType getEdgeType(EdgeTypeVertex to) {
		return adjacencies.get(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setDiscoveryTime(int)
	 */
	@Override
	public void setDiscoveryTime(int discoveryTime) {
		this.discoveryTime = discoveryTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setFinishTime(int)
	 */
	@Override
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getDiscoveryTime()
	 */
	@Override
	public int getDiscoveryTime() {
		return discoveryTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getFinishTime()
	 */
	@Override
	public int getFinishTime() {
		return finishTime;
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
		if (parent == null) {
			this.parent = parent;
			return;
		}
		if (graph == null) {
			throw new IllegalArgumentException("Vertex must belong to a graph to have a parent!");
		}
		if (parent.getGraph() != graph) {
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
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#setGraph(com.codemelon.graph.graph.DiGraph)
	 */
	@Override
	public void setGraph(DiGraph<? extends Vertex> diGraph) {
		this.graph = diGraph;
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
	/** 
	 * @throws IllegalArgumentException if the calling vertex does not belong to a graph
	 * @throws IllegalArgumentException if the adjacency to be set does not belong to the same graph
	 */
	@Override
	public boolean addAdjacency(Vertex to) {
		if (this.graph == null || this.graph != to.getGraph()) {
			throw new IllegalArgumentException("Adjacency must belong to the same graph!");
		}
		if (adjacencies.containsKey(to)) { return false; }
		adjacencies.put(to, EdgeConstants.DEFAULT_EDGE_TYPE);
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
	public Set<Vertex> getAdjacencies() {
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