import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class testing {
	
	public static void main(String[]args) throws IOException {
		
		File testDir = new File(System.getenv("APPDATA") + "\\PM\\");
		File testFile = new File(System.getenv("APPDATA") + "\\PM\\" + "test.txt");
		
		if(testDir.mkdir()) {
			System.out.println("Directory successfully created");
		}
		else {
			System.out.println("Directory already exists");
		}
		
		try {
			if(testFile.createNewFile()) {
				System.out.println("File created successfully");
			}
		} catch(IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		FileReader fr = null;
		try {
			fr = new FileReader(System.getenv("APPDATA") + "\\PM\\" + "test.txt");
		}catch(FileNotFoundException e) {
			System.out.println("PM File Not Found!");
		}
		
		BufferedReader br = new BufferedReader(fr);
		
		String buffer = "";
		while(true) {
			try {
				buffer = br.readLine();
				
			}catch(IOException i) {
				System.out.println("I/O Exception");
			}
			if(buffer == null) {
				break;
			}
			System.out.println(buffer);
		}
		
		br.close();
		
	}
	
}