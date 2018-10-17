package Classes;

import java.util.ArrayList;

public class DeliveryMan {
	public int id;
	public String name;
	public String vehiclePlate;
	
	public ArrayList<Order> orders;
	
	public DeliveryMan(int id, String name, String vehiclePlate, ArrayList<Order> orders) {
		this.id = id;
		this.name = name;
		this.vehiclePlate = vehiclePlate;
		this.orders = new ArrayList<Order>();
	}
}
