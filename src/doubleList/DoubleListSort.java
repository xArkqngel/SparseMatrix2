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
     * Ordena la lista
     */
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

    /**
     * Cambia de posicion 2 datos
     *
     * @param first
     * @param second
     */
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
    /**
     * Metodo que busca un nodo con una informacion igual al parametro info
     * @param info informacion a buscar
     * @return MyDoubleNode<T> nodo con esa informacion o null
     */
    public MyDoubleNode<T> searchNode(T info){
        if (this.last!=null&&comparator.compare(this.last.info,info)==0){
            return this.last;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&comparator.compare(this.last.info,info)!=0){
                aux = aux.next;
            }
            if (comparator.compare(this.last.info,info)==0){
                return aux;
            }
        }
        return null;
    }
}
