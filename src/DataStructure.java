import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class DataStructure {
	
	public HashMap<String, String> data = new HashMap<String, String>();
	public int items = 0;

	public void collectData(){
		
//		HashMap<String, String> data = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(System.getenv("APPDATA") + "\\PM\\" + "test.txt"));
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
	
}
