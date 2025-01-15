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
    public static int longestWarmingTrend(int[] temperatures) {

        // Hashmap representing all runs currently happening (key = last added temperature, value = run length)
        HashMap<Integer, Integer> runs = new HashMap<>();

        // Add the first temperature as the first run (of length 1) to runs
        runs.put(temperatures[0], 1);

        // Iterate through every temperature in the inputted array
        for (int temp : temperatures) {
            // Alter every run in the HashMap of runs accordingly
            for (int run : runs.keySet()) {
                // Make sure the new temperature is greater than the last entered run in the HashMap
                // If not, don't change the run entry
                if (temp > run) {
                    // If no entries have the current temperature as the latest entry, continue
                    if (!runs.containsKey(temp)) {
                        // Set the newest temperature added to the run to the current temperature
                        // Increment the length of the run by 1
                        runs.put(temp, runs.get(run) + 1);
                    }
                    // Otherwise, keep the longer run
                    else {
                        if (runs.get(run) > runs.get(temp)) {
                            // Set the newest temperature added to the run to the current temperature
                            // Increment the length of the run by 1
                            runs.put(temp, runs.get(run) + 1);
                        }
                    }

                }
            }
        }

        // Integer representing longest (already ended) run so far
        int longestRun = 0;
        // Get the longest run from the HashMap
        for (int runLength : runs.values()) {
            if (runLength > longestRun) {
                longestRun = runLength;
            }
        }
        // Return the longest run
        return longestRun;
    }
}
