/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.examples.view;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author techsupport
 */
@ManagedBean
@RequestScoped
public class viewInventories {

    /**
     * Creates a new instance of viewInventories
     */
    public List<Inventory> inventoryList = new ArrayList<Inventory>();
    private Inventory inventory;
    private Inventory inventories[];
    private List<Inventory> filteredInventory;

    public List<Inventory> getFilteredInventory() {
        return filteredInventory;
    }

    public void setFilteredInventory(List<Inventory> filteredInventory) {
        this.filteredInventory = filteredInventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory[] getInventories() {
        return inventories;
    }

    public void setInventories(Inventory[] inventories) {
        this.inventories = inventories;
    }

    public List<Inventory> getInventoryList() throws UnknownHostException {
        DB db = (new MongoClient("localhost", 27017).getDB("shopData"));
        DBCollection dBCollection = db.getCollection("Item");
        DBCollection dbc = db.getCollection("ItemDetails");
        DBCursor dBCursor = dBCollection.find();
        DBCursor dCursor = dbc.find();
        while (dBCursor.hasNext() && dCursor.hasNext()) {

            DBObject ob = dBCursor.next();
            DBObject dBObject = dCursor.next();

            if (!ob.get("_id").toString().equals("")) {
                if (ob.get("_id").toString().equals(dBObject.get("itemCode").toString())) {
                    Inventory inventory = new Inventory();
                    inventory.setItemCode(ob.get("_id").toString());
                    inventory.setItemName(ob.get("itemName").toString());
                    inventory.setProducerName(ob.get("manufacturerName").toString());
                    inventory.setQty(dBObject.get("Quantity").toString());
                    inventoryList.add(inventory);
                }

            }

        }
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
