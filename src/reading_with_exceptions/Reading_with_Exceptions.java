package reading_with_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Reading_with_Exceptions {
	
	public void process(String inputFilename) {
		
		Scanner inputStream = null;
		PrintStream printStream = null;
		
		String outputFilename = "";
		int numIntsToRead = 0;
		
		try {
			inputStream = new Scanner(new FileInputStream(inputFilename));
			
			outputFilename = inputStream.next();
			
			if (inputStream.hasNextInt()){
				numIntsToRead = inputStream.nextInt();
				inputStream.nextLine();
			}
			else {
				System.out.println("Bad input for number of ints to read, copying whole file instead.");
				numIntsToRead = -1;
				inputStream.nextLine();
			}
			printStream = new PrintStream(new FileOutputStream(outputFilename));
			
			copyNumbers(inputStream, printStream, numIntsToRead);
		}
		catch(FileNotFoundException e) {
			System.out.println("File was not found, moving on to next file.");
		}
		finally {
			if (inputStream != null) inputStream.close();
			if (printStream != null) printStream.close();
		}
		
		printToScreen(outputFilename);
	}
	
	private void copyNumbers(Scanner inputStream, PrintStream printStream, int numIntsToRead) {
		
		int x = 0;
		int intsOnLine = 0;
		
		if (numIntsToRead == -1) {
			while (inputStream.hasNext()) {
				if (inputStream.hasNextInt()) {
					if(intsOnLine < 10) {
						x = inputStream.nextInt();
						printStream.print(x + " ");
						intsOnLine++;
					}
					else {
						printStream.println();
						intsOnLine = 0;
					}
				}
				else inputStream.next();
			}
		}
		else {
			for (int i = 0; i < numIntsToRead; i++) {
				if (inputStream.hasNext()) {
					if (inputStream.hasNextInt()) {
						if(intsOnLine < 10) {
							x = inputStream.nextInt();
							printStream.print(x + " ");
							intsOnLine++;
						}
						else {
							printStream.println();
							intsOnLine = 0;
							i--;
						}
					}
					else inputStream.next();
				}
			}
		}
	}
	
	private void printToScreen(String filename) {
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(new FileInputStream(filename));
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("printToScreen: can't open: " + e);
		}
		finally {
			if (scan != null) scan.close();
		}
	}

	public static void main(String[] args) {
		Reading_with_Exceptions rwe = new Reading_with_Exceptions();
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("\n\n=========== Processing "+ args[i] + " ==========\n");
			rwe.process(args[i]);
		}
	}
}
