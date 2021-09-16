<%--
Document   : index
Created on : Aug 17, 2021, 6:29:19 AM
Author     : fpw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.unica.ProgettoBalneare.Models.SlotViewModel"%>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Piattaforma prenotazioni posti in spiaggia a villasimius" />
    <meta name="keywords" content="prenotazione, posti, piaggia, villasimius, covid" />
    <title>Progetto Balneare</title>
    <!-- Styling the site-->
    <link href="Style/SiteGeneral.css" rel="stylesheet">
    <link href="Style/SiteSections.css" rel="stylesheet">
    <link href="Style/ReservationSection.css" rel="stylesheet">
    <!-- Online libs and styles from Google CDNs -->
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/SitePopups.js"></script>
    <script src="Js/AdminHandleUser.js"></script>
</head>
<body>
<div class="site">
    <header>
        <div class="title-container">
            <h1> Prenotazioni Villasimius 🏖️ </h1>
            <p class="sub"> Controlla, Prenota, Balnea 🏊‍️</p>
        </div>
    </header>
    <!-- Card Logged User -->
    <c:if test="${not empty user}">
        <section class="card-logged-user">
            <div class="logged-user-item">
                <h2>Ciao ${user}!</h2>
            </div>
            <div class="logged-user-item">
                <button id="btn-logout" class="a-to-btn" href=""> Log out </button>
            </div>
        </section>
    </c:if>

    <nav>
        <jsp:include page="NavSection.jsp"/>
    </nav>
    <!-- se non sei loggato ti puoi loggare come amministratore e restare direttamente in questa pagina  -->
    <c:if test="${empty user}">
        <!-- Card Login -->
        <jsp:include page="LoginSection.jsp"/>
    </c:if>

    <c:if test="${not empty userRole && userRole.equals(\"admin\")}">
        <section class="handle-user-section">
            <h2> Gestione utenti </h2>
            <div class="user-tbl-container">

            </div>
        </section>
    </c:if>

    <c:if test="${empty user || !userRole.equals(\"admin\")}">
        <section class="section-error">
            Per visitare questa pagina è necessario essere loggati come amministratore.
        </section>
    </c:if>
    <footer>

    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
