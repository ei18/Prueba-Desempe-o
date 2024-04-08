package entity;

public class Store {
    //Atributos
    private int id;
    private String nameStore;
    private String address;

    //Método constructor vacío y lleno
    public Store() {
    }

    public Store(String nameStore, String address) {
        this.nameStore = nameStore;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "nameStore: " + nameStore +
                ", address: " + address;
    }
}
