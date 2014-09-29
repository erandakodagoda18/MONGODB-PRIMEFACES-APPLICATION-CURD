/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.primefaces.examples.view;

import com.mongodb.DB;
import com.mongodb.DBCollection;
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
public class Login {

    /**
     * Creates a new instance of Login
     */
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void resetFields(){
        this.password=null;
        this.username=null;
    }
    public boolean logMe() throws UnknownHostException {
        DB db=(new MongoClient("localhost",27017)).getDB("shopData");
        boolean flag=false;
        try {
            if (username.equals("")&&password.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Username/Password Should Not be Empty!", ""));
            }else{
                if (db.authenticate(username, password.toCharArray())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login Successfully!", ""));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Username/Password", ""));
            }
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
