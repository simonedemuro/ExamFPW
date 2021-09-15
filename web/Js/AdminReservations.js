/* Document ready attacco gli eventi  */
$(function() {
    /* carico la tabella */
    (function () {
        $.post("getReservationTable", {}, function (htmlOfTheTable) {
            $('.show-reservations-section').remove();
            $('nav').after(htmlOfTheTable);
        });
    })();
});

function processReservation(idReservation) {
    /* genero gli id in base all'id della prenotazione e prendo i dati*/
    let price = $('#ThPc'+idReservation).val();
    let description = $('#ThDc'+idReservation).val();
    console.log({"id":idReservation, "price:":price, "description":description});

    /* controllo che non ci siano lettere nel prezzo (se la lunghezza della regex tutto tranne numeri non è undefined)*/
    if (price.match(/[^0-9]/g)?.length !== undefined) {
        showMessage("Errore: non si possono inserire caratteri nel prezzo",3000, false);
        return;
    }

    /* ne parlo con la servlet */
    $.post("processReservation", {"Id":idReservation, "price":price, "description": description}, function(data) {
        showMessage(data, 3000, false);

        /* ricarico la tabella per vedere le modifiche */
        $.post("getReservationTable", {}, function (htmlOfTheTable) {
            $('.show-reservations-section').remove();
            $('nav').after(htmlOfTheTable);
        });
    })
    .fail(function(response) {
            showMessage(response.responseText, 3000, false);
    });
}