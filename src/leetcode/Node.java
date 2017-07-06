package leetcode;

public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(){
        this.value = 0;
        this.left = null;
        this.right = null;
    }

    Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public Class getName() {
        return getClass();
    }

    public Node(int value, Node left, Node right){
        super();
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }
}