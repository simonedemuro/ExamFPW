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
    <c:if test="${empty attr1}">
        <c:redirect url="HomeServlet"/>
        <!-- reindirizza a HomeServlet -->
    </c:if>
    <meta charset="UTF-8">
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
</head>
<body>
<div class="site">
    <header>
        <h1> &lt;header&gt; </h1>
    </header>
    <nav>Nav</nav>
    <main>
        <section class="card-login">
            <form id="from-login" action="" method="post">
                <div class="form-login-surround">
                    <div class="_2_3-form">
                        <label for="Fuser">Username: </label>
                        <input autocomplete="off" id="Fuser" name="Fuser" onfocus="this.removeAttribute('readonly');"
                               placeholder="Il tuo username"
                               readonly
                               required type="text">
                        <!-- readonly e remove serve a impedire a mozzilla dicashare le informazioni con prepotenza -->
                    </div>
                    <div class="_2_3-form">
                    <label for="Fpass">Password: </label>
                    <input autocomplete="off" id="Fpass" name="Fpass" onfocus="this.removeAttribute('readonly');"
                           placeholder="La tua password"
                           readonly
                           required type="password">
                    </div>
                    <div class="_1_3-form">
                    <label for="subm"> </label>
                    <input type="submit" id="subm" value="Login">
                    </div>
                </div>
            </form>
            <p id="msg-signup"> se non hai ancora un account <a href="/Registration.jsp">Registrati</a> </p>
        </section>
    </main>
    <footer>
    <c:if test="${not empty user}">
    <h1>Ciao ${user}!</h1>
    </c:if></footer>
</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
