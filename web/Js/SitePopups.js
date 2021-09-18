var showMessage = function (data, time, reload) {
    if (!data.toLowerCase().includes("error")) {
        $("#msgBar").text("✅️   " + data);
        // Metto il messaggio e la classe info per farlo apparire
        $("#msgBar").addClass("msg-info");

        // Rimuovo dopo 3 secondi la notifica
        setTimeout(function myFunction() {
            $("#msgBar").removeClass("msg-info");
            /* reload per far funzionare JSP */
            if(reload){
                location.reload();
            }
        }, time);
    }
    else {
        // Messaggio di errore
        $("#msgBar").text("❌   " +data);
        // Metto il messaggio e la classe info per farlo apparire
        $("#msgBar").addClass("msg-error");

        // Rimuovo dopo 3 secondi la notifica
        setTimeout(function myFunction() {
            $("#msgBar").removeClass("msg-error");
        }, time);
    }
}