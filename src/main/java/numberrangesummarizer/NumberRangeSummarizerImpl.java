package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        // 1. Guard Clause: Check if the input is null or just a bunch of empty spaces.
        // We do this to avoid a "NullPointerException" crash.
        if (input == null || input.trim().isEmpty()){
            return new ArrayList<>();
        }
        // 2. Create a list to hold the actual numbers.
        List<Integer> numbers = new ArrayList<>();
        String[] stringNumbers = input.split(",");
        // 3. Split the single string into an array of smaller strings.
        // For example: "1,3,6" becomes an array like ["1", "3", "6"]
        for (String strnum : stringNumbers) {
            numbers.add(Integer.parseInt(strnum.trim()));
        }
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        // 1. If the collection is null or empty, there are no numbers to summarize.
        // Just return an empty string "" and stop right here.
        if (input == null || input.isEmpty()) {
            return "";
        }
        // 2. Copy the input into a new ArrayList.
        List<Integer> sorted = new ArrayList<>(input);
        // 3. Sort the list from smallest to largest.
        // This is crucial because we can only group sequential numbers (like 6,7,8) if they are in order.
        Collections.sort(sorted);

        // 4. Create a List to hold our formatted ranges
        List<String> ranges = new ArrayList<>();

        // 5. Grab the very first number in the sorted list. This is the start of our very first range.
        int rangeStart = sorted.get(0);
        // 6. Keep track of the previous number we looked at.
        int prev = rangeStart;

        // 7. Start looping through the list.
        for (int i = 1; i < sorted.size(); i++) {
            int current = sorted.get(i); // this will grab the number we are currently looking at

            // Skip duplicates safely
            if (current == prev) {
                continue;
            }

            if (current == prev + 1) {
                //this means its sequential
                prev = current;
            } else {
                //the sequence has been broken
                if (rangeStart == prev) {
                    ranges.add(String.valueOf(rangeStart));
                } else {
                    ranges.add(rangeStart + "-" + prev);
                }

                // Now start a new range with the current number
                rangeStart = current;
                prev = current;
            }
        }

        // 8. Handle the very last range after the loop finishes
        if (rangeStart == prev) {
            ranges.add(String.valueOf(rangeStart));
        } else {
            ranges.add(rangeStart + "-" + prev);
        }

        // 9. Join them all together with a comma and space.
        // This is the magic Java 8 way that guarantees no trailing commas!
        return String.join(", ", ranges);
    }
}