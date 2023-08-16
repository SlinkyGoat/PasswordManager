import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainRun {
	
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	static DataStorage storage = new DataStorage();
	static DataStructure structure = new DataStructure();
	static String data[][] = structure.hashToArr();
	static String column[]={"Service","Password"};
//	static JTable table = new JTable(data, column);
	static JTable table = new JTable(new DefaultTableModel(data, column));
	
	
	public static void addComponentsToPane(Container pane) {
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
		c.gridwidth = 3;
		pane.add(title, c);
		
		JTextField searchBar = new JTextField();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0,65,0,30);
		searchBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(searchBar);
			}
		});
		pane.add(searchBar, c);
		
		JButton searchButton = new JButton("Search");
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,65);
		// this filters the JTable to show items related to entered text, still not completely sure how it works
		// took this snippet of code from stackoverflow
		// https://stackoverflow.com/questions/29095906/filtering-a-jtable
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(searchBar);
			}
		});
		pane.add(searchButton, c);
		
//		String column[]={"Service","Password"};
//		JTable table = new JTable(data, column);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.insets = new Insets(30,65,30,30);
		c.anchor = GridBagConstraints.LINE_START;
		c.weighty = 1.0;
		//pane.add(table, c);
		
		JScrollPane scroll = new JScrollPane(table);
		pane.add(scroll, c);
		
		JLabel newServiceLabel = new JLabel("*Service Name");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0,65,0,30);
		pane.add(newServiceLabel, c);
		
		JLabel newPasswordLabel = new JLabel("*Password");
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0,10,0,30);
		pane.add(newPasswordLabel, c);
		
		JTextField newServiceName = new JTextField();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.ipadx = 450;
		c.insets = new Insets(10, 65, 30, 30);
		pane.add(newServiceName, c);
		
		JTextField newPasswordName = new JTextField();
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.ipadx = 450;
		c.insets = new Insets(10, 10, 30, 30);
		pane.add(newPasswordName, c);
		
		JButton addButton = new JButton("Add");
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 4;
		c.ipadx = 0;
		c.insets = new Insets(10, 0, 30, 65);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!newServiceName.getText().equals("") && !newPasswordName.getText().equals("")) {
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					dtm.addRow(new Object[]{newServiceName.getText(), newPasswordName.getText()});
					newServiceName.setText("");
					newPasswordName.setText("");
				}
				else {
					if(newServiceName.getText().equals("")) {
						newServiceName.setText("Enter Service Name Here");
					}
					if(newPasswordName.getText().equals("")) {
						newPasswordName.setText("Enter Password Here");
					}
				}
			}
		});
		pane.add(addButton, c);
		
	}
	
	private static void search(JTextField searchbar) {
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		if(searchbar.getText().length() != 0) {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchbar.getText()));
		}
		else {
			sorter.setRowFilter(null);
		}
	}

	private static void createAndShowGUI() {
		final JFrame frame = new JFrame("Password Manager");
		
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		addComponentsToPane(frame.getContentPane());
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				TableModel tm = table.getModel();
				int nRow = tm.getRowCount();
				int nCol = tm.getColumnCount();
				String[][] tableData = new String[nRow][nCol];
				for(int x = 0; x < nRow; x++) {
					for(int y = 0; y < nCol; y++) {
						tableData[x][y] = (String)tm.getValueAt(x,y);
					}
				}
				
				try {
					storage.rewriteData(tableData);
				} catch (IOException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//finishing frame
		frame.pack();
		frame.setMinimumSize(frame.getMinimumSize());
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
//		DataStorage storage = new DataStorage();
		storage.createPasswordManagerDirectory();
		storage.createPasswordDataFile();
		
//		DataStructure structure = new DataStructure();
//		structure.collectData();
//		String data[][] = structure.hashToArr();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		
		
	}
	
}
