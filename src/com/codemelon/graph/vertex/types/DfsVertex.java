package com.codemelon.graph.vertex.types;

import java.util.IdentityHashMap;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.interfaces.EdgeTypeData;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.EdgeTypeVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.VisitedVertex;

/**
 * @author Marshall Farrier
 * @version Dec 7, 2012
 */
public class DfsVertex<T extends EdgeTypeData> implements Vertex, ColoredVertex, ChildVertex,
		VisitedVertex, EdgeTypeVertex {
	private DiGraph<DfsVertex<T>> graph;
	private IdentityHashMap<DfsVertex<T>, T> adjacencies;
	private DfsVertex<T> parent;
	private Color color;
	private int discoveryTime;
	private int finishTime;
	
	public DfsVertex() {
		graph = null;
		adjacencies = new IdentityHashMap<DfsVertex<T>, T>();
		parent = null;
		color = VertexConstants.INITIAL_COLOR;
		discoveryTime = VertexConstants.INITIAL_DISCOVERY_TIME;
		finishTime = VertexConstants.INITIAL_FINISH_TIME;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#setEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex, com.codemelon.graph.common.EdgeType)
	 */
	@Override
	public void setEdgeType(EdgeTypeVertex to, EdgeType edgeType) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#getEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex)
	 */
	@Override
	public EdgeType getEdgeType(EdgeTypeVertex to) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setDiscoveryTime(int)
	 */
	@Override
	public void setDiscoveryTime(int discoveryTime) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setFinishTime(int)
	 */
	@Override
	public void setFinishTime(int finishTime) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getDiscoveryTime()
	 */
	@Override
	public int getDiscoveryTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getFinishTime()
	 */
	@Override
	public int getFinishTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#setParent(com.codemelon.graph.vertex.interfaces.ChildVertex)
	 */
	@Override
	public void setParent(ChildVertex parent) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#getParent()
	 */
	@Override
	public ChildVertex getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#setColor(com.codemelon.graph.common.Color)
	 */
	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#getColor()
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#setGraph(com.codemelon.graph.graph.DiGraph)
	 */
	@Override
	public void setGraph(DiGraph<Vertex> diGraph) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#getGraph()
	 */
	@Override
	public DiGraph<Vertex> getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#addAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean addAdjacency(Vertex to) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#removeAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean removeAdjacency(Vertex to) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#clearAdjacencies()
	 */
	@Override
	public void clearAdjacencies() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#containsAdjacency(com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean containsAdjacency(Vertex to) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#adjacencyCount()
	 */
	@Override
	public int adjacencyCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#getAdjacencies()
	 */
	@Override
	public Set<Vertex> getAdjacencies() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.Vertex#hasAdjacencies()
	 */
	@Override
	public boolean hasAdjacencies() {
		// TODO Auto-generated method stub
		return false;
	}

}
