package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character>{
	BinaryTree<Character> tree;
	Scanner input;
	
	//Constructor
	public MorseCodeTree() {
		try {
			//Variable Set Up
			input = new Scanner(new File("MorseCodeList.txt"));
			String line, morseCode;
			char letter, currentSymbol;
			BinaryTree<Character> currentTree = new BinaryTree<Character>();
			tree = new BinaryTree<Character>(null, new BinaryTree<Character>(), new BinaryTree<Character>());
			currentTree = tree;
			
			//Reads each line of file
			while(input.hasNextLine()) 
			{
				currentTree = tree;
				line = input.nextLine().trim();
				letter = line.charAt(0); //gets letter
				morseCode = line.substring(2); //gets morse code
				
				
				//Loop through second to last symbol
				for(int i = 0; i < morseCode.length() - 1; i++) {
					currentSymbol = morseCode.charAt(i);
					if(currentSymbol == '*')
					{
						currentTree = currentTree.getLeftSubtree();
					}
					else if(currentSymbol == '-')
					{
						currentTree = currentTree.getRightSubtree();
					}
				}
				
				//At last symbol - Decide where to add
				currentSymbol = morseCode.charAt(morseCode.length()-1);
				if(currentSymbol == '*')
				{
					currentTree.addLeft(letter);
				}
				else if(currentSymbol == '-')
				{
					currentTree.addRight(letter);
				}
			}	
		} catch(FileNotFoundException e) {
			System.out.println("ERORR: " + e.getMessage());
		}
			
	}

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws IllegalArgumentException{
        //Variable Set Up 
    	BinaryTree<Character> currentTree = tree;
        char currentSymbol;
        String currentCode, translation = "";
        String[] words = morseCode.split(" ");
        
        //Loop for each word
        for(int j = 0; j < words.length ; j++) 
        {
        	//Used to keep track
        	currentCode = words[j];
        	currentTree = tree;
        	//Loop for each symbol in the word
		    for(int i = 0; i < currentCode.length(); i++)
		    {
		    	
		        currentSymbol = currentCode.charAt(i);
		        //* left
		    	if(currentSymbol == '*') {
		        	currentTree = currentTree.getLeftSubtree();
		        }
		    	
		    	//- right
		        else if(currentSymbol == '-') {
		        	currentTree = currentTree.getRightSubtree();
		        }
		    	
		    	//Invalid Character, throw exception
		        else {
		        	System.out.println("Invalid characters in Morse Code.");
		        	throw new IllegalArgumentException();
		        }
		    	
		 
		    }
		    //At Desired Location, get letter
		    translation += String.valueOf(currentTree.getData());
        }
	    return translation;
	   
    }
    
    

} // End of class MorseCodeTree