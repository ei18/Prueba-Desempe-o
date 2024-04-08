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

public class ShoppingModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Shopping objShopp = (Shopping) obj;

        try {
            String sql = "INSERT INTO shoppings(date_shopping, quantity, id_customer, id_product) VALUES(?, ?, ?, ?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objShopp.getDateShopping()));
           objPrepare.setInt(2, objShopp.getQuantity());
            objPrepare.setInt(3, objShopp.getIdCustomer());
            objPrepare.setInt(4, objShopp.getIdProduct());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objShopp.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Registro insertado correctamente.");

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objShopp;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listShopp = new ArrayList<>();

        try {
            String sql = "SELECT * FROM shoppings\n" +
                    "INNER JOIN customers ON customers.id = shoppings.id_customer\n" +
                    "INNER JOIN products ON products.id = shoppings.id_product;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Shopping objShopp = new Shopping();
                Customer objCustomer = new Customer();
                Product objProduct = new Product();

                objShopp.setId(objResult.getInt("shoppings.id"));
                objShopp.setDateShopping(objResult.getDate("shoppings.date_shopping").toLocalDate());
                objShopp.setQuantity(objResult.getInt("shoppings.quantity"));
                objShopp.setIdCustomer(objResult.getInt("shoppings.id_customer"));
                objShopp.setIdProduct(objResult.getInt("shoppings.id_product"));

                objCustomer.setNameCustomer(objResult.getString("customers.name_customer"));
                objProduct.setNameProduct(objResult.getString("products.name_product"));

                objShopp.setObjCustomer(objCustomer);
                objShopp.setObjProduct(objProduct);

                listShopp.add(objShopp);
            }

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listShopp;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Shopping objShopping = (Shopping) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE shoppings SET id_customer = ?, id_product = ?, date_shopping = ?, quantity = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objShopping.getIdCustomer());
            objPrepare.setInt(2, objShopping.getIdProduct());
            objPrepare.setDate(3, Date.valueOf(objShopping.getDateShopping()));
            objPrepare.setInt(4, objShopping.getQuantity());
            objPrepare.setInt(5, objShopping.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente.");
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

        Shopping objShopping = (Shopping) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM shoppings WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objShopping.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    @Override
    public List<Shopping> findByFilterss(String filter, String value) {
        String sql;

        List<Shopping> listShopp = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM shoppings WHERE id = ?;";

                listShopp = findById(sql, value);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listShopp;
    }

    public List<Shopping> findById(String sql, String value) {

        Connection objConnection = ConfigDB.openConnection();
        List<Shopping> listShopp = new ArrayList<>();

        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, Integer.parseInt(value));
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Shopping objShopp = new Shopping();

                objShopp.setId(objResult.getInt("id"));
                objShopp.setDateShopping(objResult.getDate("date_shopping").toLocalDate());
                objShopp.setQuantity(objResult.getInt("quantity"));

                listShopp.add(objShopp);
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Lista de compras: " + listShopp);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        return listShopp;
    }


    //Estos dos filtros no se utilizan
    @Override
    public List<Product> findByFilter(String filter, String value) {
        return null;
    }

    @Override
    public List<Customer> findByFilters(String filter, String value) {
        return null;
    }

}
