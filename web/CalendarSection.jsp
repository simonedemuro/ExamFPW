<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.unica.ProgettoBalneare.Models.SlotViewModel"%>

<section class="calendar-section">
    <div class="month-selector">
        <span onclick="prevMonth()" class="month-arrow left-arr">⬅️</span>
        <h2 class="month-selector-txt"> ${currentDate} </h2>
        <span onclick="nextMonth()" class="month-arrow right-arr">➡️</span>
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