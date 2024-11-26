import java.io.StringReader;
import java.util.Scanner;

public class AnimalGuess{

    public static boolean checkYes(String input){
        if (input.equals("y") || (input.equals("yes"))){
            return true;
        }
        else{
            //throw new RuntimeException("Thank you so much for your response! However, you should enter either Yes/ Y for a positive answer, or No/ N for a negative answer!");
            return false;
        }
    }

    public static boolean checkNo(String input){
        if (input.equals("n") || (input.equals("no"))){
            return true;
        }
        else{
            //throw new RuntimeException("Thank you so much for your response! However, you should enter either Yes/ Y for a positive answer, or No/ N for a negative answer!");
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
        while(play){
            if (AnimalGuess.checkYes(input)) {
                tree = (DecisionTree<String>) tree.getLeft();
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
                        input = sc.next();
                        String nodeName = input;
                        System.err.println("Type a yes or no question that would distinguish between a " + nodeName + " and a " + tree.getData() + "?");
                        input = sc.next();
                        DecisionTree<String> addNode = new DecisionTree<>(input);
                        System.err.println("Would you answer yes to this question for the " + nodeName + "?");
                        input = sc.next().toLowerCase();
                        if (AnimalGuess.checkYes(input)){
                            addNode.setLeft(new DecisionTree<String>(nodeName));
                            addNode.setRight(new DecisionTree<String>(tree));
                        }
                        else{
                            addNode.setLeft(new DecisionTree<String>(tree));
                            addNode.setRight(new DecisionTree<String>(nodeName));
                        }
                        tree = addNode;
                    }
                }
            }
            else{
                tree = (DecisionTree<String>) tree.getRight();
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
                        input = sc.next();
                        String nodeName = input;
                        System.err.println("Type a yes or no question that would distinguish between a " + nodeName + " and a " + tree.getData() + "?");
                        input = sc.next();
                        DecisionTree<String> addNode = new DecisionTree<>(input);
                        System.err.println("Would you answer yes to this question for the " + nodeName + "?");
                        input = sc.next().toLowerCase();
                        if (AnimalGuess.checkYes(input)){
                            addNode.setLeft(new DecisionTree<String>(nodeName));
                            addNode.setRight(new DecisionTree<String>(tree));
                        }
                        else{
                            addNode.setLeft(new DecisionTree<String>(tree));
                            addNode.setRight(new DecisionTree<String>(nodeName));
                        }
                        //tree = addNode;
                    }
                }
            }
        }
        sc.close();
    }
}