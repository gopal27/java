package com.sample.linkedlist;

import java.util.NoSuchElementException;

public class LinkedList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	
	public LinkedList() {
	}
	
	
	public void reverse() {
		Node<E> itr = head;
		Node<E> prevNode = null;
		
		Node<E> temp = null;
		while(itr != null) {
			temp = itr.nextNode;
			itr.nextNode = prevNode;
			
			prevNode = itr;
			
			itr = temp;
		}
		
		this.head = this.tail;
		this.tail = prevNode;
	}
	
	
	/**
	 * Recursive implementation - to revese a linked listed
	 */
	public void reverseRecursive() {
		if(head == null) throw new NoSuchElementException();
		reverseUtil(head, null);
	}
	
	private void reverseUtil(Node<E> current, Node<E> prev) {
		if(current.nextNode == null) {
			this.head = current;
			current.nextNode = prev;
			return;
		}
		Node<E> next1 = current.nextNode;
		current.nextNode = prev;

		reverseUtil(next1, current);
	}
	
	
	public void display() {
		Node<E> itr = this.head;
		while(itr != null) {
			System.out.print(itr.item + " -->");
			itr = itr.nextNode;
		}
		System.out.println("\\");
	}

	public void addFirst(E item) {
		if(this.head == null) {
			this.head = new Node<E>(item);
		} else {
			this.head = new Node<E>(item, this.head);			
		}
		
		// if this is the only item
		if(this.tail == null)
		{
			this.tail = this.head;
		}
	}
	

	public void addLast(E item) {
		if(this.tail == null) {
			this.tail = new Node<E>(item);
		} else {
			this.tail.nextNode = new Node<E>(item);
			this.tail = this.tail.nextNode;
		}
		
		// if this is the only item.
		if(this.head == null)
		{
			this.head = this.tail;
		}
	}

	public E removeFirst() {
		if(this.head == null) {
			throw new NoSuchElementException();
		} else {
			E item = this.head.item;
			if (this.head == this.tail) {
				this.tail = this.head.nextNode;
			}
			this.head = this.head.nextNode;
			return item;
		}
	}
	
	public E removeLast() {
		throw new UnsupportedOperationException();
	}
	
	
	
	private class Node<E> {
		private E item;
		private Node<E> nextNode;

		
		private Node(E item) {
			this(item, null);
		}
		
		private Node(E item, Node<E> nextNode) {
			this.item = item;
			this.nextNode = nextNode; 
		}
	}

}
