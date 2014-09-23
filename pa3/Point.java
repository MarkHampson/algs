import java.util.Comparator;

public class Point implements Comparable<Point> {

    //compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();
    
    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
               double slope1 = slopeTo(p1);
               double slope2 = slopeTo(p2);
               
               if(slope1 < slope2) return -1;
               if(slope1 > slope2) return 1;
               else return 0; 
        }
    }

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
        double numer = that.y - this.y;
        double denom = that.x - this.x;

        if(numer == 0 && denom == 0) return Double.NEGATIVE_INFINITY; //same point
        if(numer == 0) return 0;                                      //horizontal
        if(denom == 0) return Double.POSITIVE_INFINITY;               //vertical

        return numer / denom;                              //everything in between
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
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);
        Point p0 = new Point(0,0);
        Point p3 = new Point(0,1);
        Point p4 = new Point(0,-1);
        System.out.println(p0.slopeTo(p1));
        System.out.println(p0.slopeTo(p2));
        System.out.println(p0.slopeTo(p3));
        System.out.println(p0.slopeTo(p4));
        System.out.println(p0.slopeTo(p0));
        System.out.println(p0.SLOPE_ORDER.compare(p4,p3));
        System.out.println(p2);
    }
}
