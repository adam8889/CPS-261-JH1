package reading_with_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Reading_with_Exceptions {
	
	public void process(String inputFilename) {
		
		try {
			FileInputStream fi  = new FileInputStream(inputFilename);
			Scanner filescanner = new Scanner(fi);
		}
		catch(FileNotFoundException e) {
			
		}
	}
	
	private void copyNumbers(Scanner scan, PrintStream ps, int numIntsToRead) {
		
	}
	
	private void printToScreen(String filename) {
		
	}

	public static void main(String[] args) {
		
	}

}
