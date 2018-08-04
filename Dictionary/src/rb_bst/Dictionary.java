package rb_bst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Dictionary {

	public static final Scanner SCANNER = new Scanner(System.in);
	
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private RBTree rbTree;
	
	private String fileName;
	private String line;
	private String word;
	
	private Node node;
	
	public Dictionary() {		
		rbTree = new RBTree();
	}

	public RBTree getRBTree() {
		return this.rbTree;
	}
	
	public void loadDictionary() {
		
		System.out.println("Please Enter File name:");
		fileName = SCANNER.next();
		
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	rbTree.addNode(line);
            }
            bufferedReader.close();                                                
        }
        catch(java.io.IOException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        
		
	}
	
	public void printDictionarySize() {
		System.out.println("Dictionary Size: " + rbTree.getSize());
	}
	
	public void insertWord() {
		System.out.println("Please Enter Word to Insert");
		word = SCANNER.next();
		rbTree.addNode(word);
	}
	
	public void lookWordUp() {
		System.out.println("Please Enter Word to Look up");
		word = SCANNER.next();
		node = rbTree.find(word);
		if(node != Node.NIL) System.out.println("YES");
		else System.out.println("NO");
	}
	
	public void deleteWord() {
		System.out.println("Please Enter Word to Delete");
		word = SCANNER.next();
		rbTree.deleteNode(word);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int action=1;
		Dictionary dictionary = new Dictionary();

        while(true){
        	System.out.println("Please Enter next action to perform:");
        	System.out.println("1. Load Dictionary");
        	System.out.println("2. Insert a Word");
        	System.out.println("3. Lookup a Word");
        	System.out.println("4. Remove a Word");
        	System.out.println("5. print Dictionary Size");
        	System.out.println("6. Print Dictionary in Order");
        	System.out.println("7. End program");
        
        	action = Dictionary.SCANNER.nextInt();
        
        	switch(action) {
        		case 1:
        	        dictionary.loadDictionary();
        	        break;
        		case 2:
        			dictionary.insertWord();
        			break;
        		case 3:
        			dictionary.lookWordUp();
        			break;
        		case 4:
        			dictionary.deleteWord();
        			break;
        		case 5:
        			dictionary.printDictionarySize();
        			break;
        		case 6:
        			dictionary.getRBTree().inOrderTraversal();
        			break;
        		case 7:
        			System.out.println("Terminating..");
        			//Dictionary.SCANNER.close();
        			System.exit(0);
        	}
        }
	}

}
