/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaannila;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author eswar@vaannila.com
 */
public class LookupData implements Serializable {

    private String item;

    public LookupData()
    {
        
    }
    
    public LookupData(String tvShow)
    {
        this.item = tvShow;
    }

    public ArrayList loadData()
    {
        ArrayList userList = new ArrayList();
        userList.add(this);
        return userList;
    }

    /**
     * @return the tvShow
     */
    public String getItem() {
        return item;
    }


}
