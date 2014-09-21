import java.util.Comparator;

public class Point implements Comparable<Point> {

    //compare points by slope
    public final Comparator<Point> SLOPE_ORDER;  //TODO

    private final int x;                         // x coordinate
    private final int y;                         // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // TODO
        // may need Double.POSITIVE_INFINITY and Double.NEGATIVE_INFINITY
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if(this.y > that.y) return 1;
        else if(this.y < that.y) return -1;
             else if(this.x > that.x) return 1; // implicitly, this.y == that.y
                  else if(this.x < that.x) return -1;
                       else return 0;           //this.x == that.x
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        // TODO
    }
}
