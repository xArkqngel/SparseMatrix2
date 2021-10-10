package doubleList;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class      MyDoubleNode<T> {
    protected T info;
    protected MyDoubleNode<T> next;
    protected MyDoubleNode<T> prior;

    /**
     * Constructor
     * @param info
     */
    public MyDoubleNode(T info ){
        this.info = info;
        this.next = null;
    }

    /**
     * Constructor
     * @param info
     * @param next
     * @param prior
     */
    public MyDoubleNode(T info ,MyDoubleNode<T> next,MyDoubleNode<T> prior){
        this.info = info;
        this.next = next;
        this.prior = prior;
    }

    public T getInfo() {
        return info;
    }
}
