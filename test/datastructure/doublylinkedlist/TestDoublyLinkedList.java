package datastructure.doublylinkedlist;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
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
	DoublyLinkedList<String> actualListEmpty;
	DoublyLinkedList<String> actualListNull;
	DoublyLinkedList<String> actualListNormal;
	DoublyLinkedList<String> expectedList;
	DoublyLinkedList<String> actualList;

	@BeforeEach
	void init() {
		actualListEmpty = new DoublyLinkedList<>();

		actualListNormal = new DoublyLinkedList<>();
		actualListNormal.add("First");
		actualListNormal.add("Second");
		actualListNormal.add("Tenth");
		expectedList = new DoublyLinkedList<>();
		actualList = new DoublyLinkedList<>();
	}

	@Nested
	class testSize {
		@Test
		void whenEmpty() {
			assertEquals(0, actualListEmpty.size(), "Size should be 0");
		}

		@RepeatedTest(5)
		void whenNormal() {
			int expectedSize = new Random().nextInt(1000) + 1;
			System.out.println("Expected size is " + expectedSize);
			actualListNormal.clear();
			for (int i = 0; i < expectedSize; i++) {
				actualListNormal.add(String.valueOf(i + 1));
			}

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
		void whenNormal() {

			assertAll(() -> assertTrue(actualListNormal.contains("First"), "String of 'First' should exist"),
					() -> assertTrue(actualListNormal.contains("Second"), "String of 'Second' should exist"),
					() -> assertTrue(actualListNormal.contains("Tenth"), "String of 'Tenth' should exist"),
					() -> assertFalse(actualListNormal.contains(""), "Empty String should not exist"),
					() -> assertFalse(actualListNormal.contains(null), "Null should not exist"),
					() -> assertFalse(actualListNormal.contains("Third"), "String of 'Third' should not exist"),
					() -> assertFalse(actualListNormal.contains(new Date(0)), "Date object should not exist"));
		}

	}

	@Nested
	class testAddE {

		@Test
		void addEmptyString() {

			assertAll(() -> assertTrue(actualList.add(""), "Return of the method should be true"),
					() -> assertEquals(1, actualList.size(), "Size should be 1"),
					() -> assertEquals("", actualList.get(0), "Index 0 element should be empty string"));

		}

		@Test
		void addOnce() {

			assertAll(() -> assertTrue(actualList.add("First"), "Return of the method should be true"),
					() -> assertEquals(1, actualList.size(), "Size should be 1"),
					() -> assertEquals("First", actualList.get(0), "Index 0 element should be 'First'"));

		}

		@Test
		void addMulti() {

			assertAll(() -> assertTrue(actualList.add("First"), "Return of the method should be true"),
					() -> assertTrue(actualList.add("Second"), "Return of the method should be true"),
					() -> assertTrue(actualList.add("Third"), "Return of the method should be true"),
					() -> assertEquals(3, actualList.size(), "Size should be 1"),
					() -> assertEquals("First", actualList.get(0), "Index 0 element should be 'First'"),
					() -> assertEquals("Second", actualList.get(1), "Index 0 element should be 'Second'"),
					() -> assertEquals("Third", actualList.get(2), "Index 0 element should be 'Third'"));

		}

	}

	@Nested
	class testClear {

		@Test
		void clearEmpty() {
			actualList.clear();
			assertAll(() -> assertEquals(0, actualList.size(), "The list size should be 0"),
					() -> assertNull(actualList.get(0), "The return should be null"));
		}

		@Test
		void clearNormal() {
			actualListNormal.clear();
			assertAll(() -> assertEquals(0, actualList.size(), "The list size should be 0"),
					() -> assertNull(actualList.get(0), "The return should be null"));
		}

	}

	@Nested
	class testSet {

		@Test
		void setNormal() {
			expectedList = new DoublyLinkedList<String>();
			expectedList.add("NewFirst");
			expectedList.add("NewSecond");
			expectedList.add("Third");
			assertAll(
					() -> assertTrue("First".equals(actualListNormal.set(0, "NewFirst")),
							"Return of the method should be String of 'First'"),
					() -> assertTrue("Second".equals(actualListNormal.set(1, "NewSecond")),
							"Return of the method should be String of 'Second'"),
					() -> assertTrue("Tenth".equals(actualListNormal.set(2, "Third")),
							"Return of the method should be String of 'Second'"),
					() -> assertEquals("NewFirst", actualListNormal.get(0),
							"The Index 0 element should be like 'NewFirst'"),
					() -> assertEquals("NewSecond", actualListNormal.get(1),
							"The Index 1 element should be like 'NewFirst'"),
					() -> assertEquals("Third", actualListNormal.get(2),
							"The Index 2 element should be like 'NewFirst'"));
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
		void addEmptyString() {

			actualList.add(0, "");
			assertEquals("", actualList.get(0), "The index 0 element should be ''");

		}

		@Test
		void addOnce() {
			actualList.add(0, "First");
			assertEquals("First", actualList.get(0), "The index 0 elemnt should be like 'First'");

		}

		@Test
		void addMulti() {

			actualListNormal.add(0, "Third");
			actualListNormal.add(1, "NewSecond");

			assertAll(
					() -> assertEquals("Third", actualListNormal.get(0), "The Index 0 element should be like 'Third'"),
					() -> assertEquals("NewSecond", actualListNormal.get(1),
							"The Index 1 element should be like 'NewSecond'"),
					() -> assertEquals("First", actualListNormal.get(2), "The Index 2 element should be like 'First'"),
					() -> assertEquals("Second", actualListNormal.get(3),
							"The Index 3 element should be like 'Second'"),
					() -> assertEquals("Tenth", actualListNormal.get(4), "The Index 4 element should be like 'Tenth'"));

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
		void removeOnce() {
			assertAll(() -> assertEquals("Tenth", actualListNormal.remove(2), "Return of this method should be Tenth"),
					() -> assertEquals(2, actualListNormal.size(), "The size should be 2"),
					() -> assertEquals("First", actualListNormal.get(0), "The Index 0 element should be like 'First'"),
					() -> assertEquals("Second", actualListNormal.get(1), "The Index 1 element should be like 'Second'")

			);

		}

		@Test
		void removeMulti() {

			assertAll(() -> assertEquals("Second", actualListNormal.remove(1), "Reurn of this method should be Second"),
					() -> assertEquals("Tenth", actualListNormal.remove(1), "Reurn of this method should be Tenth"),
					() -> assertEquals("First", actualListNormal.get(0), "The Index 0 element should be like 'First'"),
					() -> assertEquals(1, actualListNormal.size(), "The size should be 1")

			);

		}

		@Test
		void removeInvalid() {

			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.remove(-1)),

					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.remove(100))

			);
		}

	}

//	@Nested
//	class testLastIndexof {
//
//		@Test
//		void whenEmpty() {
//			assertAll(
//					() -> assertEquals(-1, actualListEmpty.lastIndexOf(""), "Empty String should not exist, return -1"),
//					() -> assertEquals(-1, actualListEmpty.lastIndexOf(null), "Null should not exist, return -1"),
//					() -> assertEquals(-1, actualListEmpty.lastIndexOf("Third"),
//							"String of 'Third' should not exist, return -1"),
//					() -> assertEquals(-1, actualListEmpty.lastIndexOf(new Date(0)),
//							"Date object should not exist, return -1"));
//		}
//
//		@Test
//		void whenOnlyNull() {
//			assertAll(
//					() -> assertEquals(-1, actualListNull.lastIndexOf(""), "Empty String should not exist, return -1"),
//					() -> assertEquals(2, actualListNull.lastIndexOf(null),
//							"Last Null should exist at index 2, return 2"),
//					() -> assertEquals(-1, actualListNull.lastIndexOf("Third"),
//							"String of 'Third' should not exist, return -1"),
//					() -> assertEquals(-1, actualListNull.lastIndexOf(new Date(0)),
//							"Date object should not exist, return -1"));
//		}
//
//		@Test
//		void whenNormal() {
//			assertAll(
//					() -> assertEquals(0, actualListNormal.lastIndexOf("First"),
//							"Last String of 'First' should exist at index 0"),
//					() -> assertEquals(2, actualListNormal.lastIndexOf("Second"),
//							"Last String of 'Second' should exist at index 2"),
//					() -> assertEquals(-1, actualListNormal.lastIndexOf(""),
//							"Empty String should not exist, return -1"),
//					() -> assertEquals(-1, actualListNormal.lastIndexOf(null), "Null should not exist, return -1"),
//					() -> assertEquals(-1, actualListNormal.lastIndexOf("Third"),
//							"String of 'Third' should not exist, return -1"),
//					() -> assertEquals(-1, actualListNormal.lastIndexOf(new Date(0)),
//							"Date object should not exist, return -1"));
//		}
//
//	}
//
//	@Nested
//	class testListIteratorInt {
//		@Test
//		void whenEmpty() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.listIterator(-1)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.listIterator(100)),
//
//					() -> assertFalse(actualListEmpty.listIterator(0).hasNext(), "The list should be empty"),
//					() -> assertFalse(actualListEmpty.listIterator(0).hasPrevious(), "The list should be empty"));
//		}
//
//		@Test
//		void whenOnlyNull() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.listIterator(-1)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.listIterator(100)),
//					() -> assertEquals(0, actualListNull.listIterator(0).nextIndex(),
//							"The next cursor should be at first of the list"),
//					() -> assertEquals(1, actualListNull.listIterator(1).nextIndex(),
//							"The next cursor should be at index of 1"),
//					() -> assertEquals(2, actualListNull.listIterator(2).nextIndex(),
//							"The next cursor should be at last of the list"));
//		}
//
//		@Test
//		void whenNormal() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.listIterator(-1)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.listIterator(100)),
//					() -> assertEquals(0, actualListNull.listIterator(0).nextIndex(),
//							"The next cursor should be at first of the list"),
//					() -> assertEquals(1, actualListNull.listIterator(1).nextIndex(),
//							"The next cursor should be at index of 1"),
//					() -> assertEquals(2, actualListNull.listIterator(2).nextIndex(),
//							"The next cursor should be at last of the list"));
//		}
//
//	}
//
//	@Nested
//	class testSubList {
//		@Test
//		void whenEmpty() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.subList(-1, 0)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListEmpty.subList(0, 100)),
//					() -> assertEquals(expectedList, actualListEmpty.subList(0, 0), "The list should be empty"));
//		}
//
//		@Test
//		void whenOnlyNull() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.subList(-1, 0)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNull.subList(0, 100)),
//					() -> assertThrows(IllegalArgumentException.class, () -> actualListNull.subList(1, 0)),
//					() -> assertEquals(expectedList, actualListNull.subList(1, 1), "The list should be empty"),
//					() -> assertEquals(new LinkedList<String>(Arrays.asList(null, null)), actualListNull.subList(0, 2),
//							"The list should {null, null}"));
//		}
//
//		@Test
//		void whenNormal() {
//			assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.subList(-1, 0)),
//					() -> assertThrows(IndexOutOfBoundsException.class, () -> actualListNormal.subList(0, 100)),
//					() -> assertThrows(IllegalArgumentException.class, () -> actualListNormal.subList(1, 0)),
//					() -> assertEquals(expectedList, actualListNormal.subList(1, 1), "The list should be empty"),
//					() -> assertEquals(new LinkedList<String>(Arrays.asList("First", "Second")),
//							actualListNormal.subList(0, 2), "The list should {'First','Second'}"));
//		}
//
//	}

}
