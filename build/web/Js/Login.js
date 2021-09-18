/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() {
    /* Pulsante LOGIN */
    $('#from-login').on('submit', function(e) {
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();
        // dati dal form
        let data = $("#from-login :input").serializeArray();
        console.log(data);

        doLogin(data);
    });
    /* pulsante LOGOUT */
    $('#btn-logout').on('click', function(e) {
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();

        /* Chiama la servlet */
        $.ajax({
            type: "POST",
            url: "logout",
            data: "",
            success: function(data){
                /* in caso di log out torno alla homepage per evitare che ri facendo il log in l'utente si trovi
                * in sezioni che non potrebbe o vorrebbe vedere */
                location.href = "index.jsp";

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                /* Messaggio di errore per 3 secondi senza refresh della pagina */
                showMessage(textStatus + " " + errorThrown, 3000, false);

            }
        });
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

