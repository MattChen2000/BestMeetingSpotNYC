import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Implementation {
    private HashMap<Integer, String> AvenueMap;

    public Implementation() {
        // Populate a map that relates some of the avenue names in NYC and its index
        AvenueMap= new HashMap<>();
        AvenueMap.put(4, "Lexington Ave");
        AvenueMap.put(6, "Avenue of the Americas");
        AvenueMap.put(7, "Fashion Ave");
        AvenueMap.put(8, "Central Park West");
        AvenueMap.put(9, "Columbus Ave");
        AvenueMap.put(10, "Amsterdam Avenue");
        AvenueMap.put(11, "West End Avenue");
    }

    /**
     * This method takes a matrix of 0's and 1's and return the coordinates of the best meeting point for all 1's
     * @param Matrix: A matrix representing the streets as rows and avenues as columns in NYC
     * @return an integer array with its first item being the x-coordinate(street) and second being y-coordinate (avenue)
     */
    public int[] bestMeetingPoint(int[][] Matrix) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[0].length; j++) {
                if (Matrix[i][j] == 1) {
                    x.add(i); y.add(j);
                }
            }
        }
        return new int[]{get1DBestPoint(x), get1DBestPoint(y)};
    }

    /**
     * This helper method takes a List, sorts it, and returns its medium
     * @param axis: a List of integers
     * @return the medium of the list
     */
    private int get1DBestPoint(List<Integer> axis) {
        quickSort(axis, 0, axis.size() - 1);
        return axis.get(axis.size() / 2);
    }

    /**
     * This helper method sorts a List Object by implementing the quickSort algorithm
     * @param L: the list to be sorted
     * @param lo: the lower bound of the sorting window
     * @param hi: the upper bound of the sorting window
     */
    private void quickSort(List<Integer> L, int lo, int hi){
        if (lo >= hi) return;
        int left = lo, right = hi;
        int pivot = L.get((left + right) / 2);
        while (left <= right) {
            while (left <= right && L.get(left) < pivot) {
                left++;
            }
            while (left <= right && L.get(right) > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = L.get(left);
                L.set(left, L.get(right));
                L.set(right, temp);
                left++;
                right--;
            }
        }
        quickSort(L, lo, right);
        quickSort(L, left, hi);
    }

    /**
     * This helper method prints a matrix to console by reverse order of rows
     * @param M: the matrix to be printed to console
     */
    public static void printMatrix(int[][] M) {
        for (int i = M.length - 1; i >= 0; i--) {
            for (int j = M[0].length - 1; j >= 0; j--) {
                if (j != M[0].length - 1) {
                    System.out.print(" ");
                }
                if (M[i][j] == 0) {
                    System.out.print('-');
                } else {
                    System.out.print(M[i][j]);
                }
            }
            System.out.println("");
        }
    }

    // The driver code
    public static void main(String[] args) {
        Implementation Manhattan = new Implementation();
        int[][] M = new int[100][16];
        M[8][9] = 1;
        M[14][8] = 1;
        M[24][7] = 1;
        M[42][5] = 1;
        int[] result = Manhattan.bestMeetingPoint(M);
        M[result[0]][result[1]] = 2;
        printMatrix(M);
        String x;
        if (Manhattan.AvenueMap.containsKey(result[1] + 1)) {
            x = Manhattan.AvenueMap.get(result[1] + 1);
        } else {
            x = Integer.toString(result[1] + 1);
        }
        System.out.println("We should meet at the intersection of: "
                + x + " Avenue and " + result[0] + " Street");
    }
}
