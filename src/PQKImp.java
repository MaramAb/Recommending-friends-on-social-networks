public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
	private int maxsize;
	private int size;
	private  PQNode<P, T> head, current;



	public PQKImp(int k) {
		maxsize = k;
		size = 0;
		head = current = null;

	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public void enqueue(P pr, T e) {  // this is so wrong, I have to check if the element has higher priority than the lowest element then I should replace it with new element.

		

		// queue is empty
		if(size == 0) {
			insert(pr, e);
			return;
		}

		// if pr is higher than head
		if(pr.compareTo(head.priority) > 0) {
			PQNode<P, T> tmp = new PQNode<>(e, pr);
			tmp.next = head;
			head = tmp;
			size++;

			if(size >= maxsize) {
				while(current.next != null)
					current = current.next;
				remove();
			}
			return;
		}
		
		// check for priority of existing nodes
		current = head;
		while(current.next != null) {
			if(pr.compareTo(current.next.priority) > 0) {
				insert(pr, e);
				return;
			}
			current = current.next;
		}
		if(size < maxsize)
			insert(pr, e);
		
	}

	private void insert (P pr, T val) {
		PQNode<P, T> temp;
		if (size == 0) {
			current = head = new PQNode<> (val, pr);
		}
		else {
			temp = current.next;
			current.next = new PQNode<> (val, pr);
			current = current.next;
			current.next = temp;
		}
		if(size >= maxsize) {
			while(current.next != null)
				current = current.next;
			remove();
		}
		size++;
	}

	@Override
	public Pair<P, T> serve() {

		// queue is empty
		if(size == 0) 
			return null; 

		PQNode<P, T> node = head;
		Pair<P, T> p = new Pair<>(node.priority,node.data);
		
		current = head;
		remove();
		
		return p;
	}
	
	private void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			PQNode<P, T> temp = head;

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

}

class PQNode<P extends Comparable<P>, T> {
	public T data;
	public P priority;
	public PQNode<P, T> next;

	public PQNode() {
		next = null;
	}

	public PQNode(T e, P p) {
		data = e;
		priority = p;
		next = null;
	}

	// Setters/Getters?
}
