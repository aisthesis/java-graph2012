package com.codemelon.graph.vertex;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.edge.EdgeData;
import com.codemelon.graph.common.EdgeType;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 * 
 * Vertex class containing a map of other vertices to edge data.
 * The setter methods of this class should be used with caution, particularly when they
 * belong to undirected graphs (Graph objects), as they can cause the graph
 * to be in an inconsistent state, such as the edge weight for (u, v) being different
 * from that for (v, u) in an undirected graph. To avoid such problems, it is
 * always a good idea to use the corresponding methods from the graph object to which
 * the vertex (or edge) belongs.
 */
public class Vertex {
	private IdentityHashMap<Vertex, EdgeData> adjacencies;
	private DiGraph graph;
	
	public int label;
	public int searchOrder;	// allows arbitrary prioritization for searches
	public Color color;
	public Vertex parent;
	public int distance;	// for BFS, etc.
	// for DFS
	public int discoveryTime;
	public int finishTime;
	public int treeNumber;	// to identify components after DFS
	public double weight;
	
	public Vertex() {
		this(VertexConstants.INITIAL_COLOR);
	}
	public Vertex(int label) {
		this(label, VertexConstants.DEFAULT_SEARCH_ORDER_VALUE,
				VertexConstants.INITIAL_COLOR, null, VertexConstants.INITIAL_DISTANCE, 
				VertexConstants.INITIAL_DISCOVERY_TIME, 
				VertexConstants.INITIAL_FINISH_TIME,
				VertexConstants.INITIAL_TREE_NUMBER,
				VertexConstants.INITIAL_WEIGHT);
	}
	public Vertex(Color color) {
		this(VertexConstants.DEFAULT_LABEL, VertexConstants.DEFAULT_SEARCH_ORDER_VALUE,
				color, null, VertexConstants.INITIAL_DISTANCE, 
				VertexConstants.INITIAL_DISCOVERY_TIME, 
				VertexConstants.INITIAL_FINISH_TIME,
				VertexConstants.INITIAL_TREE_NUMBER,
				VertexConstants.INITIAL_WEIGHT);
	}
	public Vertex(int label, int searchOrder, Color color, Vertex parent, int distance, int discoveryTime,
			int finishTime, int treeNumber, double weight) {
		adjacencies = new IdentityHashMap<Vertex, EdgeData>();
		graph = null;
		this.label = label;
		this.searchOrder = searchOrder;
		this.color = color;
		this.parent = parent;
		this.distance = distance;
		this.discoveryTime = discoveryTime;
		this.finishTime = finishTime;
		this.treeNumber = treeNumber;
		this.weight = weight;
	}
	/**
	 * Copy constructor. Copies all satellite data but leaves adjacency list
	 * and parent of new vertex empty or, in the case of parent, null.
	 * @param v vertex to be copied
	 */
	public Vertex(Vertex v) {
		this(v, null);
	}
	/**
	 * Copies all satellite data from vertexToCopy and assigns vertexParent
	 * as parent. Adjacency list is empty on initialization
	 * @param vertexToCopy vertex from which to copy satellite data
	 * @param vertexParent parent to assign to new vertex
	 */
	public Vertex(Vertex vertexToCopy, Vertex vertexParent) {
		this(vertexToCopy.label, vertexToCopy.searchOrder, vertexToCopy.color, vertexParent, 
				vertexToCopy.distance, vertexToCopy.discoveryTime, vertexToCopy.finishTime, 
				vertexToCopy.treeNumber, vertexToCopy.weight);		
	}
	/**
	 * Set the graph to which the vertex belongs
	 * @param g graph with which the vertex is to be associated
	 */
	public void setGraph(DiGraph g) {
		graph = g;
	}
	/**
	 * Returns the graph to which the vertex belongs.
	 * @return the graph to which the vertex belongs
	 */
	public DiGraph getGraph() { return graph; }
	
	/**
	 * Adds the specified vertex to the set of adjacencies
	 * @param v vertex to be added
	 * @return true if the adjacency list did not already contain v
	 */
	public boolean addAdjacency(Vertex v) {
		if (this.graph != v.graph) {
			throw new IllegalArgumentException("Vertex belongs to a different graph!");
		}
		if (adjacencies.containsKey(v)) { return false; }
		adjacencies.put(v, new EdgeData());
		return true;
	}
	
	/**
	 * Removes the specified vertex from the adjacency set
	 * @param v vertex to be removed
	 * @return true if the vertex was found in the adjacency set
	 */
	public boolean removeAdjacency(Vertex v) {
		if (!adjacencies.containsKey(v)) { return false; }
		adjacencies.remove(v);
		return true;
	}
	
	/**
	 * Removes all vertices from the adjacency set. The adjacency set will be
	 * empty after this call returns.
	 */
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	 
	/**
	 * Returns true if the adjacency set contains the specified vertex
	 * @param v vertex whose presence is to be tested
	 * @return true if v is found in the adjacency set
	 */
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.containsKey(v);
	}
	
	public boolean containsAdjacency(int label) {
		Set<Vertex> adjacentVertices = adjacencies.keySet();
		Iterator<Vertex> it = adjacentVertices.iterator();
		while (it.hasNext()) {
			if (it.next().label == label) { return true; }
		}
		return false;
	}
	
	/**
	 * Returns the number of vertices in the adjacency set
	 * @return the number of vertices in this adjacency set (its cardinality)
	 */
	public int adjacencyCount() {
		return adjacencies.size();
	}
	
	public Set<Vertex> getAdjacencies() {
		return adjacencies.keySet();
	}

	/**
	 * Returns true if the adjacency is non-empty.
	 * @return true if the adjacency is non-empty.
	 */
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
	public void setEdgeColor(Vertex to, Color color) {
		adjacencies.get(to).setColor(color);
	}
	public Color getEdgeColor(Vertex to) {
		return adjacencies.get(to).getColor();
	}
	public void setEdgeType(Vertex to, EdgeType edgeType) {
		adjacencies.get(to).setType(edgeType);
	}
	public EdgeType getEdgeType(Vertex to) {
		return adjacencies.get(to).getType();
	}
	public void setEdgeWeight(Vertex to, double weight) {
		adjacencies.get(to).setWeight(weight);
	}
	public double getEdgeWeight(Vertex to) {
		return adjacencies.get(to).getWeight();
	}
	/**
	 * Don't allow subclasses to override the equals method
	 */
	@Override
	public final boolean equals(Object o) {
		return (this == o);
	}
}