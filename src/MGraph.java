public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this

	public MGraph() {
		adj = new BSTMap<>();
	}
	@Override
	public boolean addNode(K i) {
		if(isNode(i))
			return false;
		adj.insert(i, new LinkedList<K> ());
		return true;
	}
	@Override
	public boolean isNode(K i) {
		// node with key i becomes the current element, or i's possible parent
		return adj.retrieve(i).first;
	}
	
	@Override
	public boolean addEdge(K i, K j) {
		if(!isNode(i) || !isNode(j) || isEdge(i, j))
			return false;

		adj.retrieve(i).second.insert(j);
		adj.retrieve(j).second.insert(i);
		
		return true;
	}
	
	@Override
	public boolean isEdge(K i, K j) {
		if(!isNode(i) || !isNode(j))
			return false;
		// i's neighbors
		List<K> li = neighb(i);
		// j's neighbors
		List<K> lj = neighb(j);
		
		if(li.empty() || lj.empty())
			return false;
				
		li.findFirst();
		while(!li.last()) {
			if(j.equals(li.retrieve())) {
				return true;
			}
			li.findNext();
		}
		if(j.equals(li.retrieve()))
			return true;
	
	return false;
	}
	@Override
	public List<K> neighb(K i) {
		if(!isNode(i))
			return null;
		return adj.retrieve(i).second;
	}
	@Override
	public int deg(K i) {
		if(!isNode(i))
			return -1;
		return adj.retrieve(i).second.size();
	}
	@Override
	public List<K> getNodes() {
		return adj.getKeys();
	}

}



