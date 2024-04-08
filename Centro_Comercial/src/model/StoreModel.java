package model;

import database.CRUD;
import database.ConfigDB;
import entity.Product;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreModel {
    public List<Object> findAll() {

        List<Object> listStores = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM stores;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Store objStore = new Store();

                objStore.setId(objResult.getInt("id"));
                objStore.setNameStore(objResult.getString("name_store"));
                objStore.setAddress(objResult.getString("address"));

                listStores.add(objStore);
            }
        } catch (
                SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listStores;
    }


    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Store objStore = (Store) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE stores SET name_store = ?, address = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objStore.getNameStore());
            objPrepare.setString(2, objStore.getAddress());

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


}
