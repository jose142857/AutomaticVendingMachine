package jihanki;

public class Money {

	int type;
	int quantity;



	public Money(int type, int quantity) {

		this.type = type;
		this.quantity = quantity;
	}



	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Money [type=" + type + ", quantity=" + quantity + "]";
	}

}
