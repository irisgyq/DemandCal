package calculator;

public class Token {
    private double value;
    private String type;

    public Token(double value, String type){
        this.value = value;
        this.type = type;
    }

    public double getValue(){
        return this.value;
    }

    public String getType() {
        return this.type;
    }
}