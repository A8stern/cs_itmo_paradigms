package queue;

public class MyArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue, String prefix){
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, prefix + i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            ArrayQueueADT.dequeue(queue);
            System.out.println(ArrayQueueADT.toStr(queue));
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue_1 = new ArrayQueueADT();
        ArrayQueueADT queue_2 = new ArrayQueueADT();
        fill(queue_1, "s1_");
        fill(queue_2, "s2_");
        dump(queue_1);
        dump(queue_2);
    }
}
