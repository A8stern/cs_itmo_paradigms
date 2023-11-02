package expression;

public interface GenericsInterface<T> {
    T add(T a, T b);

    T subtract(T a, T b);

    T multiply(T a, T b);

    T divide(T a, T b);

    T unaryMinus(T a);

    T parseFromInt(int a);

    T parseFromString(String a);
}
