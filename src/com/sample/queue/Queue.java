package com.sample.queue;

public interface Queue<T extends QueueItem>{
	
	public void put(T item);
	public T get();
}
