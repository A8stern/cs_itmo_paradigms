package expression;

public class LiteralsAndNums<T>{
    T value;
    public Literals type;
    public LiteralsAndNums(T value, Literals literals){
        this.value = value;
        this.type = literals;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public Literals getType() {
        return this.type;
    }
}
