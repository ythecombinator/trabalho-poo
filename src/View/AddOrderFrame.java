package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Classes.Client;
import Classes.MenuItem;
import SQL.SQLManager;

import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class AddOrderFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AddOrderFrame() {
		
		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Client Name");
		label.setBounds(10, 10, 394, 22);
		contentPane.add(label);
		
		JComboBox cbClientName = new JComboBox();
		ArrayList<Client> clients = SQLManager.selectAllClients();
		for(int i = 0; i < clients.size(); i++) {
			cbClientName.addItem(clients.get(i).name);
		}
		cbClientName.setBounds(10, 38, 394, 20);
		contentPane.add(cbClientName);
		
		Label label_1 = new Label("Item Name");
		label_1.setBounds(10, 64, 394, 22);
		contentPane.add(label_1);
		
		JComboBox cbItemName = new JComboBox();
		ArrayList<MenuItem> menuItems = SQLManager.selectAllItemsInMenu();
		for(int i = 0; i < menuItems.size(); i++) {
			cbItemName.addItem(menuItems.get(i).name);
		}
		
		cbItemName.setBounds(10, 92, 394, 20);
		contentPane.add(cbItemName);
		
		Label label_2 = new Label("Item Quantity");
		label_2.setBounds(10, 118, 394, 22);
		contentPane.add(label_2);
		
		JFormattedTextField ftxtQuantity = new JFormattedTextField();
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("#############");
			mask.install(ftxtQuantity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtQuantity.setBounds(10, 146, 298, 20);
		contentPane.add(ftxtQuantity);
		
		Label label_3 = new Label("On Cart");
		label_3.setBounds(10, 172, 62, 22);
		contentPane.add(label_3);
		
		table = new JTable();
		String[] rows = {"Item Name","Quantity"};
		String[][] cols = {};
		DefaultTableModel tableModel = new DefaultTableModel(cols, rows);
		table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 200, 394, 286);
		contentPane.add(scrollPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnCancel.setBounds(10, 497, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		    	LocalDateTime now = LocalDateTime.now();
				
				String clientName = (String)cbClientName.getSelectedItem();

				SQLManager.insertNewOrder(SQLManager.selectClientNamed(clientName), now.format(dateFormat));
				
				int id = SQLManager.selectOrdersWithDate(dateFormat.format(now)).id;
				
				for (int i = 0; i < table.getRowCount(); i++) {
					int quantity = Integer.parseInt(table.getValueAt(i, 1).toString());
					int menuItemID = SQLManager.selectItemsInMenuWithName(table.getValueAt(i, 0).toString()).id;
					SQLManager.insertNewOrderItem(id, quantity, menuItemID);
				}
//				
			}
		});
		btnSave.setBounds(315, 497, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String itemName = (String)cbItemName.getSelectedItem();
				String quantity = ftxtQuantity.getText().replaceAll(" ", "");

				tableModel.addRow(new Object[] {itemName,quantity});
			}
			
		});
		btnAdd.setBounds(315, 146, 89, 23);
		contentPane.add(btnAdd);
	}
}
