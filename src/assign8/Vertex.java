package assign8;

import java.util.LinkedList;

/**
 * @author Jordan Hensley and Romney Doria jHensley, doria assignment 8 CS
 *         2420-Fall 2015 10/28/2015
 */
public class Vertex {

	// Holds the name of the vertex
	private String name;
	// Holds all of the edges from this vertex to others
	private LinkedList<Edge> adjList;
	// Holds the number of edges this vertex has coming in
	private int inDegree = 0;
	
	private int distanceFromStart;

	private boolean isVisited;
	
	private Vertex prevVertex = null;

	/**
	 * Creates a new vertex with the given name containing zero edges coming or
	 * going from it.
	 * 
	 * @param name
	 */
	public Vertex(String name) {
		this.name = name;
		this.adjList = new LinkedList<Edge>();
		distanceFromStart = 0;
		isVisited = false;
	}

	/**
	 * @return the String name of this vertex
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the int value of the edges pointing to this vertex
	 */
	public int getInDegree() {
		return this.inDegree;
	}

	/**
	 * This method is called by the topological sort to decrease the number of
	 * edges coming into this vertex.
	 */
	public void decreaseInDegree() {
		this.inDegree = this.inDegree - 1;
	}

	/**
	 * This method is called when an edge is created that goes to this vertex.
	 * When the method is called the inDegree gets incremented by 1.
	 */
	public void increaseInDegree() {
		this.inDegree = this.inDegree + 1;
	}

	/**
	 * This method adds an edge from this vertex to the vertex that is passed
	 * in.
	 * 
	 * @param adjVertex
	 */
	public void addEdge(Vertex adjVertex) {
		// If an edge exists, don't create a new one
		for (Edge e : this.adjList)
			if (e.getOtherVertex() == adjVertex)
				return;
		// Add the edge to the adjList and increase inDegree of the vertex that
		// is passed in.
		adjList.addLast(new Edge(adjVertex));
		adjVertex.increaseInDegree();
	}

	/**
	 * This method sets the isVisited variable to the boolean that gets passed
	 * in.
	 * 
	 * @param _status
	 */
	public void isVisited(boolean _status) {
		this.isVisited = _status;
	}

	/**
	 * @return a LinkedList containing all of the edges that this vertex has
	 *         coming from it.
	 */
	public LinkedList<Edge> getEdges() {
		return this.adjList;
	}

	/**
	 * @return true if the vertex has been visited and false if otherwise.
	 */
	public boolean getVisited() {
		return this.isVisited;
	}

	/**
	 * Sets the previous vertex of this vertex to the vertex that is passed in.
	 * 
	 * @param _prev
	 */
	public void setPrevVertex(Vertex _prev) {
		this.prevVertex = _prev;
	}

	/**
	 * @return the vertex that has an edge to this vertex
	 */
	public Vertex getPrevVertex() {
		return this.prevVertex;
	}
	
	/**
	 * This method sets the distance of this node from the starting node
	 * 
	 * @param _distance
	 */
	public void setDistance(int _distance){
		this.distanceFromStart = _distance;
	}
	
	/**
	 * @return an integer containing the distance of this node from the starting node
	 */
	public int getDistance(){
		return this.distanceFromStart;
	}
	
	/**
	 * Sets the distance of this node from the start to infinity
	 */
	public void setInfinite(){
		this.distanceFromStart = Integer.MAX_VALUE;
	}
}
