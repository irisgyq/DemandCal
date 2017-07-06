package leetcode;

/**
 * Created by irisgyq on 2017/6/30.
 */
public class OperatorNode extends Node {

    public OperatorNode(int value) {
        super(value);
    }

    public OperatorNode(int value,  Node left, Node right) {
        super(value, left, right);
    }
}