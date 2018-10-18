package Classes;

public class Drink extends MenuItem{

	public int stockQuantity;

	public Drink(int id, String name, float price, int inStock) {
		super(id, name, price);
		this.stockQuantity = inStock;
	}
}
