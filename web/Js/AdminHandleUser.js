/* Document ready attacco gli eventi  */
$(function() {
    /* carico qualcosa */
    (function () {
        // $.post("getReservationTable", {}, function (htmlOfTheTable) {
        //     $('.show-reservations-section').remove();
        //     $('nav').after(htmlOfTheTable);
        // });
    })();

    /* si tratta di due pulsanti con la stessa classe che voglio gestire allo stesso modo
    * facendo cambiare il loro testo in none -> asc -> desc e poi da capo */
    $('.btn-sort').click(function(e){
        /* seleziono elemento cliccato */
        let clickerId = e.target.id;                // salvo l'id dell'elemento cliccato per poi prendere l'elemento
        let innerTxt = $('#'+clickerId).html();     // prendo il testo nel pulsante cliccato

        /* deseleziono tutti gli altri */
        $('.btn-sort').html("none");                // l'altro pulsante deve tornare ad essere none
        $('.btn-sort').removeClass("active");

        /* cambio stato */
        if(innerTxt === "none"){                    // se è none cambio classe e metto come teso "asc" per ascending
            $('#'+clickerId).html("asc");
            $('#'+clickerId).addClass("active");
        } else if (innerTxt === "asc") {            // se è asc metto desc
            $('#'+clickerId).addClass("active");
            $('#'+clickerId).html("desc");
        } else if (innerTxt === "desc") {           // se è desc torno in none e tolgo la classe active
            $('#'+clickerId).html("none");
            $('#'+clickerId).removeClass("active");
        }
    });

});