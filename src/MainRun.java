import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainRun {

	public static void main(String[] args) {
		DataStorage storage = new DataStorage();
		storage.createPasswordManagerDirectory();
		storage.createPasswordDataFile();
		
		DataStructure structure = new DataStructure();
		structure.collectData();
		
		
	}
	
}
