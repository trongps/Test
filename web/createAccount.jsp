<%-- 
    Document   : CreateAccount
    Created on : Jan 4, 2023, 7:48:17 PM
    Author     : TrongPS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Controller.ERROR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            h7{
                color: red;
            }
        </style>
    </head>
    <body>
        <form action="DispatchController" method="POST">
            Username: 
            <input type="text" name="txtUsername" value="${param.txtUsername}" />
            
            <%
                ERROR e = (ERROR) request.getAttribute("Error");
                if (e != null) {
                    if (e.getAccountError() != null) {
                        out.print("<h7>"+e.getAccountError()+"</h7>");
                    }
                }
            %>
            <br>
            Password: 
            <input type="password" name="txtPassword" value="" />
            <%
                if (e != null) {
                    if (e.getPasswordError() != null) {
                        out.print("<h7>" + e.getPasswordError() + "</h7>");
                    }
                }%>

            <br>
            Confirm Password: 
            <input type="password" name="txtConfirmPassword" value="" />
            <% if (e != null) {
                    if (e.getConfirmPasswordError() != null) {
                        out.print("<h7>" + e.getConfirmPasswordError() +"</h7>");
                    }
                }
            %>

            <br>
            Full Name:
            <input type="text" name="txtFullname" value="${param.txtFullname}" />
            <%  if (e != null) {
                    if (e.getFullnameError() != null) {
                        out.print("<h7>"+ e.getFullnameError()+"<h7>");
                    }
                }
            %>
            <br>

            <input type="submit" value="Create Account" name="btAction" />
            
        </form>
    </body>
</html>
