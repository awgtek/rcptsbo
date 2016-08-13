<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lookup Details</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
        	function selectit(col) {
        		parent.document
        		.getElementById("<c:out value="${param.tableName}" />")
        		.value = col;
	        	//alert("ha" + col + "<c:out value="${param.tableName}" />" );
        	}
        </script>
    </head>
    <body>
        <display:table id="data" name="sessionScope.UserForm.actorList" 
        requestURI="/userAction.do" pagesize="10" >
            <display:column  
             title="Item" sortable="true" 
              >
            <c:set var="itemval" value="${fn:escapeXml(data.item)}" />
            <a href="javascript:selectit('${itemval}')">${itemval }</a>
            </display:column>
        </display:table>
    </body>
</html>
