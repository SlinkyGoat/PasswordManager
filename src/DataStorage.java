import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
			writer = new FileWriter(System.getenv("APPDATA") + "\\PM\\" + "data.txt");
			writer.append("\n" + service);
			writer.append("\n" + password);
			writer.close();
		} catch (IOException e) {
			System.out.println("addItem error");
			return false;
		}
		return true;
		
	}
	
	// deletes all the data in the file
	public boolean deleteAllData() {
		File f = new File(System.getenv("APPDATA") + "\\PM\\" + "data.txt");
		f.delete();
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("Error deleting file");
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
	
	// rewrites data in the data file using a 2d array rather than a hashmap
	// CHECK THIS METHOD IF APP NOT WORKING
	public boolean rewriteData(String[][] data) throws IOException {
		if(!deleteAllData()) {
			return false;
		}
		FileWriter writer = null;
		
		writer = new FileWriter(System.getenv("APPDATA") + "\\PM\\" + "data.txt");
		writer.append(data[0][0] + "\n");
		writer.append(data[0][1]);
		for(int x = 1; x < data.length; x++) {
			writer.append("\n" + data[x][0]);
			writer.append("\n" + data[x][1]);
		}
		
		writer.close();
		
		return true;
	}
	
}
