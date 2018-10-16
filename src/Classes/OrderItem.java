package Classes;

public class OrderItem {
	public int id;
	public int orderID;
	public int quantity;
	
	public MenuItem menuItem;
	
	public OrderItem(int id, int orderID, int quantity, MenuItem menuItem) {
		this.id = id;
		this.quantity = quantity;
		this.menuItem = menuItem;
	}
}
