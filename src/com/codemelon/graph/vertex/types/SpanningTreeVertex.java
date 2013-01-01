package com.codemelon.graph.vertex.types;

import java.util.IdentityHashMap;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.edge.interfaces.EdgeColorData;
import com.codemelon.graph.edge.interfaces.EdgeDataFactory;
import com.codemelon.graph.edge.interfaces.EdgeWeightData;
import com.codemelon.graph.edge.types.SpanningTreeEdgeData;
import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex;
import com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex;

/**
 * Vertex supporting the features used in spanning tree algorithms.
 * The type parameter T is a container for edge data. T must support
 * color and weight.
 * 
 * Note: We really should factor out EdgeDataVertex<T, U extends EdgeDataFactory<T>>
 * which could then be superclass to DfsVertex as well as this class. This kind
 * of vertex would implement adjacencies as a HashMap whereas SimpleVertex (no parameter)
 * could be extended for BFS or other operations requiring no edge data.
 * BfsVertex should actually be changed to an interface so that BFS can be run
 * either on SimpleVertex or on vertices supporting edge data.
 * @author Marshall Farrier
 * @my.created Dec 11, 2012
 * @my.edited Dec 11, 2012
 */
public class SpanningTreeVertex<T extends SpanningTreeEdgeData, U extends EdgeDataFactory<T>> 
		implements WeightedEdgeVertex, ColoredEdgeVertex {
	private DiGraph<? extends Vertex> graph;
	private U edgeDataFactory;
	private IdentityHashMap<Vertex, T> adjacencies;
	
	public SpanningTreeVertex(U edgeDataFactory) {
		this.edgeDataFactory = edgeDataFactory;
		adjacencies = new IdentityHashMap<Vertex, T>();
		graph = null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#setGraph(com.codemelon.graph.graph.types.DiGraph)
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

	@Override
	public void setEdgeColor(ColoredEdgeVertex to, Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getEdgeColor(ColoredEdgeVertex to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(WeightedEdgeVertex to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEdgeWeight(WeightedEdgeVertex to, double weight) {
		// TODO Auto-generated method stub
		
	}

}
