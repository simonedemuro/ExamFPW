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
    <script src="Js/jquery3.6.0.min.js"></script>
    <!-- Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/SitePopups.js"></script>
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

    <c:if test="${empty dbUser}">
        <c:redirect url ="personalData" />   <!-- reindirizza a HomeServlet -->
    </c:if>
    <!-- Questi campi mi servono per settare i radio con Jquery -->
    <input id="sex" type="hidden" value="${dbUser.getSex()}"/>
    <input id="invoice" type="hidden" value="${dbUser.isInvoiceOptIn()}"/>
    <section class="pers-sec">
        <div class="pers-sec-lvl1-col">
            <div class="personal-info-form">
                <div>
                    <h2> Sezione personale </h2>
                    <p> Ciao ${user}, qua puoi modificare le tue informazioni personali: </p>
                    <p> Cliccando sui campi che vuoi modificare compariranno i valori precedentemente impostati per aiutarti (tranne per la password 😜)</p>
                </div>

                <form id="registration-data" action="">
                <label for="Fuser">Username: </label>
                <input class="magic-complete" type="text" id="Fuser" name="Fuser" placeholder="${dbUser.getUsername()}" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required> <!-- readonly e remove serve a impedire a mozzilla di cashare le informazioni con prepotenza -->

                <h2> Cambia Password: </h2>
                <label for="Fpass1">Crea password: </label>
                <input type="password" id="Fpass1" name="Fpass1" placeholder="La tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>
                <label for="Fpass2">Ripeti password: </label>
                <input type="password" id="Fpass2" name="Fpass2" placeholder="Ri-digita la tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>

                <h2> Dati Anagrafici</h2>

                <label for="Fname">Nome: </label>
                <input class="magic-complete" type="text" id="Fname" name="Fname" placeholder="${dbUser.getName()}" autocomplete="off" required>
                <label for="Fsurn">Cognome: </label>
                <input class="magic-complete" type="text" id="Fsurn" name="Fsurn" placeholder="${dbUser.getSurname()}" autocomplete="off" required>
                <label for="Fbirt">Data di nascita: </label>
                <input class="magic-complete" type="date" id="Fbirt" name="Fbirt" placeholder="${dbUser.getBirthday()}" autocomplete="off" required>
                <label for="Fcode">Codice fiscale: </label>
                <input class="magic-complete" type="text" minlength="16" maxlength="16" id="Fcode" name="Fcode" placeholder="${dbUser.getFiscalNumber()}" autocomplete="off" required>
                <p>Sesso: </p>
                <label class="lbl-full-width">
                <input type="radio" name="Fsex" id="isMale" value="M" > Male
                </label>
                <br>
                <label class="lbl-full-width">
                <input type="radio" name="Fsex" id="isFem" value="F"> Female
                </label>
                <label for="Fmail">Indirizzo e-mail: </label>
                <input class="magic-complete" type="email" id="Fmail" name="Fmail" placeholder="${dbUser.getEmail()}" autocomplete="off" required>
                <label for="Fcell">Numero di telefono: </label>
                <input class="magic-complete" type="tel" id="Fcell" name="Fcell" placeholder="${dbUser.getPhone()}" autocomplete="off" pattern="\d*" required>
                <p>Si desidera ricevere la fattura? </p>
                <label class="lbl-full-width">
                <input type="radio" name="Finvoice" id="invoiceYes" value="true"> Si
                </label>
                <br>
                <label class="lbl-full-width">
                <input type="radio" name="Finvoice" id="invoiceNo" value="false" > No
                </label>
                <div class="registration-form-submit">
                <input id="doUpdate" type="submit" value="Update">
                </div>
                </form>
            </div>
        </div>
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
