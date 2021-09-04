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
        <!-- Registration Form Section -->
        <jsp:include page="RegistrationSection.jsp"/>
    </main>
    <footer>Footer</footer>

</div>
<div id="msgBar"> {{MESSAGGIO}}</div>
</body>
</html>
