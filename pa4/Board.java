
public class Board {

    public Board(int[][] blocks) {} //NxN array of blocks [i][j] = row col

    public int dimension() {}  //board dimension N

    public int hammming() {}  // numer of blocks out of place

    public int manhattan() {} // sum of manhattan distances between blocks and goal

    public boolean isGoal() {} // is this board the goal board?

    public Board twin() {}  // a board obtained by exch. two adj. blocks in same row

    public boolean equals(Object y) {}  // does this board equal y?

    public Iterable<Board> neighbors() {} //all neighboring boards

    public String toString()    //string representation of the board

}
