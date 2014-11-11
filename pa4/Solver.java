import java.util.Comparator;

public class Solver {
    private int totalMoves = 0;

    private class SearchNode {
        private Board board;
        private int numMoves;
        private int priority;
        private int manPriority;
        private SearchNode prevNode;

        public SearchNode(Board b, int moves, SearchNode prev) {
            board = b;
            numMoves = moves;
            priority = b.hamming() + moves;
            manPriority = b.manhattan() + moves;
            prevNode = prev;
        }

        private Iterable<Board> getNeighbors() {
            return board.neighbors();
        }

        private boolean isLastBoard(Board b) {
            if(prevNode == null) return false;
            else return b.equals(prevNode.board);
        }

        private boolean isGoal() {
            return board.isGoal();
        }

        public String toString() {
            return board.toString() + "Ham Priority = " + priority + " Man Priority = " + manPriority;
        }

    }

    public Solver(Board initial) { //find a solution to the intial board
        MinPQ<SearchNode> search = new MinPQ(MANHATTAN);   //search queue
        SearchNode init = new SearchNode(initial, totalMoves, null);
        search.insert(init);
           
        while(true) { 
            SearchNode minPriority = search.delMin();
            System.out.println("Pop: ");
            System.out.println(minPriority);
            System.out.println("Min Priority numMoves: " + minPriority.numMoves);
            totalMoves++;
            for(Board b: minPriority.getNeighbors()) {
                   if(!minPriority.isLastBoard(b)) search.insert(new SearchNode(b, totalMoves, minPriority)); 
            }

            if(minPriority.isGoal()) {
                System.out.println("Goal! " + minPriority);
                break;
            }
        }
    } 

    private final Comparator<SearchNode> HAMMING = new HamComparator();

    private class HamComparator implements Comparator<SearchNode> {
        public int compare(SearchNode s1, SearchNode s2) {
            if(s1.priority < s2.priority) return -1;
            if(s1.priority > s2.priority) return 1;
            return 0;
        }
    }

    private final Comparator<SearchNode> MANHATTAN = new ManComparator();

    private class ManComparator implements Comparator<SearchNode> {
        public int compare(SearchNode s1, SearchNode s2) {
            if(s1.manPriority < s2.manPriority) return -1;
            if(s1.manPriority > s2.manPriority) return 1;
            return 0;
        }
    }
    //public boolean isSolvable() {}  // is the initial board solvable

    //public int moves() {}           // min number of moves to solve init. board

    //public Iterable<Board> solution()   // sequence of boards in a shortest solution

    public static void main(String[] args) {  // solve a slider puzzle
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
    }

}
