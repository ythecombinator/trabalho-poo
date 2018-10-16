package Classes;

import java.util.ArrayList;

public class Order {
	public int id;
	
	public Client client;
	
	public String status;
	public String date;
	public int deliveryManID;

	public ArrayList<OrderItem> orderedItems;
	
	public Order(int id, Client client, String status, String date, int deliveryManID, ArrayList<OrderItem> orderedItems) {
		this.id = id;

		this.client = client;
		
		this.status = status;
		this.date = date;
		this.deliveryManID = deliveryManID;

		this.orderedItems = orderedItems;
	}
	
	public Order() {}
}
