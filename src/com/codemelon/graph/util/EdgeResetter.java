package com.codemelon.graph.util;

import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Dec 3, 2012
 * Utility class for resetting the edges of a graph.
 * The constructor takes the graph to reset as argument.
 */
public class EdgeResetter {
	private OldDiGraph graph;
	public EdgeResetter(OldDiGraph graph) {
		this.graph = graph;
	}
	public void resetColors(Color color) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		Set<CompleteVertex> adj;
		CompleteVertex from;
		while (it.hasNext()) {
			from = it.next();
			adj = from.getAdjacencies();
			for (CompleteVertex to : adj) {
				from.setEdgeColor(to, color);
			}
		}
	}
	public void resetColors() {
		resetColors(EdgeConstants.DEFAULT_COLOR);
	}
	public void resetEdgeTypes(EdgeType edgeType) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		Set<CompleteVertex> adj;
		CompleteVertex from;
		while (it.hasNext()) {
			from = it.next();
			adj = from.getAdjacencies();
			for (CompleteVertex to : adj) {
				from.setEdgeType(to, edgeType);
			}
		}
	}
	public void resetEdgeTypes() {
		resetEdgeTypes(EdgeConstants.DEFAULT_EDGE_TYPE);
	}
	public void resetWeights(double weight) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		Set<CompleteVertex> adj;
		CompleteVertex from;
		while (it.hasNext()) {
			from = it.next();
			adj = from.getAdjacencies();
			for (CompleteVertex to : adj) {
				from.setEdgeWeight(to, weight);
			}
		}
	}
	public void resetWeights() {
		resetWeights(EdgeConstants.DEFAULT_WEIGHT);
	}
}
