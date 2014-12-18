import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedListsTests {

	@Test
	public void canAdd() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		String value = "Hello";
		list.add(value);
		assertEquals(list.getHead().getValue(), value);
	}

	@Test
	public void canGetNext() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		String value = "Hello";
		String second = "Goodbye";
		list.add(value);
		list.add(second);
		assertEquals(list.getHead().getNext().getValue(), second);
	}

	@Test
	public void canConfirmContains() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		String value = "Hello";
		String second = "Goodbye";
		String not = "See you";
		list.add(value);
		list.add(second);
		assertTrue(list.contains(value));
		assertTrue(list.contains(second));
		assertFalse(list.contains(not));
	}

	@Test
	public void canConvertToArray() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		String value = "Hello";
		String second = "Goodbye";
		list.add(value);
		list.add(second);
		assertArrayEquals(new String[] {
			value, second
		}, list.toArray());
	}

	@Test
	public void canConfirmEmpty() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		assertTrue(list.isEmpty());
		list.add("Test");
		assertFalse(list.isEmpty());
	}

	@Test
	public void canGetSize() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		assertEquals(list.size(), 0);
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		assertEquals(list.size(), 5);
		list.add("1");
		assertEquals(list.size(), 6);
	}

	@Test
	public void canIterate() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("a");
		list.add("bc");
		list.add("def");
		list.add("ghij");
		list.add("klmno");
		String cat = "";
		for (String string: list) {
			cat += string;
		}
		assertEquals(cat, "abcdefghijklmno");
	}

	@Test
	public void canRemove() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("a");
		list.add("bc");
		list.add("def");
		list.add("ghij");
		list.add("klmno");
		boolean parity = false;
		String cat = "";
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			if (parity = !parity) {
				iterator.remove();
			}
		}
		for (String string: list) {
			cat += string;
		}
		assertEquals(cat, "bcghij");
	}

	@Test
	public void isInstanceConfined() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		MyLinkedList<String> other = new MyLinkedList<String>();
		list.add("a");
		list.add("bc");
		list.add("def");
		list.add("ghij");
		list.add("klmno");
		other.add("1");
		other.add("23");
		other.add("456");
		other.add("78910");
		other.add("1112131415");
		String listcat = "";
		String othercat = "";
		for (String string: list) {
			listcat += string;
		}
		for (String string: other) {
			othercat += string;
		}
		assertEquals(listcat, "abcdefghijklmno");
		assertEquals(othercat, "123456789101112131415");
	}

}
