import java.io.StringReader;
import java.util.Scanner;

public class AnimalGuess{

    public static boolean checkYes(String input){
        if (input.contains("y") || (input.contains("yes"))){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean checkNo(String input){
        if (input.contains("n") || (input.contains("no"))){
            return true;
        }
        else{
            return false;
        }
    }

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
        System.err.println("Think of an animal.\n" + "I'll try to guess it.\n" + tree.getData().replaceFirst(" ", ""));
        String input = sc.next().toLowerCase();
        tree = (DecisionTree<String>) tree.getLeft();
        while(play){
            if (AnimalGuess.checkYes(input)) {
                if ((tree.isBranch())){
                    System.err.println(tree.getData());
                    input = sc.next().toLowerCase();
                }
                else{
                    System.err.println("Is it a " + tree.getData() + "?");
                    input = sc.next().toLowerCase();
                    if (AnimalGuess.checkYes(input)){
                        System.err.println("I guessed it!\n" + "Play again?");
                        input = sc.next().toLowerCase();
                        if (AnimalGuess.checkNo(input)){
                            play = false;
                        }
                    }
                    else if (AnimalGuess.checkNo(input)){
                        System.err.println("I got it wrong.\n" + "Please help me to learn.\n" + "What was your animal?");
                        input = sc.next().toLowerCase();
                    }
                }
            }
            tree = (DecisionTree<String>) tree.getLeft();
        }
        sc.close();
    }
}