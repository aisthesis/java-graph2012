/**
 * Vertex class containing a map of other vertices to edge data.
 * 
 */
package com.codemelon.graph.vertex;
import java.util.concurrent.ConcurrentHashMap;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.Data;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class Vertex {
	private ConcurrentHashMap<Vertex, Data> adjacencies;
	public Color color;
	
	public Vertex() {
		adjacencies = new ConcurrentHashMap<Vertex, Data>();
		color = Color.WHITE;
	}
	public boolean addAdjacency(Vertex v) {
		if (adjacencies.containsKey(v)) { return false; }
		adjacencies.put(v,  new Data());
		return true;
	}
	public boolean removeAdjacency(Vertex v) {
		if (!adjacencies.containsKey(v)) { return false; }
		adjacencies.remove(v);
		return true;
	}
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.containsKey(v);
	}
	public int adjacencies() {
		return adjacencies.size();
	}
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
}
