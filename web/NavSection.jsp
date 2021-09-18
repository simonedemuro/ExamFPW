<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<a href="index.jsp" class="btn-nav"> Home</a>

<!-- solo se non registrato -->
<c:if test="${empty user}">
    <a href="Registration.jsp" class="btn-nav"> Registrati</a>
</c:if>

<!-- Pulsanti utente registrato: admin o semplice -->
<c:if test="${not empty user}">
    <a href="PersonalArea.jsp" class="btn-nav"> Area personale</a>
</c:if>

<!-- Pulsanti utente amministratore -->
<c:if test="${not empty user && userRole == 'admin'}">
    <a href="AdminDashboard.jsp" class="btn-nav"> slot</a>
    <a href="AdminReservedSlot.jsp" class="btn-nav"> prenotazioni</a>
    <a href="AdminHandleUsers.jsp" class="btn-nav"> utenti </a>
</c:if>

<!-- Pulsanti Utente semplice -->
<c:if test="${not empty user && userRole == 'simple'}">
    <a href="BookSlot.jsp" class="btn-nav"> Prenota slot</a>
    <a href="HandleInvoices.jsp" class="btn-nav"> Gestione fatture</a>
</c:if>
