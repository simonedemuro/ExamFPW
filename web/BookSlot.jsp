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
    <!-- Online libs and styles from Google CDNs -->
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

                        <label for="Fto">Al (lasciare in bianco in caso di prenotazione giorno unico) : </label>
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
        <c:if test="${empty fullSlots}">
            <c:redirect url ="getSlotCalendar"/>
        </c:if>
        <section>
            <div class="month-selector">
                <span class="month-arrow left-arr">⬅️</span>
                <h2 class="month-selector-txt"> ${currentDate} </h2>
                <span class="month-arrow right-arr">➡️</span>
            </div>
            <div class="box-month">
                <!-- previa chiamata a back end, riempio il template box-day con tutti gli slot del mese selezionato -->
                <c:if test="${not empty fullSlots}">
                    <c:forEach var="day" items="${fullSlots}">
                        <div class="box-day">
                            <div class="box-day-num-circle">
                                <p>${day.getDay()}</p>
                            </div>
                            <div class="box-day-slot slot-AM">
                                <p class="txt-slot">AM:</p>
                                <p class="txt-num-places">${day.getNumAm()}</p>
                            </div>
                            <div class="box-day-slot slot-PM">
                                <p class="txt-slot">PM:</p>
                                <p class="txt-num-places">${day.getNumPm()}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </section>
    </c:if>
    <main>
    </main>
    <footer>

    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
