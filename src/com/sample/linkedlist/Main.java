package com.sample.linkedlist;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 1; i <= 10; i++)
			list.addLast(i);
		
		list.display();
		System.out.println(list.removeFirst());
		
		list.reverse();
		
		System.out.println(list.removeFirst());
		list.display();
		
		list.reverseRecursive();
		list.display();

	}

}
