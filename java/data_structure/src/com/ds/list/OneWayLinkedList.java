package com.ds.list;

public class OneWayLinkedList<E> {
	
	private Node<E> head;
	
	private Node<E> tail;
	
	private int size = 0;
	
	public boolean add(E e) {
		final Node<E> last = new Node<E>(e, null);
		if (head == null) {
			head = last;
			tail = last;
		} else {
			tail.next = last;
			tail = last;
		}
		size++;
		return true;		
	}
	
	public boolean remove(E e) {
		Node<E> cursor = head;
		for (int idx = 0; idx < size; idx++) {
			if(e != null && e.equals(cursor.next.value)) {
				cursor.next = cursor.next.next;
				size--;
				return true;
			}
		}
		return false;
	}
	
	public void reverse() {
		Node<E> cursor = null;
		Node<E> previous = null;
		Node<E> current = head;
		for (int idx = 0; idx < size; idx++) {
			cursor = current.next;
			current.next = previous;
			previous = current;
			current = cursor;
		}
		current = tail;
		tail = head;
		head = current;
	}
	
	public int size() {
		return size;
	}
	
	public void printList(String msg) {
		System.out.print(msg);
		if (head == null) {
			System.out.println("List is empty");
		} else {
			Node<E> cursor = head;
			for (int idx = 0; idx < size; idx++) {
				System.out.print("[ (" + cursor.value + ") | -> ]");
				cursor = cursor.next;
			}
		}
		System.out.println();
	}
	
	private static class Node<E> {
		E value;
		Node<E> next;
		
		Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		OneWayLinkedList<Integer> owll = new OneWayLinkedList<Integer>();
		owll.add(1);
		owll.add(2);
		owll.add(3);
		owll.printList("Print entire list: ");
		owll.remove(2);
		owll.printList("Print list after remove(2): ");
		owll.add(4);
		owll.add(5);
		owll.reverse();
		owll.printList("Print list after reversed: ");
	}

}
