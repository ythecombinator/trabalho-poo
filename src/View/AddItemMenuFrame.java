package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import SQL.SQLManager;

import java.awt.Label;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddItemMenuFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtMenuItemName;

	/**
	 * Create the frame.
	 */
	public AddItemMenuFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label lblMenuItemName = new Label("Menu Item Name");
		lblMenuItemName.setBounds(10, 10, 424, 22);
		contentPane.add(lblMenuItemName);
		
		txtMenuItemName = new JTextField();
		txtMenuItemName.setBounds(10, 38, 424, 20);
		contentPane.add(txtMenuItemName);
		txtMenuItemName.setColumns(10);
		
		Label lblMenuItemPrice = new Label("Menu Item Price(R$)");
		lblMenuItemPrice.setBounds(10, 64, 424, 22);
		contentPane.add(lblMenuItemPrice);
		
		JFormattedTextField ftxtMenuItemPrice = new JFormattedTextField();
		MaskFormatter maskPrice;
		try {
			maskPrice = new MaskFormatter("#################################");
			maskPrice.install(ftxtMenuItemPrice);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtMenuItemPrice.setBounds(10, 92, 424, 20);
		contentPane.add(ftxtMenuItemPrice);
		
		Label lblMenuItemType = new Label("Menu Item Type");
		lblMenuItemType.setBounds(10, 118, 424, 22);
		contentPane.add(lblMenuItemType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Snack");
		comboBox.addItem("Dish");
		comboBox.addItem("Drink");
		comboBox.addItem("Dessert");
		comboBox.setBounds(10, 146, 424, 20);
		contentPane.add(comboBox);
		
		Label lblMenuItemQuantity = new Label("Menu Item Quantity in stock");
		lblMenuItemQuantity.setBounds(10, 172, 424, 22);
		contentPane.add(lblMenuItemQuantity);
		
		JFormattedTextField ftxtMeniItemQuantity = new JFormattedTextField();
		MaskFormatter maskQuantity;
		try {
			maskQuantity = new MaskFormatter("####################################");
			maskQuantity.install(ftxtMeniItemQuantity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ftxtMeniItemQuantity.setBounds(10, 200, 424, 20);
		contentPane.add(ftxtMeniItemQuantity);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnCancel.setBounds(10, 307, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtMenuItemName.getText();
				float price = Float.parseFloat(ftxtMenuItemPrice.getText());
				String type = (String)comboBox.getSelectedItem();
				int quantity;

				System.out.println();
				if (type.equals("Drink")) {
					quantity = Integer.parseInt(ftxtMeniItemQuantity.getText().replaceAll(" ", ""));
				} else {
					quantity = -1;
				}
				
				SQLManager.insertNewMenuItem(name, price, type, quantity);
				
				setVisible(false);
				dispose();
				
			}
		});
		btnSave.setBounds(345, 307, 89, 23);
		contentPane.add(btnSave);
	}
}
