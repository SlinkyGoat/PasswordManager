import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainRun {
	
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	public static void addComponentsToPane(Container pane, String[][] data) {
		if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if(shouldFill) {
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		// title image at top of screen
		JLabel title = new JLabel(new ImageIcon("assets\\PM_Title.png"));
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(title, c);
		
		JTextField searchBar = new JTextField();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,65,0,65);
//		c.fill = GridBagConstraints.NONE;
		pane.add(searchBar, c);
		
		JButton searchButton = new JButton("Search");
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,65);
		pane.add(searchButton, c);
		
		String column[]={"Service","Password"};
		JTable table = new JTable(data, column);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(30,65,30,0);
		c.anchor = GridBagConstraints.LINE_START;
		c.weighty = 1.0;
		pane.add(table, c);
		
		JScrollPane scroll = new JScrollPane(table);
		pane.add(scroll, c);
		
	}

	private static void createAndShowGUI(String[][] data) {
		final JFrame frame = new JFrame("Password Manager");
		
		//initialize window
//		frame.setSize(1000,800);
//		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
						
//		//creating add item button
//		JButton addItemButton = new JButton("Add Item");
//		addItemButton.setBounds(65, 720, 100, 30);
//		frame.add(addItemButton);
		
		//creating table
		
		// start y=280, end y=670
		// add scrollpane
		
		addComponentsToPane(frame.getContentPane(), data);
		
		//finishing frame
		frame.pack();
		frame.setMinimumSize(frame.getMinimumSize());
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		DataStorage storage = new DataStorage();
		storage.createPasswordManagerDirectory();
		storage.createPasswordDataFile();
		
		DataStructure structure = new DataStructure();
		structure.collectData();
		String data[][] = structure.hashToArr();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(data);
			}
		});
		
//		createAndShowGUI();
		
		
	}
	
}
