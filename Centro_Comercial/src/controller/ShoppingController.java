package controller;

import entity.Customer;
import entity.Product;
import entity.Shopping;
import model.CustomerModel;
import model.ShoppingModel;
import utils.Utils;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ShoppingController {
    ShoppingModel shoppingModel;

    public ShoppingController(ShoppingModel shoppingModel){
        this.shoppingModel = new ShoppingModel();
    }

    public void findByFilterss(){

        String[] options = {"ID"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filtro", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Introduzca los datos solicitados para la consulta \n" + "(El id de la compra)");

        List<Shopping> listShopp = this.shoppingModel.findByFilterss(selectedFilter, valueFilter);
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Shopping shoppSelected = (Shopping) JOptionPane.showInputDialog(
                null,
                "Seleccione la compra a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(shoppSelected);
    }
    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Shopping shoppSelected = (Shopping) JOptionPane.showInputDialog(
                null,
                "Seleccione la compra a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        shoppSelected.setDateShopping(LocalDate.parse(JOptionPane.showInputDialog(null,"Ingresa la fecha de la compra: YYYY-MM-DD", shoppSelected.getDateShopping())));
        shoppSelected.setQuantity(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cantidad de la compra", shoppSelected.getQuantity())));


        Object[] optionsCustomer = Utils.listToArray(CustomerController.instanceModel().findAll());
        Object[] optionsProduct = Utils.listToArray(ProductController.instanceModel().findAll());

        shoppSelected.setObjCustomer((Customer) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCustomer,
                optionsCustomer[0]
        ));

        shoppSelected.setIdCustomer(shoppSelected.getObjCustomer().getId());

        shoppSelected.setObjProduct((Product) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsProduct,
                optionsProduct[0]
        ));

        shoppSelected.setIdProduct(shoppSelected.getObjProduct().getId());

        instanceModel().update(shoppSelected);
    }
    public static void getAll(){
        String listString = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, listString);
    }
    public static String getAll(List<Object> list){
        String listString = "Lista de registros\n";

        for (Object temp : list){
            Shopping obj = (Shopping) temp;
            listString += obj.toString() + "\n";
        }
        return listString;
    }
    public static void insert(){
        LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Ingresa la fecha de la compra YYYY-MM-DD"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad de la compra"));

        Object[] optionsCustomer = Utils.listToArray(CustomerController.instanceModel().findAll());
        Object[] optionsProduct = Utils.listToArray(ProductController.instanceModel().findAll());

        Customer customerSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCustomer,
                optionsCustomer[0]
        );

        Product productSelected = (Product) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsProduct,
                optionsProduct[0]
        );

        instanceModel().insert(new Shopping(customerSelected.getId(), productSelected.getId(), date, quantity, customerSelected, productSelected));

        Shopping objShopp = new Shopping();
        int updateCantidad = productSelected.getStock() - quantity;

        if (quantity > productSelected.getStock()) {
            JOptionPane.showMessageDialog(null, "La cantidad de productos ingresada es mayor al stock disponible");
            quantity = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad: )"));

            objShopp.setIdCustomer(customerSelected.getId());
            objShopp.setIdProduct(productSelected.getId());
            objShopp.setQuantity(quantity);
            instanceModel().insert(objShopp);

        }else {

            objShopp.setIdCustomer(customerSelected.getId());
            objShopp.setIdProduct(productSelected.getId());
            objShopp.setQuantity(quantity);
            instanceModel().insert(objShopp);

        }
    }
    public static ShoppingModel instanceModel(){
        return new ShoppingModel();
    }
}
