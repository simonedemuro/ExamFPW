<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<section class="show-reservations-section">
    <h2> Gestisci lo stato delle prenotazioni da qua </h2>
    <div class="reservation-tbl-container">
        <!-- se ci sono dati renderizzo la tabella, la popolo dal servlet -->
        <c:if test="${not empty reservationTable}">
        <table id="reservation-tbl">
            <tr>
                <th> nome utente </th>
                <th> data prenotazione </th>
                <th> periodo prenotazione </th>
                <th> numero di posti </th>
                <th> prezzo totale </th>
                <th> descrizione </th>
                <th>  </th>
            </tr>
            <c:forEach var="tblItem" items="${reservationTable}">
                <tr>
                    <th class="ThUs"> ${tblItem.getUsername()} </th>
                    <th class="ThDt"> ${tblItem.getReservationDate()} </th>
                    <th class="ThPd"> ${tblItem.getReservationPeriod()} </th>
                    <th class="ThNs"> ${tblItem.getNumReservedSlot()} </th>
                    <!-- genero id univoci per aiutarmi a leggere il form dopo in js -->
                    <th> <input id="ThPc${tblItem.getReservationId()}" type="text" value=""> </th>
                    <th> <input id="ThDc${tblItem.getReservationId()}" type="text" value=""> </th>
                    <!-- uso l'id perche passare gli altri campi dal front end non sarebbe per nulla sicuro, gia in questo caso dovrei controllare che l'id metchi la ownership dello user...  -->
                    <th> <input id="submit${tblItem.getReservationId()}" type="submit" class="btn-process-reservation" value="Fattura" onclick="processReservation(${tblItem.getReservationId()})"> </th>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div>
</section>