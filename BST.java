import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.IOException;

public class BST {

	int count = 0;
	private Node root;
	
	public BST() {
		this.root = null;
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	// assuming a balanced tree, will iterate through and find a spot to put new node
	public void insert(int newValue, String newName) {
		if(root == null) {
			root = new Node(newValue, newName);
		}
		else {
			
			Node currentNode = root;
			boolean placed = false;
			
			while(!placed) {
				
				if(currentNode.getValue() == newValue) {
					placed = true;
					System.out.println("No duplicate values allowed");
				}
				else if(newValue < currentNode.getValue()) {
					//move left
					if(currentNode.getLeft() == null) {
						// cant move left, so we found insertion spot
						//insert Node
						currentNode.setLeft(new Node(newValue, newName));
						currentNode.getLeft().setParent(currentNode);
						placed = true;
					}
					else {
						// otherwise move left
						currentNode = currentNode.getLeft();
					}
				}
				else {
					//move right
					if(currentNode.getRight() == null) {
						//cant move right, insert new node
						currentNode.setRight(new Node(newValue, newName));
						currentNode.getRight().setParent(currentNode);
						placed = true;
					}
					else {
						//move right
						currentNode = currentNode.getRight();
					}
				}	
			}		
		}	
	}
	
	// uses inorder traversal to assign values of each node
	public void inOrder(Node n) {
		if(n==this.root) {
			count = 0;
		}
		if(n != null) {
			inOrder(n.getLeft());
			n.setValue(count);
			count ++;
			inOrder(n.getRight());
		}
	}
	
	// adds a new node to the tree at location of errorAt's parent, regardless of values
	public void forceInsert(String newName, String newDetail, int errorAt) {
		Node current = root;
		//go to the last node we asked about
		while(current.getValue() != errorAt && current!=null){
			if(errorAt>current.getValue() && current.getRight()!=null) {
				current = current.getRight();
			}
			else if(errorAt<current.getValue() && current.getLeft()!=null){
				current = current.getLeft();
			}else{
				System.out.println("Not found.");
				return;
			}
		}
		// update this node to have new question
		Node question = new Node(0, newDetail);
		Node animal = new Node(0, newName);
		
		// attatch question to the current parent
		if(current.isLeaf()) {
			   if (current.getParent().getLeft() == current) {
			        // If current is the left child of its parent
			        current.getParent().setLeft(question);
			   } else {
			        // If current is the right child of its parent
			        current.getParent().setRight(question);
			   }
			
			// set the children of the question node
			question.setRight(current);
			question.setLeft(animal);
			
			// set the new parents
			animal.setParent(question);
			current.setParent(question);
			
			// fix the values of the BST
			inOrder(this.root);
		}
		
		return;
	}
	
	 // print or write to a file the BST breadth first
	 public void breadthFirst(boolean isSaving) {
		Queue<Node> queue = new LinkedList<>();
		Node temp = this.root;
		
		if(temp==null) {
			return;
		}else {
			try (FileWriter writer = new FileWriter("tree.txt", false)) {
				queue.add(temp);
				
				while(!queue.isEmpty()) {
					// get the top of the queue
					temp = queue.element();
					String out = temp.getValue() + "," + temp.getName();
					
					// add the children into the queue
					if(temp.getLeft()!=null) {
						queue.add(temp.getLeft());
					}if(temp.getRight()!=null) {
						queue.add(temp.getRight());
					}
					
					// if saving, updates tree file, otherwise prints
					if(isSaving) {
						writer.write(out + "\n");
					}else {
						System.out.println(out);
					}
					
					// remove the printed/saved element from the queue
					queue.remove();
				}
			}catch(IOException e){
				e.printStackTrace();
				return;
			}
		}
	 }
	 
}
