import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class DataStructure {
	
	public HashMap<String, String> data = new HashMap<String, String>();
	public int items = 0;

	// enters all services and correlated passwords into the hashmap "data"
	// keys are the services
	// values are the passwords
	public void collectData(){
		
//		HashMap<String, String> data = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(System.getenv("APPDATA") + "\\PM\\" + "data.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File Reader unable to be created in collectData()");
//			return null;
			return;
		}
		
		String keyBuffer = "";
		String valueBuffer = "";
		while(true) {
			try {
				keyBuffer = reader.readLine();
				valueBuffer = reader.readLine();
			} catch (IOException e) {
				System.out.println("Error reading line in collectData() loop");
			}
			if(keyBuffer == null) {
				break;
			}
//			data.put(keyBuffer, valueBuffer);
			this.data.put(keyBuffer, valueBuffer);
			++this.items;
		}
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Reader failed to close in collectData()");
		}
		
//		return data;
		
	}
	
	public void add(String service, String password) {
		// TODO add an item to the data structure
		this.data.put(service, password);
		++this.items;
	}
	
	public void remove(String service) {
		this.data.remove(service);
		--this.items;
	}
	
	public void editPassword(String service, String newPassword) {

		//put overwrites the old value with the new value if key already exists
		this.data.put(service, newPassword);
	}
	
	

	// returns the HashMap "data" as a 2d array
	public String[][] hashToArr(){
		collectData();
		String[][] myData = new String[this.items][2];
		int n = 0;
		for(Map.Entry<String, String> set : data.entrySet()) {
			myData[n][0] = set.getKey();
			myData[n][1] = set.getValue();
			++n;
		}
		return myData;
	}
	
	
}
