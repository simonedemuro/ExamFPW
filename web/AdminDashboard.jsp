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
    <script src="Js/jquery3.6.0.min.js"></script>    <!-- Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/SitePopups.js"></script>
    <script src="Js/Admin.js"></script>
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

    <c:if test="${empty user}">
        <!-- Card Login -->
        <jsp:include page="LoginSection.jsp"/>
    </c:if>

    <c:if test="${not empty userRole && userRole.equals(\"admin\")}">
        <section class="admin-add-slot">
            <div class="admin-item">
                <p> Add new slot*: </p>
                <div>
                    <form id="add-slot-form">
                        <label for="Fdt">Data: </label>
                        <input type="date" id="Fdt" name="Fdt" autocomplete="off" required>
                        <p>Slot: </p>
                        <label class="lbl-full-width">
                            <input type="radio" name="Fslot" id="am" value="Mattina" checked> Mattina
                        </label>
                        <br>
                        <label class="lbl-full-width">
                            <input type="radio" name="Fslot" id="pm" value="Pomeriggio"> Pomeriggio
                        </label>
                        <label for="Naccomodation">Numero di posti disponibili: </label>
                        <input class="magic-complete" type="number" id="Naccomodation" name="Naccomodation" placeholder="1" autocomplete="off" required>
                        <div class="center-btn">
                            <input class="centered-element" id="add-slot-btn" type="submit" value="Aggiungi">
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <!-- se la tabella dei posti non è popolata reindirizzo a  -->
        <c:if test="${empty fullSlots}">
            <input type="hidden" id="isEmpty" value="true">
        </c:if>

    </c:if>
    <c:if test="${empty user || !userRole.equals(\"admin\")}">
        <section class="section-error">
            Per visitare questa pagina è necessario essere loggati come amministratore.
        </section>
    </c:if>
    <footer>
        <jsp:include page="footerSection.jsp"/>
    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
