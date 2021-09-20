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
    <!-- Online libs and styles from Google CDNs -->
    <script src="Js/jquery3.6.0.min.js"></script>    <!-- Adding the front end logics -->
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

    <c:if test="${not empty user}">
        <section class="book-slot-section">
            <div>
                <h2> Qua potrai prenotare i posti per te e i tuoi amici </h2>

                <div>
                    <form id="book-slots">
                        <label for="Ffrom">Dal: </label>
                        <input type="date" id="Ffrom" name="Ffrom" placeholder="Seleziona data di inizio" autocomplete="off" required>

                        <p>Slot: </p>
                        <label class="lbl-full-width">
                            <input type="radio" name="FfromSlot" id="FfromSlotAm" value="Mattina" checked> Mattina
                        </label>
                        <br>
                        <label class="lbl-full-width">
                            <input type="radio" name="FfromSlot" id="FfromSlotPm" value="Pomeriggio"> Pomeriggio
                        </label>

                        <label for="Fto">Al: </label>
                        <input type="date" id="Fto" name="Fto" placeholder="Seleziona data di fine" autocomplete="off">

                        <p>Slot: </p>
                        <label class="lbl-full-width">
                            <input type="radio" name="FtoSlot" id="FtoSlotAm" value="Mattina" checked> Mattina
                        </label>
                        <br>
                        <label class="lbl-full-width">
                            <input type="radio" name="FtoSlot" id="FtoSlotPm" value="Pomeriggio"> Pomeriggio
                        </label>

                        <label for="Fnumplaces">Numero di posti disponibili: </label>
                        <input type="number" id="Fnumplaces" name="Fnumplaces" placeholder="1" autocomplete="off" required>
                        <input id="btnBook" type="submit" value="Prenota">
                    </form>
                </div>
            </div>
        </section>

        <!-- se la tabella dei posti non è popolata reindirizzo a  -->

    </c:if>
    <main>
    </main>
    <footer>
        <jsp:include page="footerSection.jsp"/>
    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
