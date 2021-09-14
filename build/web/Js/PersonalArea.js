/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Document ready attacco gli eventi  */
$(function() {
    /* Funizone per promuovere autocompletamento dei dati nel form settando i valori inseriti nel database al momento della pressione
    * sul campo che si vuole modificare, in modo da non mandare tutti i campi indietro dopo la modifica ma presumibilmente solo quelli che si sono
    * effettivamente modificati. */
    $('.magic-complete').click(function(e){
        /* prendo l'elemento input che è stato cliccato dall'utente */
        let clickedInputField = $('#'+e.target.id);
        /* il vare sul db che arriva dalla servlet è nel placeholder */
        let deservedVal = clickedInputField.attr('placeholder');
        /* non resta che mettere il placeholder nel campo value e abbiamo messso l'hint per l'user */
        clickedInputField.val(deservedVal);
    });

    $('#doUpdate').click(function(e) {
        // evita il submit default che farebbe una redirect a 404
        e.preventDefault();
        // dati dal form
        let data = $("#registration-data :input").serializeArray();
        console.log({'dati form update':data});

        // Controllo che la password scelta corrisponda
        let newPassIdx = data.findIndex(x => x.name === "Fpass1");
        let repPassIdx = data.findIndex(x => x.name === "Fpass2");

        if (data[newPassIdx].value !== data[repPassIdx].value){
            // Evidenzio e lancio un popup di errore
            $("#Fpass2").css("border", "dashed #F79E02");
            showMessage("❌ Errore: le password non corrispondono", 3000, true);
            return;
        }

        // aggiungo campo per triggerare la servlet in update
        data.push({name:"action", value:"update"})
        // rimuovo i campi nulli
        data = data.filter(x => x.value);
        /* Chiama la servlet */
        $.ajax({
            type: "POST",
            url: "personalData",
            data: data,
            success: function(data){
                console.log(data);
                showMessage("✅️Modifiche Effettuate", 3000, true);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log({XMLHttpRequest, textStatus, errorThrown});
            }
        });
    });

    /* i campi radio li faccio a mano :( ... */
    (function () {
        /* prendo i campi radio da dei div nascosti nella pagina */
       let sex = $('#sex').val();
       let invoice = $('#invoice').val();

       /* sesso nelle radio */
       if(sex === "M"){
           $('input[type=radio][value=M]').prop('checked',true);
       } else if (sex === "F") {
           $('input[type=radio][value=F]').prop('checked',true);
       }

       /* fatturazione opt in  */
        if(invoice==="true"){ // sembra ovvio ma if("false") è true in js 😕
            $('input[type=radio][value=true]').prop('checked',true);
        } else  {
            $('input[type=radio][value=false]').prop('checked',true);
        }

       console.log({'sex':sex,'invoice':invoice});
    }());
});

