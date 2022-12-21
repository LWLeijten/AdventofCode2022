package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate3D implements Cloneable {
    public Integer x;
    public Integer y;
    public Integer z;

    public Coordinate3D(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Coordinate3D clone() {
        try {
            return (Coordinate3D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        Coordinate3D other = (Coordinate3D) o;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public List<Coordinate3D> getNeighbours() {
        List<Coordinate3D> neighbours = new ArrayList<>();
        List<Coordinate3D> directions = List.of(
                new Coordinate3D(-1, 0, 0),
                new Coordinate3D(1, 0, 0),
                new Coordinate3D(0, -1, 0),
                new Coordinate3D(0, 1, 0),
                new Coordinate3D(0, 0, -1),
                new Coordinate3D(0, 0, 1));
        for (Coordinate3D dir : directions) {
            neighbours.add(new Coordinate3D(this.x + dir.x, this.y + dir.y, this.z + dir.z));
        }
        return neighbours;
    }

    public int exposedSides(List<Coordinate3D> others, List<Coordinate3D> floodable) {
        int exposed = 0;
        for (Coordinate3D nb : this.getNeighbours()) {
            if (!others.contains(nb) && (floodable.size() == 0 || floodable.contains(nb))) {
                exposed++;
            }
        }
        return exposed;
    }
}
