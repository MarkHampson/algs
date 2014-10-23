import java.util.Comparator;

public class Solver {
    private int totalMoves = 0;
    private Board prevBoard = null;
    private Board prevBoardAlt = null;

    private class SearchNode {
        private Board board;
        private int numMoves;
        private int priority;

        public SearchNode(Board b, int moves) {
            board = b;
            numMoves = moves;
            priority = b.hamming() + moves;
        }

        public String toString() {
            return board.toString() + "Priority = " + priority;
        }

    }

    public Solver(Board initial) { //find a solution to the intial board
        MinPQ<SearchNode> search = new MinPQ(HAMMING);   //search queue
        MinPQ<SearchNode> altSearch = new MinPQ(HAMMING); //alternate search queue using twin
        SearchNode init = new SearchNode(initial, totalMoves);
        SearchNode initAlt = new SearchNode(initial.twin(), totalMoves);
        search.insert(init);
        altSearch.insert(initAlt);
//        for(Board b: search) System.out.println(b);
//        for(Board b: altSearch) System.out.println(b);
//        for(Board b: search.min().board.neighbors()) System.out.println(b);
        int count = 0;
        while( (count < 1)) {
            SearchNode minPriority = search.delMin();
            //System.out.println(minPriority.board);
            //System.out.println("Priority = " + minPriority.priority);
            System.out.println(minPriority);
            for(Board b: minPriority.board.neighbors()) {
                   if(!b.equals(prevBoard)) search.insert(new SearchNode(b, totalMoves)); 
            }
            for(SearchNode s: search) System.out.println(s);

            prevBoard = minPriority.board;
//            SearchNode minPriorityAlt = altSearch.delMin();
//            for(Board b: minPriorityAlt.board.neighbors()) {
//                   if(!b.equals(prevBoardAlt)) altSearch.insert(new SearchNode(b, totalMoves));
//            }
//            prevBoardAlt = minPriorityAlt.board;
            totalMoves++;
            count++;
        }

        //System.out.println(search.min().board);
        //System.out.println("Priority = " + search.min().priority);
        //System.out.println(altSearch.min().board);
    } 

    public final Comparator<SearchNode> HAMMING = new HamComparator();

    private class HamComparator implements Comparator<SearchNode> {
        public int compare(SearchNode s1, SearchNode s2) {
            if(s1.priority < s2.priority) return -1;
            if(s1.priority > s2.priority) return 1;
            return 0;
        }
    }
    //public boolean isSolvable() {}  // is the initial board solvable

    //public int moves() {}           // min number of moves to solve init. board

    //public Iterable<Board> solution()   // sequence of boards in a shortest solution

    public static void main(String[] args) {  // solve a slider puzzle
        int[][] test = new int[][] {
            new int[] {1, 2, 5},
            new int[] {4, 0, 6},
            new int[] {7, 8, 3}
        };
        Board board = new Board(test);
        Solver solve = new Solver(board);
    }

}
