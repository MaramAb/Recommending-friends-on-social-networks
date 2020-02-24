public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	
	public BSTNode<K, T> root, current; // do not change this
	public int size;
	
	public BSTMap() {
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean update(K k, T e) {
		if(!findkey(k))
			return false;
		current.data = e;
		return true;
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {

		Pair<Boolean, T> p = new Pair<> (false, null);
		if(!findkey(k)) // makes element with key k current 
			return p;
		p = new Pair<> (true, current.data);
		return p;
	}

	@Override
	public boolean insert(K k, T e) {

		BSTNode<K, T> p, q = current;
		
		if(findkey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		size++;
		p = new BSTNode<>(k, e);
		if (root == null) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}


	@Override
	public boolean remove(K k) {
		if(!findkey(k))
			return false;
		
		BooleanWrapper removed = new BooleanWrapper();
		removed.set(false);
		BSTNode<K, T> p;
		p = remove_aux(k, root, removed);
		current = root = p;
		size--;
		return removed.get();
	}
	
	private BSTNode<K, T> remove_aux(K key, BSTNode<K, T> p, BooleanWrapper flag) {
		BSTNode<K, T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key) < 0)
			p.left = remove_aux(key, p.left, flag); //go left
		else if(key.compareTo(p.key) > 0)
			p.right = remove_aux(key, p.right, flag); //go right
		else {
			flag.set(true);
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}
	private BSTNode<K, T> find_min(BSTNode<K, T> p){
		if(p == null)
			return null;
		
		while(p.left != null){
			p = p.left;
		}
		
		return p;
	}

	@Override
	public List<K> getKeys() {

		List<K> keys = new LinkedList<>();
		keys(root, keys);
		return keys;
	}
	
	private void keys(BSTNode<K, T> n, List<K> list) {
		
		if(n == null) return;
		keys(n.left, list);
		list.insert(n.key);
		keys(n.right, list);
		
	}
	
	
	
	private boolean findkey(K tkey) {
		BSTNode<K, T> p = root, q = root;
				
		if(root == null)
			return false;
		
		while(p != null) {
			q = p;
			if(p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			}
			else if(tkey.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}

}

class BooleanWrapper {
	boolean value;
	public void set(boolean b) {
		value = b;
	}
	public boolean get() {
		return value;
	}
}
