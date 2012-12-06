package com.codemelon.graph.util;

import java.util.Iterator;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.Vertex;
import com.codemelon.graph.vertex.VertexConstants;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 * Utility class for resetting the vertices of a graph.
 * The constructor takes the graph to reset as argument.
 */
public class VertexResetter {
	private DiGraph graph;
	
	public VertexResetter(DiGraph graph) {
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
	}

	/**
	 * Sets all vertices in the graph to the given color
	 * @param c color to which all vertices will be set
	 */
	public void resetColors(Color c) {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().color = c;
		}
	}
	public void resetColors() {
		resetColors(VertexConstants.INITIAL_COLOR);
	}
	public void resetParents() {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().parent = null;
		}
	}
	public void resetDistances(int distance) {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().distance = distance;
		}
	}
	public void resetDistances() {
		resetDistances(VertexConstants.INITIAL_DISTANCE);
	}
	public void resetDiscoveryTimes(int discoveryTime) {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().discoveryTime = discoveryTime;
		}
	}
	public void resetDiscoveryTimes() {
		resetDiscoveryTimes(VertexConstants.INITIAL_DISCOVERY_TIME);
	}
	public void resetFinishTimes(int finishTime) {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().finishTime = finishTime;
		}
	}
	public void resetFinishTimes() {
		resetFinishTimes(VertexConstants.INITIAL_FINISH_TIME);
	}
	public void resetTreeNumbers(int treeNumber) {
		Iterator<Vertex> it = graph.vertexIterator();
		while (it.hasNext()) {
			it.next().treeNumber = treeNumber;
		}
	}
	public void resetTreeNumbers() {
		resetTreeNumbers(VertexConstants.INITIAL_TREE_NUMBER);
	}
}
