<table id="user-tbl">
    <tr>
        <th> username </th>
        <th> ruolo </th>
        <th> nome </th>
        <th> cognome <button id="surname-sort" class="btn-sort">none</button> </th>
        <th> sesso </th>
        <th> data di nascita </th>
        <th> codice Fiscale </th>
        <th> email </th>
        <th> cellulare </th>
        <th> fattura opt-in </th>
        <th> totale posti prenotati <button id="num-reserv-sort" class="btn-sort">none</button> </th>
        <th>  </th>
    </tr>
    <c:forEach var="item" items="${tblUser}">
        <tr>
            <td> ${tblItem.getUsername()} </td>
            <td> ${tblItem.getRole()} </td>
            <td> ${tblItem.getName()} </td>
            <td> ${tblItem.getSurname()} </td>
            <td> ${tblItem.getSex()} </td>
            <td> ${tblItem.getBirthday()} </td>
            <td> ${tblItem.getFiscalnumber()} </td>
            <td> ${tblItem.getEmail()} </td>
            <td> ${tblItem.getPhone()} </td>
            <td> ${tblItem.getInvoiceoptin()} </td>
            <td> ${tblItem.getTot_num_res()} </td>
            <td>  </td>
        </tr>
    </c:forEach>
</table>