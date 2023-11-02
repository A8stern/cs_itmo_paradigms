package queue;

// length of array - n
// Model: a[1]...a[n]
// Inv: n >= 0 && for all i=1..n: a[i] != null
public class LinkedQueue extends AbstractQueue{
    private Node head;
    private Node tail;



    //Pre: element != null
    //Post: elements[n'] = element && n' = n + 1
    public void enqueueImpl(LinkedQueue this, Object element) {
        Node elem = head;
        head = new Node(element, null);
        if (size == 0){
            tail = head;
        }
        else {
            elem.next = head;
        }
        System.out.println(tail.value + " " + head.value);
    }

    // Pre: n > 0
    // Post: R = a[0] && n' = n
    public Object elementImpl() {
        return tail.value;
    }

    // Pre: n > 0
    // Post: n' = n - 1 && a[n] = null
    public Object dequeueImpl() {
        Node element = tail;
        tail = tail.next;
        return element.value;
    }


    // Pre: true
    // Post: n = 0
    public void clearImpl(){
        head = null;
        tail = null;
    }
    // Pre: true
    // Post: R = "[a[1], a[2], ... , a[n]]" && n' = n
    public String toStrImpl(){
        StringBuilder result = new StringBuilder();
        Node vyv = tail;
        if (!isEmpty()) {
            result.append(vyv.value);
        }
        for (int i = 1; i < size(); i++){
            vyv = vyv.next;
            result.append(", ").append(vyv.value);
        }
        return result.toString();
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}
