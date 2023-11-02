package queue;

// length of array - n
// Model: a[1]...a[n]
// Inv: n >= 0 && for all i=1..n: a[i] != null


public class ArrayQueueModule {
    private static int head = 0;
    private static int tail = -1;
    private static Object[] elements = new Object[10];

    //Pre: element != null
    //Post: elements[n'] = element && n' = n + 1
    public static void enqueue(Object element) {
        ensureCapacity(size());
        tail = (tail + 1) % elements.length;
        elements[tail] = element;
    }

    // Pre: true
    // Post: n' = n
    private static void ensureCapacity(int capacity) {
        //System.err.println(Arrays.toString(elements) +" eC");
        if (capacity >= elements.length - 1) {
            Object[] new_arr = new Object[2 * elements.length];
            int index = 0;
            if (head > tail) {
                for (int i = head; i < elements.length; i++) {
                    new_arr[index] = elements[i];
                    index++;
                }
                for (int i = 0; i <= tail; i++){
                    new_arr[index] = elements[i];
                    index++;
                }
            }else {
                for (int i = head; i <= tail; i++){
                    new_arr[index] = elements[i];
                    index++;
                }
            }
            head = 0;
            tail = index - 1;
            elements = new_arr;
        }
    }

    // Pre: n > 0
    // Post: R = a[0] && n' = n
    public static Object element() {
        //System.err.println(Arrays.toString(elements) +" el");
        assert size() > 0;
        if (head == elements.length){
            return elements[head - 1];
        }
        return elements[head];
    }

    // Pre: n > 0
    // Post: n' = n - 1 && a[n] = null
    public static Object dequeue() {
        //System.err.println(Arrays.toString(elements) +" de");
        assert size() > 0;
        Object x = elements[head];
        head = (head + 1) % elements.length;
        if (head == 0 && tail == elements.length - 1){
            clear();
        }
        return x;
    }

    // Pre: true
    // Post: R = n && n' = n

    public static int size() {
        if (tail == -1){
            return tail - head + 1;
        }
        else if (head > tail + 1){
            return elements.length - head + tail + 1;
        } else {
            return tail - head + 1;
        }
    }

    // Pre: true
    // Post: R = (n = 0) && n' = n
    public static boolean isEmpty() {
        return size() == 0;
    }

    // Pre: true
    // Post: n = 0
    public static void clear(){
        head = 0;
        tail = -1;
        elements = new Object[10];
    }

    // Pre: true
    // Post: R = "[a[1], a[2], ... , a[n]]" && n' = n
    public static String toStr(){
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (!isEmpty()) {
            result.append(elements[head]);
        }
        for (int i = 1; i < size(); i++){
            result.append(", ").append(elements[(i + head) % elements.length]);
        }
        result.append("]");
        return result.toString();
    }
}
