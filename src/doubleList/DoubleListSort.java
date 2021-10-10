package doubleList;

import java.util.Comparator;

/**
 * Package: doubleList
 * Name: DoubleListSort
 *
 * @Description
 * @Author Sofia Suesca
 * @Date 9/10/2021
 **/
public class DoubleListSort<T> extends MyDoubleList<T> {
    private Comparator<T> comparator;

    public DoubleListSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

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
    public T search(T info){
        if (comparator.compare(this.last.info,info)==0){
            return this.last.info;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&comparator.compare(aux.info, info)!=0){
                aux = aux.next;
            }
            if (comparator.compare(aux.info,info)==0){
                return aux.info;
            }
        }
        return null;
    }
}
