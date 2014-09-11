

public class PercolationStats{
    
    private static void runTest(int N){
        int size = N*N;
        int[] randomIndex = new int[size];
        Percolation pTest = new Percolation(N); //initialized NxN block
        //initialize randomIndex array
        for(int i=0; i<size; i++){
            randomIndex[i] = i;
        }
        //shuffle the array
        StdRandom.shuffle(randomIndex); //yay random
        //Open sites until it percolates
        int i,j,n,r;
        for(n=0; n<size; n++){
            r=randomIndex[n]; //get the random index
            i=getI(N,r);      
            j=getJ(N,r); 
            pTest.open(i,j);
            //System.out.println("Open: "+i+", "+j+" ("+r+")");
            if(pTest.percolates()) break;
        }
        System.out.println("Number of iterations before percolation: "+
                            n+1);
       

    }
    //Convert index to the ith row of the box 
    private static int getI(int N, int idx){
        int i = idx/N + 1;
        return i; 
    }
    
    //Convert index to the jth row of the box
    private static int getJ(int N, int idx){
        int j = (idx % N) + 1;   
        return j;
    }

    public static void main(String[] args){
        int N;
        if(args.length > 0) N = Integer.parseInt(args[0]);
        else N = 5;
        runTest(N); 
    }
}
