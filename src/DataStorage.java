import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
	
	// creates file to store service and password data
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
	
	// adds service and password to file containing data
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
	
	// deletes all the data in the file
	public boolean deleteAllData() {
		File f = new File(System.getenv("APPDATA") + "\\PM\\" + "test.txt");
		f.delete();
		try {
			f.createNewFile();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	// rewrites all data into data file using the data hashmap
	public boolean rewriteData(HashMap<String, String> data) {
		if(!deleteAllData()) {
			return false;
		}
		for(Map.Entry<String, String> set : data.entrySet()) {
			if(!addItem(set.getKey(), set.getValue())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean rewriteData(String[][] data) {
		if(!deleteAllData()) {
			return false;
		}
		for(int x = 0; x < data.length; x++) {
			if(!addItem(data[x][0], data[x][1])) {
				return false;
			}
		}
		return true;
	}
	
}
