import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testSort() {
        Metrics metrics = new Metrics();
        int[] arr = {5, 2, 4, 6, 1, 3};
        MergeSort sorter = new MergeSort(arr.length, metrics);
        sorter.sort(arr, 0, arr.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, arr);
        // Можно вывести метрики для проверки, например:
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Allocations: " + metrics.getAllocations());
        System.out.println("Max recursion depth: " + metrics.getMaxRecursionDepth());
    }
}