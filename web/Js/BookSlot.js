/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() {
    $('#btnBook').click(function (e){
        // essendo un submit inibisco l'action
        e.preventDefault();

        // dati dal form
        let formData = $("#book-slots :input").serializeArray();
        console.log({'dati form update':formData});

        /* Chiedo al back di processare la richiesta */
        $.post("", formData, function(data) {

        })
        .fail(function(response) {
            showMessage(response.responseText);
        });
    });

});

