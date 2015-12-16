package assign8;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class VertexClassTester {

	@Test
	public void testNewVertex() {
		Vertex v = new Vertex("V1");
		// Should contain its name, no in degree, it hasn't been visited,
		// contains no edges, and has no previous vertex.
		assertEquals("V1", v.getName());
		assertEquals(0, v.getInDegree());
		assertFalse(v.getVisited());
		List<Edge> list = v.getEdges();
		assertTrue(list.size() == 0);
		assertNull(v.getPrevVertex());
	}

	@Test
	public void testInDegree() {
		// Test the functionality of increasing and decreasing the inDegree
		// variables
		Vertex v = new Vertex("1");
		assertEquals(0, v.getInDegree());
		v.increaseInDegree();
		assertEquals(1, v.getInDegree());
		v.decreaseInDegree();
		assertEquals(0, v.getInDegree());
	}
	
	@Test
	public void testAddEdge() {
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		
		//V1 should have an inDegree of 0 and v2's should be 1
		v1.addEdge(v2);
		assertEquals(0, v1.getInDegree());
		assertEquals(1, v2.getInDegree());
		
		//v1 adjacency list should be of length 1
		List<Edge> l1 = v1.getEdges();
		assertTrue(l1.size() == 1);
		
		//also tests functionality of the Edge class's getOtherVertex method
		assertTrue(l1.get(0).getOtherVertex() == v2);
		
		//v2's adjacency list should be empty, because there are no edges coming from it.
		List<Edge> l2 = v2.getEdges();
		assertTrue(l2.size() == 0);
	}
	
	@Test
	public void setPreviousVertex() {
		//Test functionality of setPreviousVertex method
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		v1.setPrevVertex(v2);
		assertTrue(v2 == v1.getPrevVertex());
		assertFalse(v1 == v2.getPrevVertex());
		
		assertNull(v2.getPrevVertex());
	}

}
