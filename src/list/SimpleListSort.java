package list;

import java.util.Comparator;

/**
 * Package: list
 * Name: SimpleListSort
 *
 * @Description
 * @Author Sofia Suesca
 * @Date 9/10/2021
 **/
public class SimpleListSort <T> extends SimpleList<T>{
    private final Comparator<T> comparator;

    public SimpleListSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void add(T info){
        if(this.getHeader()==null){
            this.addHead(info);
        }else {
            Node<T> aux = getHeader();
            while (aux!=null ){
                if (comparator.compare(aux.info, info)>0){

                }
            }
        }
    }
}
