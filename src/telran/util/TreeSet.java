package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class TreeSet<T> implements SortedSet<T> {
	
	
	private static class Node<T>{
		T obj;
		Node <T> parent;
		Node <T> left;
		Node <T> right;
		Node(T obj){
			this.obj=obj;
		}
		
	}
	private static final String FILL_SYMBOL = " ";
	private static final int N_SYMBOLS_PER_LEVEL = 2;
	private Node<T> root;
	int size;
	Comparator<T> comp;
	
	private Node<T> getLeastNodeFrom(Node<T> node) {
		while (node.left !=null) {
			node=node.left;
		}
		return node;
	}
	
	private class TreeSetIterator implements Iterator<T>{
		 Node<T> current = root ==null ? null : getLeastNodeFrom(root);
		 Node<T> prev = null;
		 
		 boolean isNextCalled=false;
		@Override
		public boolean hasNext() {
			
			return current !=null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res= current.obj;
			prev=current;
			updateCurrent();
			isNextCalled=true;
			return res;
		}
		private void updateCurrent() {
			current = current.right !=null ? getLeastNodeFrom(current.right) : getGreaterParent(current);
			
		}

		private Node<T> getGreaterParent(Node<T> node) {
			while(node.parent !=null && node.parent.left!=node ) {
				node=node.parent;
			}
			return node.parent;
		}

		@Override
		public void remove() {
		if (!isNextCalled) {
			throw new IllegalStateException();
			}
			
		if(isJunction(prev)) {
			current=prev;
		}
		TreeSet.this.remove(prev);
		
		
		isNextCalled=false;
		
	}
	}

	public TreeSet(Comparator<T> comp){
		this.comp = comp;
	}
	
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public boolean add(T obj) {
	
		//no cycles allowed
	Node<T> newNode = new Node<>(obj);
		boolean res = add(root, newNode);
		if (res) {
			size++;
		}
		return res;
	}

	private boolean add(Node<T> parent, Node<T> newNode) {
		boolean res = true;
		if (parent == null) {
			root = newNode;
		} else {
			int resComp = comp.compare(newNode.obj, parent.obj);
			if (resComp == 0) {
				res = false;
			} else {
				if (resComp < 0) {
					if (parent.left == null) {
						insert(parent, newNode, true);//new node inserted to left from parent
					} else {
						add(parent.left, newNode);
					}
				} else {
					if (parent.right == null) {
						insert(parent, newNode, false);//new node inserted to right from parent
					} else {
						add(parent.right, newNode);
					}
				}
			}
		}
		return res;
	}
	
	private void insert(Node<T> parent, Node<T> newNode, boolean isLeft) {
		if (isLeft) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		newNode.parent = parent;
		
	}

	private Node<T> getNodeOrParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes =0;
		while (current!=null) {
			parent = current;
			compRes = comp.compare(obj,current.obj);
			if (compRes==0) {
				break;
			}
			current = compRes>0 ? current.right : current.left;	
		}
		return parent;
	}

	@Override
	public boolean remove(Object pattern) {
	
	
	
	Node<T> node=getNodeOrParent((T) pattern);
	//if (node!=null && comp.compare(node.obj, (T) pattern)==0)	{
	if (node!=null && node.obj.equals(pattern))	{
		
		if (!isJunction(node)) {
			removeNonJunctionNode(node);
			
		}
		else
		{removeJunctionNode(node);
			 }
		size--;
		return true;
		
	}
		return false;
	}

	private void removeNonJunctionNode(Node<T> node) {
		//check root
        Node<T> child;
    	
    
        child=node.left!=null ? node.left :node.right;
       
        
        if (node.parent==null) {
			child.parent=null;
			root=child;}
    	else {
    		
        if (node.parent.left==node) {
        	node.parent.left=child;
        }else node.parent.right=child;
    	
        child.parent=node.parent;
    	}
        node=child;
    	
    
	}

	private void removeJunctionNode(Node<T> node) {
	
		Node<T> next = getLeastNodeFrom(node);
		if (node.parent==null) {
			next.parent=null;
			root=next;}
		else {
		next.parent=node.parent;
		if(node.parent.left==node) {node.parent.left=next;}
		else node.parent.right=next;}
	    node=next;
	    next=null;
	
	}


	private boolean isJunction(Node<T> node) {
		
		return node.left==null || node.right==null ? false:true;
	}

	@Override
	public boolean contains(Object pattern) {
		@SuppressWarnings("unchecked")
		T tPattern = (T)pattern;
		Node<T> node = getNodeOrParent(tPattern);
		return node != null && comp.compare(tPattern, node.obj) == 0;
	
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	public T first() {
		if (root==null) {
			return null;
		}
		return getLeastNodeFrom(root).obj;
	}


	

	public T last() {
		if (root==null) {
			return null;
		}
		return getMostFrom(root).obj;
	}

	private Node<T> getMostFrom(Node<T> node) {
		while (node.right !=null) {
			node=node.right;
		}
		return node;
	}
	
	public void displayRotate() {
		displayRotated(root,0);
	}

	private void displayRotated(Node<T> root, int level) {
		if (root!=null) {
			displayRotated(root.right, level + 1);
			displayRoot(root, level);
			displayRotated(root.left, level + 1);
		}
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n",FILL_SYMBOL.repeat(level*N_SYMBOLS_PER_LEVEL),root.obj);
		
	}
	
	public void displayAsDirectory() {
		displayAsDirectory(root,0);
	}
	
	private void displayAsDirectory(Node<T> root, int level) {
		if (root!=null) {
			displayRoot(root,level); 
			displayAsDirectory(root.left,level+1);	
			displayAsDirectory(root.right,level+1);
		}
		
		
	}

	public int height() {
	
		return height(root);
	}
	
	private int height(Node<T> root) {
	int res=0;
		if (root!=null) {
			int heightLeft = height(root.left);
			int heightRight= height(root.right);
			res=Math.max(heightLeft, heightRight)+1;
		}
		return res;
	}

	public int width() {
		
		return width(root);
	}

	private int width(Node<T> root) {
		int res=0;
		if(root !=null) {
			res = root.left==null && root.right==null ? 1 : width(root.left) + width(root.right);
			}
			
		return res;
	}
	
	/**
	 * tree inversion swap of left and right subtrees
	 */
 public void inversion() {
	  inversion(root,0);
 }

	private void inversion(Node<T> root, int level) {
		if (root!=null) {
			displayRoot(root,level);
			if (root.left!=null || root.right!=null) {
			swap(root);
			if (root.left!=null) inversion(root.left,level+1);
			if (root.right!=null) inversion(root.right,level+1);
			
		
		}
		}
	}

	private Node<T> swap(Node<T> root) {
		if (root!=null) {
		Node<T> tmp =root.left;
		root.left=root.right;
		root.right=tmp;
		
		}
		return root;
	}


	
}
