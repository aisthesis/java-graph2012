/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.util.Transposer;
import com.codemelon.graph.vertex.types.OrderedDfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 30, 2012
 *
 */
public class StronglyConnectedComponentsTest {
	private DiGraph<OrderedDfsVertex> graph;

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
		HashMap<Character, OrderedDfsVertex> vertices = setUpCLRSGraph();
		Transposer<OrderedDfsVertex, OrderedDfsVertex> transposer = 
				new StronglyConnectedComponents<OrderedDfsVertex, 
				OrderedDfsVertex>(graph, OrderedDfsVertex.Factory.INSTANCE).run();
		// component abe
		assertEquals("a and b belong to same tree", vertices.get('a').getComponent(), 
				vertices.get('b').getComponent());
		assertEquals("a and e belong to same tree", vertices.get('a').getComponent(), 
				vertices.get('e').getComponent());
		// component cd
		assertEquals("c and d belong to same tree", vertices.get('c').getComponent(), 
				vertices.get('d').getComponent());
		// component fg
		assertEquals("f and g belong to same tree", vertices.get('f').getComponent(), 
				vertices.get('g').getComponent());
		// distinguish different components
		assertThat(vertices.get('a').getComponent(), is(not(vertices.get('c').getComponent())));
		assertThat(vertices.get('a').getComponent(), is(not(vertices.get('f').getComponent())));
		assertThat(vertices.get('a').getComponent(), is(not(vertices.get('h').getComponent())));
		assertThat(vertices.get('c').getComponent(), is(not(vertices.get('f').getComponent())));
		assertThat(vertices.get('c').getComponent(), is(not(vertices.get('h').getComponent())));
		assertThat(vertices.get('f').getComponent(), is(not(vertices.get('h').getComponent())));
		
		// transposer is correct
		DiGraph<OrderedDfsVertex> transposeGraph = transposer.getTransposeGraph();
		Map<OrderedDfsVertex, OrderedDfsVertex> mapToTransposeVertices = transposer.getVertexMap();
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
	private HashMap<Character, OrderedDfsVertex> setUpCLRSGraph() {
		HashMap<Character, OrderedDfsVertex> vertices = new HashMap<Character, OrderedDfsVertex>();
		for (char i = 'a'; i <= 'h'; i++) {
			vertices.put(i, new OrderedDfsVertex());
		}
		graph = new DiGraph<OrderedDfsVertex>(vertices.values());
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
