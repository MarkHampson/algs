
public class Board {

    private int[][] blocks;  //the NxN array of blocks
    private int N;          //board dimension N

    public Board(int[][] blocks) { //NxN array of blocks [i][j] = row col
       N = blocks.length;   //row dimension
       this.blocks = new int[N][N];
       for(int i = 0; i < N; i++) {  //check dimension of every row
           if(blocks[i].length != N) System.out.println("Row " + i + " does is not length " + N);
           for(int j = 0; j < N; j++) {
               this.blocks[i][j] = blocks[i][j];
           }
       }
    }
    public int dimension() { //board dimension N 
        return N;   
    }  

    public int hamming() {  // number of blocks out of place
        int wrongBlocks = 0;
        int correctBlock = 1;
        int lastBlock = N * N;
        for(int i = 0; i < N; i++) {      //row iteration
            for(int j = 0; j < N; j++) {  //col iteration
                if(blocks[i][j] == 0) {   //ignore the blank tile
                    correctBlock++;
                    continue;
                } else {
                    if(correctBlock == lastBlock) correctBlock = 0;     //the last tile is a space (0)
                    if(blocks[i][j] != correctBlock) wrongBlocks++;
                    correctBlock++;   
                }
            }
        }
        return wrongBlocks;       
    }

    // If the blocks are counting up from one, this returns
    // the row of block n in the grid.
    private int getRow(int block) {
        if (block == 0) return N - 1;
        else return (block - 1) / N;
    }

    // Returns the column of block n in the NxN grid
    private int getCol(int block) {
        if (block == 0) return N - 1;
        else return (block - 1) % N;
    }

    public int manhattan() { // sum of manhattan distances between blocks and goal
        int correctBlock = 1;
        int lastBlock = N * N;
        int distance = 0;
        int lateral;
        int vertical;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(blocks[i][j] == 0) {
                    correctBlock++;
                    continue;
                } else {
                        lateral = Math.abs(getCol(blocks[i][j]) - j);
                        vertical = Math.abs(getRow(blocks[i][j]) - i);
                        distance += lateral;
                        distance += vertical;
                        correctBlock++;
                }
            }
        }
        
        return distance;       
    }

    public boolean isGoal() { // is this board the goal board?
        if(hamming() == 0) return true;
        else return false;   //
    }

    public Board twin() {  // a board obtained by exch. two adj. blocks in same row
        //copy this board to an int array
        int[][] twink = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                twink[i][j] = blocks[i][j];
            }
        }
        //return the first twin found
        int temp = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < (N - 1); j++){
                if(twink[i][j] == 0) continue; //can't make a twin on a space
                temp = twink[i][j];         //not on a space, possible twin
                if(twink[i][j + 1] == 0) continue; // if next is a space, no twin
                twink[i][j] = twink[i][j + 1]; //can make a twin, swap the columns
                twink[i][j + 1] = temp;
                Board twinky = new Board(twink); 
                return twinky;
            }
        }
        return this;
    }

    public boolean equals(Object y) {  // does this board equal y?
        return false;   //TODO
    }

    public void neighbors() { //all neighboring boards
        //TODO return Iterable<Board>
    }

    //This is the given reference implementation
    public String toString() {   //string representation of the board
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args){
        int[][] bluh = new int[][] {
            new int[] {7, 4, 3},
            new int[] {0, 8, 6},
            new int[] {2, 1, 5} 
        };
        int[][] blah = new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 0} 
        };
        Board test = new Board(bluh);
        Board tets = new Board(blah);
        System.out.println("Dimension: " + test.dimension());
        System.out.println("Hamming distance: " + test.hamming());
        System.out.println("Manhattan distance: " + test.manhattan());
        System.out.println("Is goal board? " + test.isGoal());
        System.out.println("Hamming distance: " + tets.hamming());
        System.out.println("Manhattan distance: " + tets.manhattan());
        System.out.println("Is goal board? " + tets.isGoal());      
        System.out.println(test);
        System.out.println(tets);
        System.out.println(test.twin());
    }

}
