package queue;

// length of array - n
// Model: a[1]...a[n]
// Inv: n >= 0 && for all i=1..n: a[i] != null

public class ArrayQueue extends AbstractQueue{

    private int head = 0;
    private Object[] elements = new Object[10];

    //Pre: element != null
    //Post: elements[n'] = element && n' = n + 1
    public void enqueueImpl(Object element) {
        //System.err.println(size + " 1 " + toStrImpl() + " 2 " + element + " 3 e");
        ensureCapacity(size);
        int tail = (size() + head) % elements.length;
        elements[tail] = element;
        //System.err.println(size + " 1 " + toStrImpl() + " 2 e2");
    }

    // Pre: true
    // Post: n' = n
    private void ensureCapacity(int capacity) {
        if (capacity >= elements.length - 1) {
            Object[] new_arr = new Object[2 * elements.length];
            if (head + size() > elements.length) {
                System.arraycopy(elements, head, new_arr, 0, elements.length - head);
                System.arraycopy(elements, 0, new_arr, elements.length - head, size() - elements.length + head);
            }else {
                System.arraycopy(elements, head, new_arr, 0, size());
            }
            head = 0;
            elements = new_arr;
        }
    }

    // Pre: n > 0
    // Post: R = a[0] && n' = n
    public Object elementImpl() {
        assert size() > 0;
        if (head == elements.length){
            return elements[head - 1];
        }
        return elements[head];
    }

    // Pre: n > 0
    // Post: n' = n - 1 && a[n] = null
    public Object dequeueImpl() {
        //System.err.println(size + " " + toStrImpl() + " d");
        assert size() > 0;
        Object x = elements[head];
        head = (head + 1) % elements.length;
        return x;
    }
    // Pre: true
    // Post: n = 0
    public void clearImpl(){
        //System.err.println(size + " " + toStrImpl() + " c");
        head = 0;
        elements = new Object[10];
    }
    // Pre: true
    // Post: R = "[a[1], a[2], ... , a[n]]" && n' = n
    public String toStrImpl(){
        StringBuilder result = new StringBuilder();
        if (!isEmpty()) {
            result.append(elements[head]);
        }
        for (int i = 1; i < size(); i++){
            result.append(", ").append(elements[(i + head) % elements.length]);
        }
        return result.toString();
    }

}
