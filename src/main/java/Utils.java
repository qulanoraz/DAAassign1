import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    public static void guardIndices(int[] arr, int left, int right) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (left < 0 || right >= arr.length || left > right) {
            throw new IllegalArgumentException("Invalid indices: left=" + left + ", right=" + right + ", array length=" + arr.length);
        }
    }
}
