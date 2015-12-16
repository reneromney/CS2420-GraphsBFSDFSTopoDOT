package assign8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Utility class containing methods for operating on graphs.
 *
 * @author Erin Parker, Jordan Hensley, and Romney Doria jHensley, doria
 *         assignment 8 CS 2420-Fall 2015 10/28/2015
 */
public class GraphUtil {

	public static void main(String[] args) {
		GraphUtil.generateRandomDotFile("test.dot", 20, 3, true);
	}

	// Holds the graph that gets created
	private static Graph ourGraph = new Graph();

	// Holds the vertexes that are going to be added to the list in the
	// topological sort. This acts as a queue.
	private static LinkedList<Vertex> toBeAdded = new LinkedList<Vertex>();

	/**
	 * Performs a topological sort of the vertices in a directed acyclic graph.
	 * (See Lecture 14 for the algorithm.)
	 * 
	 * Throws an UnsupportedOperationException if the graph is undirected or
	 * cyclic.
	 * 
	 * @param filename
	 *            -- Name of a file in DOT format, which specifies the graph to
	 *            be sorted.
	 * @return a list of the vertex names in sorted order
	 */
	public static List<String> topologicalSort(String filename) {

		// Instantiate the final sorted list and the toBeAdded queue
		LinkedList<String> sortedList = new LinkedList<String>();
		toBeAdded = new LinkedList<Vertex>();

		// Generate the graph from the dot file with the name that was passed in
		Graph g = buildGraphFromDot(filename);

		// return an empty list if the graph is empty
		if (g.getSize() == 0)
			return sortedList;

		// Graph may not be directed
		if (!g.getDirected())
			throw new UnsupportedOperationException();

		Iterator<Vertex> vertexList = g.getList();
		// Iterate through the list and find vertexes with inDegree == 0
		while (vertexList.hasNext()) {
			Vertex temp = vertexList.next();
			// If the inDegree is zero enqueue the vertex
			if (temp.getInDegree() == 0) {
				toBeAdded.addFirst(temp);
			}
		}

		// If nothing has been enqueued, a loop exists.
		if (toBeAdded.isEmpty())
			throw new UnsupportedOperationException();

		while (!toBeAdded.isEmpty()) {
			// When a vertex is dequeued, add it to the sorted list and decrease
			// the indegree of all the vertices it has an edge to
			Vertex temp = toBeAdded.removeLast();
			sortedList.addLast(temp.getName());
			decreaseNextVertices(temp);
			// Checks for loops that have been found
			if (toBeAdded.size() == 0 && sortedList.size() != g.getSize())
				throw new UnsupportedOperationException();
		}
		return sortedList;
	}

	/**
	 * Performs a breadth-first search of a graph to determine the shortest path
	 * from a starting vertex to an ending vertex. (See Lecture 14 for the
	 * algorithm.)
	 * 
	 * Throws an UnsupportedOperationException if the graph is undirected or if
	 * the starting or ending vertex does not exist in the graph.
	 * 
	 * @param filename
	 *            -- Name of a file in DOT format, which specifies the graph to
	 *            be sorted.
	 * @param start
	 *            -- Name of the starting vertex in the path.
	 * @param end
	 *            -- Name of the ending vertex in the path.
	 * @return a list of the vertices that make up the shortest path from the
	 *         starting vertex (inclusive) to the ending vertex (inclusive).
	 */
	public static List<String> breadthFirstSearch(String filename, String start, String end) {

		ourGraph = buildGraphFromDot(filename);

		// Throw an exception if the graph doesn't contain either vertex or if
		// the graph is undirected
		if (!ourGraph.containsVertices(start, end) || !ourGraph.getDirected())
			throw new UnsupportedOperationException();

		// Q <- a new queue of vertices to visit
		Queue<Vertex> queue = new LinkedList<Vertex>();

		Vertex startVertex = ourGraph.getVertices().get(start);

		// start vertex should be added and marked as visited
		queue.add(startVertex);
		startVertex.setDistance(0);
		startVertex.isVisited(true);

		while (!queue.isEmpty()) {

			// Remove the current element from the queue
			Vertex nextVertex = queue.poll();

			for (Edge e : nextVertex.getEdges()) {
				Vertex temp = e.getOtherVertex();

				// If this vertex has an edge to a vertex that hasn't been
				// visited, mark it as visited and give it a distance from start
				if (!temp.getVisited()) {
					temp.isVisited(true);
					temp.setDistance(nextVertex.getDistance() + 1);
					temp.setPrevVertex(nextVertex);
					queue.add(temp); // Add to the queue
				}
			}
		}

		Vertex endVertex = ourGraph.getVertices().get(end);

		List<String> results = new ArrayList<String>();
		Stack<Vertex> stack = new Stack<Vertex>();

		// Traverse backwards from the end vertex and push it on a stack
		while (endVertex != null) {
			stack.push(endVertex);
			endVertex = endVertex.getPrevVertex();
		}
		// Now pop off everything in the stack and add to results
		while (!stack.isEmpty()) {
			results.add(stack.pop().getName());
		}
		return results;
	}

	/**
	 * Builds a graph according to the edges specified in the given DOT file
	 * (e.g., "a -- b" or "a -> b"). Accepts directed ("digraph") or undirected
	 * ("graph") graphs.
	 * 
	 * Accepts many valid DOT files (see examples posted with assignment).
	 * --accepts \\-style comments --accepts one edge per line or edges
	 * terminated with ; --does not accept attributes in [] (e.g., [label =
	 * "a label"])
	 * 
	 * @param filename
	 *            -- name of the DOT file
	 */
	public static Graph buildGraphFromDot(String filename) {
		// creates a new, empty graph (CHANGE AS NEEDED)
		Graph g = new Graph();

		Scanner s = null;
		try {
			s = new Scanner(new File(filename)).useDelimiter(";|\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}

		// Determine if graph is directed or not (i.e., look for "digraph id {"
		// or
		// "graph id {")
		String line = "", edgeOp = "";
		while (s.hasNext()) {
			line = s.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				g.setDirected(true); // Denotes that graph is directed (CHANGE
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
			if (line.indexOf("graph") >= 0) {
				g.setDirected(false); // Denotes that graph is undirected
				edgeOp = "--";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}

		// Look for edge operators -- (or ->) and determine the left and right
		// vertices for each edge.
		while (s.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				// add edge between vertex1 and vertex2
				g.addEdge(vertex1, vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = s.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}
		return g;
	}

	/**
	 * Called in the topological sort, this method decreases the inDegree of all
	 * the vertices a vertex has an edge to.
	 * 
	 * @param v
	 */
	private static void decreaseNextVertices(Vertex v) {
		for (Edge e : v.getEdges()) {
			e.getOtherVertex().decreaseInDegree();
			// The vertex should be enqueued if its inDegree is now zero
			if (e.getOtherVertex().getInDegree() == 0)
				toBeAdded.addFirst(e.getOtherVertex());
		}
	}

	/**
	 * This method creates a random dot file with the given filename, number of
	 * vertexes, edge density, and direction.
	 * 
	 * @param filename
	 * @param vertexCount
	 */
	public static void generateRandomDotFile(String filename, int vertexCount, int graphDensity, boolean isDirected) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (IOException e) {
			System.out.println(e);
		}

		Random rng = new Random();

		// randomly construct either a graph or a digraph
		String edgeOp = "--";
		if (isDirected) {
			out.print("di");
			edgeOp = "->";
		}
		out.println("graph G {");

		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;

		// Randomize the array
		for (int i = 0; i < vertexCount; i++) {
			int random = rng.nextInt(vertexCount);
			String temp = vertex[i];
			vertex[i] = vertex[random];
			vertex[random] = temp;
		}

		// randomly connect the vertices using graphDensity * |V| edges
		for (int i = 0; i < vertexCount - graphDensity; i++)
			for (int j = 1; j <= graphDensity; j++)
				out.println("\t" + vertex[i] + edgeOp + vertex[i + j]);

		out.println("}");
		out.close();
	}
}
