package queue;

public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue, String prefix){
        for (int i = 0; i < 10; i++) {
            queue.enqueue(prefix + i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            queue.dequeue();
            System.out.println(queue.toStr());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue_1 = new ArrayQueue();
        ArrayQueue queue_2 = new ArrayQueue();
        fill(queue_1, "s1_");
        fill(queue_2, "s2_");
        dump(queue_1);
        dump(queue_2);
    }
}
