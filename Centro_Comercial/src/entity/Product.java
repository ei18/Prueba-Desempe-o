package entity;

public class Product {
    private int id;
    private String nameProduct;
    private double price;
    private int idStore;
    private int stock;

    private Store objStore;

    public Product() {
    }

    public Product(String nameProduct, double price, int idStore, Store objStore, int stock) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.idStore = idStore;
        this.objStore = objStore;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public Store getObjStore() {
        return objStore;
    }

    public void setObjStore(Store objStore) {
        this.objStore = objStore;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product: " +
                "nameProduct: " + nameProduct +
                ", price: " + price +
                ", Store: " + objStore +
                ", stock: " + stock;
    }
}
