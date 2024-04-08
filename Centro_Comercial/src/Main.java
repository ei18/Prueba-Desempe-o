import controller.CustomerController;
import controller.ProductController;
import controller.ShoppingController;
import database.ConfigDB;
import model.CustomerModel;
import model.ProductModel;
import model.ShoppingModel;

import javax.swing.*;

import static controller.ProductController.insert;

public class Main {
    public static void main(String[] args) {
        /*ConfigDB.openConnection();*/

        ProductModel productModel = new ProductModel();
        ProductController productController = new ProductController(productModel);

        CustomerModel customerModel = new CustomerModel();
        CustomerController customerController = new CustomerController(customerModel);

        ShoppingModel shoppingModel = new ShoppingModel();
        ShoppingController shoppingController = new ShoppingController(shoppingModel);

        int option = 0, option2 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ¡BIENVENIDO A DE MODA OUTLET!
                    1. Administrar Productos
                    2. Administrar Clientes
                    3. Administrar Compras
                    4. Salir.
                                                      
                    Ingrese una opción:
                    """));
            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                            1. Listar Productos
                            2. Crear Producto
                            3. Actualizar Producto
                            4. Eliminar Producto
                            5. Filtrar Producto
                            6. Salir
                            
                            Ingrese una opción:
                            """));

                        switch (option2){
                            case 1:
                                ProductController.getAll();
                                break;

                            case 2:
                                ProductController.insert();
                                break;

                            case 3:
                                ProductController.update();
                                break;

                            case 4:
                                ProductController.delete();
                                break;

                            case 5:
                                productController.findByFilter();
                                break;

                        }
                    }while (option2 != 6);
                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                            1. Listar Clientes
                            2. Crear Cliente
                            3. Actualizar Cliente
                            4. Eliminar Cliente
                            5. Filtrar Cliente
                            6. Salir
                            
                            Ingrese una opción:
                            """));

                        switch (option2){
                            case 1:
                                CustomerController.getAll();
                                break;

                            case 2:
                                CustomerController.insert();
                                break;

                            case 3:
                                CustomerController.update();
                                break;

                            case 4:
                                CustomerController.delete();
                                break;

                            case 5:
                                customerController.findByFilters();
                                break;
                        }

                    }while (option2 != 6);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                            1. Listar Compras
                            2. Crear Compra
                            3. Actualizar Compra
                            4. Eliminar Compra
                            5. Filtrar Compra
                            6. Salir
                            
                            Ingrese una opción:
                            """));

                        switch (option2){
                            case 1:
                                ShoppingController.getAll();
                                break;

                            case 2:
                                ShoppingController.insert();
                                break;

                            case 3:
                                ShoppingController.update();
                                break;

                            case 4:
                                ShoppingController.delete();
                                break;

                            case 5:
                                shoppingController.findByFilterss();
                                break;
                        }
                    }while (option2 != 6);
                    break;
            }
        }while (option != 4);

    }
}