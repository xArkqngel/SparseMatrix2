package doubleList;


/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class MyDoubleList<T> {
    protected  MyDoubleNode<T> first;
    protected  MyDoubleNode<T> last;

    /**
     * Constructor
     */
    public MyDoubleList() {
        this.first = null;
        this.last = null;
    }

    /**
     * Obtiene el primer elemento de la lista
     * @return
     */
    public MyDoubleNode<T> getFirst() {
        return first;
    }

    /**
     * Obtiene el siguiente elemento de un nodo dado
     * @param node nodo
     * @return nodo
     */
    public MyDoubleNode<T> getNext(MyDoubleNode<T> node){
        return node.next;
    }

    /**
     * Obtiene el ultimo elemento de la lista
     * @return
     */
    public MyDoubleNode<T> getLast() {
        return last;
    }

    /**
     * Verifica que la lista no este vacia
     * @return
     */
    public boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Añade un elemento
     * @param info
     */
    public void add(T info){
        if (this.first != null){
            this.last = new MyDoubleNode<>(info,null, this.last);
            this.last.prior.next = last;
        }else {
            this.first =this.last = new MyDoubleNode<>(info);
        }
    }

    /**
     * Añade un nodo
     * @param info
     */
    public void addNode(MyDoubleNode<T> info){
        if (this.first != null){
            info.next=null;
            info.prior=this.getLast();
            this.last = info;
            this.last.prior.next = last;
        }else {
            this.first =this.last =info;
        }
    }

    /**
     * Inserta un elemento
     * @param info
     */
    public void insert(T info){
        if (this.first == null) {
            this.first = this.last = new MyDoubleNode(info);
        }else{
            this.first = new MyDoubleNode<>(info,this.first,null);
        }
    }

    public void print() {
        MyDoubleNode<T> aux = this.first;
        while (aux.next != null) {
            System.out.print(aux.info + " ");
            aux = aux.next;
        }
        System.out.println();
    }
    public void printBack() {
        MyDoubleNode<T> aux = this.last;
        while (aux.prior != null) {
            System.out.print(aux.info + " ");
            aux = aux.prior;
        }
        System.out.println();
    }

    /**
     * Busca un nodo y retorna su informacion
     * @param info nodo
     * @return informacion
     */
    public T search(MyDoubleNode info){
        if (this.last!=null&&this.last==info){
            return this.last.info;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&aux!=info){
                aux = aux.next;
            }
            if (aux==info){
                return aux.info;
            }
        }
        return null;
    }
    public boolean remove(T info){
        String infoS = info.toString();
        boolean result = false;
        if ((isEmpty())){
        }
        else if (this.first == this.last && this.first.info.toString().equals(infoS)) {
            this.first =this.last = null;
            result = true;
        }
        else if (this.first.info.toString().equals(infoS)) {
            this.first = this.first.next;
            this.first.prior = null;
            result = true;
        }
        else if (this.last.info.toString().equals(infoS)) {
            this.last = this.last.prior;
            this.first.next = null;
            result = true;
        }
        else {
            MyDoubleNode<T> aux = this.first;
            while (aux != null && aux.info.toString().equals(infoS)) {
                aux = aux.next;
            }
            if (aux!=null){
                aux.prior.next = aux.next;
                result = true;
            }
        }
        return result;
    }
}
