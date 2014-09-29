/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.examples.view;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author techsupport
 */
@ManagedBean
@RequestScoped
public class insertInventory {

    /**
     * Creates a new instance of insertInventory
     */
    private String itemName;
    private String producerName;
    private String barcode;
    private String itemCode;
    private String itemDetailCode;
    private String qty;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDetailCode() {
        return itemDetailCode;
    }

    public void setItemDetailCode(String itemDetailCode) {
        this.itemDetailCode = itemDetailCode;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void resetData() {
        this.itemName = null;
        this.producerName = null;
        this.barcode = null;
        this.itemDetailCode = null;
        this.qty = null;
    }

    public void insertItemData() throws UnknownHostException {
        DB db = (new MongoClient("localhost", 27017)).getDB("shopData"); // JDBC
        if (!itemName.equals("") && !producerName.equals("") && !barcode.equals("")) {
            DBCollection dBCollection = db.getCollection("Item"); // Requesting to get collection
            DBObject dBObject = new BasicDBObject(); //Creating a database object
            //inserting items to Java Database Object
            dBObject.put("itemName", itemName);
            dBObject.put("manufacturerName", producerName);
            dBObject.put("_id", barcode);
            dBCollection.insert(dBObject);
            DBCollection dBCollection1 = db.getCollection("ItemDetails");
            DBObject dBObject1 = new BasicDBObject();
            dBObject1.put("_id", itemDetailCode);
            dBObject1.put("itemCode",barcode);
            dBObject1.put("Quantity", qty);
            dBCollection1.insert(dBObject1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS:", "Added Item Successful"));
            resetData();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Submition Failed"));
        }
    }
}
