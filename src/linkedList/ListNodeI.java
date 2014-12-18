package linkedList;
/**
 * MY NAME
 * @param <E> type of __value
 */
public interface ListNodeI<E> {
	public E getValue( );
	public ListNodeI<E> getNext( );
	public void setValue(E theNewValue);
	public void setNext(ListNodeI<E> theNewNext)	;
}
