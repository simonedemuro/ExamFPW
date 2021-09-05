<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<a href="index.jsp" class="btn-nav"> Home</a>
<c:if test="${empty user}">
    <a href="Registration.jsp" class="btn-nav"> Registrati</a>
</c:if>
<c:if test="${not empty user}">
    <a class="btn-nav"> Area personale</a>
</c:if>
<c:if test="${not empty user && userRole == 'admin'}">
    <a class="btn-nav"> Gestione prenotazioni</a>
</c:if>
<c:if test="${not empty user && userRole == 'guest'}">
    <a class="btn-nav"> Gestione fatture</a>
</c:if>