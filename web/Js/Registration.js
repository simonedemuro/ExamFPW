/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() { 
    $('#registration-data').on('submit', function(e) { 
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();  
        // dati dal form
        let data = $("#registration-data :input").serializeArray();
        console.log(data);

        // Controllo che la password scelta corrisponda
        let newPassIdx = data.findIndex(x => x.name === "Fpass1");
        let repPassIdx = data.findIndex(x => x.name === "Fpass2");

        if (data[newPassIdx].value !== data[repPassIdx].value){
            // Evidenzio e lancio un popup di errore
            $("#Fpass2").css("border", "dashed #F79E02");
            return;
        }

        registerUser(data);
    });
    
    var registerUser = function (data) {
        $.post("RegistrationServlet", data, function (data, status) {
            // Se è andato a buon fine non compare la parola errorr
            console.log(data);
            if (status === "success" && !data.toLowerCase().includes("error")) {
                $("#msgBar").text(data);
                // Metto il messaggio e la classe info per farlo apparire
                $("#msgBar").addClass("msg-info");

                // Rimuovo dopo 3 secondi la notifica
                setTimeout(function myFunction() {
                    /* messaggio di susccesso e mando alla homepage */
                    $("#msgBar").removeClass("msg-info");
                    location.href = "index.jsp";
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

