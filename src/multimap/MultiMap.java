package multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * MultiMap utilites
 * 
 * @param <K>
 *            map key
 * @param <V>
 *            Collection iterable by E
 * @param <E>
 *            element
 */

public abstract class MultiMap<K, V extends Collection<E> & Iterable<E>, E> {

	
	/**
	 * @param map<K, V>
	 *            Multimap
	 * @param element
	 *            Element of the map
	 * @param key Key of the map
	 */
	public boolean putToIterableMap(Map<K, V> map, E element, K key) {
		if (key == null)
			return false;
		V col = map.get(key);
		if (col == null) {
			col = createMapCollection();
			map.put(key, col);
		}
		return col.add(element);
	}

	public abstract V createMapCollection();


	/**
	 * Removing an element from the multimap: a map where key corresponds to a
	 * collection.
	 * 
	 * @param map
	 *            Multimap
	 * @param element
	 *            Element of the map
	 * @param key Key of the map
	 */
	public boolean removeFromIterableMap(Map<K, V> map, E element, K key) {
		V col = map.get(key);
		if (col == null)
			return false;
		return col.remove(element);
	}
	
	/**
	 * Converts the map with iterable payload into ArrayList
	 * 
	 * @param map
	 *            any map where value is iterable
	 */
	public static final <K, V extends Iterable<E>, E> ArrayList<E> getList(Map<K, V> map) {
		ArrayList<E> lst = new ArrayList<E>();
		for (Entry<K, V> entry : map.entrySet()) {
			for (E element : entry.getValue())
				lst.add(element);
		}
		return lst;
	}

	/**
	 * @param map multimap
	 * @return integer count of multimap cells
	 */
	public static final <K, V extends Collection<E>, E> long getMultiMapSize(Map<K, V> map) {
		long res = 0; 
		for (Entry<K, V> entry : map.entrySet()) {
				res+= entry.getValue().size();
		}
		return res;
	}
	
	/**
	 * prints a multimap into console
	 * @param map multimap
	 */
	public static final <K, V extends Iterable<E>, E> void display(Map<K, V> map) {
		for (Entry<K, V> entry : map.entrySet()) {
			long size = entry.getValue().spliterator().getExactSizeIfKnown();
			System.out.println("SIZE= "+size+" "+ entry.toString());
		}
	}
}
