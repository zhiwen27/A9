import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DecisionTree<E> extends BinaryTree<String>{

    /** The value at this node */
    private String data;

    /** Left child of this node */
    private DecisionTree<String> left;

    /** Right child of this node */
    private DecisionTree<String> right;

    /** This constructor creates a leaf node */
    public DecisionTree(String data){
        super(data);
    }

    /** This constructor creates a branch node */
    public DecisionTree(String data, DecisionTree<String> left, DecisionTree<String> right){
        super(data, left, right);
    }

    /** This constructor creates a deep copy of the entire tree structure */ 
    public DecisionTree(DecisionTree<String> tree){
        super(tree);
    }

    /** Accessor for node data */
    public String getData() {
        return data;
    }

    /** Accessor for left child */
    public DecisionTree<String> getLeft() {
        return left;
    }

    /** Accessor for right child */
    public DecisionTree<String> getRight() {
        return right;
    }

    /** Manipulator for node data */
    public void setData(String data) {
        this.data = data;
    }

    /** Manipulator for left child */
    public void setLeft(DecisionTree<String> left) {
        this.left = left;
    }

    /** Manipulator for right child */
    public void setRight(DecisionTree<String> right) {
        this.right = right;
    }

    /** Determines whether a tree is empty */
    public static boolean isEmpty(DecisionTree node) {
        return (node == null);
    }

    public static DecisionTree<String> followPath(DecisionTree<String> node, String input){
        DecisionTree<String> returnNode = node;
        for(int i = 0; i < input.length(); i++){
            if (input.charAt(i) == 'Y'){
                returnNode = returnNode.getLeft();
            }
            else{
                returnNode = returnNode.getRight();
            }
        }
        return returnNode;
    }

    public static void readFile(String name){
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
        DecisionTree<String> initialTree = new DecisionTree<>("");
        initialTree.setData(inputLine.get(0));
        for(int i = 1; i < inputLine.size(); i++){
            String[] storage = inputLine.get(i).split(" ");
            String temp = storage[0];
        //     //for (int j = 2; j < temp.length(); j++){
        // if (temp.charAt(1) == 'Y'){
        //     initialTree.setLeft(new DecisionTree<>(storage[1]));;
        // }
        // else{
        //     initialTree.setRight(new DecisionTree<>(storage[1]));
        // }
        //     //}
        //     initialTree.setLeft(new DecisionTree<>(storage[1]));
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
        DecisionTree.readFile(inputLine);
    }
}