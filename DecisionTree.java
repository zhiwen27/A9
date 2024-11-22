public class DecisionTree<E> extends BinaryTree<String>{

    /** The value at this node */
    private String data;

    /** Left child of this node */
    private BinaryTree<String> left;

    /** Right child of this node */
    private BinaryTree<String> right;

    /** This constructor creates a leaf node */
    public DecisionTree(String data){
        super(data);
    }

    /** This constructor creates a branch node */
    public DecisionTree(String data, BinaryTree<String> left, BinaryTree<String> right){
        super(data, left, right);
    }

    /** This constructor creates a deep copy of the entire tree structure */ 
    public DecisionTree(BinaryTree<String> tree){
        super(tree);
    }

    /** Accessor for node data */
    public String getData() {
        return data;
    }

    /** Accessor for left child */
    public BinaryTree<String> getLeft() {
        return left;
    }

    /** Accessor for right child */
    public BinaryTree<String> getRight() {
        return right;
    }

    /** Manipulator for node data */
    public void setData(String data) {
        this.data = data;
    }

    /** Manipulator for left child */
    public void setLeft(BinaryTree<String> left) {
        this.left = left;
    }

    /** Manipulator for right child */
    public void setRight(BinaryTree<String> right) {
        this.right = right;
    }

    /** Determines whether a tree is empty */
    public static boolean isEmpty(BinaryTree node) {
        return (node == null);
    }

    public BinaryTree<String> followPath(BinaryTree<String> node, String input){
        BinaryTree<String> returnNode = node;
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
}