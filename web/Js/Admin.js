$(function() {
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
            showMessage(data, 3000, false);
        });
    });

});