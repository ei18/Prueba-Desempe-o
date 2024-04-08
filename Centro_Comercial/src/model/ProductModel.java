package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;
import entity.Product;
import entity.Shopping;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static database.ConfigDB.objConnection;

public class ProductModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Castear el objeto
        Product objProduct = (Product) obj;

        try {
            //Sentencia SQL
            String sql = "INSERT INTO products(name_product, price, id_store, stock) VALUES(?, ?, ?, ?);";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignamos valores al query ?
            objPrepare.setString(1, objProduct.getNameProduct());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getIdStore());
            objPrepare.setInt(4, objProduct.getStock());

            //Ejecutamos
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objProduct.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Registro creado correctamente.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR > " + e.getMessage());
        }

        //Cerramos la conexión
        ConfigDB.closeConnection();

        return objProduct;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear una nueva lista
        List<Object> listProducts = new ArrayList<>();

        try {
            //Sentencia sql con INNER JOIN, por tener foreign_key
            String sql = "SELECT * FROM products\n" +
                    "INNER JOIN stores ON stores.id = products.id_store;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //Crear un nuevo Product y Store
                Product objProduct = new Product();
                Store objStore = new Store();

                //Asignar valores, con INNER JOIN se especifica la tabla. Primero se realiza con el nuevo objeto de la entidad principal, después con el nuevo objeto de la entidad de la llave foránea
                objProduct.setId(objResult.getInt("products.id"));
                objProduct.setNameProduct(objResult.getString("products.name_product"));
                objProduct.setPrice(objResult.getDouble("products.price"));
                objProduct.setIdStore(objResult.getInt("products.id_store"));
                objProduct.setStock(objResult.getInt("products.stock"));

                objStore.setId(objResult.getInt("stores.id"));
                objStore.setNameStore(objResult.getString("stores.name_store"));
                objStore.setAddress(objResult.getString("stores.address"));

                objProduct.setObjStore(objStore);
                listProducts.add(objProduct);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR > " + e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listProducts;
    }

    @Override
    public boolean update(Object obj) {
        //Abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        //Castear el objeto que llega por parámetro
        Product objProduct = (Product) obj;

        //Crear bandera
        boolean isUpdated = false;

        try {
            //Sentencia sql
            String sql = "UPDATE products SET name_product = ?, price = ?, id_store = ?, stock = ? WHERE id = ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objProduct.getNameProduct());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getIdStore());
            objPrepare.setInt(4, objProduct.getStock());
            objPrepare.setInt(5, objProduct.getId());

            //Validar el total de filas afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente.");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR > " + e.getMessage());
        }

        //Cerrar conexión
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Product objProduct = (Product) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM products WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objProduct.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente.");
            }
        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Product> findByFilter(String filter, String value) {
        String sql;

        List<Product> listProduct = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM products WHERE id = ?;";

                listProduct = findById(sql, value);

            }
            if (Objects.equals(filter, "Name")) {

                sql = "SELECT * FROM products WHERE name_product LIKE ?;";

                listProduct = findByName(sql, value);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listProduct;
    }

    public List<Product> findById(String sql, String value) {

        Connection objConnection = ConfigDB.openConnection();
        List<Product> listProduct = new ArrayList<>();

        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, Integer.parseInt(value));
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Product objProduct = new Product();

                objProduct.setId(objResult.getInt("id"));
                objProduct.setNameProduct(objResult.getString("name_product"));
                objProduct.setPrice(Double.parseDouble(String.valueOf(objResult.getDouble("price"))));

                listProduct.add(objProduct);
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Lista de productos: " + listProduct);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        return listProduct;
    }


    private List<Product> findByName(String sql, String value) {

        Connection objConnection = ConfigDB.openConnection();
        List<Product> listProduct = new ArrayList<>();

        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + value + "%");
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Product objProduct = new Product();

                objProduct.setId(objResult.getInt("id"));
                objProduct.setNameProduct(objResult.getString("name_product"));
                objProduct.setPrice(objResult.getDouble("price"));


                listProduct.add(objProduct);
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Lista de productos: " + listProduct);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());

        }

        return listProduct;
    }

    public void updateProduct(int quantity, int id) {

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE products SET stock = ? WHERE id = ?;";


            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, quantity);
            objPrepare.setInt(2, id);

            objPrepare.execute();

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    //Estos dos filtros no se utilizan
    @Override
    public List<Customer> findByFilters(String filter, String value) {
        return null;
    }

    @Override
    public List<Shopping> findByFilterss(String filter, String value) {
        return null;
    }

}
