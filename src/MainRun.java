import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainRun {

	private static void createAndShowGUI() {
		final JFrame frame = new JFrame("Password Manager");
		
		//initialize window
		frame.setSize(1000,800);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		//create Title image
		JLabel title = new JLabel(new ImageIcon("assets\\PM_Title.png"));
		title.setBounds(0,0,1000,150);
		frame.add(title);
		
		//create search bar at top of screen
		//creating the text field
		JTextField searchText = new JTextField();
		searchText.setBounds(65, 200, 150, 30);
		frame.add(searchText);
		
		//creating the search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(235, 200, 100, 30);
		frame.add(searchButton);
		
		//finishing frame
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		DataStorage storage = new DataStorage();
		storage.createPasswordManagerDirectory();
		storage.createPasswordDataFile();
		
		DataStructure structure = new DataStructure();
		structure.collectData();
		
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createAndShowGUI();
//			}
//		});
		
		createAndShowGUI();
		
		
	}
	
}
