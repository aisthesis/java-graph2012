package com.codemelon.graph.edge;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;

/**
 * @author Marshall Farrier
 * @version Dec 2, 2012
 * Constants used by edges
 */
public class EdgeConstants {
	/**
	 * Default value for the epsilon below which edge weights are to be considered
	 * equal.
	 */
	public static final double DEFAULT_WEIGHT_EPSILON = 0.000001;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final EdgeType DEFAULT_EDGE_TYPE = EdgeType.UNKNOWN;
	public static final double DEFAULT_WEIGHT = 1.0;
	private EdgeConstants() {}
}
