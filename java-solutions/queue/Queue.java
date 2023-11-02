package queue;

// length of array - n
// Model: a[1]...a[n]
// Inv: n >= 0 && for all i=1..n: a[i] != null
public interface Queue {
    //Pre: element != null
    //Post: elements[n'] = element && n' = n + 1
    void enqueue(Object element);

    // Pre: n > 0
    // Post: R = a[0] && n' = n
    Object element();

    // Pre: n > 0
    // Post: n' = n - 1 && a[n] = null
    Object dequeue();

    // Pre: true
    // Post: R = n && n' = n
    int size();

    // Pre: true
    // Post: R = (n = 0) && n' = n
    boolean isEmpty();
    // Pre: true
    // Post: n = 0
    void clear();

    // Pre: true
    // Post: R = "[a[1], a[2], ... , a[n]]" && n' = n
    String toStr();

    // Pre: element != null
    // Post: R = x && for all a[1]...a[n]
    //       if n[i] == element: x++
    int count(Object element);
}
