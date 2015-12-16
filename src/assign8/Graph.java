package assign8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A class that constructs a graph by adding edges between vertices.
 * 
 * @author Jordan Hensley and Romney Doria jHensley, doria assignment 8 CS
 *         2420-Fall 2015 10/28/2015
 */
public class Graph {

	// Holds the vertices
	private Map<String, Vertex> graphMap;

	private boolean directed = false;

	/**
	 * Constructs a new, empty graph.
	 */
	public Graph() {
		graphMap = new HashMap<String, Vertex>();
	}

	/**
	 * Creates an edge between the two given vertices, if the vertices do not
	 * exits, ones will be created. If the graph is undirected both vertices
	 * will have an edge to each other, otherwise, only an edge from the first
	 * vertex to the second vertex will be created.
	 * 
	 * @param n1
	 * @param n2
	 */
	public void addEdge(String n1, String n2) {
		Vertex v1;

		// Check the existence of the vertex with name n1
		if (graphMap.containsKey(n1))
			v1 = graphMap.get(n1);
		else {
			// Create a new vertex
			v1 = new Vertex(n1);
			graphMap.put(n1, v1);
		}

		Vertex v2;

		if (graphMap.containsKey(n2))
			v2 = graphMap.get(n2);
		else {
			v2 = new Vertex(n2);
			graphMap.put(n2, v2);
		}
		// If directed than add an edge back to v1.
		if (directed == false)
			v2.addEdge(v1);
		v1.addEdge(v2);
	}

	/**
	 * @return an iterator of the vertexes in the graph
	 */
	public Iterator<Vertex> getList() {
		return graphMap.values().iterator();
	}

	/**
	 * @return the number of vertexes in the graph
	 */
	public int getSize() {
		return graphMap.size();
	}

	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return true if the graph contains the two vertices and false if
	 *         otherwise
	 */
	public boolean containsVertices(String s1, String s2) {
		return (graphMap.containsKey(s1) && graphMap.containsKey(s2));
	}

	/**
	 * Sets the directed variable to the boolean that is passed in
	 * 
	 * @param b
	 */
	public void setDirected(boolean b) {
		directed = b;
	}

	/**
	 * @return true if the graph is directed and false if otherwise
	 */
	public boolean getDirected() {
		return directed;
	}

	/**
	 * @return the map containing all of the vertices
	 */
	public Map<String, Vertex> getVertices() {
		return this.graphMap;
	}
}
