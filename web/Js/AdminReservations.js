/* Document ready attacco gli eventi  */
$(function() {
    /* carico la tabella */
    (function () {
        $.post("getReservationTable", {}, function (htmlOfTheTable) {
            $('.show-reservations-section').remove();
            $('nav').after(htmlOfTheTable);
        });
    })();

    /* Invia prenotazione  */
    $('#btnBook').click(function (e){
        e.preventDefault();

        /* controllo che ci siano i campi che potrebbero essere stati diemnticati */
        let validFrom = formData.filter(x => x.name === "Ffrom")[0].value !== "" &&
            formData.filter(x => x.name === "...")[0].value !== "" &&
            formData.filter(x => x.name === "...")[0].value !== "";
        if(!validFrom) {
            showMessage("Errore: compila tutti i campi obbligatori e riprova", 3000, false);
            return;
        }

        /* Chiedo al back di processare la richiesta */
        $.post("", formData, function(data) {
            showMessage(data, 3000, false);

            /* ricarico la tabella per vedere le modifiche */
            $.post("...", {}, function (htmlOfTheCalendar) {

            });
        })
            .fail(function(response) {
                showMessage(response.responseText, 3000, false);
            });
    });

});

function some(html) {
    /* rimuovo il vecchio calendario se presente */
    $('.class').remove();
    /* attacco il nuovo */
    $('.selaftertheeltn').after(html);
}