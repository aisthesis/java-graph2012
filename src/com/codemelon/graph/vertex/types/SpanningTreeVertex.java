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
public class SpanningTreeVertex<T extends EdgeColorData & EdgeWeightData, U extends EdgeDataFactory<T>> 
		extends EdgeDataVertex<T, U> implements WeightedEdgeVertex, ColoredEdgeVertex {
	
	public SpanningTreeVertex(U edgeDataFactory) {
		super(edgeDataFactory);
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
