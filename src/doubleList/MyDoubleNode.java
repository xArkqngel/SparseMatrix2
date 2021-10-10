package doubleList;

public class MyDoubleNode<T> {
    protected T info;
    protected MyDoubleNode<T> next;
    protected MyDoubleNode<T> prior;

    public MyDoubleNode(T info ){
        this.info = info;
        this.next = null;
    }
    public MyDoubleNode(T info ,MyDoubleNode<T> next,MyDoubleNode<T> prior){
        this.info = info;
        this.next = next;
        this.prior = prior;
    }

    public T getInfo() {
        return info;
    }
}
