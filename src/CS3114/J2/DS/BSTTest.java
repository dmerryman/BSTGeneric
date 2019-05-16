package CS3114.J2.DS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTTest {

	@Test
	void testBST() {
		BST<Integer> testBST = new BST<Integer>();
		assertNull(testBST.getRoot());
		assertNull(testBST.getPool());
		assertEquals(0, testBST.getPoolSize());
	}

	@Test
	void testBSTInt() {
		BST<Integer> testBST = new BST<Integer>(5);
		assertNull(testBST.getRoot());
		assertNull(testBST.getPool());
		assertEquals(5, testBST.getPoolSize());
	}

	@Test
	void testIsEmpty() {
		BST<Integer> testBST = new BST<Integer>();
		assertTrue(testBST.isEmpty());
	}

	@Test
	void testFind() {
		BST<Integer> testTree = new BST<Integer>();
		testTree.insert(50);
		testTree.insert(30);
		testTree.insert(40);
		testTree.insert(75);
		testTree.insert(60);
		testTree.insert(80);
		testTree.insert(55);
		testTree.insert(70);
		assertNull(testTree.find(500));
		assertEquals(40, testTree.find(40));
		assertEquals(60, testTree.find(60));
		assertEquals(55, testTree.find(55));
	}

	@Test
	void testInsert() {
		BST<Integer> testTree = new BST<Integer>();
		assertTrue(testTree.insert(50));
		assertEquals(0, testTree.getRoot().compareTo(50));
		assertFalse(testTree.insert(50));
		assertEquals(0, testTree.getRoot().compareTo(50));
		assertTrue(testTree.insert(30));
		assertTrue(testTree.insert(40));
		assertTrue(testTree.insert(75));
		assertTrue(testTree.insert(60));
		assertTrue(testTree.insert(80));
		assertTrue(testTree.insert(55));
		assertTrue(testTree.insert(70));
		assertEquals(0, testTree.getRoot().compareTo(50));
	}
	
	@Test
	void testRemove()
	{
		BST<Integer> testTree = new BST<Integer>();
		testTree.insert(50);
		testTree.insert(30);
		testTree.insert(40);
		testTree.insert(75);
		testTree.insert(60);
		testTree.insert(80);
		testTree.insert(55);
		testTree.insert(70);
		assertTrue(testTree.remove(80));
		assertTrue(testTree.remove(30));
		assertTrue(testTree.remove(50));
		assertEquals(55, testTree.getRoot().GetElement());
		assertTrue(testTree.remove(55));
		assertEquals(60, testTree.getRoot().GetElement());
		//testTree.printTree();
		//assertTrue(testTree.remove(50));
	}

	@Test
	void testCapWith() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

}
