/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() {
    let svrData = {};

    let username = $('#_user').val();
    /* Al caricamento della pagina chiama la servlet per pre-popolare il form con i dati dell'utente */
    $.ajax({
        type: "GET",
        url: "personalData",
        data: {"user":username},
        success: function(data){
            // Se è andato a buon fine non compare la parola errorr
            console.log(data);

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest, textStatus, errorThrown);
        }
    });
});

