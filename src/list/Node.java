package list;


public class Node <T> implements Cloneable{
	protected T info;
	protected Node<T> next;
	

	public Node(T info) {
		this.info = info;
		this.next = null;
	}


	public Node(T info, Node<T> next) {
		this.info = info;
		this.next = next;
	}
	
	public T getInfo() {
		return info;
	}
}
