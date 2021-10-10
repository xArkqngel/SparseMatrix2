package list;

import java.util.Comparator;


public class SimpleList<T> {

	private Node<T> header;
	private int size;
	private Node<T> current;

	public SimpleList() {
		this.header = null;
		this.size = 0;
	}
	

	public boolean isEmpty() {
		return header == null;
	}

	public void addHead(T info){
		this.header = new Node<>(info);
	}

	public void add(T item) {
		if (header == null) {
			addHead(item);
			size++;
		} else {
			Node<T> aux = this.header;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = new Node<>(item);
			size++;
		}
	}

	public void addSort(T item, Comparator<T> comparator) {
		if (header != null) {
			searchWhereAdd(item, comparator);
		} else {
			this.header = new Node<>(item);
		}
	}

	public void add(SimpleList<T> simpleList) {
		if (!simpleList.isEmpty()) {
			simpleList.reset();
			while (simpleList.isInto()) {
				this.add(simpleList.getNext());
			}
		}	
	}

	private void searchWhereAdd(T item, Comparator<T> comparator) {
		Node<T> aux = this.header;
		while (aux != null) {
			if (comparator.compare(aux.info, item) > 0) {
				change(item, aux);
				break;
			} else if (aux.next == null) {
				aux.next = new Node<>(item);
				break;
			} else {
				aux = aux.next;
			}
		}
	}

	private void change(T item, Node<T> aux) {
		Node<T> nodeCopy = new Node<>(aux.info, aux.next);
		aux.info = item;
		aux.next = nodeCopy;
	}


	public Node<T> getHeader() {
		return header;
	}

	public void sort(Comparator<T> comparator) {
		T aux = null;
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < (size - i); j++) {
				if (comparator.compare(get(j - 1), get(j)) > 0) {
					aux = get(j - 1);
					set(j - 1, get(j));
					set(j, aux);
				}

			}
		}
	}

	public boolean remove(T item) {
		if (header != null) {
			if (header.info.equals(item)) {
				header = header.next == null ? null : header.next;
				size--;
				return true;
			} else {
				Node<T> aux = header;
				while (aux.next != null) {
					if (aux.next.info.equals(item)) {
						aux.next = aux.next.next == null ? null : aux.next.next;
						size--;
						return true;
					}
					aux = aux.next;
				}
			}
		}
		return false;
	}

	public void insert(T item) {
		header = new Node<>(item, header);
	}

	public T search(Comparator<T> comparator, T itemSearch) {
		if (header != null) {
			if (comparator.compare(header.info, itemSearch) > 0) {
				return header.info;
			}
			Node<T> aux = header;
			while (aux.next != null) {
				if (comparator.compare(aux.next.info, itemSearch) > 0) {
					return aux.next.info;
				}
				aux = aux.next;
			}
		}
		return null;
	}

	public SimpleList<T> find(Comparator<T> comparator, T itemToCompare) {
		SimpleList<T> listFinder = new SimpleList<>();
		if (header != null) {
			if (comparator.compare(header.info, itemToCompare) > 0) {
				listFinder.add(header.info);
			}
			Node<T> aux = header;
			while (aux.next != null) {
				if (comparator.compare(aux.next.info, itemToCompare) > 0) {
					listFinder.add(aux.next.info);
				}
				aux = aux.next;
			}
		}
		return listFinder;
	}
	
	public void removeAll() {
		header = null;
	}

	public T get(int index) {
		if (header != null) {
			Node<T> aux = header;
			for (int i = 0; i < index; i++, aux = aux.next)
				;
			return aux.info;
		}
		return null;
	}

	public void reset() {
		this.current = header;
	}

	public T getNext() {
		T item = current.info;
		current = current.next;
		return item;
	}

	public void set(int index, T itemToSet) {
		if (header != null) {
			Node<T> aux = header;
			for (int i = 0; i < index; i++, aux = aux.next)
				;
			aux.info = itemToSet;
		}

	}

	public boolean isInto() {
		return current != null;
	}

	public int size() {
		return size;
	}
	
	public Node<T> getCurrent() {
		return current;
	}
}
