/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.primefaces.examples.view;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
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
public class insertJSON {

    /**
     * Creates a new instance of insertJSON
     */
   private String inputJSON;

    public String getInputJSON() {
        return inputJSON;
    }

    public void setInputJSON(String inputJSON) {
        this.inputJSON = inputJSON;
    }
    public void insertJSON() throws UnknownHostException{
        DB db=(new MongoClient("localhost",27017).getDB("shopData"));
        if (inputJSON.startsWith("{") && inputJSON.endsWith("}")) {
            DBCollection dBCollection=db.getCollection("Item");
            dBCollection.insert((DBObject)JSON.parse(inputJSON));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS:","Submited JSON"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:","Not a valide JSON"));
        }
    }
    public void resetData(){
        this.inputJSON=null;
    }
    
}
