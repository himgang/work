<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Customer_DashBoard</title>
<%
if(session.getAttribute("id")==null){
		out.print("log in to see this page");
	}
	else if(session.getAttribute("id")!=null){%>
<center></center><font color="blue" size =6>Welcome Mr. <%=request.getAttribute("name") %> to your Customer Dashboard</font>
</head>
<body background="j.jpg">
<br/>
<p><input type="button" onclick="./Customer_Dash_css.jsp" value="Log Out"/>"</p>
<br/>
<br/>
<center><table id="tab">
	<tr>
		<td> Contact Number:</td> <td><%=request.getAttribute("Customer_Phone") %></td>
	</tr>
	<tr>
		<td> Loan Amount Requested</td><td><%=request.getAttribute("Amount") %></td>
	</tr>
	<tr>
		<td> Duration of Loan</td><td> <%=request.getAttribute("Time") %></td>
	</tr>
	<tr>
		<td> <%! String a; %>
			<% a= (String) request.getAttribute("Status"); %>
			<% 
			if(a.equals("1")){
				%>
				<p id="sanctioned">Loan Status</td><td>Sanctioned</p>
				Your EMI is:</td><td> <%=request.getAttribute("Customer_EMI") %>
				<%
			}
			else if(a.equals("0")){
				%>
				<p id="not_sanctioned">Loan Status</td><td>Not Sanctioned</p>
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
</table></center>
</body>
</html><%}%>