<%--
Document   : index
Created on : Aug 17, 2021, 6:29:19 AM
Author     : fpw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
    <script src="Js/jquery3.6.0.min.js"></script>
    <!-- Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/SitePopups.js"></script>
    <script src="Js/BookSlot.js"></script>
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

    <!-- Permetto a tutti gli utenti registrati e visitatori di vedere gli slot disponibili  -->
    <!-- La sezione anche se invisibile mi serve per usare il js di BookSlot che fa attach sull'after di questa section  -->
    <section class="book-slot-section">
        <h2> Visualizza i posti disponibili </h2>
        <p> In questo sito puoi visualizzare l'elenco di slot disponibili per la spiaggia di villasimius.  </p>
        <p> Registrandosi è possibile prenotare i posti e ricevere le fatture.  </p>
    </section>

    <main>
    </main>
    <footer>
        <jsp:include page="footerSection.jsp"/>
    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
