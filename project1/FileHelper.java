/*
 * Some of this code is taken from:
 * https://www.geeksforgeeks.org/java-program-to-write-into-a-file/
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


//Some of this code is taken from
//https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
import java.nio.file.*;

public class FileHelper {

	public static String writeToFile(String text, String path, int numChars) {
		//path example: /Users/mayanksolanki/Desktop/demo.docx


		// Try block to check for exceptions
		try {
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
			String works = "File is created successfully with the content.";
			System.out.println(works);

			// Step 5: Close the BufferedWriter object
			f_writer.close();
			return works;
		}

		// Catch block to handle if exceptions occurs
		catch (IOException e) {

			// Print the exception on console
			// using getMessage() method
			System.out.println("ERROR COULD NOT SAVE TO FILE");
			String err = e.getMessage();
			System.out.println(err);
			return err;
		}
	}



	public static String[] getFileAsString(String filePath) {
		String[] arr = new String[2];
		//0th element is result 1th element is error msg
		try {
			String resu = new String(Files.readAllBytes(Paths.get(filePath)));
			//System.out.println("File has been successfully imported.");
			arr[0] = resu;
			arr[1] = "File has been successfully imported.";
			return arr;
		}
		catch (IOException e) {
			//System.out.println("ERROR");
			//System.out.println(e.getMessage());
			//if there is error then arr[0] will be null  
			arr[1] = e.getMessage();
			return arr;
		}
	}
}

