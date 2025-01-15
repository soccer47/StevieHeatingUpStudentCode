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
            // Make a copy of runs to store the changes made during this check through with the current temperature
            HashMap<Integer, Integer> holder = new HashMap<>(runs);

            // Add the current temperature to the holder HashMap as the start of its own run if it isn't already a key
            if (!holder.containsKey(temp)) {
                holder.put(temp, 1);
            }

            // Alter every run in the HashMap of runs accordingly
            for (int run : runs.keySet()) {
                // Make sure the new temperature is greater than the last entered run in the HashMap
                // If not, don't change the given run entry
                if (temp > run) {
                    // If the current run would be longer than the other run with the current temp as the latest entry
                    // replace the other run with the current run
                    if (holder.get(run) >= holder.get(temp)) {
                        // Set the newest temperature added to the run to the current temperature
                        // Increment the length of the run by 1
                        holder.put(temp, holder.get(run) + 1);
                    }
                }
            }
            // Add the changes made to the holder HashMap back into the original HashMap of runs
            runs = holder;
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
