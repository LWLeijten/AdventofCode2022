package utils;

import java.util.List;

public class Matrix {
    List<List<Integer>> matrix;

    public Matrix(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public int getWidth() {
        return matrix.get(0).size();
    }

    public int getHeight() {
        return matrix.size();
    }

    public int getEdgeCount() {
        return (matrix.get(0).size() * 2 + matrix.size() * 2) - 4;
    }

    private boolean coordinateIsVisible(int x, int y) {
        int value = matrix.get(y).get(x);
        List<Integer> row = matrix.get(y);
        // rows
        if (row.subList(0, x).stream().allMatch(i -> i < value)
                || row.subList(x + 1, getWidth()).stream().allMatch(i -> i < value)) {
            return true;
        } // columns
        else {
            boolean topVisible = true;
            boolean bottomVisible = true;
            for (int yy = 0; yy < y; yy++) {
                if (!(matrix.get(yy).get(x) < value)) {
                    topVisible = false;
                    break;
                }
            }
            for (int yy = y + 1; yy < getHeight(); yy++) {
                if (!(matrix.get(yy).get(x) < value)) {
                    bottomVisible = false;
                    break;
                }
            }
            return topVisible || bottomVisible;
        }
    }

    public int visibleCoordinates() {
        int visible = getEdgeCount();
        for (int y = 1; y < getHeight() - 1; y++) {
            for (int x = 1; x < getWidth() - 1; x++) {
                visible += coordinateIsVisible(x, y) ? 1 : 0;
            }
        }
        return visible;
    }

    public int highestScenicScore() {
        int highestScenicScore = 0;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int value = matrix.get(y).get(x);
                int top = 0;
                int bottom = 0;
                int left = 0;
                int right = 0;
                // left
                for (int xx = x - 1; xx >= 0; xx--) {
                    left++;
                    if (matrix.get(y).get(xx) >= value) {
                        break;
                    }
                }
                // right
                for (int xx = x + 1; xx < getWidth(); xx++) {
                    right++;
                    if (matrix.get(y).get(xx) >= value) {
                        break;
                    }
                }
                // top
                for (int yy = y - 1; yy >= 0; yy--) {
                    top++;
                    if (matrix.get(yy).get(x) >= value) {
                        break;
                    }
                }
                // bottom
                for (int yy = y + 1; yy < getHeight(); yy++) {
                    bottom++;
                    if (matrix.get(yy).get(x) >= value) {
                        break;
                    }
                }
                highestScenicScore = Integer.max(highestScenicScore, (top * bottom * left * right));
            }
        }
        return highestScenicScore;
    }
}
