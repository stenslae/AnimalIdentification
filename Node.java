public class Node {
	
	private int value;
	private String name;
	private Node rightChild;
	private Node leftChild;
	private Node parent;
	
	public Node(int newValue, String newName) {
		this.value = newValue;
		this.name = newName;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public Node getLeft() {
		return this.leftChild;
	}
	
	public Node getRight() {
		return this.rightChild;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setParent(Node newParent) {
		this.parent = newParent;
	}
	
	public void setLeft(Node newNode) {
		this.leftChild = newNode;
	}
	
	public void setRight(Node newNode) {
		this.rightChild = newNode;
	}
	
	public void setValue(int newValue) {
		this.value = newValue;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public boolean isLeaf() {
		if(this.rightChild == null && this.leftChild == null) {
			return true;
		}
		return false;
	}
	
}