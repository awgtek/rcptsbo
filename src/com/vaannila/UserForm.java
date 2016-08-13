/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaannila;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eswar@vaannila.com
 */
public class UserForm extends org.apache.struts.action.ActionForm {
    
   private List actorList;
   

    public UserForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the actorList
     */
    public List getActorList() {
        return actorList;
    }

    /**
     * @param actorList the actorList to set
     */
    public void setActorList(List actorList) {
        this.actorList = actorList;
    }

   
}
