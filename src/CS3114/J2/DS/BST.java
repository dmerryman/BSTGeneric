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
		 throw new UnsupportedOperationException("Not implemented yet");
	 }
	 
	 private T find(BinaryNode currNode, T elementToFind)
	 {
		 throw new UnsupportedOperationException("Not implemented yet");
	 }
	 
	 // Delete element matching x from the BST, if present. Return true if
	 // matching element is removed from the tree and false otherwise.
	 // Pre: x is null or points to a valid object of type T
	 // Post: the binary tree does not contain x
	 public boolean insert(T x)
	 {
		 throw new UnsupportedOperationException("Not implemented yet");
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
}
