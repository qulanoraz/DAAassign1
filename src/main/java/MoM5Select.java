public class MoM5Select {
    private final Metrics metrics;

    public MoM5Select(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();
        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }
        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);
        int length = pivotIndex - left + 1;
        if (k == length) {
            metrics.exitRecursion();
            return arr[pivotIndex];
        } else if (k < length) {
            int res = select(arr, left, pivotIndex - 1, k);
            metrics.exitRecursion();
            return res;
        } else {
            int res = select(arr, pivotIndex + 1, right, k - length);
            metrics.exitRecursion();
            return res;
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            insertionSort(arr, left, right);
            return left + n / 2;
        }
        int numMedians = (int) Math.ceil((double) n / 5);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight);
            int medianIdx = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, medianIdx);
            metrics.incrementAllocations();
        }
        return select(arr, left, left + numMedians - 1, (numMedians + 1) / 2);
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

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
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