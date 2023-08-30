package chapter3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QueenConstraint extends Constraint<Integer, Integer> {

    List<Integer> columns;

    public QueenConstraint(List<Integer> columns) {
        super(columns);
        this.columns = columns;
    }

    @Override
    public boolean satisfied(Map<Integer, Integer> assignment) {
        for (Entry<Integer, Integer> item : assignment.entrySet()) {
            int q1c = item.getKey();
            int q1r = item.getValue();
            for (int q2c = q1c + 1; q2c <= columns.size(); q2c++) {
                if (assignment.containsKey(q2c)) {
                    int q2r = assignment.get(q2c);
                    if (q1r == q2r) {
                        return false;
                    }

                    if (Math.abs(q1r - q2r) == Math.abs(q1c - q2c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Integer, List<Integer>> rows = new HashMap<>();
        for (int column : columns) {
            rows.put(column, List.of(1, 2, 3, 4, 5, 6, 7, 8));
        }
        CSP<Integer, Integer> csp = new CSP<>(columns, rows);
        csp.addConstraint(new QueenConstraint(columns));

        Map<Integer, Integer> solution = csp.backtrackingSearch();
        if (solution == null) {
            System.out.println("Solution not found!");
        }
        printSolution(solution);
    }

    public static void printSolution(Map<Integer, Integer> solution) {
        String[][] grid = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                grid[i][j] = "#";
            }
        }

        for (Entry<Integer, Integer> item : solution.entrySet()) {
            grid[item.getKey() - 1][item.getValue() - 1] = "Q";
        }

        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
