import java.io.StringReader;
import java.util.Scanner;

public class AnimalGuess{

    public static void main(String[] args) {
        String inputLine = "";
        if (args.length == 0) {
            System.err.println("Usage:  java Postfix <expr>");
        } else {
            Scanner sc = new Scanner(new StringReader(args[0]));
            while (sc.hasNext()) {
                inputLine = sc.next();
            }
            sc.close();
        }
        DecisionTree<String> tree = DecisionTree.readFile(inputLine);
        Boolean play = true;
        Scanner sc = new Scanner(System.in);
        while(play){
            System.err.println("Think of an animal.\n" + "I'll try to guess it.\n" + tree.getData());
            String input = sc.next().toLowerCase();
            if (input.contains("yes")){
                System.err.println("I guessed it!\n" + "Play again?");
                input = sc.next().toLowerCase();
                if (input.contains("no")){
                    play = false;
                }
            }
            else{
                System.err.println("I got it wrong.\n" + "Please help me to learn.\n" + "What was your animal?");
                input = sc.next().toLowerCase();
            }
        }
        sc.close();
    }
}