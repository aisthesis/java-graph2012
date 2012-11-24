/**
 * Vertex class containing a map of other vertices to edge data.
 * 
 */
package com.codemelon.graph.vertex;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class SimpleVertex implements Vertex {
	private ConcurrentHashMap<Vertex, Data> adjacencies;
	public SimpleVertex() {
		adjacencies = new ConcurrentHashMap<Vertex, Data>();
	}
	@Override
	public boolean addAdjacency(Vertex v) {
		if (adjacencies.containsKey(v)) { return false; }
		adjacencies.put(v,  new Data());
		return true;
	}
	@Override
	public boolean removeAdjacency(Vertex v) {
		if (!adjacencies.containsKey(v)) { return false; }
		adjacencies.remove(v);
		return true;
	}
	@Override
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	@Override
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.containsKey(v);
	}
	@Override
	public int adjacencies() {
		return adjacencies.size();
	}
	@Override
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
}
