/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.util.Transposer;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 30, 2012
 *
 */
public class StronglyConnectedComponentsTest {
	private DiGraph graph;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.codemelon.graph.search.StronglyConnectedComponents#run()}.
	 */
	@Test
	public void testRun() {
		HashMap<Character, CompleteVertex> vertices = setUpCLRSGraph();
		Transposer transposer = new StronglyConnectedComponents(graph).run();
		// component abe
		assertEquals("a and b belong to same tree", vertices.get('a').treeNumber, 
				vertices.get('b').treeNumber);
		assertEquals("a and e belong to same tree", vertices.get('a').treeNumber, 
				vertices.get('e').treeNumber);
		// component cd
		assertEquals("c and d belong to same tree", vertices.get('c').treeNumber, 
				vertices.get('d').treeNumber);
		// component fg
		assertEquals("f and g belong to same tree", vertices.get('f').treeNumber, 
				vertices.get('g').treeNumber);
		// distinguish different components
		assertThat(vertices.get('a').treeNumber, is(not(vertices.get('c').treeNumber)));
		assertThat(vertices.get('a').treeNumber, is(not(vertices.get('f').treeNumber)));
		assertThat(vertices.get('a').treeNumber, is(not(vertices.get('h').treeNumber)));
		assertThat(vertices.get('c').treeNumber, is(not(vertices.get('f').treeNumber)));
		assertThat(vertices.get('c').treeNumber, is(not(vertices.get('h').treeNumber)));
		assertThat(vertices.get('f').treeNumber, is(not(vertices.get('h').treeNumber)));
		
		// transposer is correct
		DiGraph transposeGraph = transposer.getTransposeGraph();
		HashMap<CompleteVertex, CompleteVertex> mapToTransposeVertices = transposer.getVertexMap();
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('b')), 
				mapToTransposeVertices.get(vertices.get('a'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('e')), 
				mapToTransposeVertices.get(vertices.get('b'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('f')), 
				mapToTransposeVertices.get(vertices.get('b'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('c')), 
				mapToTransposeVertices.get(vertices.get('b'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('g')), 
				mapToTransposeVertices.get(vertices.get('c'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('d')), 
				mapToTransposeVertices.get(vertices.get('c'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('c')), 
				mapToTransposeVertices.get(vertices.get('d'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('h')), 
				mapToTransposeVertices.get(vertices.get('d'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('a')), 
				mapToTransposeVertices.get(vertices.get('e'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('f')), 
				mapToTransposeVertices.get(vertices.get('e'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('g')), 
				mapToTransposeVertices.get(vertices.get('f'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('f')), 
				mapToTransposeVertices.get(vertices.get('g'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('h')), 
				mapToTransposeVertices.get(vertices.get('g'))));
		assertTrue("Correct edges in transpose graph", transposeGraph
				.containsEdge(mapToTransposeVertices.get(vertices.get('h')), 
				mapToTransposeVertices.get(vertices.get('h'))));
	}
	/**
	 * Graph from CLRS, p. 616
	 */
	private HashMap<Character, CompleteVertex> setUpCLRSGraph() {
		HashMap<Character, CompleteVertex> vertices = new HashMap<Character, CompleteVertex>();
		for (char i = 'a'; i <= 'h'; i++) {
			vertices.put(i, new CompleteVertex(i));
		}
		graph = new DiGraph(vertices.values());
		graph.addEdge(vertices.get('a'), vertices.get('b'));
		graph.addEdge(vertices.get('b'), vertices.get('f'));
		graph.addEdge(vertices.get('b'), vertices.get('e'));
		graph.addEdge(vertices.get('b'), vertices.get('c'));
		graph.addEdge(vertices.get('c'), vertices.get('d'));
		graph.addEdge(vertices.get('c'), vertices.get('g'));
		graph.addEdge(vertices.get('d'), vertices.get('c'));
		graph.addEdge(vertices.get('d'), vertices.get('h'));
		graph.addEdge(vertices.get('e'), vertices.get('a'));
		graph.addEdge(vertices.get('e'), vertices.get('f'));
		graph.addEdge(vertices.get('f'), vertices.get('g'));
		graph.addEdge(vertices.get('g'), vertices.get('f'));
		graph.addEdge(vertices.get('g'), vertices.get('h'));
		graph.addEdge(vertices.get('h'), vertices.get('h'));
		return vertices;
	}
}
