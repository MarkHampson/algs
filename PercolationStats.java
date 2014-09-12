

public class PercolationStats{

    public PercolationStats(int N, int T){
        this.N = N;
        this.T = T;
        this.percTimeResults = new double[T]; //allocate the results array

        for(int t=0; t<T; t++){  //outer T loop
            //Begin test 
            int size = N*N;
            int[] randomIndex = new int[size];
            Percolation pTest = new Percolation(N); //initialized NxN block

            //initialize randomIndex array
            for(int x=0; x<size; x++){
                randomIndex[x] = x;
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
            double fractionOpen = (double)(n+1)/size;
            this.percTimeResults[t]=fractionOpen; //record the result of this run
            //System.out.println("Number of iterations before percolation: "+
            //                    n+1);
        }       


    }
   
    private int N;
    private int T; 
    private double[] percTimeResults;
    private double mean;
    private double stddev;
    private double confLo;
    private double confHi;
    
    public double mean(){
        this.mean=0;  //initialize the mean
        for(int i=0; i<this.T; i++){
            this.mean += this.percTimeResults[i]; //accumulate results
        }
        this.mean /= this.T; //divide by number of trials gives the mean
        return this.mean;
    }
    public double stddev(){
        this.stddev=0; //init the stddev
        double var = 0;  //variance
        for(int i=0; i<this.T; i++){
            var += Math.pow((this.percTimeResults[i] - this.mean), 2);
        }
        this.stddev = Math.sqrt(var);
        return this.stddev;
    }
    
    public double confidenceHi(){
        return 1.0;    
    }
    public double confidenceLo(){
        return 1.0;
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
        int N,T;  //NxN grid, T experiments
        if(args.length != 2) throw new java.lang.IllegalArgumentException("Two args pls");
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean        = " + stats.mean());
        System.out.println("stddev      = " + stats.stddev());         

    }
}
