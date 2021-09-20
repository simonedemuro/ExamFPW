/* Document ready attacco gli eventi  */
$(function() {
    /* carico la tabella */
    (function () {
        /* dalla classe .active seleziono l'id dell'elemento attovo, dall'id so il campo da ordinare, per farlo uso le funzioni labda di js */
        let sortBy =(()=> {if ($('.btn-sort.active').attr('id') === "surname-sort") return "surname";
                            else if ($('.btn-sort.active').attr('id') === "num-reserv-sort") return "tot_num_res";
                            else return ""; })();

        /* per il tipo di sort è più facile e basta prendere il testo di chi è active o stringa vuota */
        let sortType = $('.btn-sort.active').html()??"";

        console.log("order by " + sortBy + " " + sortType);

        $.post("getTblUser", {"sortBy": sortBy, "sortType": sortType}, function (htmlOfTheTable) {
            $('.dt-row').remove();
            $('#user-tbl-header-row').after(htmlOfTheTable);
        });
    })();

    /* si tratta di due pulsanti con la stessa classe che voglio gestire allo stesso modo
    * facendo cambiare il loro testo in none -> asc -> desc e poi da capo */
    $('.btn-sort').click(function(e){
        handleSortClick(e);
    });

});

function handleSortClick(e) {
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

    /* prendo i parametri e chiamo la servlet */
    let sortBy = ( () => {if(clickerId==="surname-sort") return "surname"; else if(clickerId==="num-reserv-sort") return "tot_num_res"; else return "";} )();
    let sortType = ( () => { if($('#'+clickerId).html()==="none") return ""; else return $('#'+clickerId).html(); })();
    console.log("order by " + sortBy + " " + sortType);

    $.post("getTblUser", {"sortBy": sortBy, "sortType": sortType}, function (htmlOfTheTable) {
        $('.dt-row').remove();
        $('#user-tbl-header-row').after(htmlOfTheTable);
    });

}