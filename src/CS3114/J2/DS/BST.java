package CS3114.J2.DS;

public class BST<T extends Comparable<? super T>> {
	// BST<> provides a generic implementation of a binary search tree
	//
	// BST<> implementation constraints:
	// - The tree uses package access for root, and for the node type.
	// - The node type uses package access for its data members.
	// - The tree never stores two objects for which compareTo() returns 0.
	// - All tree traversals are performed recursively.
	// - Optionally, the BST<> employs a pool of deleted nodes.
	// If so, when an insertion is performed, a node from the pool is used
	// unless the pool is empty, and when a deletion is performed, the
	// (cleaned) deleted node is added to the pool, unless the pool is
	// full. The maximum size of the pool is set via the constructor.
	//
	// User data type (T) constraints:
	// - T implements compareTo() and equals() appropriately
	// - compareTo() and equals() are consistent; that is, compareTo()
	// returns 0 in exactly the same situations equals() returns true
	//
	class BinaryNode {
		// Initialize a childless binary node.
		// Pre:		elem is not null
		// Post:	(in the new node)
		//			element == elem
		//			left == right == null
		private T element;
		private BinaryNode left;
		private BinaryNode right;
		public BinaryNode(T elem)
		{
			element = elem;
		}
		
		// Initialize a binary node with children.
		 // Pre: elem is not null
		 // Post: (in the new node)
		 // element == elem
		 // left == lt, right == rt 
		public BinaryNode(T elem, BinaryNode lt, BinaryNode rt)
		{
			element = elem;
			left = lt;
			right = rt;
		}
		
		public int compareTo(T x)
		{
			return element.compareTo(x);
		}
	}
	
	private BinaryNode root;
	private BinaryNode pool;
	private int pSize;
	
	 // Initialize empty BST with no node pool.
	 // Pre: none
	 // Post: (in the new tree)
	 // root == null, pool == null, pSize = 0
	 public BST()
	 {
		 root = null;
		 pool = null;
		 pSize = 0;
	 }
	 
	 // Initialize empty BST with a node pool of up to pSize nodes.
	 // Pre: none
	 // Post: (in the new tree)
	 // root == null, pool = null, pSize == Sz 
	 public BST(int Sz)
	 {
		 root = null;
		 pool = null;
		 pSize = Sz;
	 }
	 
	 // Return true iff BST contains no nodes.
	 // Pre: none
	 // Post: the binary tree is unchanged
	 public boolean isEmpty()
	 {
		 return root == null;
	 }
	 
	 // Return pointer to matching data element, or null if no matching
	 // element exists in the BST. "Matching" should be tested using the
	 // data object's compareTo() method.
	 // Pre: x is null or points to a valid object of type T
	 // Post: the binary tree is unchanged
	 public T find(T x)
	 {
		 return find(root, x);
	 }
	 
	 private T find(BinaryNode currNode, T elementToFind)
	 {
		 if (currNode == null)
		 {
			 return null;
		 }
		 int comparisonResult = currNode.compareTo(elementToFind);
		 if (comparisonResult == 0)
		 {
			 return currNode.element;
		 }
		 else if (comparisonResult > 0)
		 {
			 return find(currNode.left, elementToFind);
		 }
		 else if (comparisonResult < 0)
		 {
			 return find(currNode.right, elementToFind);
		 }
		 return null;
	 }
	 
	 // Delete element matching x from the BST, if present. Return true if
	 // matching element is removed from the tree and false otherwise.
	 // Pre: x is null or points to a valid object of type T
	 // Post: the binary tree does not contain x
	 public boolean insert(T x)
	 {
		 int numElementsBeforeInsert = getNumberOfElementsInTree(root, 0);
		 root = insert(root, x);
		 return (numElementsBeforeInsert + 1 == getNumberOfElementsInTree(root, 0)); 
	 }
	 
	 private BinaryNode insert(BinaryNode currNode, T elementToInsert)
	 {
		 if (currNode == null)
		 {
			 return new BinaryNode(elementToInsert);
		 }
		 int comparisonResult = currNode.compareTo(elementToInsert);
		 if (comparisonResult > 0)
		 {
			 currNode.left = insert(currNode.left, elementToInsert);
		 }
		 else if (comparisonResult < 0)
		 {
			 currNode.right = insert(currNode.right, elementToInsert);
		 }
		 return currNode;
	 }
	 
	 // Delete element matching x from the BST, if present. Return true if
	 // matching element is removed from the tree and false otherwise.
	 // Pre: x is null or points to a valid object of type T
	 // Post: the binary tree does not contain x
	 public boolean remove(T x)
	 {
		 int numElementsBeforeRemoval = getNumberOfElementsInTree(root, 0);
		 root = remove(root, x);
		 return (numElementsBeforeRemoval - 1 == getNumberOfElementsInTree(root, 0));
	 }
	 
	 private BinaryNode remove(BinaryNode currNode, T elementToRemove)
	 {
		 if (currNode == null)
		 {
			 return currNode;
		 }
		 int comparisonResult = currNode.compareTo(elementToRemove);
		 if (comparisonResult == 0)
		 {
			 // Element found.
			 currNode = deleteHelper(currNode);
		 }
		 else if (comparisonResult > 0)
		 {
			 // Look left.
			 currNode.left = remove(currNode.left, elementToRemove);
		 }
		 else if (comparisonResult < 0)
		 {
			 // Look right.
			 currNode.right = remove(currNode.right, elementToRemove);
		 }
		 return currNode;
	 }
	 
	 // Pre: currNode is not null.
	 private BinaryNode deleteHelper(BinaryNode currNode)
	 {
		 int numberOfChildren = getNumberOfChildren(currNode);
		 if (numberOfChildren == 0)
		 {
			 // Node is a leaf.
			 currNode = null;
		 }
		 else if (numberOfChildren == 1)
		 {
			 if (currNode.left != null)
			 {
				 currNode = currNode.left;
			 }
			 else if (currNode.right != null)
			 {
				 currNode = currNode.right;
			 }
		 }
		 else
		 {
			 // two children exists.
			 return deleteRightMinimum(currNode.right);
			 //throw new UnsupportedOperationException("Not implemented yet");
		 }
		 return currNode;
	 }
	 
	 private BinaryNode deleteRightMinimum(BinaryNode currNode)
	 {
		 if (currNode.left.left != null)
		 {
			 deleteRightMinimum(currNode.left);
		 }
		 BinaryNode tempBinaryNode = currNode.left;
		 currNode.left = null;
		 return tempBinaryNode;
		 //throw new UnsupportedOperationException("Not implemented yet");
	 }
	 
	 private int getNumberOfChildren(BinaryNode node)
	 {
		 if ((node.left == null) && (node.right == null))
		 	{
			 return 0;
			}
		 else if ((node.left != null) && (node.right != null))
		 {
			 return 2;
		 }
		 else
		 {
			 return 1;
		 }
	 }
	 
	 // Remove from the tree all values y such that y > x, according to
	 // compareTo().
	 // Pre: x is null or points to a valid object of type T
	 // Post: the tree contains no value y such that compareTo()
	 // indicates y > x
	 public void capWith(T x)
	 {
		 throw new UnsupportedOperationException("Not implemented yet");
	 }
	 
	 // Return true iff other is a BST that has the same physical structure
	 // and stores equal data values in corresponding nodes. "Equal" should
	 // be tested using the data object's equals() method.
	 // Pre: other is null or points to a valid BST<> object, instantiated
	 // on the same data type as the tree on which equals() is invoked
	 // Post: both binary trees are unchanged
	 public boolean equals(Object other)
	 {
		 throw new UnsupportedOperationException("Not implemented yet");
	 }
	 
	 public BinaryNode getRoot()
	 {
		 return root;
	 }
	 
	 public BinaryNode getPool()
	 {
		 return pool;
	 }
	 
	 public int getPoolSize()
	 {
		 return pSize;
	 }
	 
	 private int getNumberOfElementsInTree(BinaryNode currNode, int elementCount)
	 {
		 if (currNode != null)
		 {
			 elementCount++;
			 elementCount = getNumberOfElementsInTree(currNode.left, elementCount);
			 elementCount = getNumberOfElementsInTree(currNode.right, elementCount);
		 }
		 return elementCount;
	 }
}
