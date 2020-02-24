
class Node<T> {
	public T data;
	public Node<T> next;

	public Node () {
		data = null;
		next = null;
	}

	public Node (T val) {
		data = val;
		next = null;
	}	
}

public class LinkedList<T> implements List<T> {
	
	private Node<T> head;
	private Node<T> current;
	public int size;

	public LinkedList () {
		head = current = null;
	}

	public boolean empty () {
		return head == null;
	}

	public boolean last () {
		return current.next == null;
	}
	
	public boolean full () {
		return false;
	}
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	public T retrieve () {
		return current.data;
	}
	public void update (T val) {
		current.data = val;
	}
	
	public void insert (T val) {
		Node<T> temp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			temp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = temp;
		}
		size++;
	}
	
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> temp = head;

			while (temp.next != current)
				temp = temp.next;

			temp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean exists(T e) {
		Node<T> p = head;
		
		while(p != null) {
			if(e.equals(e))
				return true;
			p = p.next;
		}
		
		return false;
	}
	
}