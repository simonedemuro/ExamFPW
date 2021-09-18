<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Piattaforma prenotazioni posti in spiaggia a villasimius"/>
    <meta name="keywords" content="prenotazione, posti, piaggia, villasimius, covid"/>
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
    <script src="Js/HandleInvoices.js"></script>
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
                    <button id="btn-logout" class="a-to-btn" href=""> Log out</button>
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

        <section class="invoice-section">
            <div class="invoice-cont-width">
                <h2> Fatture </h2>
                <p> In questa sezione è possibile visualizzare gli estremi delle fatture emesse a tuo carico </p>
            <div class="invoice-container">
                <table id="invoice-tbl">
                    <tr class="invoice-tbl-header-row">
                        <th> Identificativo fattura</th>
                        <th> Intestatario</th>
                        <th> Prezzo</th>
                        <th> Descrizione</th>
                        <th> Data prenotazione</th>
                        <th> Periodo permanenza</th>
                        <th> Numero posti riservati</th>
                    </tr>


                </table>
            </div>
        </div>
        </section>

        <main>
        </main>
        <footer>

        </footer>
    </div>

    <div id="msgBar"> {{MESSAGGIO}}</div>
    </body>
</html>
