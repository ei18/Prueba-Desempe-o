package controller;

import database.CRUD;
import model.ProductModel;
import model.StoreModel;

public class StoreController {

    public static StoreModel instanceModel(){
        return new StoreModel();
    }
}
