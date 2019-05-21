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
		public T element;
		public BinaryNode left;
		public BinaryNode right;
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
		
		public T GetElement()
		{
			return element;
		}
	}
	
	public BinaryNode root;
	public BinaryNode pool;
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
			 if (pSize == 0)
			 {
				 currNode = new BinaryNode(elementToInsert);
			 }
			 else
			 {
				 // Insert from the pool if possible.
				 BinaryNode tempNode = getElementFromPool(pool, elementToInsert);
				 if (tempNode != null)
				 {
					 removeElementFromPool(elementToInsert);
					 tempNode.right = null;
					 currNode = tempNode;
				 }
				 else
				 {
					 currNode = new BinaryNode(elementToInsert);
				 }
			 }
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
	 
	 private BinaryNode getElementFromPool(BinaryNode currPool, T elementToCheckFor)
	 {
		 if (currPool == null)
		 {
			 return currPool;
		 }
		 int comparisonResult = currPool.compareTo(elementToCheckFor);
		 if (comparisonResult == 0)
		 {
			 // remove and return node.
			 return currPool;
		 }
		 else if (comparisonResult < 0)
		 {
			 // go right
			 return getElementFromPool(currPool.right, elementToCheckFor);
		 }
		 else if (comparisonResult > 0)
		 {
			 // not found.
			 return null;
		 }
		 return null;
	 }
	 
	 private void removeElementFromPool(T elementToRemove)
	 {
		 pool = removeElementFromPoolHelper(pool, elementToRemove);
	 }
	 
	 private BinaryNode removeElementFromPoolHelper(BinaryNode currPool, T elementToRemove)
	 {
		 if (currPool == null)
		 {
			 return currPool;
		 }
		 int comparisonResult = currPool.compareTo(elementToRemove);
		 if (comparisonResult == 0)
		 {
			 currPool = currPool.right;
		 }
		 else if (comparisonResult < 0)
		 {
			 currPool.right = removeElementFromPoolHelper(currPool.right, elementToRemove);
		 }
		 else if (comparisonResult > 0)
		 {
			 // Element not found. This shouldn't execute, assuming we just added an element
			 // from the pool.
		 }
		 return currPool;
	 }
	 
	 // Delete element matching x from the BST, if present. Return true if
	 // matching element is removed from the tree and false otherwise.
	 // Pre: x is null or points to a valid object of type T
	 // Post: the binary tree does not contain x
	 public boolean remove(T x)
	 {
		 int numElementsBeforeRemoval = getNumberOfElementsInTree(root, 0);
		 //System.out.println("Tree has " + numElementsBeforeRemoval + " nodes before removal.");
		 root = remove(root, x);
		 //System.out.println("Tree has " +  getNumberOfElementsInTree(root, 0) + " nodes after removal");
		 return (numElementsBeforeRemoval - 1 == getNumberOfElementsInTree(root, 0));
	 }
	 
	 private BinaryNode remove(BinaryNode currNode, T elementToRemove)
	 {
		 if (currNode == null)
		 {
			 //System.out.println(" Element not found");
			 return currNode;
		 }
		 //System.out.println(" Comparing " + elementToRemove + " to " + currNode.element);
		 int comparisonResult = currNode.compareTo(elementToRemove);
		 if (comparisonResult == 0)
		 {
			 // Element found.
			 //System.out.println("  Removing element");
//			 if (pSize != 0)
//			 {
//				 // Add node to the pool if theres space.
//				 //System.out.println(" Attempting to insert " + elementToRemove + " into pool");
//				 insertIntoPool(currNode);
//			 }
			 BinaryNode tempNode = currNode;
			 currNode = deleteHelper(currNode); 
			 if (pSize != 0)
			 {
				 // Add node to the pool if theres space.
				 insertIntoPool(tempNode);
			 }
			 
		 }
		 else if (comparisonResult > 0)
		 {
			 // Look left.
			 //System.out.println("  Proceeding left");
			 currNode.left = remove(currNode.left, elementToRemove);
		 }
		 else if (comparisonResult < 0)
		 {
			 // Look right.
			 //System.out.println("  Proceeding right");
			 currNode.right = remove(currNode.right, elementToRemove);
		 }
		 return currNode;
	 }
	 
	 private void insertIntoPool(BinaryNode nodeToInsert)
	 {
		 if (getNumberOfElementsInPool(pool, 0) < pSize)
		 {
			 nodeToInsert.left = null;
			 nodeToInsert.right = null;
			 pool = insertIntoPoolHelper(pool, nodeToInsert);
		 }
	 }
	 
	 private BinaryNode insertIntoPoolHelper(BinaryNode currPool, BinaryNode nodeToInsert)
	 {
		 if (currPool == null)
		 {
			 currPool = nodeToInsert;
		 }
		 int comparisonResult = currPool.compareTo(nodeToInsert.element);
		 if (comparisonResult > 0)
		 {
			 // shift em down.
			 BinaryNode tempNode = currPool;
			 currPool = nodeToInsert;
			 currPool.right = tempNode;
		 }
		 else if (comparisonResult < 0)
		 {
			 // go right.
			 currPool.right = insertIntoPoolHelper(currPool.right, nodeToInsert);
		 }
		 return currPool;
	 }
	 
	 private int getNumberOfElementsInPool(BinaryNode currPoolNode, int currLevel)
	 {
		 if (currPoolNode != null)
		 {
			 currLevel++;
			 currLevel = getNumberOfElementsInPool(currPoolNode.right, currLevel);
		 }
		 return currLevel;
	 }
	 
	 private BinaryNode deleteHelper(BinaryNode currNode)
	 {
		 //System.out.println("current node: " + currNode.toString());
		 //System.out.println("   Deleting node with " + currNode.element);
		 int numberOfChildren = getNumberOfChildren(currNode);
		 if (numberOfChildren == 0)
		 {
			 //System.out.println("     No children. Basic removal");
			 currNode = null;
			 return currNode;
		 }
		 else if (numberOfChildren == 1)
		 {
			 if (currNode.left != null)
			 {
				 //System.out.println("     Left Child only. Pulling up " + currNode.left.element);
				 return currNode.left;
			 }
			 else if (currNode.right != null)
			 {
				 //System.out.println("     Right Child only. Pulling up " + currNode.right.element);
				 return currNode.right;
			 }
		 }
		 BinaryNode tempNode = getMinLeftNodeOfRightSubtree(currNode.right);
		 currNode.right = DeleteMinLeftElementOfRightSubtree(currNode.right);
		 tempNode.left = currNode.left;
		 tempNode.right = currNode.right;
		 currNode = tempNode;
		 //TESTING
		 
		 //System.out.println("     Two children. Complicated. Getting the minimum left element from root " + currNode.right.element);
		 //currNode.element = getMinLeftElementOfRightSubtree(currNode.right);
		 //System.out.println("     Deleting the minimum left element from root " + currNode.right.element);
		 //currNode.right = DeleteMinLeftElementOfRightSubtree(currNode.right);
		 //System.out.println("returning node: " + currNode.toString());
		 return currNode;
	 }
	 
	 private BinaryNode getMinLeftNodeOfRightSubtree(BinaryNode currNode)
	 {
		 if (currNode.left != null)
		 {
			 return getMinLeftNodeOfRightSubtree(currNode.left);
		 }
		 return currNode;
	 }
	 
	 private T getMinLeftElementOfRightSubtree(BinaryNode currNode)
	 {
		 if (currNode.left != null)
		 {
			 return getMinLeftElementOfRightSubtree(currNode.left);
		 }
		 T tempElement = currNode.element;
		 currNode = null;
		 return tempElement;
	 } 
	 private BinaryNode DeleteMinLeftElementOfRightSubtree(BinaryNode currNode)
	 {
		 if (currNode.left == null)
		 {
			 if (currNode.right != null)
			 {
				 currNode = currNode.right;
			 }
			 else
			 {
				 currNode = null;
			 }
		 }
		 else
		 {
			 currNode.left = DeleteMinLeftElementOfRightSubtree(currNode.left);
		 }
		 return currNode;
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
		 root = capWith(root, x);
	 }
	 
	 private BinaryNode capWith(BinaryNode currNode, T elementToCapWith)
	 {
		 if (currNode != null)
		 {
			 currNode.left = capWith(currNode.left, elementToCapWith);
			 currNode.right = capWith(currNode.right, elementToCapWith);
			 int comparisonResult = currNode.compareTo(elementToCapWith);
			 if (comparisonResult > 0)
			 {
				 currNode = remove(currNode, currNode.element);
			 }
		 }
		 return currNode;
	 }
	 
	 public void clear()
	 {
		 root = clearHelper(root);
	 }
	 
	 private BinaryNode clearHelper(BinaryNode currNode)
	 {
		 if (currNode != null)
		 {
			 currNode.left = clearHelper(currNode.left);
			 currNode.right = clearHelper(currNode.right);
			 if (pSize != 0)
			 {
				 // Add node to the pool, if possible.
			 }
			 currNode = null;
		 }
		 return currNode;
	 }
	 
	 // Return true iff other is a BST that has the same physical structure
	 // and stores equal data values in corresponding nodes. "Equal" should
	 // be tested using the data object's equals() method.
	 // Pre: other is null or points to a valid BST<> object, instantiated
	 // on the same data type as the tree on which equals() is invoked
	 // Post: both binary trees are unchanged
	 public boolean equals(Object other)
	 {
		 if (other == null)
		 {
			 return false;
		 }
		 else if (!this.getClass().equals(other.getClass()))
		 {
			 return false;
		 }
		 BST otherTree = (BST)other;
		 return this.equals(root, otherTree.getRoot());
	 }
	 
	 private boolean equals(BinaryNode thisTree, BinaryNode otherTree)
	 {
		 boolean same = true;
		 if (thisTree != null && otherTree != null)
		 {
			 same = thisTree.element.equals(otherTree.element);
			 if (same)
			 {
				 same = equals(thisTree.left, otherTree.left);
			 }
			 if (same)
			 {
				 same = equals(thisTree.right, otherTree.right);
			 }
		 }
		 else if ((thisTree == null && otherTree != null) || (thisTree != null && otherTree == null))
		 {
			 same = false;
		 }
		 return same;
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
	 
	 public void printTree()
	 {
		 printTree(root, 0);
	 }
	 
	 private void printTree(BinaryNode currNode, int level)
	 {
		 if (currNode != null)
		 {
			 StringBuilder sb = new StringBuilder();
			 for (int i = 0; i < level; i++)
			 {
				 sb.append(' ');
			 }
			 sb.append(currNode.element);
			 System.out.println(sb);
			 printTree(currNode.left, level + 1);
			 printTree(currNode.right, level + 1);
		 }
	 }
}
