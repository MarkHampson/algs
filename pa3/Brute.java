import java.util.Arrays;

public class Brute{

    public static void main(String[] args){
        int N;
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);

        String filename = args[0];
        In in = new In(filename);
        N = in.readInt();

        Point[] p = new Point[N];
        //read in and plot the points
        for(int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x,y);    
            p[i].draw();
        }
        
        StdDraw.setPenRadius(0.001);
        Point[] plines = new Point[4];
        //check all four-point combinations for collinearity
        for(int i = 0; i < (N - 3); i++){
            for(int j = i + 1; j < (N - 2); j++){
                for(int k = j + 1; k < (N - 1); k++){
                    for(int l = k + 1; l < N; l++){
                        //Compare the slopes of all points from p[i]
                        double slope = p[i].slopeTo(p[j]);
                        if(slope == Double.NEGATIVE_INFINITY) continue; //no self points
                        if(p[i].slopeTo(p[k]) != slope) continue;
                        if(p[i].slopeTo(p[l]) != slope) continue;
                        //The slopes all matched if we didn't continue 
                        plines[0] = p[i];
                        plines[1] = p[j];
                        plines[2] = p[k];
                        plines[3] = p[l];
                        Arrays.sort(plines);    //Print in ascending order
                        System.out.print(plines[0]);
                        System.out.print(" -> ");
                        System.out.print(plines[1]); 
                        System.out.print(" -> "); 
                        System.out.print(plines[2]);
                        System.out.print(" -> ");
                        System.out.println(plines[3]);
                        plines[0].drawTo(plines[3]);
                    }
                }
            }
        }
    }

}
