package problems;

import java.util.Arrays;

public class BoatsToSavePeople_881 {

    public static int numRescueBoats(int[] people, int limit) {
        int result = 0;

        Arrays.sort(people);

        int i = 0, j = people.length-1;
        int count = 0;

        while (count < people.length) {
            if (people[i] + people[j] <= limit) {
                i++;
                count++;
            }
            count++;
            j--;
            result++;
        }

        return result;
    }

    public static int[] p = {3,5,3,4};

    public static void main(String[] args) {
        System.out.println(numRescueBoats(p, 5));
    }
}