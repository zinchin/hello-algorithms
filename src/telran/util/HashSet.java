package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> implements Set<T> {
private static final double DEFAULT_FACTOR = 0.75;
private static final int DEFAULT_HASH_TABLE_CAPACITY = 16;
private List<T> [] hashTable;
private int size;
private double factor ;
@SuppressWarnings("unchecked")
public HashSet(int hashTableCapacity, double factor) {
	this.factor = factor;
	hashTable = new List[hashTableCapacity];
}
public HashSet(int hashTableCapacity) {
	this(hashTableCapacity, DEFAULT_FACTOR);
}
public HashSet() {
	this(DEFAULT_HASH_TABLE_CAPACITY, DEFAULT_FACTOR);
}

private class HashSetIterator implements Iterator<T> {
   int currentInd=0;
   int currentBucket=0;
   

   @Override
	public boolean hasNext() {
	   	
		return currentInd<size;
	}

	@Override
	public T next() {
	    T res= null;
	    
		boolean flNext=false;
		   
		    while(!hasNext() || flNext==true) {
					
		    	if (hashTable[currentBucket]!=null || hashTable[currentBucket].size()==0) {
					currentBucket++;
				}
		    	else {
		    		List<T> bucket = hashTable[currentBucket];
		    		int i=0;
		    		
		    		while(i<bucket.size() && flNext==true) {
		    		    if (bucket.get(i)!=null) {
		    				res=bucket.get(i);
		    				currentInd++;
		    			}
		    		    else i++;
		    		}
		    	}
		   	
			}
		
		return res;
	}
	

	@Override
	public void remove() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			next();
		}
	}
	
}
	@Override
	public boolean add(T obj) {
		// set can not have two equal objects
		// that's why the method returns false at adding an object that already exists
		boolean res = false;
		if (!contains(obj)) {
			res = true;
			if (size >= hashTable.length * factor) {
				recreateHashTable();
			} 
			int hashTableInd = getHashTableIndex(obj.hashCode());
			if (hashTable[hashTableInd] == null) {
				hashTable[hashTableInd] = new LinkedList<T>();
			}
			hashTable[hashTableInd].add(obj);
			size++;
		}
		return res;
	}

	private void recreateHashTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2); //tmp hashset has table with twice capacity
		for (List<T> list: hashTable) {
			if (list != null) {
				for(T obj: list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}

	private int getHashTableIndex(int hashCode) {
		int res = Math.abs(hashCode) % hashTable.length;
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = getHashTableIndex(pattern.hashCode());
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object pattern) {
		int index = getHashTableIndex(pattern.hashCode());
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}

}
