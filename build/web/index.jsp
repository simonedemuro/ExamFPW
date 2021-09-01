<%-- 
    Document   : index
    Created on : Aug 17, 2021, 6:29:19 AM
    Author     : fpw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty attr1}">
            <c:redirect url ="HomeServlet" />   <!-- reindirizza a HomeServlet -->
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>ciao ${attr1} </p>
    </body>
</html>
