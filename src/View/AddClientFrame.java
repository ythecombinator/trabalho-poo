package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import SQL.SQLManager;

import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

public class AddClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtClientName;
	private JTextField txtClientAddress;
	private JTextField txtClientAddressReference;


	/**
	 * Create the frame.
	 */
	public AddClientFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label lblClientName = new Label("Client Name");
		lblClientName.setBounds(10, 10, 414, 22);
		contentPane.add(lblClientName);
		
		txtClientName = new JTextField();
		txtClientName.setBounds(10, 38, 414, 20);
		contentPane.add(txtClientName);
		txtClientName.setColumns(10);
		
		Label lblClientAddress = new Label("Client Address");
		lblClientAddress.setBounds(10, 64, 414, 22);
		contentPane.add(lblClientAddress);
		
		txtClientAddress = new JTextField();
		txtClientAddress.setBounds(10, 92, 414, 20);
		contentPane.add(txtClientAddress);
		txtClientAddress.setColumns(10);
		
		Label lblClientAddressReference = new Label("Client Address Reference");
		lblClientAddressReference.setBounds(10, 118, 414, 22);
		contentPane.add(lblClientAddressReference);
		
		txtClientAddressReference = new JTextField();
		txtClientAddressReference.setBounds(10, 146, 414, 20);
		contentPane.add(txtClientAddressReference);
		txtClientAddressReference.setColumns(10);
		
		Label lblClientPhone = new Label("Client Phone");
		lblClientPhone.setBounds(10, 172, 414, 22);
		contentPane.add(lblClientPhone);
		
		JFormattedTextField ftxtClientPhone = new JFormattedTextField();
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("+##(##)#########");
			mask.install(ftxtClientPhone);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ftxtClientPhone.setBounds(10, 200, 414, 20);
		contentPane.add(ftxtClientPhone);
		
		
		
		Button btnCancel = new Button("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnCancel.setBounds(10, 289, 70, 22);
		contentPane.add(btnCancel);
		
		Button btnSave = new Button("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtClientName.getText();
				String address = txtClientAddress.getText();
				String addressReference = txtClientAddressReference.getText();
				String phone = ftxtClientPhone.getText();
				
				SQLManager.insertNewClient(name, address, addressReference, phone);;
				
				setVisible(false);
				dispose();
				
			}
		});
		btnSave.setBounds(354, 289, 70, 22);
		contentPane.add(btnSave);
	}
}
