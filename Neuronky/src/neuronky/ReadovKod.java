package neuronky;

import java.util.Stack;

/**
 *
 * @author tomas
 */
public class ReadovKod {

    String read;
    Stack<Integer> stack = new Stack<>();
    int size = 11;

    public void getRead(int aktRoot, boolean[][] matrix) {
        int pocetDeti = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (matrix[aktRoot][i]) {
                stack.add(i);
                pocetDeti++;
            }
        }
        read = read + pocetDeti;
        if (stack.isEmpty()) {
            return;
        }
        getRead(stack.pop(), matrix);

    }

    public static void main(String[] args) {
        ReadovKod n = new ReadovKod();
        n.read = "";
        int edgesM[][] = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 6}, {2, 8}, {4, 5}, {6, 7}, {8, 9}, {8, 10}};
        boolean matrix[][] = new boolean[n.size][n.size];
        for (int i = 0; i < edgesM.length; i++) {
            matrix[edgesM[i][0]][edgesM[i][1]] = true;
        }
        int root = 0;
        n.getRead(root, matrix);
        System.out.println(n.read);

    }

}
