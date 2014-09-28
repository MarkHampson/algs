import java.util.Arrays;

public class Fast {

    private static int findMin(Point[] p, int origin, int min, int max) {

        Arrays.sort(p, min, max + 1);
        if(p[origin].compareTo(p[min]) <= 0) {
            return origin;
        }
        else {
            return min;
        }
    }

    private static int findMax(Point[] p, int origin, int min, int max) {

        Arrays.sort(p, min, max + 1);
        if(p[origin].compareTo(p[max]) >= 0) {
            return origin;
        }
        else {
            return max;
        }
    }
    //Draw all lines eminating from p[i] 
    private static void drawLines(Point[] p, int i, int N) {
        double slope = p[i].slopeTo(p[i + 1]);      //save the first slope to p[i]
        int count = 1;
        for(int j = i + 2; j < N; j++) {
           if(p[i].slopeTo(p[j]) == slope) count++; //this point has the same slope
                                      //as the previous point 
           else {                     //this point does not have the
                                      //same slope as the previous point
               if(count >= 3) {       //if we got to at least three,
                                      //draw a  line to the previous point
                   //int min = findMin(p, i, j - 1 - count + 1, j - 1);
                   //int max = findMax(p, i, j - 1 - count + 1, j - 1);
                   Point[] temp = new Point[count + 1];
                   for(int k = 0; k < count; k++) {
                       temp[k] = p[j - 1 - count + 1 + k];    
                   }
                   temp[count] = p[i];
                   Arrays.sort(temp);
                   temp[0].drawTo(temp[count]);
                   for(int k = 0; k < count; k++) {
                       System.out.print(temp[k] + " -> ");
                   }
                   System.out.println(temp[count]);
                   //p[min].drawTo(p[max]);
               }


               count = 1; //reset the count 
               slope = p[i].slopeTo(p[j]); //reset the slope
           }

           //Handle the case where the last point is continuing a line
           if(j == (N - 1)) {
               if(count >= 3) {
                   int min = findMin(p, i, j - count + 1, j);
                   int max = findMax(p, i, j - count + 1, j);
                   p[min].drawTo(p[max]);
               }
           }
       }    
               
    }

    public static void main(String[] args) {
        int N;
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        String filename = args[0];
        In in = new In(filename);
        N = in.readInt();

        Point[] p = new Point[N];
        //read in and plot the points
        for(int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
            p[i].draw();
        }

        //reset pen radius for lines
        StdDraw.setPenRadius(0.001);
        
        //initial position sort
//        Arrays.sort(p);        
        for(int i = 0; i < (N - 3); i++) {
            //sort by slope order from (almost) every point
            Arrays.sort(p, i, N, p[i].SLOPE_ORDER);
            drawLines(p, i, N); 
        }
        
    }
}
