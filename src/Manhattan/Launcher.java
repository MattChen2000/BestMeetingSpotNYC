import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        int firstArg;
        List<int[]> argSteam = new ArrayList<>();
        if (args.length > 0) {
            try {
                args = args[1].split(" ");
                for (int i = 0; i < args.length - 1; i += 2) {
                    argSteam.add(new int[]{Integer.parseInt(args[i]), Integer.parseInt(args[i+1])});
                }
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be integers.");
                System.exit(1);
            }
        }
        Implementation NYC = new Implementation();
        int[][] M = new int[100][16];
        M[8][9] = 1;
        M[14][8] = 1;
        M[24][7] = 1;
        M[42][5] = 1;
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
