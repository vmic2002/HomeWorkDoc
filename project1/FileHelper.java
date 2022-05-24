/*
 * Some of this code is taken from:
 * https://www.geeksforgeeks.org/java-program-to-write-into-a-file/
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

	public static void writeToFile(String text, String path, int numChars) {
		//path example: /Users/mayanksolanki/Desktop/demo.docx

		
		// Try block to check for exceptions
		try {
			System.out.println("BEGINNING WRITE TO FILE FUNCTION");
			// Step 1: Create an object of BufferedWriter
			BufferedWriter f_writer = new BufferedWriter(new FileWriter(path), numChars);
			
			// Step 2: Write text(content) to file
			f_writer.write(text);

			// Step 3: Printing the content inside the file
			// on the terminal/CMD
			System.out.print(text);

			// Step 4: Display message showcasing
			// successful execution of the program
			System.out.println();
			System.out.println("File is created successfully with the content.");

			// Step 5: Close the BufferedWriter object
			f_writer.close();
			System.out.println("END OF WRITE TO FILE FUNCTION");
		}

		// Catch block to handle if exceptions occurs
		catch (IOException e) {

			// Print the exception on console
			// using getMessage() method
			System.out.println("ERROR COULD NOT SAVE TO FILE");
			System.out.println(e.getMessage());
		}
	}
}

