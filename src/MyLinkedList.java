import linkedList.LinkedListI;
import linkedList.ListNodeI;
import structures.SimpleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements LinkedListI<E> {

	protected ListNodeI<E> _head;

	@Override
	public ListNodeI<E> getHead() {
		return _head;
	}

	/**
	 * Append value to list tail.
	 *
	 * @param value to add
	 */
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

	/**
	 * Prepend value to list head.
	 *
	 * @param value to add
	 */
	@Override
	public void prepend(E value) {
		SimpleNode<E> first = new SimpleNode<E>(value);
		first.setNext(_head);
		_head = first;
	}

	/**
	 * Append value to list tail.
	 *
	 * @param val to add
	 */
	@Override
	public void add(E val) {
		append(val);
	}

	/**
	 * Convert list to array.
	 *
	 * @return array of values
	 */
	@Override
	public E[] toArray() {
		ArrayList<E> list = new ArrayList<E>();
		ListNodeI<E> node = _head;
		if (node != null) {
			while (((SimpleNode) (node)).hasNext()) {
				list.add(node.getValue());
				node = node.getNext();
			}
			list.add(node.getValue());
		}
		return list.toArray((E[])(new Object[] {}));
	}

	/**
	 * Determine whether value is present in list.
	 *
	 * @param val to search for
	 *
	 * @return whether value is present
	 */
	@Override
	public boolean contains(E val) {
		ListNodeI<E> node = _head;
		if (node != null) {
			while (((SimpleNode) (node)).hasNext()) {
				if (val.equals(node.getValue())) {
					return true;
				}
				node = node.getNext();
			}
			return val.equals(node.getValue());
		}
		return false;
	}

	/**
	 * Determine whether list is empty.
	 *
	 * @return list is empty
	 */
	@Override
	public boolean isEmpty() {
		return _head == null;
	}

	/**
	 * Get size of list.
	 *
	 * @return size of the list
	 */
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

	/**
	 * Remove value from list.
	 *
	 * @param val to remove
	 *
	 * @return whether value was removed
	 */
	@Override
	public boolean remove(E val) {
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(val)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Clear all nodes from list.
	 */
	@Override
	public void clear() {
		_head = null;
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

			/**
			 * Determine whether iterator has an instantiated next node.
			 *
			 * @return next node is not null
			 */
			@Override
			public boolean hasNext() {
				return ((SimpleNode)(current)).hasNext();
			}

			/**
			 * Get value of next node.
			 *
			 * @return next value
			 */
			@Override
			public E next() {
				if (((SimpleNode)(current)).hasNext()) {
					previous = current;
					current = current.getNext();
					return current.getValue();
				}
				else {
					throw new NoSuchElementException();
				}
			}


			/**
			 * Remove current node.
			 */
			@Override
			public void remove() {
				if (previous.getNext() == current) {
					if (current == _head) {
						_head = current.getNext();
					}
					previous.setNext(current.getNext());
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
	}
}
