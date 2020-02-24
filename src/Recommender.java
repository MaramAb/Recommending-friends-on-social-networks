import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
				
		// i doesn't exist
		if(g == null || !g.isNode(i))
			return null;
		
		// create the queue we're going to return
		PQK<Double, K> q = new PQKImp<>(k);

		// get the keys in graph g
		List<K> keys = g.getNodes();
		
		keys.findFirst();
		while(!keys.last()) {
			K key = keys.retrieve();
			keys.findNext();
			if(key.equals(i) || g.isEdge(i, key))
				continue;
			double n = g.deg(key);
			q.enqueue(n, key);
		}
		// last key
		K key = keys.retrieve();
		if(key.equals(i) || g.isEdge(i, key))
			return q;
		double n = g.deg(key);
		q.enqueue(n, key);
		
		return q;
	}

	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		// i doesn't exist
				if(!g.isNode(i))
					return null;
				// create the queue we're going to return
				PQK<Double, K> q = new PQKImp<>(k);
				
				// get the keys in graph g
				List<K> keys = g.getNodes();
				
				keys.findFirst();
				while(!keys.last()) {
					K key = keys.retrieve();
					keys.findNext();
					if(key.equals(i) || g.isEdge(i, key))
						continue;
					double n = commen(g, i, key);
					q.enqueue(n, key);
				}
				// last key
				K key = keys.retrieve();
				if(key.equals(i) || g.isEdge(i, key))
					return q;
				double n = commen(g, i, key);
				q.enqueue(n, key);
				return q;
	}
	
	private static <K extends Comparable<K>> double commen(Graph<K> g, K i, K j) {
		
		double c = 0;
		
		List<K> li = g.neighb(i);
		li.findFirst();
		
		while(!li.last()) {
			K key = li.retrieve();
			if(g.isEdge(key, j))
				c++;
			li.findNext();
		}
		K key = li.retrieve();
		if(g.isEdge(key, j))
			c++;
		
		return c;
	}

	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
