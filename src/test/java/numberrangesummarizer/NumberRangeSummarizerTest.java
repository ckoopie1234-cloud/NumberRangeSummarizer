package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberRangeSummarizerTest {

    // Create an instance of your code to test it
    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

    @Test
    void shouldReproduceSpecSample() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        // This tests the exact output required by the instructions
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result);
    }

    @Test
    void collectShouldHandleNullAndEmptyAndSpaces() {
        assertTrue(summarizer.collect(null).isEmpty());
        assertTrue(summarizer.collect("").isEmpty());
        assertEquals(List.of(1, 3), new ArrayList<>(summarizer.collect(" 1 , 3 ")));
    }

    @Test
    void summarizeShouldHandleUnsortedInput() {
        // If we pass in unsorted numbers, it should still figure out the ranges
        assertEquals("6-8", summarizer.summarizeCollection(List.of(8, 6, 7)));
    }

    @Test
    void summarizeShouldHandleSingleNumber() {
        assertEquals("5", summarizer.summarizeCollection(List.of(5)));
    }
}