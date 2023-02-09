<%-- 
    Document   : search
    Created on : Dec 30, 2022, 11:07:26 AM
    Author     : TrongPS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="registration.registrationDTO"%>
<%@page import="registration.registrationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Index Page</title>
    </head>
    <body>        
        Welcome, <%--=session.getAttribute("mySessionName")%>
        <c:set var = "fname" value = "${sessionScope.USER.fullname}" scope = "session"/--%>
        ${sessionScope.USER.fullname}
        <form action="DispatchController" method="POST">
            Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="search" name="btAction" />
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <c:set var = "searchValue" value ="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">            
            <c:set var ="result" value = "${requestScope.USER_ACCOUNT}"/>
            <c:if test = "${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>                    
                        <c:forEach var = "dto" items="${result}" varStatus="counter">
                        <form action="DispatchController">
                            <tr>
                                <td>${counter.count}</td>

                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />

                                </td>

                                <td>
                                    <input type="" name="txtPassword" value="${dto.password}" />                                                                                
                                </td>

                                <td>
                                    <input type="text" name="txtFullname" value="${dto.fullname}" />
                                </td>

                                <td>${dto.isAdmin}</td>

                                <td>
                                    <%--
                                    String username = ${dto.username};
                                    String btAction = "Delete";
                                    String lastSearchVal = request.getParameter("searchValue");
                                    String url = "DispatchController?btAction="+btAction+"&txtUsername="+ username + "&lastSearchVal=" + lastSearchVal;
                                    --%>
                                    <c:url var="url" value="DispatchController" >
                                        <c:param name="btAction" value="Delete"/>                                  
                                        <c:param name="txtUsername" value="${dto.username}"/>                                  
                                        <c:param name="lastSearchVal" value="${searchValue}"/>                                  
                                    </c:url>
                                    <a href="${url}">Delete</a>
                                </td>

                                <td>
                                    <input type="hidden" name="lastSearchVal" value="${searchValue}" />
                                    <input type="submit" value="Update" name = "btAction"/>
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test = "${empty result}">
            <h1>Nothing!</h1>
        </c:if>
    </c:if>           
    <%--String key = request.getParameter("searchValue");%>
<%
    if (key != null) {
        List<registrationDTO> result = (List<registrationDTO>) request.getAttribute("USER_ACCOUNT");
        if (result != null) {
%>

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (registrationDTO dto : result) {
                        count++;

                        if (dto != null) {
                %>
                <tr>
                    <td><%= count%></td>
                    <td><%= dto.getUsername()%></td>
                    <td><%= dto.getPassword()%></td>
                    <td><%= dto.getFullname()%></td>
                    <td><%= dto.isIsAdmin()%></td>
                </tr>
                <%
                                } else {
                                    out.print("Nothing!");
                                }
                            }
                        } else {
                            out.print(":))");
                        }
                    }
                %>
            </tbody>
        </table> --%>

</body>
</html>
