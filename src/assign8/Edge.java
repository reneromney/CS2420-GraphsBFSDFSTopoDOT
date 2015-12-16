package assign8;

/**
 * @author Jordan Hensley and Romney Doria
* jHensley, doria
* assignment 8
* CS 2420-Fall 2015
* 10/28/2015
 */
public class Edge {
	
	//Holds the the vertex this edge goes to.
	private Vertex adjVertex;
	
	/**
	 * Creates a new edge going to the passed in vertex
	 * 
	 * @param nextVertex
	 */
	public Edge(Vertex nextVertex){
		adjVertex = nextVertex;
	}

	/**
	 * @return the vertex this edge goes to
	 */
	public Vertex getOtherVertex() {
		return adjVertex;
	}
}
