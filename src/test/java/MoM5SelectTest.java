import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Random;

public class MoM5SelectTest {

    @Test
    void testSelect() {
        Metrics metrics = new Metrics();
        MoM5Select select = new MoM5Select(metrics);

        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3; // 3rd smallest element
        int selected = select.select(arr.clone(), 0, arr.length - 1, k);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[k-1], selected);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Allocations: " + metrics.getAllocations());
        System.out.println("Max recursion depth: " + metrics.getMaxRecursionDepth());

        // Проверка на 100 случайных массивах
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int size = 100 + rand.nextInt(901); // от 100 до 1000
            int[] testArr = new int[size];
            for (int i = 0; i < size; i++) {
                testArr[i] = rand.nextInt(10000);
            }
            int pos = rand.nextInt(size) + 1;
            metrics.reset();
            int res = select.select(testArr.clone(), 0, size - 1, pos);
            Arrays.sort(testArr);
            assertEquals(testArr[pos - 1], res);
        }
    }
}