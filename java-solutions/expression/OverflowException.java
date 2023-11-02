package expression;

public class OverflowException extends RuntimeException{

    public OverflowException(String a){super("OverflowException: " + a);}
}
