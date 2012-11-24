/**
 * Vertex class containing a set of
 * adjacent vertices.
 */
package com.codemelon.graph.vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class SimpleVertex implements Vertex {
	private Set<Vertex> adjacencies;
	public SimpleVertex() {
		adjacencies = Collections.synchronizedSet(new HashSet<Vertex>());
	}
	@Override
	public boolean addAdjacency(Vertex v) {
		return adjacencies.add(v);
	}
	@Override
	public boolean addAllAdjacencies(Collection<Vertex> c) {
		return adjacencies.addAll(c);
	}
	@Override
	public boolean removeAdjacency(Vertex v) {
		return adjacencies.remove(v);
	}
	@Override
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	@Override
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.contains(v);
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
