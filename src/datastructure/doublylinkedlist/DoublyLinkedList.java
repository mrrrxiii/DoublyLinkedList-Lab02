package datastructure.doublylinkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedList<R> implements List<R> {

	private class Node<E> {
		private E value;
		private Node<E> next;
		private Node<E> prev;

		public Node(E value) {
			this.value = value;
		}

		public Node(Node<E> next, Node<E> prev) {
			this.next = next;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	private Node<R> head;
	private Node<R> tail;
	private int size;

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	@Override
	public boolean contains(Object o) {

		if (isEmpty()) {
			return false;
		}

		Node<R> temp = head;
		while (temp != null) {
			if (temp.value.equals(o)) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	@Override
	public boolean add(R e) {
		if (contains(e))
			return false;

		Node<R> eNode = new Node<R>(e);

		if (isEmpty()) {
			tail = eNode;
			head = eNode;
			size++;
			return true;
		}

		eNode.prev = tail;
		eNode.prev.next = eNode;
		tail = eNode;
		size++;
		return true;
	}

	@Override
	public R remove(int index) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}

		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<R> temp = null;
		if (head.equals(tail)) {
			temp = head;
			clear();
			return temp.value;
		}

		if (index == 0) {
			temp = head;
			head = head.next;
			head.prev = null;
			size--;
			return temp.value;

		}

		if (index == size - 1) {
			temp = tail;
			tail = tail.prev;
			tail.next = null;
			size--;
			return temp.value;
		}

		temp = findIndexNode(index);
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		return temp.value;

	}

	private Node<R> findIndexNode(int index) {
		Node<R> temp = null;

		if (index <= size / 2) {
			int cur = 0;
			temp = head;
			while (cur != index) {
				cur++;
				temp = head.next;
			}

		} else {
			int cur = size - 1;
			temp = tail;
			while (cur != index) {

				cur--;
				temp = tail.prev;

			}

		}
		return temp;
	}

	@Override
	public void add(int index, R e) {
		if (contains(e)) {
			throw new IllegalArgumentException("already contains " + e);
		}
		if (isEmpty()) {
			if (index != 0)
				throw new IndexOutOfBoundsException();
		} else {
			if (index > size - 1 || index < 0) {
				throw new IndexOutOfBoundsException();
			}
		}

		Node<R> eNode = new Node<R>(e);

		if (size == 0) {
			add(e);

		} else {
			if (index == 0) {
				eNode.next = head;
				eNode.next.prev = eNode;
				head = eNode;
			} else if (index == size - 1) {
				add(e);
			} else {

				Node<R> temp = findIndexNode(index);

				eNode.prev = temp.prev;
				eNode.next = temp;
				eNode.prev.next = eNode;
				eNode.next.prev = eNode;
			}
		}
		size++;
	}

	@Override
	public R set(int index, R e) {
		if (contains(e)) {
			throw new IllegalArgumentException("already contains " + e);
		}
		if (isEmpty()) {
			if (index != 0)
				throw new IndexOutOfBoundsException();
		} else {
			if (index > size - 1 || index < 0) {
				throw new IndexOutOfBoundsException();
			}
		}
		Node<R> eNode = new Node<R>(e);
		R tempValue = null;
		if (size == 0) {
			add(e);
			return null;
		} else {

			if (index == 0) {
				tempValue = head.value;
				head.value = e;
				return tempValue;
			}

			if (index == size - 1) {
				tempValue = tail.value;
				tail.value = e;
				return tempValue;
			}
			Node<R> temp = findIndexNode(index);
			tempValue = temp.value;
			temp.value = e;
		}
		return tempValue;

	}

	@Override
	public boolean addAll(Collection<? extends R> arg0) {
		throw new RuntimeException("[addAll] is not supported");
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends R> arg1) {
		throw new RuntimeException("[addAll] is not supported");

	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new RuntimeException("[containsAll] is not supported");
	}

	@Override
	public R get(int index) {
		if (isEmpty()) {
			return null;
		} else {
			if (index > size - 1 || index < 0) {
				throw new IndexOutOfBoundsException();
			}
		}
		Node<R> temp = findIndexNode(index);
		return temp.value;

	}

	@Override
	public int indexOf(Object arg0) {
		throw new RuntimeException("[indexOf] is not supported");
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public Iterator<R> iterator() {
		throw new RuntimeException("[iterator] is not supported");
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new RuntimeException("[lastIndexOf] is not supported");
	}

	@Override
	public ListIterator<R> listIterator() {
		throw new RuntimeException("[listIterator] is not supported");
	}

	@Override
	public ListIterator<R> listIterator(int arg0) {
		throw new RuntimeException("[listIterator] is not supported");
	}

	@Override
	public boolean remove(Object arg0) {
		throw new RuntimeException("[remove] is not supported");
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new RuntimeException("[removeAll] is not supported");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new RuntimeException("[retainAll] is not supported");
	}

	@Override
	public List<R> subList(int arg0, int arg1) {
		throw new RuntimeException("[subList] is not supported");
	}

	@Override
	public Object[] toArray() {
		throw new RuntimeException("[toArray] is not supported");
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new RuntimeException("[toArray] is not supported");
	}

}
