import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        while (left < right) {
            metrics.enterRecursion();
            int pivotIndex = randomizedPartition(arr, left, right);
            int leftSize = pivotIndex - left;
            int rightSize = right - pivotIndex;

            if (leftSize < rightSize) {
                sort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                sort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
            metrics.exitRecursion();
        }
    }

    private int randomizedPartition(int[] arr, int left, int right) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);
        return partition(arr, left, right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        metrics.incrementAllocations();
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            metrics.incrementAllocations();
        }
    }
}