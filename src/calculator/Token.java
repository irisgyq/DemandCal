package calculator;

public class Token {
    private int value;
    private String type;

    public Token(){
        this.value = 0;
        this.type = "";
    }

    public Token(int value, String type){
        this.value = value;
        this.type = type;
    }

    public int getValue(){
        return this.value;
    }

    public String getType() {
        return this.type;
    }

    public Token getToken(){
        return this;
    }

    public void setToken(int value, String type){
        this.value = value;
        this.type = type;
    }
}