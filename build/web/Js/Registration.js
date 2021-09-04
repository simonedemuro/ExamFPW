/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() { //shorthand document.ready function
    $('#registration-data').on('submit', function(e) { 
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();  
        var data = $("#registration-data :input").serializeArray();
        console.log(data); 
        registerUser(data);
    });
    
    var registerUser = function (data) {
    // Retrieve form data

    // Call Servlet
    $.get("RegistrationServlet", {name: "Donald", town: "Ducktown"}, function (data) {
        alert("Data: " + data);
    });

    $.post("RegistrationServlet",
            {
                name: "Donald Duck",
                city: "Duckburg"
            },
            function (data, status) {
                alert("Data: " + data + "\nStatus: " + status);
            });
}

});

