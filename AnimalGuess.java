import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalGuess{

    public static ArrayList<String> breadthFirstTraversal(DecisionTree<String> tree, int level, ArrayList<String> nodes) {
        if (tree == null)
            return nodes;
        if (level == 1){
            nodes.add(tree.getData());
            return nodes;
        }
        else if (level > 1) {
            breadthFirstTraversal((DecisionTree<String>)tree.getLeft(), level - 1, nodes);
            breadthFirstTraversal((DecisionTree<String>)tree.getRight(), level - 1, nodes);
        }
        return nodes;
    }

    public static void writeFile(DecisionTree<String> tree){
        try {
            PrintWriter w = new PrintWriter(new FileWriter("Test.txt"));
            //w.print(" " + tree.getData());
            ArrayList<String> nodes = new ArrayList<>();
            AnimalGuess.breadthFirstTraversal(tree, tree.height(), nodes);
            for(String s: nodes){
                System.err.println(s);
            }
            w.close();
        } catch (IOException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1); 
        }
    }

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
                inputLine = sc.nextLine();
            }
            sc.close();
        }
        Boolean playOut = true;
        Scanner sc = new Scanner(System.in);
        
        while(playOut){
            DecisionTree<String> tree = DecisionTree.readFile(inputLine);
            DecisionTree<String> pointer = tree;
            System.err.println("Think of an animal.\n" + "I'll try to guess it.\n" + tree.getData().replaceFirst(" ", ""));
            String input = sc.nextLine().toLowerCase();
            Boolean playIn = true;
            while(playIn){
                if ((AnimalGuess.checkYes(input)) && (playIn)) {
                    DecisionTree<String> treeTemp = (DecisionTree<String>) tree.getLeft();
                    if ((treeTemp.isBranch())){
                        System.err.println(treeTemp.getData());
                        input = sc.nextLine().toLowerCase();
                    }
                    else{
                        System.err.println("Is it a " + treeTemp.getData() + "?");
                        input = sc.nextLine().toLowerCase();
                        if (AnimalGuess.checkYes(input)){
                            System.err.println("I guessed it!\n" + "Play again?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                break;
                            }
                            else{
                                playIn = false;
                                playOut = false;
                            }
                        }
                        else if (AnimalGuess.checkNo(input)){
                            System.err.println("I got it wrong.\n" + "Please help me to learn.\n" + "What was your animal?");
                            input = sc.nextLine();
                            String nodeName = input;
                            System.err.println("Type a yes or no question that would distinguish between a " + nodeName + " and a " + treeTemp.getData() + "?");
                            input = sc.nextLine();
                            DecisionTree<String> addNode = new DecisionTree<>(input);
                            System.err.println("Would you answer yes to this question for the " + nodeName + "?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                addNode.setLeft(new DecisionTree<String>(nodeName));
                                addNode.setRight(new DecisionTree<String>(treeTemp));
                                tree.setLeft(addNode);;
                            }
                            else{
                                addNode.setLeft(new DecisionTree<String>(treeTemp));
                                addNode.setRight(new DecisionTree<String>(nodeName));
                                tree.setLeft(addNode);;
                            }
                            AnimalGuess.writeFile(pointer);
                            System.err.println("Play again?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                break;
                            }
                            else{
                                playIn = false;
                                playOut = false;
                            }
                        }
                    }
                    tree = (DecisionTree<String>) tree.getLeft();
                }
                else if ((AnimalGuess.checkNo(input)) && (playIn)){
                    DecisionTree<String> treeTemp = (DecisionTree<String>) tree.getRight();
                    if ((treeTemp.isBranch())){
                        System.err.println(treeTemp.getData());
                        input = sc.nextLine().toLowerCase();
                    }
                    else{
                        System.err.println("Is it a " + treeTemp.getData() + "?");
                        input = sc.nextLine().toLowerCase();
                        if (AnimalGuess.checkYes(input)){
                            System.err.println("I guessed it!\n" + "Play again?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                break;
                            }
                            else{
                                playIn = false;
                                playOut = false;
                            }
                        }
                        else if (AnimalGuess.checkNo(input)){
                            System.err.println("I got it wrong.\n" + "Please help me to learn.\n" + "What was your animal?");
                            input = sc.nextLine();
                            String nodeName = input;
                            System.err.println("Type a yes or no question that would distinguish between a " + nodeName + " and a " + treeTemp.getData() + "?");
                            input = sc.nextLine();
                            DecisionTree<String> addNode = new DecisionTree<>(input);
                            System.err.println("Would you answer yes to this question for the " + nodeName + "?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                addNode.setLeft(new DecisionTree<String>(nodeName));
                                addNode.setRight(new DecisionTree<String>(treeTemp));
                                tree.setRight(addNode);;
                            }
                            else{
                                addNode.setLeft(new DecisionTree<String>(treeTemp));
                                addNode.setRight(new DecisionTree<String>(nodeName));
                                tree.setRight(addNode);;
                            }
                            AnimalGuess.writeFile(pointer);
                            System.err.println("Play again?");
                            input = sc.nextLine().toLowerCase();
                            if (AnimalGuess.checkYes(input)){
                                break;
                            }
                            else{
                                playIn = false;
                                playOut = false;
                            }
                        }
                    }
                    tree = (DecisionTree<String>) tree.getRight();
                }
            }
        }
        sc.close();
    }
}