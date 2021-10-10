package doubleList;

import java.util.Comparator;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class DoubleListSort<T> extends MyDoubleList<T> {
    private Comparator<T> comparator;

    public DoubleListSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Metodo que añade ordenadamente la informacion
     * @param info informacion a añadir
     */

    public void addOrder(T info){
        if (this.first ==null){
            this.first =this.last = new MyDoubleNode<>(info);

        }else if(comparator.compare(this.last.getInfo(),info)>0){
            this.last = new MyDoubleNode<>(info,null, this.last);
            this.last.prior.next = last;
        }else{
            MyDoubleNode<T> node = this.first;
            while (node!=null&&comparator.compare(node.info, info)>0){
                node = node.next;
            }
            node = new MyDoubleNode<T>(info,node,node.prior);
        }
    }

    public void insertionSort(){
        MyDoubleNode<T> front = this.first;
        MyDoubleNode<T> back = null;
        while (front!=null){
            back=front.next;
            while (back!= null && back.prior != null && comparator.compare(back.info,back.prior.info)<0){
                swapData(back,back.prior);
                back = back.prior;
            }
            front = front.next;
        }
    }

    public void swapData(MyDoubleNode<T> first, MyDoubleNode<T> second){
        T value = first.info;
        first.info = second.info;
        second.info = value;
    }

    /**
     * Metodo de busqueda de la información
     * @param info informacion a buscar
     * @return
     */
    public T search(T info){
        if (this.last!=null && comparator.compare(this.last.info,info)==0){
            return this.last.info;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&comparator.compare(aux.info, info)!=0){
                aux = aux.next;
            }
            if (aux != null && comparator.compare(aux.info,info)==0){
                return aux.info;
            }
        }
        return null;
    }
}
