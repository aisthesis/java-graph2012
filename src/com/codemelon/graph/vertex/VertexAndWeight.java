package com.codemelon.graph.vertex;

/**
 * @author Marshall Farrier
 * @version Dec 4, 2012
 * 
 * Object allowing us to associate a vertex with a weight
 */
public class VertexAndWeight {
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
}
