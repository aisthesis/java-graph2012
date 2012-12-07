package com.codemelon.graph.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Dec 3, 2012
 */
public class EdgeResetterTest {
	private static final int VERTICES_IN_TEST_GRAPH = 100;
	private static final int ODD_VERTEX_TAIL_INDEX = 35;
	private static final int ODD_VERTEX_HEAD_INDEX = 79;
	private static final Color OFF_COLOR = Color.GRAY;
	private static final Color NEW_COLOR = Color.BLACK;
	private static final EdgeType OFF_TYPE = EdgeType.BACK;
	private static final EdgeType NEW_TYPE = EdgeType.TREE;
	private static final double OFF_WEIGHT = 2.71828;
	private static final double NEW_WEIGHT = 3.14159;
	private OldDiGraph graph;
	private ArrayList<CompleteVertex> vertices;
	private EdgeResetter edgeResetter;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		vertices = new ArrayList<CompleteVertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new CompleteVertex(i));
		}
		graph = new OldDiGraph(vertices);// connect all vertices
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				graph.addEdge(vertices.get(i), vertices.get(j));
			}
		}
		edgeResetter = new EdgeResetter(graph);
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
		graph = null;
		vertices = null;
		edgeResetter = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetColors(com.codemelon.graph.common.Color)}.
	 */
	@Test
	public void testResetColorsColor() {
		// set 1 edge to a different color
		graph.setEdgeColor(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_COLOR);
		edgeResetter.resetColors(NEW_COLOR);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertEquals("Correct color", NEW_COLOR, graph.getEdgeColor(vertices.get(i), 
						vertices.get(j)));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetColors()}.
	 */
	@Test
	public void testResetColors() {
		// set 1 edge to a different color
		graph.setEdgeColor(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_COLOR);
		edgeResetter.resetColors();
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertEquals("Correct default color", EdgeConstants.DEFAULT_COLOR, graph.getEdgeColor(vertices.get(i), 
						vertices.get(j)));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetEdgeTypes(com.codemelon.graph.common.EdgeType)}.
	 */
	@Test
	public void testResetEdgeTypesEdgeType() {
		// set 1 edge to a different type
		graph.setEdgeType(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_TYPE);
		edgeResetter.resetEdgeTypes(NEW_TYPE);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertEquals("Correct type", NEW_TYPE, graph.getEdgeType(vertices.get(i), 
						vertices.get(j)));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetEdgeTypes()}.
	 */
	@Test
	public void testResetEdgeTypes() {
		// set 1 edge to a different type
		graph.setEdgeType(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_TYPE);
		edgeResetter.resetEdgeTypes();
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertEquals("Correct default type", EdgeConstants.DEFAULT_EDGE_TYPE, 
						graph.getEdgeType(vertices.get(i), vertices.get(j)));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetWeights(double)}.
	 */
	@Test
	public void testResetWeightsDouble() {
		// set 1 edge to a different type
		graph.setEdgeWeight(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_WEIGHT);
		edgeResetter.resetWeights(NEW_WEIGHT);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertTrue("Correct weight", graph.areEqualWeights(NEW_WEIGHT, graph.getEdgeWeight(vertices.get(i), 
						vertices.get(j))));
			}
		}
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.EdgeResetter#resetWeights()}.
	 */
	@Test
	public void testResetWeights() {
		// set 1 edge to a different type
		graph.setEdgeWeight(vertices.get(ODD_VERTEX_TAIL_INDEX), vertices.get(ODD_VERTEX_HEAD_INDEX),
				OFF_WEIGHT);
		edgeResetter.resetWeights();
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				assertTrue("Correct default weight", 
						graph.areEqualWeights(EdgeConstants.DEFAULT_WEIGHT, graph.getEdgeWeight(vertices.get(i), 
						vertices.get(j))));
			}
		}
	}
}
