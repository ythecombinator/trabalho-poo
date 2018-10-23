package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SQL.SQLManager;

import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClearDBFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField ptxtPassword;

	/**
	 * Create the frame.
	 */
	public ClearDBFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label lblLogin = new Label("Admin Login");
		lblLogin.setBounds(10, 10, 414, 22);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(10, 38, 414, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		Label lblPassword = new Label("Admin Password");
		lblPassword.setBounds(10, 64, 414, 22);
		contentPane.add(lblPassword);
		
		ptxtPassword = new JPasswordField();
		ptxtPassword.setBounds(10, 92, 414, 20);
		contentPane.add(ptxtPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnCancel.setBounds(10, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnClearDb = new JButton("Clear DB");
		btnClearDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean isAdminLogin = txtLogin.getText().equals("admin");
				
				boolean isAdminPassword = new String(ptxtPassword.getPassword()).equals("admin");
						
				if (isAdminLogin && isAdminPassword) {
					SQLManager.deleteTableNamed("clients");
					SQLManager.deleteTableNamed("deliverymen");
					SQLManager.deleteTableNamed("menuitems");
					SQLManager.deleteTableNamed("orders");
					SQLManager.deleteTableNamed("ordersitems");

					SQLManager.createClientTable();
					SQLManager.createDeliveryMenTable();
					SQLManager.createMenuItemsTable();
					SQLManager.createOrdersTable();
					SQLManager.createOrdersItemsTable();
				}
				
				setVisible(false);
				dispose();
				
			}
		});
		btnClearDb.setBounds(335, 227, 89, 23);
		contentPane.add(btnClearDb);
	}
}
