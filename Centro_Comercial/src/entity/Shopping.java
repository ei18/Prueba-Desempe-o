package entity;

import java.time.LocalDate;

public class Shopping {
    private int id;
    private int idCustomer;
    private int idProduct;
    private LocalDate dateShopping;
    private int quantity;

    private Customer objCustomer;
    private Product objProduct;

    public Shopping() {
    }

    public Shopping(int idCustomer, int idProduct, LocalDate dateShopping, int quantity, Customer objCustomer, Product objProduct) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.dateShopping = dateShopping;
        this.quantity = quantity;
        this.objCustomer = objCustomer;
        this.objProduct = objProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public LocalDate getDateShopping() {
        return dateShopping;
    }

    public void setDateShopping(LocalDate dateShopping) {
        this.dateShopping = dateShopping;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getObjCustomer() {
        return objCustomer;
    }

    public void setObjCustomer(Customer objCustomer) {
        this.objCustomer = objCustomer;
    }

    public Product getObjProduct() {
        return objProduct;
    }

    public void setObjProduct(Product objProduct) {
        this.objProduct = objProduct;
    }

    @Override
    public String toString() {
        return "Shopping: " +
                "id: " + id +
                ", dateShopping: " + dateShopping +
                ", quantity: " + quantity;
    }
}
