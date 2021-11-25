// add your imports here
import java.util.ArrayList;
class TSTNode<T extends Comparable<T>>{
	T element;     	            // The data in the node
	TSTNode<T>  left;   		// left child
	TSTNode<T>  mid;   		    // middle child
	TSTNode<T>  right;  		// right child

	// TODO: implement the node class here

	TSTNode(T element){
		this.element = element;
	}

	TSTNode<T> findMax(){
		if (right==null)
			return this;
		return right.findMax();
	}

	TSTNode<T> findMin(){
		if (left==null)
			return this;
		return left.findMin();
	}

	TSTNode<T> findLastMid(){
		if (mid ==null)
			return this;
		return mid.findLastMid();

	}

	int height(){
		if (left ==null&&mid==null&&right==null)
			return 0;
		else{
			int h = 0;
			if (right!=null)
				h= max(right.height(), h);
			if (left!=null)
				h = max(left.height(), h);
			if (mid!=null)
				h = max(mid.height(), h);
			return 1 + h;
		}	
	}

	ArrayList<TSTNode<T>> getAsList(){
		ArrayList<TSTNode<T>> result = new ArrayList<TSTNode<T>>();
		this.addToList(result);
		return result;
	}

	void addToList(ArrayList<TSTNode<T>> list) {
		if (left!=null)
			left.addToList(list);
		if (mid!=null) 
			mid.addToList(list);
		list.add(this);
		if (right!=null)
			right.addToList(list);

	}

	void insert(T element) {
		int temp  = element.compareTo(this.element);
		if (temp>0) {
			if (right!=null) {
				right.insert(element);
			} else {
				right = new TSTNode<T>(element);
				return;
			}
		} else if (temp==0) {
			if (mid!=null) {
				mid.insert(element);
			} else {
				mid = new TSTNode<T>(element);
				return;
			}
		} else {
			if (left!=null) {
				left.insert(element);
			} else {
				left = new TSTNode<T>(element);
				return;
			}
		}
	}

	boolean contains(T element) {
		int temp  = element.compareTo(this.element);
		if (temp>0) {
			if (right!=null) {
				return right.contains(element);
			} else {
				return  false;
			}
		} else if (temp==0) {
			return true;

		} else {
			if (left!=null) {
				return left.contains(element);
			} else {
				return false;
			}
		}
	}

	TSTNode<T> remove(TSTNode<T> root, T element) {		 // returns root node

		int temp  = element.compareTo(root.element);

		if( root == null )
			return null;
		else if (temp<0)
			root.left = remove ( root.left, element );
		else if (temp>0 )
			root.right = remove ( root.right, element);
		else { // key == root.key
			if (root.left == null) {
				if (root.right!=null) {
					root.element = root.right.findMin().element;
					root.right = remove(root.right, root.element );
				}else if (root.mid != null) {
					root.element = root.findLastMid().element;
					root.mid = remove(root.mid, root.element);
				}else {
					root = null;
					
				}
			}else { // neither left nor right child is null
				root.element = root.left.findMax().element;
				root.left = remove( root.left, root.element );
			}
		}
		return root;








	}



	int max(int x, int y) {
		return (x>y)?x:y;
	}
}