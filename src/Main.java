// Реализовать алгоритм пирамидальной сортировки (сортировка кучей).

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(arr.length);
        }

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        long end = System.currentTimeMillis();
        long time1 = end - start;
        sortingTest(arr1);
        System.out.printf("Время сортировки пузырьком %d миллисекунд\n", time1);

        start = System.currentTimeMillis();
        heapSort(arr2);
        end = System.currentTimeMillis();
        long time2 = end - start;
        sortingTest(arr2);
        System.out.printf("Время пирамидальной сортировки %d миллисекунд\n", time2);
    }

    static void heapSort(int[] arr) {
        int offset = 0;
        while (offset < arr.length - 1) {
            for (int i = (arr.length - offset) / 2 - 1; i >= 0; i--) {
                int parent = i;
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int max = left;
                if (right < arr.length - offset) {
                    max = arr[left] > arr[right] ? left : right;
                }
                exchange(arr, max, parent);
            }
            exchange(arr, 0, arr.length - offset - 1);
            offset++;
        }
    }

    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                exchange(arr, j, j + 1);
            }
        }
    }

    static void exchange(int[] arr, int index1, int index2) {
        if (arr[index1] > arr[index2]) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    static void sortingTest(int[] arr) {
        boolean ok = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) ok = false;
        }
        if (!ok) System.out.println("Сортировка не работает!");

    }

    static void print(int[] arr) {
        for (int i: arr) System.out.print(i + " ");
        System.out.println();
    }

}