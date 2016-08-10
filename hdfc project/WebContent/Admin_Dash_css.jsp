<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="classes_HDFC.applications" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#tab {
    width: 100%;
    background-color: #D8F0DA;
    border: 1px solid #006699;
    min-width: 100%;
    position: relative;
    opacity: 0.97;
    background: transparent;
    color:#F0F0F0;
    
}
table, tr, td{
	border: 3px solid #006699;
	
}
</style>
<title>Admin Dashboard</title>
</head>
<body background="j2.jpg">
<p> the applications details are</p>
<table id="tab">
<tr>
			<td>Customer Name</td>
			<td>Customer Salary</td>
			<td>Loan Amount</td>
			<td>Loan Period</td>
			<td>Customer EMI</td>
			<td>Loan Status</td>
			</tr>
	<%
		List<applications> lp =(List<applications>)request.getAttribute("results");
		for(applications l: lp){
			%>
			<tr>
			<td><% out.println(l.getCustomer_Name());%></td>
			<td><% out.println(l.getCustomer_Salary());%></td>
			<td><% out.println(l.getAmount());%></td>
			<td><% out.println(l.getTime());%></td>
			<td><% out.println(l.getCustomer_EMI());%></td>
			<td>
			<%! Integer a; %>
			<% a=l.getCustomer_Status(); %>
			<% 
			if(a.equals(1)){
				%>
				<p id="sanctioned">SANCTIONED</p>
				<%
			}
			else if(a.equals(0)){
				%>
				<p id="not_sanctioned">DENIED</p>
				<%
			}
			else {
				%>
				<p>Boo yeah</p>
				<%
			}
		%>
		</td>
			</tr>
		<%		
		}
	%>
    
</table>
</body>
</html>