/**
 * 
 */
package com.sample.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author suresh
 *
 */
public class BlockingQueue<T extends QueueItem> implements Queue<QueueItem> {
	
	private QueueItem[] array;
	private int head;
	private int tail;
	private int size;
	
	
	private final Lock _lock = new ReentrantLock();
	private final Condition _emptyCondition = _lock.newCondition();
	private final Condition _fullCondition = _lock.newCondition();
	
	public BlockingQueue(int size) {
		this.size = size;
		this.array = new QueueItem[size];
		this.head = 0;
		this.tail = 0;
	}
	
	private boolean isEmpty() {
		return this.head == this.tail;
	}
	
	private boolean isFull() {
		return this.tail == this.size;
	}
	
	public void put(QueueItem item) {
		_lock.lock();
		try {
			while(isFull()) {
				try {
					_emptyCondition.await();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
	
			// at this point, queue has some room 
			this.array[tail++] = item;
			_fullCondition.signalAll();
		}
		finally {
			_lock.unlock();
		}
	}
	
	public QueueItem get() {
		_lock.lock();
		try {
			while(isEmpty()) {
				try {
					_fullCondition.await();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			
			// at this point there, queue has some element to read
			QueueItem temp = this.array[head];
			this.array[head++] = null;
			
			if(this.head == this.tail) {
				System.out.println(" Resetting Queue pointers to zero. (current values head:" +
								this.head + 
								", tail:" + this.tail);
						
				this.head = 0;
				this.tail = 0;
			}
			
			_emptyCondition.signalAll();
			return temp;
		} 
		finally {
			_lock.unlock();
		}
	}
	
}
