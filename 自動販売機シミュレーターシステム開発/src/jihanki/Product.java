package jihanki;

public class Product {
    public Product(int id, String name, int value, int quantity) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }
    public Product() {

    }
    int id;
    String name;
    int value;
    int quantity;
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", value=" + value + ", quantity=" + quantity + "]";
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}