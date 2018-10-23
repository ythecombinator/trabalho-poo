package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import SQL.SQLManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class HireDeliveryManFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDeliveryManName;

	/**
	 * Create the frame.
	 */
	public HireDeliveryManFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		Label lblDeliveryManName = new Label("Delivery Man Name");
		lblDeliveryManName.setBounds(10, 10, 414, 22);
		contentPane.add(lblDeliveryManName);
		
		txtDeliveryManName = new JTextField();
		txtDeliveryManName.setBounds(10, 38, 414, 20);
		contentPane.add(txtDeliveryManName);
		txtDeliveryManName.setColumns(10);
		
		Label lblDeliveryManVehiclePlate = new Label("Vehicle Plate");
		lblDeliveryManVehiclePlate.setBounds(10, 64, 414, 22);
		contentPane.add(lblDeliveryManVehiclePlate);
		
		JFormattedTextField ftxtDeliveryManVehiclePlate = new JFormattedTextField();
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("UUU-####");
			mask.install(ftxtDeliveryManVehiclePlate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDeliveryManVehiclePlate.setBounds(10, 92, 414, 20);
		contentPane.add(ftxtDeliveryManVehiclePlate);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnCancel.setBounds(10, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtDeliveryManName.getText();
				String vehiclePlate = ftxtDeliveryManVehiclePlate.getText();
				
				SQLManager.insertNewDeliveryMan(name, vehiclePlate);
				
				setVisible(false);
				dispose();
				
			}
		});
		btnSave.setBounds(335, 227, 89, 23);
		contentPane.add(btnSave);
	}

}
