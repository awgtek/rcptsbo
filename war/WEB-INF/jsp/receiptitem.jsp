<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
         .ui-widget-header,.ui-state-default, ui-button{
            background:#b9cd6d;
            border: 1px solid #b9cd6d;
            color: #FFFFFF;
            font-weight: bold;
         }
         iframe {
         	height: 580px;
         }
</style>
<title><fmt:message key="title"/></title>

     <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

      <!-- Javascript -->
      <script>
      $(function () {
    	  var arr = ["genericName", "productName", "productBrand"];
    	  $.each(arr, function (index, value) {
    		  
	    	   $("#" + value + "DialogContent").dialog({
	    	     autoOpen: false,
	    	     modal: true,
	    	     height: 500,
	    	     width: 'auto',
	    	     resizable: true
	    	   });
	
	    	   $("#" + value + "Dialog").click(function () {
	    	     $("#" + value + "DialogContent").dialog( "open" );
	    	     return false;
	    	   });
    	  });
    	 });
      </script>

</head>
<body>
<h1><fmt:message key="receipt.heading"/></h1>
<form:form method="POST" commandName="receiptItem">
		<form:errors path="*" cssClass="errorblock" element="div" />
	<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td alignment="right" width="20%">Receipt:</td>
			<td>
				    <form:select path="receiptId">
				     	<form:option value="-1" label="--- Select ---" />
		                <form:options itemValue="id" itemLabel="label" items="${receipts}" />
		            </form:select>
			</td>
			<td><form:errors path="receiptId" cssClass="error" /></td>
		</tr>
		<tr>
			<td alignment="right" width="20%">
			Generic Name:</td>
			<spring:bind path="receiptItem.genericName">
				<td width="20%">
					<input id="genericname" type="text" size="50" name="genericName" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
			<td><div id="genericNameDialogContent" title="Basic dialog">
				  <iframe src="userAction.do?tableName=genericname"></iframe>
				</div>
				<button id="genericNameDialog">Open Dialog</button>
			</td>
		</tr>
		<tr>
			<td alignment="right" width="20%">
			Product Name:</td>
			<spring:bind path="receiptItem.productName">
				<td width="20%">
					<input id="productname" type="text" size="50" name="productName" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
			<td><div id="productNameDialogContent" title="Basic dialog">
				  <iframe src="userAction.do?tableName=productname"></iframe>
				</div>
				<button id="productNameDialog">Open Dialog</button>
			</td>
		</tr>
		<tr>
			<td alignment="right" width="20%">
			Product Brand:</td>
			<td width="20%">
				<form:input id="productbrand" path="productBrand" />
			</td>
			<spring:bind path="receiptItem.productBrand">
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
			<td><div id="productBrandDialogContent" title="Basic dialog">
				  <iframe src="userAction.do?tableName=productbrand"></iframe>
				</div>
				<button id="productBrandDialog">Open Dialog</button>
			</td>
		</tr>
		<tr>
			<td alignment="right" width="20%">Quantity:</td>
			<td width="20%">
			<form:input path="quantity" size="30" />
			</td>
			<spring:bind path="receiptItem.quantity">
<!-- 				<td width="20%"> -->
					
<!-- 					<input type="text" size="50" name="quantity"  -->
<%-- 					value="<c:out value="${status.value}"/>"> --%>
<!-- 				</td> -->
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
		</tr>
		<tr>
			<td alignment="right" width="20%">Size Unit Type:</td>
			<td>
				    <form:select path="sizeUnitType">
				     	<form:option value="" label="--- Select ---" />
		                <form:options items="${sizeUnitTypes}" />
		            </form:select>
			</td>
			<td><form:errors path="sizeUnitType" cssClass="error" /></td>
		</tr>
		<tr>
			<td alignment="right" width="20%">Size Unit Amount:</td>
			<spring:bind path="receiptItem.sizeUnitAmount">
				<td width="20%">
					<input type="text" size="50" name="sizeUnitAmount" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
		</tr>
		<tr>
			<td alignment="right" width="20%">Size Unit Count:</td>
			<spring:bind path="receiptItem.sizeUnitCount">
				<td width="20%">
					<input type="text" size="50" name="sizeUnitCount" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
		</tr>
		<tr>
			<td alignment="right" width="20%">Total Cost:</td>
			<spring:bind path="receiptItem.totalCost">
				<td width="20%">
					<input type="text" size="50" name="totalCost" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
		</tr>
	</table>
	<br>
	<spring:hasBindErrors name="receiptItem">
		<b>Please fix all errors!</b>
	</spring:hasBindErrors>
	<br><br>
	<input type="submit" alignment="center" value="Execute">
</form:form>
<a href="<c:url value="hello.htm"/>">Home</a>
</body>
</html>