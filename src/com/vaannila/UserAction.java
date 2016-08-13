/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaannila;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.awgtek.rcptsbo.dao.LookupsDao;

import org.apache.struts.action.ActionForward;

/**
 *
 * @author eswar@vaannila.com
 */
public class UserAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String tableName = request.getParameter("tableName");
        UserForm userForm = (UserForm) form;
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationDataSources.xml");
		LookupsDao lookupsDao = new LookupsDao((DataSource) ac.getBean("dataSource"));
        LookupData actorData = new LookupData(tableName);
        userForm.setActorList(lookupsDao.getUniqueItems(tableName));
        return mapping.findForward(SUCCESS);
    }
}
