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
        var data = $("#registration-data :input").serializeArray();
        console.log(data); 
        registerUser(data);
    });
    
    var registerUser = function (data) {
        $.post("RegistrationServlet", data, function (data, status) {
            alert("Data: " + data + "\nStatus: " + status);
        });
    }

});

