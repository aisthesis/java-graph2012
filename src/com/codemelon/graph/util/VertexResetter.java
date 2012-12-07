package com.codemelon.graph.util;

import java.util.Iterator;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.VertexConstants;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 * Utility class for resetting the vertices of a graph.
 * The constructor takes the graph to reset as argument.
 */
public class VertexResetter {
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
	public void resetColors(Color c) {
		Iterator<CompleteVertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().color = c;
		}
	}
	public void resetColors() {
		resetColors(VertexConstants.INITIAL_COLOR);
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
