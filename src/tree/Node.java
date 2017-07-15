package tree;

public class Node {
    private int value;
    private String type;
    private Node left;
    private Node right;

    public Node() {
        this.value = 0;
        this.type = "";
        this.left = null;
        this.right = null;
    }

    public Node(int value, String type) {
        this.value = value;
        this.type = type;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public String getType() { return type;}

    public Node(int value, String type, Node left, Node right) {
        super();
        this.value = value;
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}