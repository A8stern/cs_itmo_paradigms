package expression;

import java.util.Objects;

public class Variable implements InterfaceCreate{
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int n) {
        return n;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (this.var) {
            case "x" -> x;
            case "-x" -> -x;
            case "y" -> y;
            case "-y" -> -y;
            case "z" -> z;
            case "-z" -> -z;
            default ->  throw new NullPointerException();
        };
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        return (this.getClass() == o.getClass()) && (this.var.equals(((Variable) o).var));
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.var);
    }
}