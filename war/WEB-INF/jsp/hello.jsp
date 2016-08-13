<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page import="java.util.Map" %>
	<%!
	public String storeFullName(Map stores, String storeId) {
		return (String) stores.get(storeId);
	}
	
	%>
<jsp:useBean id="storeService" class="com.awgtek.rcptsbo.service.StoreService" /> 
<html>
<head><title><fmt:message key="title"/></title></head>
<body>
<h1><fmt:message key="heading"/></h1>
<p><fmt:message key="greeting"/> <c:out value="${model.now}"/>.</p>
<h3>Recent Receipts</h3>
<table width="50%">
<c:forEach items="${model.receipts }" var="receipt">
	<tr>
		<td><c:out value="${receipt.id }"/></td>
		<td><fmt:formatDate type="date" 
            value="${receipt.dateOfPurchase}" /></td>
		<td><i><c:out value="${model.storeList[receipt.store]}" /> </i></td>
	</tr>
</c:forEach>
</table>
<h3>Recent Receipt Items</h3>
<table width="50%" border="1">
<thead>
	<th>id</th><th>receipt id</th><th>Generic name</th>
	<th>product brand</th><th>product Name</th><th>quantity</th>
	<th>size unit amount</th><th>size unit type</th><th>size unit count</th>
	<th>total cost</th>
</thead>
<c:forEach items="${model.receiptItems }" var="receiptItem">
	<tr>
		<td><c:out value="${receiptItem.id }"/></td>
		<td><c:out value="${receiptItem.receiptId }"/></td>
		<td><c:out value="${receiptItem.genericName }"/></td>
		<td><c:out value="${receiptItem.productBrand }"/></td>
		<td><c:out value="${receiptItem.productName }"/></td>
		<td><c:out value="${receiptItem.quantity }"/></td>
		<td><c:out value="${receiptItem.sizeUnitAmount }"/></td>
		<td><c:out value="${receiptItem.sizeUnitType }"/></td>
		<td><c:out value="${receiptItem.sizeUnitCount }"/></td>
		<td><c:out value="${receiptItem.totalCost }"/></td>
	</tr>
</c:forEach>
</table>
<br>
<a href="<c:url value="receipt.htm"/>">Add receipt.</a><br/>
<a href="<c:url value="receiptitem.htm"/>">Add receipt item.</a> 
<a href="<c:url value="store.htm"/>">Add store.</a>
<br>
<br>
<h3>Products</h3>
<c:forEach items="${model.products }" var="prod">
	<c:out value="${prod.description }"/> <i>$<c:out value="${prod.price}"/></i><br><br>
</c:forEach>
<br>

</body>
</html>