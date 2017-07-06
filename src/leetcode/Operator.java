package leetcode;

public enum Operator {
    ADD(43), SUB(45), MUL(42), DIV(47), LEFT_BRACE(40), RIGHT_BRACE(41);

    private int value;

    private Operator(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}