import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Stevie K. Halprin
 */

public class WeatherPatterns {

    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */

    // Adjacency list with a list of the edges leading to each vertex at edges[vertex]
    static ArrayList<Integer>[] edges;
    // Array to keep track of the longest path ending at each node
    static int[] longestPaths;

    public static int longestWarmingTrend(int[] temperatures) {
        // Initialize the adjacency list (the array of edges)
        edges = new ArrayList[temperatures.length];
        // Initialize all the ArrayLists in the edges array
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        // Initialize the array of longest paths
        longestPaths = new int[temperatures.length];
        // Iterate through the temperatures array to fill the edges array
        for (int i = 0; i < temperatures.length; i++) {
            // Iterate through every temperature that has been passed already
            for (int j = 0; j < i; j++) {
                // Add the current temperature as an edge if the prior temperature is lower than the current temp
                if (temperatures[j] < temperatures[i]) {
                    edges[j].add(i);
                }
            }
        }
        // Initialize the Integer to hold the longest recorded run to 0
        int longestRun = 0;
        // Integer to hold the length of the longest run ending at the current vertex
        int currentRun;
        // Find the longest path ending at each node and return the longest one
        // Start at the last node to reduce excess alteration of longestRun
        for (int i = edges.length - 1; i >= 0; i--) {
            currentRun = longestPathTo(i);
            // If the current vertex ends a longer run than the longest previously recorded run, update longestRun
            if (currentRun > longestRun) {
                longestRun = currentRun;
            }
        }
        // Return the longest run
        return longestRun;
    }

    // Method to return the longest path ending at a given vertex using recursion
    public static int longestPathTo(int vertex) {
        // If the current node has already been visited, just return the longest path ending at this node
        if (longestPaths[vertex] != 0) {
            return longestPaths[vertex];
        }
        // Initialize the length of the longest path ending at the current vertex to 0
        int len = 0;
        // Recursively call longestPathTo on each of the vertices that leads to this vertex
        // Set len to the longest found path ending at a parent vertex
        for (int nextNode : edges[vertex]) {
            int longestPath = longestPathTo(nextNode);
            // If the longest path from the given parent vertex is the longest yet, update len
            if (len < longestPath) {
                len = longestPath;
            }
        }
        // Add the longest path ending at the current node to the array for quicker future calls
        longestPaths[vertex] = len + 1;
        // Return the longest path ending at the current vertex
        return len + 1;
    }
}