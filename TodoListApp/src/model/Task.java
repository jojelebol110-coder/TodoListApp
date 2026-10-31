package model;

import java.io.Serializable;

/** Represents one inventory/task record shown in MainForm. */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private String PId;
    private String Name;
    private String Product;
    private String Price;
    private String status;

    public Task() { }
    public Task(String name, String product, String price, String status) {
        this.Name = name;
        this.Product = product;
        this.Price = price;
        this.status = status;
    }
    public String getPId() { return PId; }
    public void setPId(String PId) { this.PId = PId; }
    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }
    public String getProduct() { return Product; }
    public void setProduct(String Product) { this.Product = Product; }
    public String getPrice() { return Price; }
    public void setPrice(String Price) { this.Price = Price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override public String toString() {
        return "Task{" + "PId=" + PId + ", Name=" + Name + ", Product=" + Product
                + ", Price=" + Price + ", status=" + status + '}';
    }
}
