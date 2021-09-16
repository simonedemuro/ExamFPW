<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:forEach var="item" items="${tblUser}">
    <tr class="dt-row">
        <td> ${item.getUsername()} </td>
        <td> ${item.getRole()} </td>
        <td> ${item.getName()} </td>
        <td> ${item.getSurname()} </td>
        <td> ${item.getSex()} </td>
        <td> ${item.getBirthday()} </td>
        <td> ${item.getFiscalnumber()} </td>
        <td> ${item.getEmail()} </td>
        <td> ${item.getPhone()} </td>
        <td> ${item.getInvoiceoptin()} </td>
        <td> ${item.getTot_num_res()} </td>
        <td>  </td>
    </tr>
</c:forEach>