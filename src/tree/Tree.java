package tree;

public class Tree<T> {

    private T root;

    public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public T getRoot() {
        return this.root;
    }

    public void setRoot(T node) {
        root = node;
    }

}