package model;

import java.util.Objects;

public class Square {
    private int x;
    private int y;
    private Piece piece;

    public Square(int x, int y, Piece piece) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Square coordinates must be between 0 and 7");
        }
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public Square(int x, int y) {

        this(x, y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > 7) {
            throw new IllegalArgumentException("X coordinate must be between 0 and 7");
        }
        this.x = x;
    }

    // for getY
    // Returns the y coordinate of the square
    public int getY() {
        return y;
    }

    // for setY
    // Sets the y coordinate of the square
    // throws IllegalArgumentException if the y coordinate is not between 0 and 7
    public void setY(int y) {
        if (y < 0 || y > 7) {
            throw new IllegalArgumentException("Y coordinate must be between 0 and 7");
        }
        this.y = y;
    }

    public Square getPosition() {
        return this;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // for isOccupied
    // Returns true if the square is occupied by a piece
    public boolean isOccupied() {
        return piece != null;
    }

    // for isLightColor
    // Returns true if the square is light colored
    // in Board there are 64 squares, 32 light and 32 dark
    public boolean isLightColor() {
        return (x + y) % 2 == 0;
    }

    // for toString
    // Converts coordinates to chess notation
    @Override
    public String toString() {
        char file = (char) ('a' + x);
        int rank = y + 1;
        String position = "" + file + rank;

        if (piece != null) {
            return position + " (" + piece.toString() + ")";
        } else {
            return position;
        }
    }

    // for equals
    // Compares two squares based on their coordinates
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Square square = (Square) o;
        return x == square.x && y == square.y;
    }

    // for hashCode
    // Returns a hash code value for this square based on its coordinates
    // imp for using it as a key in a hash map
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void clear() {
        this.piece = null;
    }

    public String getAlgebraicNotation() {
        char file = (char) ('a' + x);
        int rank = y + 1;
        return "" + file + rank;
    }

    // for copy
    public Square copy() {
        return new Square(this.x, this.y, this.piece);
    }

    // for add
    // Returns a new square offset by dx and dy from this square
    // Useful for calculating destination squares
    // Example: square.add(2, 1) for knight move
    public Square add(int dx, int dy) {
        int newX = this.x + dx;
        int newY = this.y + dy;
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return null; // Return null if out of bounds
        }
        return new Square(newX, newY);
    }

    // for subtract (distance calculation)
    // Returns the horizontal distance to another square
    // Useful for move validation (e.g., rook moves)
    public int subtractX(Square other) {
        return this.x - other.x;
    }

    // for subtract (distance calculation)
    // Returns the vertical distance to another square
    // Useful for move validation (e.g., rook moves)
    public int subtractY(Square other) {
        return this.y - other.y;
    }

}
