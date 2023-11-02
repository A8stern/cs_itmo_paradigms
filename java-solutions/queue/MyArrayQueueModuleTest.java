package queue;

public class MyArrayQueueModuleTest {
    public static void fill(){
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue("Q1_" + i);
            System.out.println(ArrayQueueModule.element());
        }
    }

    public static void fill_2(){
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue("Q2_" + i);
            System.out.println(ArrayQueueModule.element());
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            ArrayQueueModule.dequeue();
            System.out.println(ArrayQueueModule.element());
            System.out.println(ArrayQueueModule.toStr());
        }
    }

    public static void main(String[] args) {
        System.out.println(ArrayQueueModule.size());
        fill();
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.isEmpty());
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.isEmpty());
        System.out.println(ArrayQueueModule.toStr());
        fill_2();
        dump();
    }
}
