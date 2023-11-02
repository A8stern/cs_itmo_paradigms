package queue;

public abstract class AbstractQueue implements Queue{
    protected int size;

    public void enqueue(Object element){
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element(){
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue(){
        Object x = dequeueImpl();
        size--;
        return x;
    }

    protected abstract Object dequeueImpl();

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    public String toStr(){
        return "[" + toStrImpl() + "]";
    }

    public int count(Object element){
        int num = 0;
        int sz = size();
        for (int i = 0; i < sz; i++){
            Object x = dequeue();
            if (x.equals(element)){
                num++;
            }
            enqueue(x);
        }
        return num;
    }

    protected abstract String toStrImpl();
}
