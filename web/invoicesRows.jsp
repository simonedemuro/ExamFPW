<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<c:if test="${not empty errorMessage}">
        ${errorMessage}
</c:if>
<c:forEach var="item" items="${tblInvoices}">
    <tr class="invoices-row">
        <td> ${item.getId()} </td>
        <td> ${item.getOnwerFulllName()} </td>
        <td> ${item.getPrice()} </td>
        <td> ${item.getDescription()} </td>
        <td> ${item.getReserveationDate()} </td>
        <td> ${item.getReservationPeriod()} </td>
        <td> ${item.getNumReserverSlot()} </td>
    </tr>
</c:forEach>