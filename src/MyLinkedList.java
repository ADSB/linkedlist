import linkedList.LinkedListI;
import linkedList.ListNodeI;
import structures.SimpleNode;

import java.util.ArrayList;
import java.util.Iterator;

public class MyLinkedList<E> implements LinkedListI<E>, Iterable<E> {

	protected ListNodeI<E> _head;

	@Override
	public ListNodeI<E> getHead() {
		return _head;
	}

	@Override
	public void append(E value) {
		if (_head != null) {
			ListNodeI<E> last = _head;
			while (((SimpleNode) (last)).hasNext()) {
				last = last.getNext();
			}
			last.setNext(new SimpleNode<E>(value));
		}
		else {
			_head = new SimpleNode<E>(value);
		}
	}

	@Override
	public void prepend(E value) {
		SimpleNode<E> first = new SimpleNode<E>(value);
		first.setNext(_head);
		_head = first;
	}

	@Override
	public void add(E val) {
		append(val);
	}

	@Override
	public E[] toArray() {
		ArrayList<E> list = new ArrayList<E>();
		ListNodeI<E> node = _head;
		while (((SimpleNode)(node)).hasNext()) {
			list.add(node.getValue());
			node = node.getNext();
		}
		list.add(node.getValue());
		return list.toArray((E[])(new Object[] {}));
	}

	@Override
	public boolean contains(E val) {
		ListNodeI<E> node = _head;
		while (((SimpleNode)(node)).hasNext()) {
			if (val.equals(node.getValue())) {
				return true;
			}
			node = node.getNext();
		}
		return val.equals(node.getValue());
	}

	@Override
	public boolean isEmpty() {
		return _head == null;
	}

	@Override
	public int size() {
		int count = 0;
		ListNodeI<E> last = _head;
		if (_head != null) {
			while (((SimpleNode)(last)).hasNext()) {
				count++;
				last = last.getNext();
			}
			count++;
		}
		return count;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private ListNodeI<E> previous;
			private ListNodeI<E> current;

			{
				current = new SimpleNode<E>();
				current.setNext(_head);
			}

			@Override
			public boolean hasNext() {
				return ((SimpleNode)(current)).hasNext();
			}

			@Override
			public E next() {
				previous = current;
				current = current.getNext();
				return current.getValue();
			}

			@Override
			public void remove() {
				if (current == _head) {
					_head = current.getNext();
				}
				else {
					previous.setNext(current.getNext());
				}
			}
		};
	}
}
