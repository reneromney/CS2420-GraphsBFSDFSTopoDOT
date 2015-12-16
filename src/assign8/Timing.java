package assign8;

/**
 * @author Jordan Hensley and Romney Doria jHensley, doria assignment 8 CS
 *         2420-Fall 2015 10/28/2015
 */
public class Timing {

	public static void main(String[] args) {

		// Do 10000 lookups and use the average running time.
		int timesToLoop = 200;

		// For each problem size n . . .
		for (int n = 100; n <= 2000; n += 100) {
			// Setup of n size for testing
			long startTime, midpointTime, stopTime;
			
			GraphUtil.generateRandomDotFile("/home/jordan/Documents/mars_workspace/CS_2420/testTime.dot", n, 5, true);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			// Run the methods for testing

			for (int i = 0; i < timesToLoop; i++) {
				GraphUtil.breadthFirstSearch("/home/jordan/Documents/mars_workspace/CS_2420/testTime.dot", "v0", "v" + (n-1));
			}

			midpointTime = System.nanoTime();

			// Time it takes to run loop

			for (int i = 0; i < timesToLoop; i++) {
				//Graph g = GraphUtil.buildGraphFromDot("/home/jordan/Documents/mars_workspace/CS_2420/testTime.dot");
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(averageTime);

		}

	}

}
