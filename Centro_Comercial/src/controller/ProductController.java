package controller;

import entity.Product;
import entity.Store;
import model.ProductModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ProductController {
    ProductModel productModel;

    public ProductController(ProductModel productModel){
        this.productModel = new ProductModel();
    }

    public void findByFilter(){

        String[] options = {"ID" ,"Name"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filtro", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Introduzca los datos solicitados para la consulta \n" + "(El id del producto, nombre del producto)");

        List<Product> listProduct = this.productModel.findByFilter(selectedFilter, valueFilter);
    }
    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Product objProduct = (Product) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objProduct);
    }
    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Product objProduct = (Product) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String name = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto: " + objProduct.getNameProduct());
        double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el precio del producto: " + objProduct.getPrice()));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el stock del producto: " + objProduct.getStock()));


        Object[] optionsStores = Utils.listToArray(StoreController.instanceModel().findAll());

        Store objStore = (Store) JOptionPane.showInputDialog(
                null,
                "Seleccione una tienda: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsStores,
                optionsStores[0]
        );

        instanceModel().update(new Product(name, price, objStore.getId(),objStore, stock));

    }
    public static void getAll(){
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }
    public static String getAll(List<Object> list){
        String listString = "Lista de registros \n";

        for (Object temp : list){
            Product objProduct = (Product) temp;
            listString += objProduct.toString() + "\n";
        }
        return listString;
    }
    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre del producto");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock del producto"));

        Object[] optionsStore = Utils.listToArray(StoreController.instanceModel().findAll());


        Store objStore = (Store) JOptionPane.showInputDialog(
                null,
                "Seleccione una tienda: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsStore,
                optionsStore[0]
        );

        instanceModel().insert(new Product(name, price, objStore.getId(), objStore, stock));
    }

    //Creamos una instancia de model
    public static ProductModel instanceModel(){
        return new ProductModel();
    }
}
