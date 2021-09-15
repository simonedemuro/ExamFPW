$(function() {
    /* per rendere condiviso il codice tra le pagine chiamo cosi la servlet
    *  e interagisco con la DOM a mano aggiungendo il calendario */
    (function () {
        $.post("getSlotCalendar", {"sender":"AdminDashboard"}, function (htmlOfTheCalendar) {
            /* metto il calendario ma non mostro un messaggio che lo dice in forma di popup */
            attachCalendar(htmlOfTheCalendar, false);
        });
    })();

    // no lettere nel numero posti
    $('#Naccomodation').keypress(function(e){
        // non faccio passare i keystrokes che non sono compresi tra i valori ascii
        if (e.which < 48 || e.which > 57) {
            e.preventDefault();
        }
    });
    // aggiungi posto
    $('#add-slot-btn').click(function(e){
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();
        // dati dal form
        let formData = $("#add-slot-form :input").serializeArray();
        if (formData.length < 3 || formData[0].value === "" || formData[1].value === "" || formData[2].value === ""){
            showMessage("errore: seleziona tutti i campi e riprova", 3000, false);
            return;
        }

        console.log(formData);

        /* Aggiungo slot */
        $.post("addSlot", formData, function (data) {
            /* mostro un messaggio 3 secondi e non faccio refresh */
            showMessage(data, 1000, true);
        });
    });

});

/* la gestione del precedente e mese successivo usa la stessa servlet ma facendo uso del parametro navigation action
*  poiché il navigatore di mesi è attaccato dinamicamente non è possibile attaccare gli eventi nel document ready
*  per questo motivo l'evento click è direttamente nell'heml dei pulsanti */
function prevMonth () {
    $.post("getSlotCalendar", {"navigationAction" : "previousMonth"}, function (htmlOfTheCalendar) {
        attachCalendar(htmlOfTheCalendar, true);
    });
}
function nextMonth() {
    $.post("getSlotCalendar", {"navigationAction" : "nextMonth"}, function (htmlOfTheCalendar) {
        attachCalendar(htmlOfTheCalendar, true);
    });
}
/* gestisce la sostituzione del calendario vecchio con il nuovo */
function attachCalendar(htmlOfTheCalendar, displayMessage) {
    /* rimuovo il vecchio calendario se presente */
    $('.calendar-section').remove();
    /* attacco il nuovo */
    $('section.admin-add-slot').after(htmlOfTheCalendar);
    /* mostro messaggio se desiderato */
    if(displayMessage){
        showMessage("Calendario caricato", 1000, false);
    }
}
