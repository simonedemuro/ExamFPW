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
    <!-- Online libs and styles from Google CDNs -->
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/PersonalArea.js"></script>
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
        <!-- Errore in caso di mancato login -->
    </c:if>
    <input type="hidden" value="${user}"/>
    <section class="pers-sec">
        <div>
            <p> Ciao ${user}, qua puoi modificare le tue informazioni personali: </p>
        </div>
        <div class="pers-sec-lvl1-col">
            <div class="personal-info-form">
                <form id="registration-data" action="">
                <label for="Fuser">Username: </label>
                <input type="text" id="Fuser" name="Fuser" placeholder="Il tuo username" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required> <!-- readonly e remove serve a impedire a mozzilla di cashare le informazioni con prepotenza -->
                <label for="Fpass1">Crea password: </label>
                <input type="password" id="Fpass1" name="Fpass1" placeholder="La tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>
                <label for="Fpass2">Ripeti password: </label>
                <input type="password" id="Fpass2" name="Fpass2" placeholder="Ri-digita la tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>

                <h2> Dati Anagrafici</h2>

                <label for="Fname">Nome: </label>
                <input type="text" id="Fname" name="Fname" placeholder="Il tuo nome" autocomplete="off" required>
                <label for="Fsurn">Cognome: </label>
                <input type="text" id="Fsurn" name="Fsurn" placeholder="Il tuo cognome" autocomplete="off" required>
                <label for="Fbirt">Data di nascita: </label>
                <input type="date" id="Fbirt" name="Fbirt" placeholder="Seleziona data di nascita" autocomplete="off" required>
                <label for="Fcode">Codice fiscale: </label>
                <input type="text" minlength="16" maxlength="16" id="Fcode" name="Fcode" placeholder="Il tuo codice fiscale a 16 cifre" autocomplete="off" required>
                <p>Sesso: </p>
                <label class="lbl-full-width">
                <input type="radio" name="Fsex" id="isMale" value="M" checked> Male
                </label>
                <br>
                <label class="lbl-full-width">
                <input type="radio" name="Fsex" id="isFem" value="F"> Female
                </label>
                <label for="Fmail">Indirizzo e-mail: </label>
                <input type="email" id="Fmail" name="Fmail" placeholder="La tua e-mail" autocomplete="off" required>
                <label for="Fcell">Numero di telefono: </label>
                <input type="tel" id="Fcell" name="Fcell" placeholder="Il tuo numero di telefono" autocomplete="off" pattern="\d*" required>
                <p>Si desidera ricevere la fattura? </p>
                <label class="lbl-full-width">
                <input type="radio" name="Finvoice" id="invoiceYes" value="true"> Si
                </label>
                <br>
                <label class="lbl-full-width">
                <input type="radio" name="Finvoice" id="invoiceNo" value="false" checked> No
                </label>
                <div class="registration-form-submit">
                <input type="reset" value="Pulisci">
                <input type="submit" value="Registrati">
                </div>
                </form>
            </div>
        </div>
    </section>
    <main>
    </main>
    <footer>
        footer
    </footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
