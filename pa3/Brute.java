

public class Brute{

public static void main(String[] args){
    int N = 10;
    int count = 5;
    StdDraw.setPenRadius(0.01);
    StdDraw.setXscale(0,N);
    StdDraw.setYscale(0,N);

    Point p0 = new Point(0,0);
    p0.draw();
    Point[] pArray = new Point[count];
    for(int i = 0; i < count; i++) {
       pArray[i] = new Point(StdRandom.uniform(N), StdRandom.uniform(N));  
       pArray[i].draw();
       System.out.println(pArray[i]);
    }
}

}
