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
</style>
<title><fmt:message key="title"/></title></head>
<body>
<h1><fmt:message key="receipt.heading"/></h1>
<form:form method="POST" commandName="receipt">
		<form:errors path="*" cssClass="errorblock" element="div" />
	<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td alignment="right" width="20%">Store:</td>
			<td>
				    <form:select path="store">
				     	<form:option value="-1" label="--- Select ---" />
		                <form:options items="${storeList}" />
		            </form:select>
			</td>
			<td><form:errors path="store" cssClass="error" /></td>
		</tr>
		<tr>
			<td alignment="right" width="20%">Date: (MM/dd/yyyy)</td>
			<spring:bind path="receipt.dateOfPurchase">
				<td width="20%">
					<input type="text" size="50" name="dateOfPurchase" 
					value="<c:out value="${status.value}"/>">
				</td>
				<td width="60%">
					<font color="red"><c:out value="${status.errorMessage}"/></font>
				</td>
			</spring:bind>
		</tr>
	</table>
	<br>
	<spring:hasBindErrors name="receipt">
		<b>Please fix all errors!</b>
	</spring:hasBindErrors>
	<br><br>
	<input type="submit" alignment="center" value="Execute">
</form:form>
<a href="<c:url value="hello.htm"/>">Home</a>
</body>
</html>