/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() {
    $('#from-login').on('submit', function(e) {
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();
        // dati dal form
        let data = $("#from-login :input").serializeArray();
        console.log(data);

        doLogin(data);
    });

    var doLogin = function (data) {
        $.post("login", data, function (data, status) {
            // Se è andato a buon fine non compare la parola errorr
            console.log(data);
            if (status === "success" && !data.toLowerCase().includes("error")) {
                $("#msgBar").text(data);
                // Metto il messaggio e la classe info per farlo apparire
                $("#msgBar").addClass("msg-info");

                // Rimuovo dopo 3 secondi la notifica
                setTimeout(function myFunction() {
                    $("#msgBar").removeClass("msg-info");
                    /* reload per far funzionare JSP */
                    location.reload();
                }, 3000);
            }
            else {
                // Messaggio di errore
                $("#msgBar").text(data);
                // Metto il messaggio e la classe info per farlo apparire
                $("#msgBar").addClass("msg-error");

                // Rimuovo dopo 3 secondi la notifica
                setTimeout(function myFunction() {
                    $("#msgBar").removeClass("msg-error");
                }, 3000);
            }
        });
    }

});

