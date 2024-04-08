package entity;

public class Customer {
    private int id;
    private String nameCustomer;
    private String lastName;
    private String email;

    public Customer() {
    }

    public Customer(String nameCustomer, String lastName, String email) {
        this.nameCustomer = nameCustomer;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer: " +
                "id: " + id +
                ", nameCustomer: " + nameCustomer +
                ", lastName: " + lastName +
                ", email: " + email;
    }
}
