package structures;

import linkedList.ListNodeI;

public class SimpleNode<E> implements ListNodeI<E> {

	protected E _value;
	protected ListNodeI<E> _next;


	public SimpleNode() {
		_next = null;
	}

	public SimpleNode(E value) {
		this();
		_value = value;
	}

	@Override
	public E getValue() {
		return _value;
	}

	@Override
	public ListNodeI<E> getNext() {
		return _next;
	}

	@Override
	public void setValue(E theNewValue) {
		_value = theNewValue;
	}

	@Override
	public void setNext(ListNodeI<E> theNewNext) {
		_next = theNewNext;
	}

	public boolean hasNext() {
		return _next != null;
	}
}
