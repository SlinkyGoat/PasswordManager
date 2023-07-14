import java.io.File;
import java.io.IOException;

public class testing {
	
	public static void main(String[]args) {
		
		File testDir = new File(System.getenv("APPDATA") + "\\PM\\");
		File testFile = new File(System.getenv("APPDATA") + "\\PM\\" + "test.txt");
		
		if(testDir.mkdir()) {
			System.out.println("Directory successfully created");
		}
		else {
			System.out.println("Directory not created");
		}
		
		try {
			if(testFile.createNewFile()) {
				System.out.println("Fill created successfully");
			}
		} catch(IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
	}
	
}