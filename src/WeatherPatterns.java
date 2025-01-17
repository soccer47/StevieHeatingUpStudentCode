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

    // Adjacency matrix representing edges of graph at edges[from][to]
    static ArrayList<Integer>[] edges;

    public static int longestWarmingTrend(int[] temperatures) {

        // Initialize the adjacency matrix (the array of edges)
        edges = new ArrayList[temperatures.length];

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

        for (int i = 0; i < edges.length; i++) {
            currentRun = longestPathTo(i);
            // If the current vertex ends a longer run than the longest previously recorded run, update longestRun
            if (currentRun > longestRun) {
                longestRun = currentRun;
            }
        }

        // Return the longest run
        return longestRun;
    }

    // Method to return the longest path ending at a given vertex
    public static int longestPathTo(int vertex) {
        // Initialize the length of the path to 0
        int len = 0;
        // Recursively call longestPathTo on each of the vertices that leads to this vertex
        for (int nextNode : edges[vertex]) {
            len = (len, longestPathTo(nextNode));
        }
        return len + 1;
    }
}
