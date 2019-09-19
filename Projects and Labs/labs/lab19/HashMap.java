import java.util.Iterator;

public class public class HashMap<K, V> implements Map61BL<K, V> {

    /* Instance variables here? */
    private Entry<K, V>[] array;
    private int size;
    private double loadFactor;

    public HashMap() {
        array = new Entry[16];
        loadFactor = 0.75;
    }

    public HashMap(int initialCapacity) {
        array = new Entry[initialCapacity];
        loadFactor = 0.75;
    }

    public HashMap(int initialCapacity, float loadFactor) {
        array = new Entry[initialCapacity];
        this.loadFactor = (double) loadFactor;
    }

    /* Returns true if the given KEY is a valid name that starts with A - Z. */

    private int findKey(K key, int arrayLength) {
        return Math.floorMod(key.hashCode(), arrayLength);
    }

    @Override
    public boolean remove(K key, V value) {
        if (containsKey(key)) {
            int index = findKey(key, array.length);
            Entry<K, V> pointer = array[index];
            if (pointer.key.equals(key)) {
                array[index] = pointer.next;
            } else {
                while (pointer.next != null) {
                    if (pointer.next.key.equals(key)) {
                        pointer.next = pointer.next.next;
                        break;
                    }
                }
            }
        }
    }

    /* Returns true if the map contains the KEY. */
    public boolean containsKey(K key) {
        int index = findKey(key, array.length);
        if (array[index] == null) {
            return false;
        } else {
            Entry pointer  = array[index];
            while (pointer != null) {
                if (pointer.key.equals(key)) {
                    return true;
                }
                pointer = pointer.next;
            }
            return false;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator<K> implements Iterator {
        Entry[] a;
        int index;
        Entry pointer;
        public HashMapIterator() {
            this.a = array;
            index = 0;
            pointer = a[0];
        }

        public K next() {
            if (hasNext()) {
                if (pointer != null) {
                    K toReturn = pointer.key;
                    pointer = pointer.next;
                    return toReturn;
                } else {
                    index += 1;
                    if (hasNext()) {
                        pointer = a[index];
                        return next();
                    }
                    return null;
                }
            }
            return null;

        }

        public boolean hasNext() {
            return index < a.length && pointer != null;
        }
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    public V get(K key) {
        if (containsKey(key)) {
            int index = findKey(key, array.length);
            Entry pointer = array[index];
            while (!pointer.key.equals(key)) {
                pointer = pointer.next;
            }
            return pointer.value;
        }
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    public void put(K key, V value) {
        Entry<K, V> a = new Entry(key, value);
        int index = findKey(key, array.length);
        System.out.println(index);
        if (array[index] == null) {
            array[index] = a;
            size += 1;
            if (this.loadFactor() > loadFactor) {
                resize();
            }
        } else if (a.keyEquals(array[index])) {
            array[index].value = value;
        } else {
            array[index] = new Entry(key, value, array[index]);
            size += 1;
            if (this.loadFactor() > loadFactor) {
                resize();
            }
        }
    }

    private void resize() {
        Entry<K, V>[] copy = array;
        Entry<K, V>[] newArray = new Entry[array.length * 2];
        this.array = newArray;
        for (int i = 0; i < copy.length; i++) {
            Entry pointer = array[i];
            while (pointer != null) {
                put(pointer.key, pointer.value);
                pointer = pointer.next;
            }
        }
    }


    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    public V remove(K key) {
        if (containsKey(key)) {
            int index = findKey(key, array.length);
            V val = array[index].value;
            array[index] = null;
            return val;
        }
        return null;
    }

    public void clear() {
        this.array = new Entry[array.length];
    }

    public int size() {
        return this.size;
    }
    public double loadFactor() {
        return (double) this.size / (double) array.length;
    }
    public int capacity() {
        return this.array.length;
    }

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Entry next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        Entry(K key, V value, Entry entry) {
            this.key = key;
            this.value = value;
            this.next = entry;
        }
        /* Returns true if this key matches with the OTHER's key. */

        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }
}

