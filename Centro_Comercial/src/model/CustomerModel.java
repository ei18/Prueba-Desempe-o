package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;
import entity.Product;
import entity.Shopping;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Customer objCustomer = (Customer) obj;

        try {
            String sql = "INSERT INTO customers (name_customer, last_name, email) VALUES (?, ?, ?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCustomer.getNameCustomer());
            objPrepare.setString(2, objCustomer.getLastName());
            objPrepare.setString(3, objCustomer.getEmail());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCustomer.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Registro exitoso.");

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCustomer;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listCustomer = new ArrayList<>();

        try {
            String sql = "SELECT * FROM customers;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Customer objCustomer = new Customer();

                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setNameCustomer(objResult.getString("name_customer"));
                objCustomer.setLastName(objResult.getString("last_name"));
                objCustomer.setEmail(objResult.getString("email"));

                listCustomer.add(objCustomer);
            }

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();

        return listCustomer;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Customer objCustomer = (Customer) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE customers SET name_customer = ?, last_name = ?, email = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCustomer.getNameCustomer());
            objPrepare.setString(2, objCustomer.getLastName());
            objPrepare.setString(3, objCustomer.getEmail());
            objPrepare.setInt(4, objCustomer.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;

                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
            }

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Customer objCustomer = (Customer) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM customers WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCustomer.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente.");
            }

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public List<Customer> findByFilters(String filter, String value) {
        String sql;

        List<Customer> listCustomer = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM customers WHERE id = ?;";

                listCustomer = findById(sql, value);

            }
            if (Objects.equals(filter, "Name")) {

                sql = "SELECT * FROM customers WHERE name_customer LIKE ?;";

                listCustomer = findByName(sql, value);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listCustomer;
    }

    public List<Customer> findById(String sql, String value) {

        Connection objConnection = ConfigDB.openConnection();
        List<Customer> listCustomer = new ArrayList<>();

        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, Integer.parseInt(value));
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Customer objCustomer = new Customer();

                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setNameCustomer(objResult.getString("name_customer"));
                objCustomer.setLastName(objResult.getString("last_name"));
                objCustomer.setEmail(objResult.getString("email"));

                listCustomer.add(objCustomer);
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes: " + listCustomer);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        return listCustomer;
    }


    private List<Customer> findByName(String sql, String value) {

        Connection objConnection = ConfigDB.openConnection();
        List<Customer> listCustomer = new ArrayList<>();

        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + value + "%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Customer objCustomer = new Customer();

                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setNameCustomer(objResult.getString("name_customer"));
                objCustomer.setLastName(objResult.getString("last_name"));
                objCustomer.setEmail(objResult.getString("email"));

                listCustomer.add(objCustomer);
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes: " + listCustomer);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        return listCustomer;
    }

    //Estos dos filtros no se utilizan
    @Override
    public List<Product> findByFilter(String filter, String value) {
        return null;
    }

    @Override
    public List<Shopping> findByFilterss(String filter, String value) {
        return null;
    }

}
