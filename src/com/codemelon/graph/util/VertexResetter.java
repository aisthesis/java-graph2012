package com.codemelon.graph.util;

import java.util.Iterator;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.graph.Graph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.DistanceVertex;
import com.codemelon.graph.vertex.types.BfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 * Static methods for resetting the vertices of a graph.
 */
public class VertexResetter {
	public static void resetColors(DiGraph<ColoredVertex> graph, Color color) {
		Iterator<ColoredVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setColor(color);
		}
	}
	public static void resetColors(DiGraph<ColoredVertex> graph) {
		resetColors(graph, VertexConstants.INITIAL_COLOR);
	}
	public static void resetColors(Graph<BfsVertex> graph, Color color) {
		Iterator<BfsVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setColor(color);
		}
	}
	public static void resetColors(Graph<BfsVertex> graph) {
		resetColors(graph, VertexConstants.INITIAL_COLOR);
	}
	public static void resetParents(DiGraph<ChildVertex> graph) {
		Iterator<ChildVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setParent(null);
		}
	}
	public static void resetParents(Graph<BfsVertex> graph) {
		Iterator<BfsVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setParent(null);
		}
	}
	public static void resetDistances(DiGraph<DistanceVertex> graph, int distance) {
		Iterator<DistanceVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setDistance(distance);
		}
	}
	public static void resetDistances(DiGraph<DistanceVertex> graph) {
		resetDistances(graph, VertexConstants.INITIAL_DISTANCE);
	}
	public static void resetDistances(Graph<BfsVertex> graph, int distance) {
		Iterator<BfsVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().setDistance(distance);
		}
	}
	public static void resetDistances(Graph<BfsVertex> graph) {
		resetDistances(graph, VertexConstants.INITIAL_DISTANCE);
	}
	
	private OldDiGraph graph;
	
	public VertexResetter(OldDiGraph graph) {
		this.graph = graph;
	}
	/**
	 * Resets the properties needed for breadth-first search
	 * to their correct initial values
	 */
	public void bfsReset() {
		resetColors();
		resetParents();
		resetDistances();
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
