package Classes;

public class Client {
	public int id;
	public String name;
	public String address;
	public String addressReference;
	public String phone;
	public boolean active;
	
	public Client(int id, String name, String address, String addressReference, String phone, boolean active) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.addressReference = addressReference;
		this.phone = phone;
		
		this.active = active;
	}
	
	public Client() {}
}
