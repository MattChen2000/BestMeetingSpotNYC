package Manhattan;

import com.sun.jdi.InvalidCodeIndexException;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static boolean isSafe(int x, int y) {
        return x >= 0 && x < 100 && y >= 0 && y < 16;
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        int firstArg;
        List<int[]> argSteam = new ArrayList<>();
        if (args.length > 0) {
            try {
                args = args[0].split(" ");
                for (int i = 0; i < args.length - 1; i += 2) {
                    argSteam.add(new int[]{Integer.parseInt(args[i]), Integer.parseInt(args[i+1])});
                }
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be a string representing integers.");
                System.exit(1);
            }
        }
        Implementation NYC = new Implementation();
        int[][] M = new int[100][16];
        for (int[] array : argSteam) {
            if (!isSafe(array[0], array[1])) {
                throw new IndexOutOfBoundsException("Invalid input: index out of bound");
            }
            M[array[0]][array[1]] = 1;
        }
        int[] result = NYC.bestMeetingPoint(M);
        M[result[0]][result[1]] = 2;
        Implementation.printMatrix(M);
        String x;
        if (NYC.AvenueMap.containsKey(result[1] + 1)) {
            x = NYC.AvenueMap.get(result[1] + 1);
        } else {
            x = Integer.toString(result[1] + 1);
        }
        System.out.println("We should meet at the intersection of: "
                + x + " Avenue and " + result[0] + " Street");
    }
}
