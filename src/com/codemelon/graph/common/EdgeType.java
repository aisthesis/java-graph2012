/**
 * 
 */
package com.codemelon.graph.common;

/**
 * Enumeration of different edge types, as are determined in depth-first search
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public enum EdgeType {
	UNKNOWN, 
	TREE, 
	BACK, 
	FORWARD, 
	CROSS;
}
