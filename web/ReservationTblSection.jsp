<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<section class="show-reservations-section">
    <h2> Gestisci lo stato dee prenotazioni da qua </h2>
    <div class="reservation-tbl-container">
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
                    <th> ${tblItem.getUsername()} </th>
                    <th> ${tblItem.getReservationDate()} </th>
                    <th> ${tblItem.getReservationPeriod()} </th>
                    <th> ${tblItem.getNumReservedSlot()} </th>
                    <th> <input type="text" value=""> </th>
                    <th> <input type="text" value=""> </th>
                    <th> <input type="submit" class="btn-process-reservation" value="Fattura"> </th>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>