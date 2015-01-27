import linkedList.LinkedListI;
import linkedList.ListNodeI;
import structures.SimpleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements LinkedListI<E> {

	protected ListNodeI<E> _head;
	protected ListNodeI<E> _tail;
	protected boolean _restricted = true;

	@Override
	public ListNodeI<E> getHead() {
		return _head;
	}

	public ListNodeI<E> getTail() {
		return _tail;
	}

	/**
	 * Append value to list tail.
	 *
	 * @param value to add
	 */
	@Override
	public void append(E value) {
		if (_head != null) {
			_tail.setNext(new SimpleNode<E>(value));
			_tail = _tail.getNext();
		}
		else {
			_head = new SimpleNode<E>(value);
			_tail = _head;
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
	public Object[] toArray() {
		ArrayList<E> list = new ArrayList<E>();
		ListNodeI<E> node = _head;
		if (node != null) {
			while (node != _tail) {
				list.add(node.getValue());
				node = node.getNext();
			}
			list.add(node.getValue());
		}
		return list.toArray();
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
			while (node != _tail) {
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
			while (last != _tail) {
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
		_tail = null;
	}

	public void loop() {
		if (_tail != null) {
			_tail.setNext(_head);
		}
	}

	public void restrictIterator(boolean restrict) {
		_restricted = restrict;
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
				return ((SimpleNode)(current)).hasNext() && (current != _tail | !_restricted);
			}

			/**
			 * Get value of next node.
			 *
			 * @return next value
			 */
			@Override
			public E next() {
				if (hasNext()) {
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
					if (current == _tail) {
						_tail = previous;
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
