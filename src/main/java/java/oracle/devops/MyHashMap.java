package java.oracle.devops;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> {

    LinkedList<MyEntry>[] table;
    int size; 
    float loadFactor;
    float lf;

    public class MyEntry{
	K key;
	V val;
	
	@Override
	public int hashCode() {
	    return key.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
	    if (key.equals(o)) {
		return true;
	    } else {
		return false;
	    }
	}

	public String toString() {
	    String s = key.toString();
	    s = s.concat("=" + val.toString());
	    return s;
	}
    }

    MyHashMap(int capacity, float loadfactor) {
	table = (LinkedList<MyEntry>[]) new LinkedList[capacity];
	loadFactor = loadfactor;
	for (int i = 0; i < capacity; i++) {
	    table[i] = new LinkedList<MyEntry>();
	}
	size = 0;
    }

    MyHashMap() {
	this(11, (float) .75);
    }

    public int size() {
	return this.size;
    }

    public boolean isEmpty() {
	for (int i = 0; i < table.length; i++) {
	    if (!table[i].isEmpty()) {
		return false;
	    }
	}
	return true;
    }

    public void clear() {
	for (int i = 0; i < table.length; i++) {
	    table[i].clear();
	}
	size = 0;
    }

    public String toString() {
	String s = "{";
	for (int i = 0; i < table.length; i++) {
	    if (!table[i].isEmpty()) {
		s = s.concat(table[i].toString());
	    }
	}
	s = s.concat("}");
	return s;
    }
    
    public MyEntry getHM(K key) {
	MyEntry temp = null;
	int place = (key.hashCode()) % (table.length);
	if (place < 0) {
	    place += table.length;
	}
	if (!containsKey(key)) {
	    return null;
	}
	for (int i = 0; i < table[place].size(); i++) {
	    if (table[place].get(i).key.equals(key)) {
		temp = table[place].element();
	    }
	}
	return temp;
    }


    public V put(K key, V value)  {
	lf = (float) (size + 1) / table.length;

	MyEntry temp2 = new MyEntry();
	temp2.key = key;
	temp2.val = value;

	if (key==null || value == null) {
	    throw new NullPointerException();
	}
	if (lf > loadFactor) {
	    resize();
	}
	int place = (key.hashCode()) % (table.length);
	if (place < 0) {
	    place += table.length;
	}
	
	if (this.containsKey(key)) {
	    int ind = -1;
	    for (int i = 0; i < table[place].size(); i++) {
		if (table[place].get(i).key.equals(key)) {
		    ind = i;
		}
	    }
	    V temp = table[place].get(ind).val;
	    table[place].get(ind).val = value;
	    return temp;
	} else {
	    table[place].add(temp2);
	    size += 1;
	    return null;
	}
    }

    public V get(K key) {
	V temp = null;
	int place = (key.hashCode()) % (table.length);
	if (place < 0) {
	    place += table.length;
	}
	if (!containsKey(key)) {
	    return null;
	}
	for (int i = 0; i < table[place].size(); i++) {
	    if (table[place].get(i).key.equals(key)) {
		temp = table[place].element().val;
	    }
	}
	return temp;
    }

    public V remove(K key) {
	lf = (float) (size - 1) / table.length;
	int place = (key.hashCode()) % (table.length);
	if (place < 0) {
	    place += table.length;
	}
	if (!containsKey(key)) {
	    return null;
	    // } else if (get(key) == null) {
	    // return null;
	} else {
	    V temp = this.get(key);
	    for (int i = 0; i < table[place].size(); i++) {
		if (table[place].get(i).equals(key)) {
		    table[place].remove(i);
		}
	    }
	    size -= 1;
	    return temp;
	}
    }

    public boolean containsKey(K key) {
	int place = (key.hashCode()) % (table.length);
	if (place < 0) {
	    place += table.length;
	}
	for (int i = 0; i < table[place].size(); i++) {
	    if (table[place].get(i).equals(key)) {
		return true;
	    }
	}

	return false;
    }

    public boolean containsValue(V value) {
	for (int i = 0; i < table.length; i++) {
	    for (int j = 0; j < table[i].size(); j++) {
		if (table[i].get(j).val.equals(value)) {
		    return true;
		}
	    }
	}
	return false;
    }

    public Iterator<K> keys() {
	return new Iterator<K>() {
	    int bucket = 0;
	    Iterator<MyEntry> itr = table[bucket].iterator();
	    int nextCount = 0;

	    public boolean hasNext() {
		if (nextCount == size) {
		    return false;
		}
		return true;
	    }

	    public K next() {
		if (!this.hasNext()) {
		    throw new NoSuchElementException();
		}
		while (!itr.hasNext()) {
		    bucket += 1;
		    itr = table[bucket].iterator();
		}
		nextCount += 1;
		return itr.next().key;
	    }

	    public void remove() {
		itr.remove();
		size -= 1;
		nextCount -= 1;
	    }
	};
    }

    public Iterator<V> values() {
	return new Iterator<V>() {
	    int bucket = 0;
	    Iterator<MyEntry> itr = table[bucket].iterator();
	    int nextCount = 0;

	    public boolean hasNext() {
		if (nextCount == size) {
		    return false;
		}
		return true;
	    }

	    public V next() {
		if (!this.hasNext()) {
		    throw new NoSuchElementException();
		}
		while (!itr.hasNext()) {
		    bucket += 1;
		    itr = table[bucket].iterator();
		}
		nextCount += 1;
		return itr.next().val;
	    }

	    public void remove() {
		itr.remove();
		size -= 1;
		nextCount -= 1;
	    }
	};
    }

    private boolean isPrime(int x) {
	for (int i = 2; i < Math.sqrt(x); i++) {
	    if (x % i == 0) {
		return false;
	    }
	}
	return true;
    }

    private void resize() {
	int x = table.length;
	int i = 2*x+1;
	while(!isPrime(i)) {
	    i+=2;
	}
	LinkedList<MyEntry>[] temp = table;
	table = (LinkedList<MyEntry>[]) new LinkedList[i];
	for (int p = 0; p < i; p++) {
	    table[p] = new LinkedList<MyEntry>();
	}
	size = 0;
	for (int j = 0; j < temp.length; j++) {
	    for (int k = 0; k < temp[j].size(); k++) {
		K key = temp[j].get(k).key;
		V val = temp[j].get(k).val;
		put(key, val);
	    }
	}
    }
}
