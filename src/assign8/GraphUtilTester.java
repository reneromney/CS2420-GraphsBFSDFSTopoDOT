package assign8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author Jordan Hensley and Romney Doria jHensley, doria assignment 8 CS
 *         2420-Fall 2015 10/28/2015
 */
public class GraphUtilTester {

	@Test(expected = UnsupportedOperationException.class)
	public void testTopoFail() {
		// This graph should fail because it contains loops
		GraphUtil.topologicalSort("TestGraphs/TopoTest1.dot");
	}

	@Test
	public void testTopoSort() {
		// This tests a topological sort on a graph that has five nodes all
		// pointing to one node (n0)
		List<String> testList = GraphUtil.topologicalSort("TestGraphs/TopoTest2.dot");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("n1", "n2", "n3", "n4", "n5", "n0"));
		assertTrue(testList.size() == 6);
		// Assure the last item is "nO"
		assertTrue(testList.get(5).equals("n0"));
		// Assure all items are there
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testLargerTopoSort() {
		// Tests a topological sort on a larger directed acyclic graph
		List<String> testList = GraphUtil.topologicalSort("TestGraphs/TopoTest3.dot");
		assertTrue(testList.size() == 12);
		// The way the code should topologically sort
		List<String> expected = Arrays.asList("MATH 2250", "CS 1410", "CS 2420", "CS 2100", "CS 3200*", "CS 3500",
				"CS 3810", "CS 3100*", "CS 4150", "CS 3505", "CS 4400", "CS 4500");
		assertEquals(expected, testList);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTopoFail2() {
		// Tests the expected exception when calling a topological sort on an
		// undirected graph.
		GraphUtil.topologicalSort("TestGraphs/TopoTest4.dot");
	}

	@Test
	public void testSmallTopoSort() {
		// Tests a topological sort on a minimal graph with just two vertexes
		List<String> testList = GraphUtil.topologicalSort("TestGraphs/TopoTest5.dot");
		List<String> expected = new ArrayList<String>();
		expected.add("a");
		expected.add("b");
		assertEquals(expected, testList);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTopoSortFail3() {
		// This test just has one node that points to itself in a self-loop.
		GraphUtil.topologicalSort("TestGraphs/TopoTest6.dot");
	}

	@Test
	public void testTopoOnEmpty() {
		// Tests the topologicalSort on an empty graph.
		List<String> testEmpty = GraphUtil.topologicalSort("TestGraphs/TopoTest7.dot");
		List<String> expected = new LinkedList<String>();
		assertEquals(expected, testEmpty);
	}

	@Test
	public void testTopoSort4() {
		// Tests the functionality of a topological sort on a directed, acyclic
		// graph
		List<String> test = GraphUtil.topologicalSort("TestGraphs/TopoTest8.dot");
		// a starts with an inDegree of zero
		assertEquals("a", test.get(0));
		// d starts with the greatest inDegree
		assertEquals("d", test.get(3));
		assertEquals("b", test.get(1));
		assertEquals("c", test.get(2));
		assertTrue(test.get(1) != test.get(2));
		assertEquals(4, test.size());
	}

	@Test
	public void testBFS() {

		// examplegraph: bfs path from vertex 1 to vertex 4, only vertex 1 and 4
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph.dot", "vertex 1", "vertex 4");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("vertex 1", "vertex 4"));
		assertTrue(testList.size() == 2);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS2() {
		// examplegraph2 path from A to C, bfs for A, D, C
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph2.dot", "A", "C");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("A", "D", "C"));
		assertTrue(testList.size() == 3);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS3() {
		// examplegraph3 path from n3 to n0, only 1 vertex to travel
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph3.dot", "n3", "n0");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("n3", "n0"));

		assertTrue(testList.size() == 2);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS4() {
		// examplegraph4 path from u0 to u4
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph4.dot", "u0", "u4");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("u0", "u2", "u3", "u4"));

		assertTrue(testList.size() == 4);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS5() {
		// examplegraph5 path from 1 to 5
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph5.dot", "1", "5");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("1", "2", "3", "4", "5"));

		assertTrue(testList.size() == 5);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS6() {
		// examplegraph6 path from 1 to 5
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph6.dot", "1", "5");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("1", "2", "7", "5"));

		assertTrue(testList.size() == 4);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS7() {
		// examplegraph7 path from CS1410 to CS4500
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph7.dot", "CS 1410", "CS 4500");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("CS 1410", "CS 2420", "CS 3500", "CS 3505", "CS 4500"));

		assertTrue(testList.size() == 5);
		assertTrue(testList.containsAll(expected));
	}

	@Test
	public void testBFS8() {
		// examplegraph8 path from San Diego to Washington DC
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph8.dot", "San Diego",
				"Washington DC");
		ArrayList<String> expected = new ArrayList<String>();
		expected.addAll(Arrays.asList("San Diego", "Salt Lake City", "Dallas", "Washington DC"));

		assertTrue(testList.size() == 4);
		assertTrue(testList.containsAll(expected));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testBFSFail1() {
		// Should throw an exception for a undirected graph
		List<String> testList = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph9.dot", "1", "7");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testBFSFail2() {
		// Should throw an exception, because there is no vertex with the name
		// Utah
		List<String> test = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph8.dot", "San Diego", "Utah");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testBFSFail3() {
		// Should throw an exception, because Ogden is not the name of a vertex
		List<String> test = GraphUtil.breadthFirstSearch("TestGraphs/examplegraph8.dot", "Ogden", "San Diego");
	}
}
