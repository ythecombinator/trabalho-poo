package Classes;

public class Check {
	public int id;
	public int bankAccount;
	public int bankAgency;
	public String bankName;
	
	public Order order;
	
	public Check(int id, int bankAccount, int bankAgency, String bankName, Order order) {
		this.id = id;
		this.bankAccount = bankAccount;
		this.bankAgency = bankAgency;
		this.bankName = bankName;
		
		this.order = order;
	}
}
