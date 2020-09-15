package grokking;

public class Part_12_ModifiedBinarySearch {

    /**
     * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
     * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
     * You should assume that the array can have duplicates.
     * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
     */
    public static int binarySearch(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;
        boolean isAscending = arr[l] < arr[r];

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return mid;

            if (isAscending) {
                if (key < arr[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (key > arr[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }

        }

        return -1;
    }

    /**
     * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
     * The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.
     * Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.
     */
    public static int searchCeilingOfANumber(int[] arr, int key) {
        if (key > arr[arr.length - 1])
            return -1;

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return mid;
            if (key < arr[mid])
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }

    /**
     * Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ‘key’.
     * Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter.
     * This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
     * Write a function to return the next letter of the given ‘key’.
     */
    public static char searchNextLetter(char[] letters, char key) {
        if (letters[letters.length - 1] < key || letters[0] > key)
            return letters[0];

        int l = 0;
        int r = letters.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (key < letters[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (l >= letters.length)
            l = 0;

        return letters[l];
    }

    /**
     * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’.
     * The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
     * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].
     */
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[]{-1, -1};
        result[0] = findKeyDirectional(arr, key, false);
        if (result[0] != -1)
            result[1] = findKeyDirectional(arr, key, true);
        return result;
    }

    public static int findKeyDirectional(int[] arr, int key, boolean findMaxIndex) {
        int l = 0;
        int r = arr.length - 1;
        int keyIndex = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (key < arr[mid]) {
                r = mid - 1;
            } else if (key > arr[mid]) {
                l = mid + 1;
            } else {
                keyIndex = mid;
                if (findMaxIndex)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return keyIndex;
    }

    static class ArrayReader {
        int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length)
                return Integer.MAX_VALUE;
            return arr[index];
        }
    }

    /**
     * Given an infinite sorted array (or an array with unknown size), find if a given number ‘key’ is present in the array.
     * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
     * Since it is not possible to define an array with infinite (unknown) size, you will be provided with an interface ArrayReader to read elements of the array.
     * ArrayReader.get(index) will return the number at index; if the array’s size is smaller than the index, it will return Integer.MAX_VALUE.
     */
    public static int searchInfiniteSortedArray(ArrayReader reader, int key) {
        int l = 0;
        int r = 1;

        while (reader.get(r) < key) {
            l = r;
            r *= 2;
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (reader.get(mid) == key)
                return mid;
            if (reader.get(mid) < key)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return -1;
    }

    /**
     * Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.
     */
    public static int searchMinDiffElement(int[] arr, int key) {
        if (key < arr[0])
            return arr[0];
        if (key > arr[arr.length - 1])
            return arr[arr.length - 1];

        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return arr[mid];
            if (arr[mid] < key)
                l = mid + 1;
            else
                r = mid - 1;
        }

        if (Math.abs(key - arr[l]) > Math.abs(key - arr[r]))
            return arr[r];
        return arr[l];
    }

    /**
     * Find the maximum value in a given Bitonic array.
     * An array is considered bitonic if it is monotonically increasing and then monotonically decreasing.
     * Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].
     */
    public static int maxInBitonicArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid + 1])
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    /**
     * Given a Bitonic array, find if a given ‘key’ is present in it.
     * An array is considered bitonic if it is monotonically increasing and then monotonically decreasing.
     * Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].
     * Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.
     */
    public static int searchBitonicArray(int[] arr, int key) {
        int max = maxInBitonicArray(arr);
        int l = binarySearchInRange(arr, key, 0, max);
        if (l != -1)
            return l;
        int r = binarySearchInRange(arr, key, max, arr.length - 1);

        return r;
    }

    public static int binarySearchInRange(int arr[], int key, int l, int r) {
        boolean isAscending = arr[l] < arr[r];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return mid;
            if (isAscending) {
                if (key < arr[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (arr[mid] > key)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number, find if a given ‘key’ is present in it.
     * Write a function to return the index of the ‘key’ in the rotated array.
     * If the ‘key’ is not present, return -1.
     * You can assume that the given array does not have any duplicates.
     */
    public static int searchRotatedArray(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[l] <= arr[mid]) {
                if (arr[l] <= key && key < arr[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (key > arr[mid] && key <= arr[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    }

    /**
     * How do we search in a sorted and rotated array that also has duplicates?
     */
    public static int searchRotatedArrayWithDuplicates(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == key)
                return mid;

            if (arr[l] == arr[mid] && arr[r] == arr[mid]) {
                l++;
                r--;
            }
            if (arr[l] <= arr[mid]) {
                if (arr[l] <= key && key < arr[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (key > arr[mid] && key <= arr[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Given an array of numbers which is sorted in ascending order and is rotated ‘k’ times around a pivot, find ‘k’.
     * You can assume that the array does not have any duplicates.
     */
    public static int countRotations(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < arr[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    /**
     * How do we find the rotation count of a sorted and rotated array that has duplicates too?
     */
    public static int countRotationsWithDuplicates(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[l] == arr[mid] && arr[mid] == arr[r]) {
                if (arr[r-1] > arr[r])
                    return r;
                l++;
                r--;
            } else if (arr[mid] <= arr[r])
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        System.out.println(countRotationsWithDuplicates(new int[] { 3, 3, 7, 3 }));
        System.out.println(countRotationsWithDuplicates(new int[] { 2, 2, 2, 0, 1}));
        System.out.println(countRotationsWithDuplicates(new int[] { 1, 3, 3}));
    }
}
