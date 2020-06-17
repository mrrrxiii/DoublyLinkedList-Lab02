package datastructure.doublylinkedlist;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/*
 * In this test, when the method tested does not updated the list, 
 * the test will run under three conditions:
 * 1. list is empty
 * 2. list only contains null
 * 3. list contains normal data
 * 
 */
class TestDoublyLinkedList {
	List<String> actualListEmpty;
	List<String> actualListNull;
	List<String> actualListNormal;
	List<String> expectedList;
	List<String> actualList;

	@BeforeEach
	void init() {
		actualListEmpty = new LinkedList<>();
		actualListNull = new LinkedList<>(Arrays.asList(null, null, null));
		actualListNormal = new LinkedList<>(Arrays.asList("First", "Second", "Second"));
		expectedList = new LinkedList<>();
		actualList = new LinkedList<>();
	}

	@Nested
	class testSize {
		@Test
		void whenEmpty() {
			assertEquals(0, actualListEmpty.size(), "Size should be 0");
		}

		@Test
		void whenOnlyNull() {
			assertEquals(3, actualListNull.size(), "Size should be 3");
		}

		@RepeatedTest(5)
		void whenNormal() {
			int expectedSize = new Random().nextInt(1000) + 1;
			System.out.println("Expected size is " + expectedSize);
			ArrayList<String> auxList = new ArrayList<>();
			for (int i = 0; i < expectedSize; i++) {
				auxList.add(String.valueOf(i + 1));
			}
			actualListNormal = new LinkedList<>(auxList);
			assertEquals(expectedSize, actualListNormal.size(),
					"Size should be " + expectedSize + ", but actual is" + actualListNormal.size());
		}

	}

	@Nested
	class testContains {
		@Test
		void whenEmpty() {
			assertAll(() -> assertFalse(actualListEmpty.contains(""), "Empty String should not exist"),
					() -> assertFalse(actualListEmpty.contains(null), "Null should not exist"),
					() -> assertFalse(actualListEmpty.contains("Third"), "String of 'Third' should not exist"),
					() -> assertFalse(actualListEmpty.contains(new Date(0)), "Date object should not exist"));
		}

		@Test
		void whenOnlyNull() {
			assertAll(() -> assertFalse(actualListNull.contains(""), "Empty String should not exist"),
					() -> assertTrue(actualListNull.contains(null), "Null should exist"),
					() -> assertFalse(actualListNull.contains("Third"), "String of 'Third' should not exist"),
					() -> assertFalse(actualListNull.contains(new Date(0)), "Date object should not exist"));
		}

		@Test
		void whenNormal() {
			assertAll(() -> assertTrue(actualListNormal.contains("First"), "String of 'First' should exist"),
					() -> assertTrue(actualListNormal.contains("Second"), "String of 'Second' should exist"),
					() -> assertFalse(actualListNormal.contains(""), "Empty String should not exist"),
					() -> assertFalse(actualListNormal.contains(null), "Null should not exist"),
					() -> assertFalse(actualListNormal.contains("Third"), "String of 'Third' should not exist"),
					() -> assertFalse(actualListNormal.contains(new Date(0)), "Date object should not exist"));
		}

	}

	@Nested
	class testAddE {

		@Test
		void addNull() {
			ArrayList<String> auxList = new ArrayList<>();
			auxList.add(null);
			expectedList = new LinkedList<>(auxList);

			assertAll(() -> assertTrue(actualList.add(null), "Return of the method should be true"),
					() -> assertEquals(expectedList, actualList, "The list should be like {null}"));

		}

		@Test
		void addEmptyString() {
			expectedList = new LinkedList<>(Arrays.asList(""));
			assertAll(() -> assertTrue(actualList.add(""), "Return of the method should be true"),
					() -> assertEquals(expectedList, actualList, "The list should be like {''}"));

		}

		@Test
		void addOnce() {
			expectedList = new LinkedList<>(Arrays.asList("First"));
			assertAll(() -> assertTrue(actualList.add("First"), "Return of the method should be true"),
					() -> assertEquals(expectedList, actualList, "The list should be like {'First'}"));

		}

		@Test
		void addMulti() {
			expectedList = new LinkedList<>(Arrays.asList("First", "Second", "Third"));
			assertAll(() -> assertTrue(actualList.add("First"), "Return of the method should be true"),
					() -> assertTrue(actualList.add("Second"), "Return of the method should be true"),
					() -> assertTrue(actualList.add("Third"), "Return of the method should be true"),
					() -> assertEquals(expectedList, actualList, "The list should be like {'First','Second','Third'}"));

		}

	}

	@Nested
	class testClear {

		@Test
		void clearEmpty() {
			actualList.clear();
			assertEquals(expectedList, actualList, "The list should be empty");
		}

		@Test
		void clearNormal() {
			actualListNormal.clear();
			assertEquals(expectedList, actualListNormal, "The list should be empty");
		}

	}

	@Nested
	class testSet {
		@Test
		void setNull() {
			assertAll(
					() -> assertEquals("First", actualListNormal.set(0, null),
							"Return of the method should be String of 'First'"),
					() -> assertEquals("Second", actualListNormal.set(1, null),
							"Return of the method should be String of 'Second'"),
					() -> assertEquals("Second", actualListNormal.set(2, null),
							"Return of the method should be String of 'Second'"),
					() -> assertEquals(actualListNull, actualListNormal, "The list should be like {null,null,null}"));
		}

		@Test
		void setNormal() {
			expectedList = new LinkedList<>(Arrays.asList("NewFirst", "NewSecond", "Third"));
			assertAll(
					() -> assertEquals("First", actualListNormal.set(0, "NewFirst"),
							"Return of the method should be String of 'First'"),
					() -> assertEquals("Second", actualListNormal.set(1, "NewSecond"),
							"Return of the method should be String of 'Second'"),
					() -> assertEquals("Second", actualListNormal.set(2, "Third"),
							"Return of the method should be String of 'Second'"),
					() -> assertEquals(expectedList, actualListNormal,
							"The list should be like {'First','NewSecond','Third'}"));
		}

		@Test
		void setOutBoundary() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.set(-1, "negative")),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.set(100, "too far"))

			);
		}

	}

	@Nested
	class testAddIntE {

		@Test
		void addNull() {
			ArrayList<String> auxList = new ArrayList<>();
			auxList.add(null);
			expectedList = new LinkedList<>(auxList);
			actualList.add(0, null);
			assertEquals(expectedList, actualList, "The list should be like {null}");

		}

		@Test
		void addEmptyString() {
			expectedList = new LinkedList<>(Arrays.asList(""));
			actualList.add(0, "");
			assertEquals(expectedList, actualList, "The list should be like {''}");

		}

		@Test
		void addOnce() {
			expectedList = new LinkedList<>(Arrays.asList("First"));
			actualList.add(0, "First");
			assertEquals(expectedList, actualList, "The list should be like {'First'}");

		}

		@Test
		void addMulti() {
			expectedList = new LinkedList<>(Arrays.asList("First", "Second", "Third"));
			actualList.add(0, "Third");
			actualList.add(0, "Second");
			actualList.add(0, "First");
			assertEquals(expectedList, actualList, "The list should be like {'First','Second','Third'}");

		}

		@Test
		void addOutBoundary() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.add(-1, "negative")),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.add(100, "too far"))

			);
		}

	}

	@Nested
	class testRemoveInt {

		@Test
		void removeNull() {
			assertFalse(actualListNormal.remove(null), "Return of this method should be false");
		}

		@Test
		void removeOnce() {
			expectedList = new LinkedList<>(Arrays.asList("First", "Second"));
			assertAll(() -> assertEquals("Second", actualListNormal.remove(2), "Reurn of this method should be Second"),
					() -> assertEquals(expectedList, actualListNormal, "The list should be like {'First','Second'}")

			);

		}

		@Test
		void removeMulti() {
			expectedList = new LinkedList<>(Arrays.asList("Second"));
			assertAll(() -> assertEquals("First", actualListNormal.remove(0), "Reurn of this method should be Second"),
					() -> assertEquals("Second", actualListNormal.remove(0), "Reurn of this method should be Second"),
					() -> assertEquals(expectedList, actualListNormal, "The list should be like {'Second'}")

			);

		}

		@Test
		void removeInvalid() {

			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.remove(-1)),

					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.remove(100))

			);
		}

	}

	@Nested
	class testLastIndexof {

		@Test
		void whenEmpty() {
			assertAll(
					() -> assertEquals(-1, actualListEmpty.lastIndexOf(""), "Empty String should not exist, return -1"),
					() -> assertEquals(-1, actualListEmpty.lastIndexOf(null), "Null should not exist, return -1"),
					() -> assertEquals(-1, actualListEmpty.lastIndexOf("Third"),
							"String of 'Third' should not exist, return -1"),
					() -> assertEquals(-1, actualListEmpty.lastIndexOf(new Date(0)),
							"Date object should not exist, return -1"));
		}

		@Test
		void whenOnlyNull() {
			assertAll(
					() -> assertEquals(-1, actualListNull.lastIndexOf(""), "Empty String should not exist, return -1"),
					() -> assertEquals(2, actualListNull.lastIndexOf(null),
							"Last Null should exist at index 2, return 2"),
					() -> assertEquals(-1, actualListNull.lastIndexOf("Third"),
							"String of 'Third' should not exist, return -1"),
					() -> assertEquals(-1, actualListNull.lastIndexOf(new Date(0)),
							"Date object should not exist, return -1"));
		}

		@Test
		void whenNormal() {
			assertAll(
					() -> assertEquals(0, actualListNormal.lastIndexOf("First"),
							"Last String of 'First' should exist at index 0"),
					() -> assertEquals(2, actualListNormal.lastIndexOf("Second"),
							"Last String of 'Second' should exist at index 2"),
					() -> assertEquals(-1, actualListNormal.lastIndexOf(""),
							"Empty String should not exist, return -1"),
					() -> assertEquals(-1, actualListNormal.lastIndexOf(null), "Null should not exist, return -1"),
					() -> assertEquals(-1, actualListNormal.lastIndexOf("Third"),
							"String of 'Third' should not exist, return -1"),
					() -> assertEquals(-1, actualListNormal.lastIndexOf(new Date(0)),
							"Date object should not exist, return -1"));
		}

	}

	@Nested
	class testListIteratorInt {
		@Test
		void whenEmpty() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.listIterator(-1)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.listIterator(100)),

					() -> assertFalse(actualListEmpty.listIterator(0).hasNext(), "The list should be empty"),
					() -> assertFalse(actualListEmpty.listIterator(0).hasPrevious(), "The list should be empty"));
		}

		@Test
		void whenOnlyNull() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.listIterator(-1)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.listIterator(100)),
					() -> assertEquals(0, actualListNull.listIterator(0).nextIndex(),
							"The next cursor should be at first of the list"),
					() -> assertEquals(1, actualListNull.listIterator(1).nextIndex(),
							"The next cursor should be at index of 1"),
					() -> assertEquals(2, actualListNull.listIterator(2).nextIndex(),
							"The next cursor should be at last of the list"));
		}

		@Test
		void whenNormal() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.listIterator(-1)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.listIterator(100)),
					() -> assertEquals(0, actualListNull.listIterator(0).nextIndex(),
							"The next cursor should be at first of the list"),
					() -> assertEquals(1, actualListNull.listIterator(1).nextIndex(),
							"The next cursor should be at index of 1"),
					() -> assertEquals(2, actualListNull.listIterator(2).nextIndex(),
							"The next cursor should be at last of the list"));
		}

	}

	@Nested
	class testSubList {
		@Test
		void whenEmpty() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.subList(-1, 0)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.subList(0, 100)),
					() -> assertEquals(expectedList, actualListEmpty.subList(0, 0), "The list should be empty"));
		}

		@Test
		void whenOnlyNull() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.subList(-1, 0)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.subList(0, 100)),
					() -> assertThrows(IllegalArgumentException.class, () -> actualListNull.subList(1, 0)),
					() -> assertEquals(expectedList, actualListNull.subList(1, 1), "The list should be empty"),
					() -> assertEquals(new LinkedList<String>(Arrays.asList(null, null)), actualListNull.subList(0, 2),
							"The list should {null, null}"));
		}

		@Test
		void whenNormal() {
			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.subList(-1, 0)),
					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.subList(0, 100)),
					() -> assertThrows(IllegalArgumentException.class, () -> actualListNormal.subList(1, 0)),
					() -> assertEquals(expectedList, actualListNormal.subList(1, 1), "The list should be empty"),
					() -> assertEquals(new LinkedList<String>(Arrays.asList("First", "Second")),
							actualListNormal.subList(0, 2), "The list should {'First','Second'}"));
		}

	}

}
