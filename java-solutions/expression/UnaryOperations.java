package expression;

import java.util.Objects;

public abstract class UnaryOperations implements InterfaceCreate{
    protected final String operanda;

    protected final InterfaceCreate value;


    protected UnaryOperations(String operanda, InterfaceCreate value) {
        this.operanda = operanda;
        this.value = value;
    }

    @Override
    public int evaluate(int n) {
        return function(value.evaluate(n));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return function(value.evaluate(x, y, z));
    }

    public int function(int value){
        return 0;
    }

    @Override
    public String toString() { return operanda + "(" + value.toString() + ")";}

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (this.getClass() == o.getClass()){
            if (value.equals(((UnaryOperations) o).value)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.operanda, this.value);
    }
}

