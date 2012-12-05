package com.codemelon.graph.vertex;

/**
 * @author Marshall Farrier
 * @version Dec 4, 2012
 * 
 * Object associating a vertex with a weight
 */
public class VertexAndWeight implements Comparable<VertexAndWeight> {
	private Vertex v;
	private double weight;
	public VertexAndWeight(Vertex v, double weight) {
		this.v = v;
		this.weight = weight;
	}
	public Vertex vertex() {
		return v;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(VertexAndWeight weightedVertex) {
		if (this.weight < weightedVertex.weight) { return -1; }
		if (this.weight > weightedVertex.weight) { return 1; }
		return 0;
	}
}
