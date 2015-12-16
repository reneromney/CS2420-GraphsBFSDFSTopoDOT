package assign8;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class GraphTester {

	@Test
	public void testNewGraph() {
		//Test creating a new graph
		Graph g = new Graph();
		assertTrue(g.getSize() == 0);
		assertFalse(g.getDirected());
	}
	
	@Test
	public void testAddEdges() {
		//Test adding new edges
		Graph g = new Graph();
		g.addEdge("1", "2");
		assertTrue(g.getSize() == 2);
		g.addEdge("0", "1");
		assertTrue(g.getSize() == 3);
	}
	
	@Test
	public void testDirection() {
		//Test that only the second vertex gets an inDegree since the graph is directed
		Graph directed = new Graph();
		directed.setDirected(true);
		directed.addEdge("1", "2");
		assertEquals(0, directed.getVertices().get("1").getInDegree());
		assertEquals(1, directed.getVertices().get("2").getInDegree());
	}
	
	@Test
	public void testUnDirected(){
		//Test that both vertexes have an indegree of one since the graph is undirected
		Graph unDirected = new Graph();
		unDirected.setDirected(false);
		unDirected.addEdge("1", "2");
		assertEquals(1, unDirected.getVertices().get("1").getInDegree());
		assertEquals(1, unDirected.getVertices().get("2").getInDegree());
	}
	
	@Test
	public void testContainsVertices(){
		//Test the containsVertices method.
		Graph g = new Graph();
		g.addEdge("1", "2");
		g.addEdge("2", "4");
		//Should contain 1, 2, and 4.
		assertTrue(g.containsVertices("1", "2"));
		assertTrue(g.containsVertices("1", "4"));
		//Should not contain zero
		assertFalse(g.containsVertices("0", "1"));
		//Should work if the same vertex is given
		assertTrue(g.containsVertices("1", "1"));
	}

}
