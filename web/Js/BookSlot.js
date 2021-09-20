/* Document ready attacco gli eventi  */
$(function() {
    /* per rendere condiviso il codice tra le pagine chiamo cosi la servlet
    *  e interagisco con la DOM a mano aggiungendo il calendario */
    (function () {
        $.post("getSlotCalendar", {"sender":"AdminDashboard"}, function (htmlOfTheCalendar) {
            attachCalendar(htmlOfTheCalendar);
        });
    })();

    /* Invia prenotazione  */
    $('#btnBook').click(function (e){
        e.preventDefault();
        // dati dal form
        let formData = $("#book-slots :input").serializeArray();
        console.log({'dati form update':formData});

        /* controllo che ci siano i campi che potrebbero essere stati diemnticati */
        let validFrom = formData.filter(x => x.name === "Ffrom")[0].value !== "" &&
                        formData.filter(x => x.name === "Fto")[0].value !== "" &&
                        formData.filter(x => x.name === "Fnumplaces")[0].value !== "";
        if(!validFrom) {
            showMessage("Errore: compila tutti i campi obbligatori e riprova", 3000, false);
            return;
        }

        /* Chiedo al back di processare la richiesta */
        $.post("bookSlot", formData, function(data) {
            showMessage(data, 3000, false);
            /* ricarico la tabella per vedere le modifiche */
            $.post("getSlotCalendar", {"sender":"AdminDashboard"}, function (htmlOfTheCalendar) {
                /* resetto il form */
                $('#book-slots').trigger("reset");
                attachCalendar(htmlOfTheCalendar);
            });
        })
        .fail(function(response) {
            showMessage(response.responseText, 3000, false);
        });
    });

});
/* la gestione del precedente e mese successivo usa la stessa servlet ma facendo uso del parametro navigation action
*  poiché il navigatore di mesi è attaccato dinamicamente non è possibile attaccare gli eventi nel document ready
*  per questo motivo l'evento click è direttamente nell'heml dei pulsanti */
function prevMonth () {
    $.post("getSlotCalendar", {"navigationAction" : "previousMonth"}, function (htmlOfTheCalendar) {
        attachCalendar(htmlOfTheCalendar);
        showMessage("Mese cambiato", 1000, false);
    });
}
function nextMonth() {
    $.post("getSlotCalendar", {"navigationAction" : "nextMonth"}, function (htmlOfTheCalendar) {
        attachCalendar(htmlOfTheCalendar);
        showMessage("Mese cambiato", 1000, false);
    });
}

/* gestisce la sostituzione del calendario vecchio con il nuovo */
function attachCalendar(htmlOfTheCalendar) {
    /* rimuovo il vecchio calendario se presente */
    $('.calendar-section').remove();
    /* attacco il nuovo */
    $('section.book-slot-section').after(htmlOfTheCalendar);
}