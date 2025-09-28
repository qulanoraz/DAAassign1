import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsTest {
    @Test
    void testCountersAndRecursion() {
        Metrics metrics = new Metrics();

        metrics.incrementComparisons();
        metrics.incrementComparisons();
        assertEquals(2, metrics.getComparisons());

        metrics.incrementAllocations();
        assertEquals(1, metrics.getAllocations());

        metrics.enterRecursion();
        metrics.enterRecursion();
        assertEquals(2, metrics.getMaxRecursionDepth());

        metrics.exitRecursion();
        metrics.exitRecursion();
        assertEquals(2, metrics.getMaxRecursionDepth());

        metrics.reset();
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getAllocations());
        assertEquals(0, metrics.getMaxRecursionDepth());
    }
}