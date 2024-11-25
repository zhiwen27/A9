import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DecisionTree<E> extends BinaryTree<E>{

    /** This constructor creates a leaf node */
    public DecisionTree(E data){
        super(data);
    }

    /** This constructor creates a branch node */
    public DecisionTree(E data, DecisionTree<E> left, DecisionTree<E> right){
        super(data, left, right);
    }

    /** This constructor creates a deep copy of the entire tree structure */ 
    public DecisionTree(DecisionTree<E> tree){
        super(tree);
    }

    public static DecisionTree<String> followPath(DecisionTree<String> node, String input){
        DecisionTree<String> returnNode = node;
        for(int i = 0; i < input.length(); i++){
            if (input.charAt(i) == 'Y'){
                returnNode = (DecisionTree<String>) returnNode.getLeft();
            }
            else{
                returnNode = (DecisionTree<String>) returnNode.getRight();
            }
        }
        return returnNode;
    }

    public static DecisionTree<String> readFile(String name){
        Scanner file = null;
        try {
          file = new Scanner(new File(name));
        } catch (FileNotFoundException e) {
          System.err.println("Cannot locate file.");
          System.exit(-1);
        }
        ArrayList<String> inputLine = new ArrayList<>();
        while (file.hasNextLine()){
            inputLine.add(file.nextLine());
        }
        DecisionTree<String> initialTree = new DecisionTree<>(inputLine.get(0));
        DecisionTree<String> pointer = initialTree;
        for(int i = 1; i < inputLine.size(); i++){
            String[] storage = inputLine.get(i).split(" ",2);
            String temp = storage[0];
            initialTree = pointer;
            for (int j = 0; j < temp.length() - 1; j++){
                if (temp.charAt(j) == 'Y'){
                    if (initialTree.getLeft() != null){
                        initialTree = (DecisionTree<String>) initialTree.getLeft();
                    }
                }
                else{
                    if (initialTree.getRight() != null){
                        initialTree = (DecisionTree<String>) initialTree.getRight();
                    }
                }
            }
            if (temp.charAt(temp.length() - 1) == 'Y'){
                initialTree.setLeft(new DecisionTree<>(storage[1]));
            }
            else{
                initialTree.setRight(new DecisionTree<>(storage[1]));
            }
        }
        return pointer;
    }

    // public static void main(String[] args) {
    //     String inputLine = "";
    //     if (args.length == 0) {
    //         System.err.println("Usage:  java Postfix <expr>");
    //     } else {
    //         Scanner sc = new Scanner(new StringReader(args[0]));
    //         while (sc.hasNext()) {
    //             inputLine = sc.next();
    //         }
    //         sc.close();
    //     }
    //     DecisionTree.readFile(inputLine);
    // }
}