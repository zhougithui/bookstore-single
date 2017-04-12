package org.bear.bookstore.lock.tools;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

}

/**
 * how to perform lock downgrading after updating a cache 
 * (exception handling is particularly tricky when handling
 *  multiple locks in a non-nested fashion): 
 * @author q
 *
 */
class CachedData {
	Object data;
	volatile boolean cacheValid;
	final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	void processCachedData() {
		rwl.readLock().lock();
		if (!cacheValid) {
			// Must release read lock before acquiring write lock
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			try {
				// Recheck state because another thread might have
				// acquired write lock and changed state before we did.
				if (!cacheValid) {
					data = "data";
					cacheValid = true;
				}
				// Downgrade by acquiring read lock before releasing write lock
				rwl.readLock().lock();
			} finally {
				rwl.writeLock().unlock(); // Unlock write, still hold read
			}
		}

		try {
			use(data);
		} finally {
			rwl.readLock().unlock();
		}
	}

	private void use(Object data2) {

	}
}

/**
 * ReentrantReadWriteLocks can be used to improve concurrency 
 * in some uses of some kinds of Collections. This is typically 
 * worthwhile only when the collections are expected to be large, 
 * accessed by more reader threads than writer threads, and entail 
 * operations with overhead that outweighs synchronization overhead. 
 * For example, here is a class using a TreeMap that is 
 * expected to be large and concurrently accessed
 * @author q
 *
 */
class RWDictionary {
	private final Map<String, Data> m = new TreeMap<>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	public Data get(String key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	public String[] allKeys() {
		r.lock();
		try {
			return (String[]) m.keySet().toArray();
		} finally {
			r.unlock();
		}
	}

	public Data put(String key, Data value) {
		w.lock();
		try {
			return m.put(key, value);
		} finally {
			w.unlock();
		}
	}

	public void clear() {
		w.lock();
		try {
			m.clear();
		} finally {
			w.unlock();
		}
	}
}

class Data {

}