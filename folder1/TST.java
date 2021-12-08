
import java.util.ArrayList;
import java.util.Iterator;

public class TST<T extends Comparable<T>> implements Iterable<T>{
	// root node of the tree
	TSTNode<T> root;

	// constructor
	public TST() {
		this.root = null;
	}

	// TODO: implement the tree class here

	public void insert(T element){
		if (root==null) {
			root = new TSTNode<T>(element);
			return;
		}
		root.insert(element);

	}

	public void remove(T element){
		if (root==null)
			return;
		root = root.remove(root, element);
	}

	public boolean contains(T element){
		if (root==null)
			return false;
		return root.contains(element);
	}

	public void rebalance(){
		if (root==null)
			return;
		ArrayList<TSTNode<T>> nodeList = root.getAsList();
		T temp = nodeList.get(nodeList.size()/2).element;
		TSTNode<T> newRoot = new TSTNode<T>(temp);
		int i;
		for (i=0; i<nodeList.size()/2; i++) 
			newRoot.insert(nodeList.get(i).element);
		i++;
		for (i++; i<nodeList.size(); i++)
			newRoot.insert(nodeList.get(i).element);

		root = newRoot;
		newRoot = null;
	}


	public ArrayList<TSTNode<T>> getAsList() {
		if (root==null) {
			return new ArrayList<TSTNode<T>>();
		}
		else return root.getAsList();
	}

	// add your own helper methods if necessary


	/**
	 * Caculate the height of the tree.
	 * You need to implement the height() method in the TSTNode class.
	 *
	 * @return -1 if the tree is empty otherwise the height of the root node
	 */
	public int height(){
		if (this.root == null)
			return -1;
		return this.root.height();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return new TSTIterator<T>(this);
	}
	// --------------------PROVIDED METHODS--------------------
	// The code below is provided to you as a simple way to visualize the tree
	// This string representation of the tree mimics the 'tree' command in unix
	// with the first child being the left child, the second being the middle child, and the last being the right child.
	// The left child is connect by ~~, the middle child by -- and the right child by __.
	// e.g. consider the following tree
	//               5
	//            /  |  \
	//         2     5    9
	//                   /
	//                  8
	// the tree will be printed as
	// 5
	// |~~ 2
	// |   |~~ null
	// |   |-- null
	// |   |__ null
	// |-- 5
	// |   |~~ null
	// |   |-- null
	// |   |__ null
	// |__ 9
	//     |~~ 8
	//     |   |~~ null
	//     |   |-- null
	//     |   |__ null
	//     |-- null
	//     |__ null
	@Override
	public String toString() {
		if (this.root == null)
			return "empty tree";
		// creates a buffer of 100 characters for the string representation
		StringBuilder buffer = new StringBuilder(100);
		// build the string
		stringfy(buffer, this.root,"", "");
		return buffer.toString();
	}

	/**
	 * Build a string representation of the tertiary tree.
	 * @param buffer String buffer
	 * @param node Root node
	 * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
	 * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
	 */
	private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
		buffer.append(nodePrefix);
		buffer.append(node.element);
		buffer.append('\n');
		if (node.left != null)
			stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
		else
			buffer.append(childrenPrefix + "|~~ null\n");
		if (node.mid != null)
			stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
		else
			buffer.append(childrenPrefix + "|-- null\n");
		if (node.right != null)
			stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
		else
			buffer.append(childrenPrefix + "|__ null\n");
	}

	/**
	 * Print out the tree as a list using an enhanced for loop.
	 * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
	 */
	public void inorderPrintAsList(){
		String buffer = "[";
		for (T element: this) {
			buffer += element + ", ";
		}
		int len = buffer.length();
		if (len > 1)
			buffer = buffer.substring(0,len-2);
		buffer += "]";
		System.out.println(buffer);
	}


}