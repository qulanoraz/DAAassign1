import java.awt.Point;
import java.util.Random;

public class CommandLineInterface {
    private final Metrics metrics = new Metrics();

    public void run(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar program.jar [algorithm] [size]");
            System.out.println("Algorithms: mergesort, quicksort, select, closest");
            return;
        }
        String algo = args[0];
        int size = Integer.parseInt(args[1]);

        switch (algo.toLowerCase()) {
            case "mergesort":
                int[] mergeArray = randomIntArray(size);
                MergeSort mergeSort = new MergeSort(size, metrics);
                long start = System.nanoTime();
                mergeSort.sort(mergeArray, 0, size - 1);
                long end = System.nanoTime();
                printMetrics("MergeSort", size, end - start);
                break;
            case "quicksort":
                int[] quickArray = randomIntArray(size);
                QuickSort quickSort = new QuickSort(metrics);
                start = System.nanoTime();
                quickSort.sort(quickArray);
                end = System.nanoTime();
                printMetrics("QuickSort", size, end - start);
                break;
            case "select":
                int[] selectArray = randomIntArray(size);
                MoM5Select select = new MoM5Select(metrics);
                int k = size / 2;
                start = System.nanoTime();
                int kth = select.select(selectArray, 0, size - 1, k);
                end = System.nanoTime();
                System.out.println("Selected " + k + "-th smallest: " + kth);
                printMetrics("Select", size, end - start);
                break;
            case "closest":
                Point[] points = randomPointsArray(size);
                ClosestPair closestPair = new ClosestPair(metrics);
                start = System.nanoTime();
                double dist = closestPair.closestPair(points);
                end = System.nanoTime();
                System.out.println("Closest pair distance: " + dist);
                printMetrics("Closest Pair", size, end - start);
                break;
            default:
                System.out.println("Unknown algorithm: " + algo);
        }
    }

    private int[] randomIntArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for(int i=0; i<size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    private Point[] randomPointsArray(int size) {
        Random rand = new Random();
        Point[] points = new Point[size];
        for(int i=0; i<size; i++) {
            points[i] = new Point(rand.nextInt(size * 10), rand.nextInt(size * 10));
        }
        return points;
    }

    private void printMetrics(String algo, int n, long timeNs) {
        System.out.println("Algorithm: " + algo);
        System.out.println("Input size: " + n);
        System.out.println("Time (ns): " + timeNs);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Allocations: " + metrics.getAllocations());
        System.out.println("Max recursion depth: " + metrics.getMaxRecursionDepth());

        metrics.reset();
    }
}