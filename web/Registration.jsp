<%-- 
    Document   : Registration
    Created on : Sep 3, 2021, 3:46:45 PM
    Author     : fpw
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <title>Progetto Balneare</title>
    <!--    Styling the site-->
    <link rel="stylesheet" href="Style/SiteGeneral.css">
    <link rel="stylesheet" href="Style/SiteSections.css">
    <script src="Js/jquery3.6.0.min.js"></script>    <!--    Adding the front end logics -->
    <script src="Js/Registration.js"></script>
    <script src="Js/Login.js"></script>
    <script src="Js/SitePopups.js"></script>
</head>

<body>
    <div class="site">
        <header>
            <div class="title-container">
                <h1> Prenotazioni Villasimius 🏖️ </h1>
                <p class="sub"> Controlla, Prenota, Balnea 🏊‍️</p>
            </div>
        </header>
        <nav>
        <jsp:include page="NavSection.jsp"/>
        </nav>
        <c:if test="${empty user}">
            <!-- Card Login -->
            <jsp:include page="LoginSection.jsp"/>

            <main>
            <!-- Registration Form Section -->
            <jsp:include page="RegistrationSection.jsp"/>
            </main>
        </c:if>
        <main>
        <c:if test="${not empty user}">
            ${user} risulti correntemente loggato, per registrare un nuovo account effettua il log out.
        </c:if>
        </main>
        <footer>
            <jsp:include page="footerSection.jsp"/>
        </footer>
    </div>
    <div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
