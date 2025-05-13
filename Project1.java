import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Project1 {
	private BST tree;
	private String adjectiveList = " ";
	private Node current;
	private Scanner scanner = new Scanner(System.in);
	
	public Project1() {
		this.tree = new BST();
	}
	
	//
	public void run() {
		// Allow for repeated guesses
		while(true) {
			System.out.println("Do you have another animal to identify? <Y/N>");
			if(this.scanner.nextLine().trim().equals("Y")) {
				//Reload the tree
				this.tree = buildTree("tree.txt");
				current = this.tree.getRoot();
				//Make guesses
				Node guess = guessing(current);
				
				//If the guess is wrong update the information in the tree
				if(guess!=null) {
					System.out.println("Hmmm... I think I know.\nIs it " + guess.getName() + "?");
					if(this.scanner.nextLine().trim().equals("Y")){
						System.out.println("Good! All done");
					}else{
						System.out.println("I was wrong...");
						fixTree(guess);
					}
				}else {
					fixTree(guess);
				}
				
				//Save any changes made to the tree
				this.tree.breadthFirst(true);
			}else {
				// Ends the while loop
				System.out.println("Okay. Ending program.");
				this.scanner.close();
				break;
			}
		}
	}
	
	// asks the users characteristics until it reaches an answer matching those adjectives
	public Node guessing(Node current){
		while(!current.isLeaf()) {
			System.out.println("Is this animal " + current.getName() + "?");
			if(this.scanner.nextLine().trim().equals("Y")) {
				adjectiveList += current.getName() + " ";
				current = current.getLeft();
			}else{
				adjectiveList += "not-" + current.getName() + " ";
				current = current.getRight();
			}
		}
		return current;
	}
	
	// if there was a wrong guess, a new characteristic and animal is added to the tree
	public void fixTree(Node guess) {
		System.out.println("I dont know any " + adjectiveList.trim() + " animals");
		System.out.println("What is the new animal?");
		String name = this.scanner.nextLine().trim();
		System.out.println("What characteristic does " + name + " have that " + guess.getName() + " does not?");
		String adjective = this.scanner.nextLine().trim();
		this.tree.forceInsert(name, adjective, guess.getValue());
		return;
	}
	
	// iterates through the file and adds values to the tree
	public BST buildTree(String name) {
		BST tree = new BST();
		// read in from the file
				try {
					// Open the file
					File file = new File(name);
		            Scanner scan = new Scanner(file);
		            
		            // iterate through the file
		            while(scan.hasNextLine()) {
		            	// read line and separate into two strings
		            	String line = scan.nextLine().trim();
		            	String[] parts = line.split(",");
		            	
		            	// add value
		            	if(parts.length==2) {
		            		try{
		            			tree.insert(Integer.parseInt(parts[0]), parts[1]);
		            		}catch (NumberFormatException e){
		            			System.out.println("Invalid file format.");
		            		}
		            	}
		            }
					scan.close();
				}catch (FileNotFoundException e){
					System.out.println("File not found.");
				}
				return tree;
	}
}