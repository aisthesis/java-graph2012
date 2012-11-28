/**
 * Since there is no guarantee that an edge must map to 
 * a unique EdgeData, it is advantageous for this class to
 * be immutable.
 */
package com.codemelon.graph.common;


/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class EdgeData {
	private Color color;
	private EdgeType edgeType;
	public EdgeData() { this(Color.WHITE, EdgeType.UNKNOWN); }
	public EdgeData(Color c) { this(c, EdgeType.UNKNOWN); }
	public EdgeData(EdgeType et) { this(Color.WHITE, et); }
	public EdgeData(Color c, EdgeType et) {
		color = c;
		edgeType = et;
	}
	public Color getColor() { return color; }
	public EdgeType getEdgeType() { return edgeType; }
	public void setColor(Color color) { this.color = color; }
	public void setEdgeType(EdgeType edgeType) { this.edgeType = edgeType; }
}
