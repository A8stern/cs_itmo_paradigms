package queue;

// length of array - n
// Model: a[1]...a[n]
// Inv: n >= 0 && for all i=1..n: a[i] != null

public class ArrayQueueADT {

    private int head = 0;
    private int tail = -1;
    private Object[] elements = new Object[10];

    //Pre: element != null
    //Post: elements[n'] = element && n' = n + 1
    public static void enqueue(ArrayQueueADT queue, Object element) {
        //System.err.println(Arrays.toString(queue.elements) +" en " + queue.head + " " + queue.tail);
        ensureCapacity(queue, size(queue));
        queue.tail = (queue.tail + 1) % queue.elements.length;
        queue.elements[queue.tail] = element;
    }

    // Pre: true
    // Post: n' = n
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity >= queue.elements.length - 1) {
            Object[] new_arr = new Object[2 * queue.elements.length];
            int index = 0;
            if (queue.head > queue.tail) {
                for (int i = queue.head; i < queue.elements.length; i++) {
                    new_arr[index] = queue.elements[i];
                    index++;
                }
                for (int i = 0; i <= queue.tail; i++){
                    new_arr[index] = queue.elements[i];
                    index++;
                }
            }else {
                for (int i = queue.head; i <= queue.tail; i++){
                    new_arr[index] = queue.elements[i];
                    index++;
                }
            }
            queue.head = 0;
            queue.tail = index - 1;
            queue.elements = new_arr;
        }
    }

    // Pre: n > 0
    // Post: R = a[0] && n' = n

    public static Object element(ArrayQueueADT queue) {
        assert size(queue) > 0;
        if (queue.head == queue.elements.length){
            return queue.elements[queue.head - 1];
        }
        return queue.elements[queue.head];
    }

    // Pre: n > 0
    // Post: n' = n - 1 && a[n] = null
    public static Object dequeue(ArrayQueueADT queue) {
        //System.err.println(Arrays.toString(queue.elements) +" de " + queue.head + " " + queue.tail);
        assert size(queue) > 0;
        Object x = queue.elements[queue.head];
        queue.head = (queue.head + 1) % queue.elements.length;
        if (queue.head == 0 && queue.tail == queue.elements.length - 1){
            clear(queue);
        }
        return x;
    }

    // Pre: true
    // Post: R = n && n' = n
    public static int size(ArrayQueueADT queue) {
        //System.err.println(Arrays.toString(queue.elements) +" si " + queue.head + " " + queue.tail);
        if (queue.tail == -1){
            return queue.tail - queue.head + 1;
        }
        else if (queue.head > queue.tail + 1){
            return queue.elements.length - queue.head + queue.tail + 1;
        } else {
            return queue.tail - queue.head + 1;
        }
    }

    // Pre: true
    // Post: R = (n = 0) && n' = n

    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    // Pre: true
    // Post: n = 0
    public static void clear(ArrayQueueADT queue){
        //System.err.println(Arrays.toString(queue.elements) +" cl " + queue.head + " " + queue.tail);
        for (int i = 0; i < size(queue); i++){
            queue.elements[i] = null;
        }
        queue.head = 0;
        queue.tail = -1;
        queue.elements = new Object[10];
    }
    // Pre: true
    // Post: R = "[a[1], a[2], ... , a[n]]" && n' = n
    public static String toStr(ArrayQueueADT queue){
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (!isEmpty(queue)) {
            result.append(queue.elements[queue.head]);
        }
        for (int i = 1; i < size(queue); i++){
            result.append(", ").append(queue.elements[(i + queue.head) % queue.elements.length]);
        }
        result.append("]");
        return result.toString();
    }
}
