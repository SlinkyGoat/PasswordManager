import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class DataStorage {

	// Creates new directory "PasswordManager" in AppData folder
	public boolean createPasswordManagerDirectory() {
		File PMDir = new File(System.getenv("APPDATA") + "\\PasswordManager\\");
		if(!PMDir.mkdir()) {
			return false;
		}
		return true;
	}
	
	public boolean createPasswordDataFile() {
		File PMData = new File(System.getenv("APPDATA") + "\\PasswordManager\\data.txt");
		try {
			PMData.createNewFile();
		}
		catch(IOException e) {
			System.out.println("Error in creating password data file");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addItem(String service, String password) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(System.getenv("APPDATA") + "\\PasswordManager\\");
			writer.append("\n" + service);
			writer.append("\n" + password);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
		
	}
	
}
