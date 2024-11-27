import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AnimalGuess{

    /**
     * Traverse the tree in breath first order, add the path and the value of each node to an arraylist of strings and return the
     * arraylist (to rewrite the file)
     * @param tree the tree to be traversed
     * @return the value (including the path) of every node in the tree
     */
    public static ArrayList<String> breadthFirstTraversal(DecisionTree<String> tree) {
        Queue<DecisionTree<String>> queue = new LinkedList<DecisionTree<String>>();
        Queue<String> path = new LinkedList<String>();
        ArrayList<String> nodeValue = new ArrayList<> ();
        queue.add(tree);
        path.add("");
        nodeValue.add(tree.getData());
        while (!queue.isEmpty()) {
            DecisionTree<String> temp = queue.poll();
            String s = path.poll();
            nodeValue.add(s + " " + temp.getData());
            if (temp.getLeft() != null) {
                queue.add((DecisionTree<String>) temp.getLeft());
                path.add(s + "Y");
            }
            if (temp.getRight() != null) {
                queue.add((DecisionTree<String>) temp.getRight());
                path.add(s + "N");
            }
        }
        nodeValue.remove(1);
        return nodeValue;
    }

    /**
     * Write the tree to the file
     * @param tree the tree to be written
     * @param fileName the name of the file to be written in
     */
    public static void writeFile(DecisionTree<String> tree, String fileName){
        try {
            PrintWriter w = new PrintWriter(new FileWriter(fileName));
            ArrayList<String> nodes = AnimalGuess.breadthFirstTraversal(tree);
            w.println(nodes.removeFirst());
            for(String s: nodes){
                w.println(s);
            }
            w.close();
        } catch (IOException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1); 
        }
    }

    /**
     * Check if input answer is Yes/ Y (or the lowercase)
     * @param input input from player
     * @return if input answer is yes
     */
    public static boolean checkYes(String input){
        if (input.equals("y") || (input.equals("yes"))){
            return true;
        }
        else{
            //throw new RuntimeException("Thank you so much for your response! However, you should enter either Yes/ Y for a positive answer, or No/ N for a negative answer!");
            return false;
        }
    }

    /**
     * Check if input answer is No/ N (or the lowercase)
     * @param input input from player
     * @return if input answer is no
     */
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
                            AnimalGuess.writeFile(pointer, inputLine);
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
                            AnimalGuess.writeFile(pointer, inputLine);
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
                            AnimalGuess.writeFile(pointer, inputLine);
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
                            AnimalGuess.writeFile(pointer, inputLine);
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