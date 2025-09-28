public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    private final Metrics metrics;
    private final int[] buffer;

    public MergeSort(int maxSize, Metrics metrics) {
        this.metrics = metrics;
        this.buffer = new int[maxSize];
    }

    public void sort(int[] arr, int left, int right) {
        metrics.enterRecursion();
        int length = right - left + 1;
        if (length <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
        } else if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        metrics.exitRecursion();
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            metrics.incrementAllocations();
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.incrementComparisons();
                arr[j + 1] = arr[j];
                j--;
                metrics.incrementAllocations();
            }
            arr[j + 1] = key;
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
                metrics.incrementAllocations();
            } else {
                arr[k++] = buffer[j++];
                metrics.incrementAllocations();
            }
        }
        while (i <= mid) {
            arr[k++] = buffer[i++];
            metrics.incrementAllocations();
        }
        while (j <= right) {
            arr[k++] = buffer[j++];
            metrics.incrementAllocations();
        }
    }
}