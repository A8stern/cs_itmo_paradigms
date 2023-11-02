package expression;

import java.util.Objects;

public class Const implements InterfaceCreate{
    private final int constant;

    public Const(int constant){
        this.constant = constant;
    }

    @Override
    public int evaluate(int n) {
        return this.constant;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.constant;
    }

    @Override
    public String toString() {
        return String.valueOf(this.constant);
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        return (this.getClass() == o.getClass() && this.constant == ((Const) o).constant);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.constant);
    }
}
