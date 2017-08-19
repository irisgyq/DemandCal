package calculator;

public enum Operator {
    ADD(43), SUB(45), MUL(42), DIV(47), LEFT_BRACE(40), RIGHT_BRACE(41), POW(94), MOD(37), SQRT(128), PI(129), LOG(130),E(131), COS(132), SIN(133), TAN(134),COMMA(135);

    private int value;

    private Operator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}