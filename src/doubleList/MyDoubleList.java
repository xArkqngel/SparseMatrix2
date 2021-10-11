package doubleList;


import java.util.Comparator;

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
    /**
     * Busca un nodo y retorna su informacion
     * @param info nodo
     * @return informacion
     */
    public MyDoubleNode<T> searchInfo(Comparator<T> info){
        if (this.last!=null&&info.compare(this.last.info,this.last.info)==0){
            return this.last;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&info.compare(this.last.info,this.last.info)!=0){
                aux = aux.next;
            }
            if (info.compare(this.last.info,this.last.info)==0){
                return aux;
            }
        }
        return null;
    }
    public MyDoubleNode<T> search(MyDoubleNode info){
        if (this.last!=null&&this.last==info){
            return this.last;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&aux!=info){
                aux = aux.next;
            }
            if (aux==info){
                return aux;
            }
        }
        return null;
    }



    public void remove(Comparator<T> comparator,T info){
        if ((isEmpty())){
        }
        else if (this.first == this.last && comparator.compare(this.first.info,info)==0) {
            this.first =this.last = null;
        }
        else if (comparator.compare(this.first.info,info)==0) {
            this.first = this.first.next;
            this.first.prior = null;
        }
        else if (comparator.compare(this.last.info,info)==0) {
            this.last = this.last.prior;
            this.last.next = null;
        }
        else {
            MyDoubleNode<T> aux = this.first;
            while (aux != null && comparator.compare(aux.info,info)!=0) {
                aux = aux.next;
            }
            if (comparator.compare(aux.info,info)==0){
                aux.prior.next = aux.next;
            }
        }
    }
}
