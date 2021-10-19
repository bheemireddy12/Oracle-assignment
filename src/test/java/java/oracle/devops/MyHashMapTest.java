package java.oracle.devops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;


public class MyHashMapTest {

    @Ignore
    public void testMyHashMapIntFloat() {
	HashMap<String, Integer> real = new HashMap<String, Integer>(12,
		(float) .75);
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(12,
		(float) .75);
	assertEquals(real.size(), test.size);
	// real.put("asdf", 1);
	// real.put("bwwww", 2);
	// test.put("ghjjj", 1);
	// test.put("bwwww", 2);
	// System.out.print(real.toString());
    }

    @Ignore
    public void testMyHashMap() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	// System.out.print(test.table.length + " " + test.loadFactor);
    }

    @Ignore
    public void testSize() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	real.put("kat", 0);
	test.put("derek", 1);
	test.put("bob", 2);
	test.put("kat", 0);
	assertEquals(real.size(), test.size());
    }

    @Ignore
    public void testIsEmpty() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	assertEquals(real.isEmpty(), test.isEmpty());
    }

    @Ignore
    public void testClear() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	real.clear();
	test.clear();
	assertEquals(real.isEmpty(), test.isEmpty());
    }

    @Ignore
    public void testToString() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	// System.out.print(test.toString());
	// Works - 11/12
    }

    @Test
    public void testput() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	test.put("bob", 3);
	System.out.print(test.get("bob"));
	assertEquals(test.containsKey("derek"), real.containsKey("derek"));
    }


    @Ignore
    public void testget() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	test.put("bob", 3);
	//System.out.print("a".hashCode() % 11 + " " + "l".hashCode() % 11);
	// System.out.print(test.get("bob"));
    }

    @Ignore
    public void testremove() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	test.remove("derek");
	assertFalse(test.containsKey("derek"));
    }

    @Ignore
    public void testContainsKey() {
	// Tested in remove and put
    }

    @Ignore
    public void testContainsValue() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	real.put("derek", 1);
	real.put("bob", 2);
	test.put("derek", 1);
	test.put("bob", 2);
	assertTrue(test.containsValue(2));
    }

    @Ignore
    public void testKeys() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	String s = "s";
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	for (int i = 0; i <= 10; i++) {
	    test.put((s.concat(String.valueOf(i))), i);
	}
	// System.out.print(test);
	// Iterator<String> keys = test.keys();
	// while (keys.hasNext()) {
	// System.out.println(keys.next());
	// }
    }

    @Ignore
    public void testValues() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	String s = "s";
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	for (int i = 0; i <= 10; i++) {
	    test.put((s.concat(String.valueOf(i))), i);
	}
	Iterator<Integer> values = test.values();
	// System.out.println(test);
	// while (values.hasNext()) {
	// System.out.print(values.next() + " ");
	// }
	// values.next();

    }

    @Ignore
    public void testResize() {
	HashMap<String, Integer> real = new HashMap<String, Integer>();
	String s = "s";
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
	// for (int i = 0; i <= 99; i++) {
	// test.put((s.concat(String.valueOf(i))), i);
	// System.out.println(test + " " + test.table.length + " " + test.lf);
	// }
	//
	// for (int i = 0; i <= 99; i++) {
	// test.remove(s.concat(String.valueOf(i)));
	// System.out.println(test + " " + test.size() + " " + test.lf);
	// }
    }

}