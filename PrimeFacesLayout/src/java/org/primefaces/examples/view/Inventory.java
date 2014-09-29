/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.primefaces.examples.view;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author techsupport
 */
@ManagedBean
@RequestScoped
public class Inventory {

    /**
     * Creates a new instance of Inventory
     */
    private String itemName;
    private String producerName;
    private String itemCode;
    private String itemDetailCode;
    private String qty;
    
    
    
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


}
