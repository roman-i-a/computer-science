package chapter3;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class WordGrid {

    public static class GridLocation {
        public final int row, column;

        public GridLocation(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int result = prime * 1;
            result = prime * result + row;
            result = prime * result + column;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            GridLocation other = (GridLocation) obj;
            if (column != other.column) {
                return false;
            }
            if (row != other.row) {
                return false;
            }

            return true;
        }
    }

    private final char ALPHABET_LENGTH = 26;
    private final char FIRST_LETTER = 'A';
    private final int rows, columns;
    private char[][] grid;

    public WordGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];

        Random random = new Random();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                char randomLetter = (char) (random.nextInt(ALPHABET_LENGTH) + FIRST_LETTER);
                grid[row][column] = randomLetter;
            }
        }
    }

    public void mark(String word, List<GridLocation> locations) {
        for (int i = 0; i < locations.size(); i++) {
            GridLocation location = locations.get(i);
            grid[location.row][location.column] = word.charAt(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] rowArray : grid) {
            sb.append(rowArray);
            sb.append(System.lineSeparator());
        }
    return sb.toString();
    }

    public void fillRight(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int c = column; c < (column + length); c++) {
            locations.add(new GridLocation(row, c));
        }
        domain.add(locations);
    }

    public void fillDiagonalRight(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        int r = row;
        for (int c = column; c < (column + length); c++) {
            locations.add(new GridLocation(r, c));
            r++;
        }
        domain.add(locations);
    }

    public void fillDown(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int r = row; r < (row + length); r++) {
            locations.add(new GridLocation(r, column));
        }
        domain.add(locations);
    }

    public void fillDiagonalLeft(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        int c = column;
        for (int r = row; r < (row + length); r++) {
            locations.add(new GridLocation(r, c));
            c--;
        }
        domain.add(locations);
    }

    public List<List<GridLocation>> generateDomain(String word) {
        List<List<GridLocation>> domain = new ArrayList<>();
        int length = word.length();

        for (int row = 0; row < rows; row++ ) {
            for (int column = 0; column < columns; column++) {
                if (column + length <= columns) {
                    fillRight(domain, row, column, length);
                    if (row + length <= rows) {
                        fillDiagonalRight(domain, row, column, length);
                    }
                }
                if (row + length <= rows) {
                    fillDown(domain, row, column, length);
                    if (column - length >= 0) {
                        fillDiagonalLeft(domain, row, column, length);
                    }
                }
            }
        }
        return domain; 
    }
}
