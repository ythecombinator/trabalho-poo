import SQL.SQLManager;
import View.InitialFrame;

public class Main {
	
	public static void main(String[] args) {
		SQLManager.createClientTable();
		SQLManager.createDeliveryMenTable();
		SQLManager.createMenuItemsTable();
		SQLManager.createOrdersTable();
		SQLManager.createOrdersItemsTable();
		
		try {
			InitialFrame window = new InitialFrame();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
