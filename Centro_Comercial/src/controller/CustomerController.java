package controller;

import entity.Customer;
import entity.Product;
import model.CustomerModel;
import model.ProductModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CustomerController {
    CustomerModel customerModel;

    public CustomerController(CustomerModel customerModel){
        this.customerModel = new CustomerModel();
    }

    public void findByFilters(){

        String[] options = {"ID" ,"Name", "Last name", "Email"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filtro", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Introduzca los datos solicitados para la consulta \n" + "(El id del cliente, nombre del cliente");

        List<Customer> listCustomer = this.customerModel.findByFilters(selectedFilter, valueFilter);
    }
    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Customer customerSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(customerSelected);
    }
    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Customer customerSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        customerSelected.setNameCustomer(JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre", customerSelected.getNameCustomer()));
        customerSelected.setLastName(JOptionPane.showInputDialog(null,"Ingrese el nuevo apellido", customerSelected.getLastName()));
        customerSelected.setEmail(JOptionPane.showInputDialog(null,"Ingrese el nuevo email", customerSelected.getEmail()));

        instanceModel().update(customerSelected);
    }
    public static void getAll(){
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }
    public static String getAll(List<Object> list){
        String listString = "Lista de registros";

        for (Object temp : list){
            Customer objCustomer = (Customer) temp;
            listString += objCustomer.toString() + "\n";
        }
        return listString;
    }
    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingresa el nombre del cliente");
        String lastName = JOptionPane.showInputDialog("Ingresa los apellidos del cliente");
        String email = JOptionPane.showInputDialog("Ingresa el email del cliente");

        instanceModel().insert(new Customer(name, lastName, email));

    }

    public static CustomerModel instanceModel(){
        return new CustomerModel();
    }
}
