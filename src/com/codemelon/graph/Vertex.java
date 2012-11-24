/**
 * Vertex class containing a set of
 * adjacent vertices.
 */
package com.codemelon.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class Vertex {
	private Set<Vertex> adjacencies;
	public Vertex() {
		adjacencies = Collections.synchronizedSet(new HashSet<Vertex>());
	}
	public boolean addAdjacency(Vertex v) {
		return adjacencies.add(v);
	}
	public boolean addAllAdjacencies(Collection<Vertex> c) {
		return adjacencies.addAll(c);
	}
	public boolean removeAdjacency(Vertex v) {
		return adjacencies.remove(v);
	}
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.contains(v);
	}
	public int adjacencies() {
		return adjacencies.size();
	}
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
}
