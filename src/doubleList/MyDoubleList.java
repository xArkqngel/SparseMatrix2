package doubleList;

import java.util.Comparator;

public class MyDoubleList<T> {
    protected  MyDoubleNode<T> first;
    protected  MyDoubleNode<T> last;

    public MyDoubleList() {
        this.first = null;
        this.last = null;
    }

    public MyDoubleNode<T> getFirst() {
        return first;
    }

    public MyDoubleNode<T> getLast() {
        return last;
    }

    public boolean isEmpty(){
        return this.first == null;
    }
    public void add(T info){
        if (this.first != null){
            this.last = new MyDoubleNode<>(info,null, this.last);
            this.last.prior.next = last;
        }else {
            this.first =this.last = new MyDoubleNode<>(info);
        }
    }
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
    public T search(Comparator<T> comparator){
        if (comparator.compare(this.last.info,this.last.info)==0){
            return this.last.info;
        }else {
            MyDoubleNode<T> aux = this.first;
            while (aux!=null&&comparator.compare(aux.info, this.last.info)!=0){
                aux = aux.next;
            }
            if (comparator.compare(aux.info,this.last.info)==0){
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
