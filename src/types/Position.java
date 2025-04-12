package types;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid() {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    Position getClone() {
        return new Position(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // same reference
        if (obj == null || getClass() != obj.getClass()) return false;  // null or not same class
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }
}