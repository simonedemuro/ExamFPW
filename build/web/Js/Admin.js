$(function() {
    // no lettere nel numero posti
    $('#Naccomodation').keypress(function(e){
        // non faccio passare i keystrokes che non sono compresi tra i valori ascii
        if (e.which < 48 || e.which > 57) {
            e.preventDefault();
        }
    });

});