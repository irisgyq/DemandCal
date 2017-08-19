package calculator;

public class Token {
    private double value;
    private String type;

    public Token(){
        this.value = 0;
        this.type = "";
    }

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

    public Token getToken(){
        return this;
    }

    public void setToken(double value, String type){
        this.value = value;
        this.type = type;
    }
}