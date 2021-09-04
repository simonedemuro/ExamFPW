<%-- 
    Document   : Registration
    Created on : Sep 3, 2021, 3:46:45 PM
    Author     : fpw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <title>Progetto Balneare</title>
    <!--    Styling the site-->
    <link rel="stylesheet" href="Style/SiteGeneral.css">
    <link rel="stylesheet" href="Style/SiteSections.css">
    <!-- Online libs and styles from Google CDNs -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!--    Adding the front end logics -->
    <script src="Js/Registration.js"></script>

</head>
    <body>

<div class="site">

    <header>
        <h1> &lt;header&gt;  </h1>
    </header>
    <nav>Nav</nav>
    <main>
        <section class="card-registration">
            <div class="registration-form">
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
                        <input type="radio" name="isMale" id="isMale" value="isMale" checked> Male
                    </label>
                        <br>
                    <label class="lbl-full-width">
                        <input type="radio" name="isFem" id="isFem" value="isFem"> Female
                    </label>
                    <label for="Fmail">Indirizzo e-mail: </label>
                    <input type="email" id="Fmail" name="Fmail" placeholder="La tua e-mail" autocomplete="off" required>
                    <label for="Fcell">Numero di telefono: </label>
                    <input type="tel" id="Fcell" name="Fcell" placeholder="Il tuo numero di telefono" autocomplete="off" required>
                    <p>Si desidera ricevere la fattura? </p>
                    <label class="lbl-full-width">
                        <input type="radio" name="invoiceYes" id="invoiceYes" value="invoiceYes"> Si
                    </label>
                    <br>
                    <label class="lbl-full-width">
                        <input type="radio" name="invoiceNo" id="invoiceNo" value="invoiceNo" checked> No
                    </label>
                    <div class="registration-form-submit">
                        <input type="reset" value="Pulisci">
                        <input type="submit" value="Registrati">
                    </div>
                </form>
            </div>
        </section>
    </main>
    <footer>Footer</footer>

</div>

</body>
</html>
