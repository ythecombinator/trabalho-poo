package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.Box;

public class InitialFrame {

	public JFrame frame;
	private JTable table;

	/**
	 * Create the application.
	 */
	public InitialFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 590, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnClients = new JMenu("Clients");
		menuBar.add(mnClients);
		
		JMenuItem mntmAddClient = new JMenuItem("Add Client");
		mntmAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new AddClientFrame().setVisible(true);
				
			}
		});
		mnClients.add(mntmAddClient);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmAddItemTo = new JMenuItem("Add Item to Menu");
		mntmAddItemTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddItemMenuFrame().setVisible(true);
				
			}
		});
		mnMenu.add(mntmAddItemTo);
		
		JMenu mnDeliveryMan = new JMenu("DeliveryMan");
		menuBar.add(mnDeliveryMan);
		
		JMenuItem mntmHireDeliveryMan = new JMenuItem("Hire delivery man");
		mntmHireDeliveryMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new HireDeliveryManFrame().setVisible(true);
				
			}
		});
		mnDeliveryMan.add(mntmHireDeliveryMan);
		
		JMenu mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmClearDb = new JMenuItem("Clear DB");
		mntmClearDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ClearDBFrame().setVisible(true);
				
			}
		});
		mnAdmin.add(mntmClearDb);
		
		JMenu mnActionlog = new JMenu("ActionLog");
		menuBar.add(mnActionlog);
		
		JMenuItem mntmShowAllClients = new JMenuItem("Show all Clients");
		mntmShowAllClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new AllClientsFrame().setVisible(true);
				
			}
		});
		mnActionlog.add(mntmShowAllClients);
		
		JMenuItem mntmShowAllDeliveryMan = new JMenuItem("Show all Delivery man");
		mntmShowAllDeliveryMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AllDeliveryMenFrame().setVisible(true);
				
			}
		});
		mnActionlog.add(mntmShowAllDeliveryMan);
		
		JMenuItem mntmShowAllItensInMenu = new JMenuItem("Show all Itens in Menu");
		mntmShowAllItensInMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AllItemsInMenuFrame().setVisible(true);
				
			}
		});
		mnActionlog.add(mntmShowAllItensInMenu);
		
		JMenuItem mntmShowAllOrders = new JMenuItem("Show all Orders");
		mntmShowAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AllOrdersFrame().setVisible(true);
				
			}
		});
		mnActionlog.add(mntmShowAllOrders);
		
		JMenuItem mntmShowReport = new JMenuItem("Show report");
		mnActionlog.add(mntmShowReport);
		
		
		

		JButton btnCreateOrder = new JButton("Create Order");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddOrderFrame().setVisible(true);
				
			}
		});
		btnCreateOrder.setBounds(10, 11, 120, 23);
		frame.getContentPane().add(btnCreateOrder);
		
		JButton btnEditOrder = new JButton("Edit Order");
		btnEditOrder.setBounds(454, 11, 120, 23);
		frame.getContentPane().add(btnEditOrder);
		
		JButton btnUpdate = new JButton("Update Table");
		btnUpdate.setBounds(140, 11, 120, 23);
		frame.getContentPane().add(btnUpdate);
		
		table = new JTable();
		String[] rows = {"ID","Client Info","Date","Status","Active","Delivery Man Name"};
		String[][] cols = {};
		DefaultTableModel tableModel = new DefaultTableModel(cols, rows);
		table.setModel(tableModel);
		table.setRowSelectionAllowed(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 45, 564, 344);
		frame.getContentPane().add(scrollPane);
	}
}
