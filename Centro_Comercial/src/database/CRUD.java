package database;

import entity.Customer;
import entity.Product;
import entity.Shopping;

import java.util.List;

public interface CRUD {
    public Object insert(Object obj);
    public List<Object> findAll();
    public boolean update(Object obj);
    public boolean delete(Object obj);
    public List<Product> findByFilter(String filter, String value);
    public List<Customer> findByFilters(String filter, String value);
    public List<Shopping> findByFilterss(String filter, String value);


}

