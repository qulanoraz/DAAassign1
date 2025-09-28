import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    private AtomicLong comparisons = new AtomicLong(0);
    private AtomicLong allocations = new AtomicLong(0);
    private long recursionDepth = 0;
    private long maxRecursionDepth = 0;

    public void incrementComparisons() {
        comparisons.incrementAndGet();
    }

    public long getComparisons() {
        return comparisons.get();
    }

    public void incrementAllocations() {
        allocations.incrementAndGet();
    }

    public long getAllocations() {
        return allocations.get();
    }

    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public long getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void writeToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.append("comparisons,allocations,maxRecursionDepth\n");
            writer.append(comparisons.toString()).append(",");
            writer.append(allocations.toString()).append(",");
            writer.append(String.valueOf(maxRecursionDepth)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        comparisons.set(0);
        allocations.set(0);
        recursionDepth = 0;
        maxRecursionDepth = 0;
    }
}