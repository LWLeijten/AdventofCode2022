package utils;

import java.util.ArrayList;
import java.util.List;

public class Coordinate extends Pair<Integer> {
    public Coordinate(Integer elementOne, Integer elementTwo) {
        super(elementOne, elementTwo);
    }

    @Override
    public Coordinate clone() {
        return (Coordinate) super.clone();
    }

    public boolean touchesOtherCoordinate(Coordinate other) {
        return Math.abs(this.elementOne - other.elementOne) <= 1 && Math.abs(this.elementTwo - other.elementTwo) <= 1;
    }

    public List<Coordinate> getNeighbours(boolean diagonals) {
        List<Coordinate> neighbours = new ArrayList<>();
        List<Coordinate> directions = new ArrayList<>(
                List.of(new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(0, -1), new Coordinate(0, 1)));
        if (diagonals) {
            directions.addAll(List.of(
                    new Coordinate(-1, -1), new Coordinate(1, 1), new Coordinate(1, -1), new Coordinate(-1, 1)));
        }
        for (Coordinate dir : directions) {
            int nX = this.elementOne + dir.elementOne;
            int nY = this.elementTwo + dir.elementTwo;
            neighbours.add(new Coordinate(nX, nY));
        }
        return neighbours;
    }

    @Override
    public int hashCode() {
        int tmp = (elementTwo + ((elementOne + 1) / 2));
        return elementOne + (tmp * tmp);
    }
}
