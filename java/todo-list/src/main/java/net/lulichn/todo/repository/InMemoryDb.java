package net.lulichn.todo.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryDb {
	// FIXME: thread safe???
	private static final ConcurrentMap<Integer, String> map = new ConcurrentHashMap<>();
	private static int lastIndex = 0;

	public static int put(String data) {
		synchronized (InMemoryDb.class) {
			map.put(lastIndex++, data);
			return lastIndex;
		}
	}

	public static String get(int index) {
		synchronized (InMemoryDb.class) {
			return map.get(index);
		}
	}

	public static Collection<String> values() {
		synchronized (InMemoryDb.class) {
			return map.values();
		}
	}
}
