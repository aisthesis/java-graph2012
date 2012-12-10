package com.codemelon.graph.vertex.util;

import java.util.Iterator;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.graph.types.Graph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.DistanceVertex;
import com.codemelon.graph.vertex.interfaces.VisitedVertex;
import com.codemelon.graph.vertex.types.BfsVertex;
import com.codemelon.graph.vertex.types.DfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 * Static methods for resetting the vertices of a graph.
 */
public class VertexResetter {
	public static void resetColors(DiGraph<? extends ColoredVertex> graph, Color color) {
		Iterator<? extends ColoredVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setColor(color);
		}
	}
	public static void resetColors(DiGraph<? extends ColoredVertex> graph) {
		resetColors(graph, Color.WHITE);
	}
	public static void resetParents(DiGraph<? extends ChildVertex> graph) {
		Iterator<? extends ChildVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setParent(null);
		}
	}
	public static void resetDistances(DiGraph<? extends DistanceVertex> graph, int distance) {
		Iterator<? extends DistanceVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setDistance(distance);
		}
	}
	public static void resetDistances(DiGraph<? extends DistanceVertex> graph) {
		resetDistances(graph, VertexConstants.INITIAL_DISTANCE);
	}
	public static void resetDiscoveryTimes(DiGraph<? extends VisitedVertex> graph, int discoveryTime) {
		Iterator<? extends VisitedVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setDiscoveryTime(discoveryTime);
		}		
	}
	public static void resetDiscoveryTimes(DiGraph<? extends VisitedVertex> graph) {
		resetDiscoveryTimes(graph, VertexConstants.INITIAL_DISCOVERY_TIME);
	}
	public static void resetFinishTimes(DiGraph<? extends VisitedVertex> graph, int finishTime) {
		Iterator<? extends VisitedVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setFinishTime(finishTime);
		}		
	}
	public static void resetFinishTimes(DiGraph<? extends VisitedVertex> graph) {
		resetFinishTimes(graph, VertexConstants.INITIAL_FINISH_TIME);
	}
	/**
	 * Resets the properties needed for breadth-first search
	 * to their correct initial values
	 */
	public static void resetForBfs(Graph<? extends BfsVertex> graph) {
		resetColors(graph);
		resetParents(graph);
		resetDistances(graph);
	}
	public static void resetForDfs(DiGraph<? extends DfsVertex> graph) {
		resetColors(graph);
		resetParents(graph);
		resetDiscoveryTimes(graph);
		resetFinishTimes(graph);
	}
	
	public static <T extends ColoredVertex> void blah(DiGraph<T> graph) {
		
	}
	private OldDiGraph graph;
	
	public VertexResetter(OldDiGraph graph) {
		this.graph = graph;
	}
	
	public void dfsReset() {
		resetColors();
		resetParents();
		resetDiscoveryTimes();
		resetFinishTimes();
	}
	
	public void primReset() {
		resetColors();
		resetParents();
		resetWeights(Double.MAX_VALUE);
	}

	/**
	 * Sets all vertices in the graph to the given color
	 * @param c color to which all vertices will be set
	 */
	public void resetColorsOld(Color c) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().color = c;
		}
	}
	public void resetColors() {
		resetColorsOld(VertexConstants.INITIAL_COLOR);
	}
	public void resetParents() {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().parent = null;
		}
	}
	public void resetDistances(int distance) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().distance = distance;
		}
	}
	public void resetDistances() {
		resetDistances(VertexConstants.INITIAL_DISTANCE);
	}
	public void resetDiscoveryTimes(int discoveryTime) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().discoveryTime = discoveryTime;
		}
	}
	public void resetDiscoveryTimes() {
		resetDiscoveryTimes(VertexConstants.INITIAL_DISCOVERY_TIME);
	}
	public void resetFinishTimes(int finishTime) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().finishTime = finishTime;
		}
	}
	public void resetFinishTimes() {
		resetFinishTimes(VertexConstants.INITIAL_FINISH_TIME);
	}
	public void resetTreeNumbers(int treeNumber) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().treeNumber = treeNumber;
		}
	}
	public void resetTreeNumbers() {
		resetTreeNumbers(VertexConstants.INITIAL_TREE_NUMBER);
	}
	public void resetWeights(double weight) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().weight = weight;
		}		
	}
	public void resetWeights() {
		resetWeights(VertexConstants.INITIAL_WEIGHT);
	}
}
