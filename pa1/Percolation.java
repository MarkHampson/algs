

public class Percolation {

    private int width;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF percData;
    private boolean[] openList;

    public Percolation(int N){
        size = N*N+2; //The additional two account for the virtual
                      //top and bottom sites
        width = N;
        top=size-2;    //next to last index
        bottom=size-1; //last index

        openList = new boolean[size]; //keep track of open sites
        percData = new WeightedQuickUnionUF(size);
        for(int i=0; i<size; i++) openList[i]=false;//init sites as closed
        openList[top]=true; //open the top and bottom sites
        openList[bottom]=true;
    }

    private int getIndex(int i, int j){
        int row = i;
        int col = j;
        int idx = 0;
        if((i<1 || i>width) || (j<1 || j>width)){
            throw new IndexOutOfBoundsException("That point doesn't exist");
        }
        idx += width*(row-1);
        idx += (col-1);
        return idx; 
    }   
        
    private int getCount(){
        return percData.count(); 
    }

    private void showAllID(){
        for(int i=0; i<size; i++) System.out.println(percData.find(i));
    }
    
    public void open(int i, int j){
        int idx = getIndex(i,j);
        openList[idx]=true;
        //connect to position on the left
        if(j>1){ //we are not in the first column
            if(isOpen(i,j-1)){ //spot on left is open
                percData.union(idx,idx-1);
            }
        }
        //connect to position on the right
        if(j<width){ //we are not in the last column
            if(isOpen(i,j+1)){ //spot on right is open
                percData.union(idx,idx+1);
            }
        }
        //connect to position above
        if(i>1){ //we are not on top row
            if(isOpen(i-1,j)){ //spot above is open
                percData.union(idx,idx-width);
            }
        }
        else {
            percData.union(idx,top); //connect to top
        }
        //connect to position below
        if(i<width){ //we are not on bottom row
            if(isOpen(i+1,j)){ //spot below is open
                percData.union(idx,idx+width);
            }
        }
        else {
            percData.union(idx,bottom); //connect to bottom
        }
    }

    public boolean isOpen(int i, int j){
        int idx = getIndex(i,j);
        return openList[idx];
    }

    public boolean isFull(int i, int j){
        int idx = getIndex(i,j);
        return percData.connected(idx,top); //if connected to top, then full
    }

    public boolean percolates(){
        return percData.connected(top,bottom); //if top and bottom are 
                                               //connected then it percolates
    }

    public static void main(String[] args){
        Percolation percTest = new Percolation(4);
        percTest.open(1,3);
        percTest.open(2,3);
        percTest.open(1,4);
        percTest.open(3,2);
        percTest.open(3,3);
        percTest.open(4,3);
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){
                System.out.println(i+", "+j+": "+percTest.getIndex(i,j)+
                " "+percTest.isOpen(i,j)+
                " "+percTest.isFull(i,j));
            }
        }
        percTest.showAllID();
        System.out.println("Count: "+percTest.getCount());
        System.out.println("Percolates: "+percTest.percolates());
    }
}
