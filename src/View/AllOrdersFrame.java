package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Client;
import Classes.Order;
import Classes.OrderItem;
import SQL.SQLManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Label;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AllOrdersFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AllOrdersFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnBack.setBounds(10, 277, 89, 23);
		contentPane.add(btnBack);
		
		JComboBox comboBox = new JComboBox();
		String[][] array = SQLManager.selectAllOrdersAsLogWithMod("");
		for (int i = 0; i < array.length ; i++) {
			comboBox.addItem(array[i][0]);
		}
		
		comboBox.setBounds(506, 278, 89, 20);
		contentPane.add(comboBox);
		
		Label label = new Label("Index:");
		label.setBounds(457, 277, 43, 22);
		contentPane.add(label);
		
		table = new JTable();
		String[] rows = {"ID","Client Info","Date","Status","Active","Delivery Man Name"};
		String[][] cols = SQLManager.selectAllOrdersAsLogWithMod("");
		DefaultTableModel tableModel = new DefaultTableModel(cols, rows);
		table.setModel(tableModel);
		table.setRowSelectionAllowed(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 11, 684, 255);
		contentPane.add(scrollPane);
		

		JButton btnDetail = new JButton("Detail");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Order selectedOrder = SQLManager.selectOrderAtIndex(Integer.parseInt((String)comboBox.getSelectedItem()));

				ArrayList<OrderItem> orderedItems = SQLManager.selectAllItemsInMenuOnOrderWithIndex(selectedOrder.id);
//				Client client = SQLManager.selectOrderAtIndex(orderedItems.get(0).id).client;
				Client client = SQLManager.selectClientAtIndex(selectedOrder.client.id);

				System.out.println("\nClient: " + client.name);
				System.out.println(" phone: " + client.phone);
				System.out.println("Address: " + client.address + "\nAddress Reference: " + client.addressReference);
				System.out.println("-- Ordered Items --");
				
				for (int i = 0; i < orderedItems.size() ; i++ ) {
					OrderItem orderedItem = orderedItems.get(i);
					System.out.println("Item Name: "+ orderedItem.menuItem.name + "\n Qnt: " + orderedItem.quantity + "\n");
				}
			}
		});
		btnDetail.setBounds(605, 277, 89, 23);
		contentPane.add(btnDetail);
	}
}
