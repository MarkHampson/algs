

public class Subset{

    public static void main(String[] args){
        if(args.length != 1) throw new IllegalArgumentException("Needs one argument");
        RandomizedQueue<String> stdinStrings = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]); //number of strings to print

        //Read strings from StdIn
        while(!StdIn.isEmpty()){
            stdinStrings.enqueue(StdIn.readString());
        } 

        for(int i = 0; i<k; i++) System.out.println(stdinStrings.dequeue());

    }

    
}
