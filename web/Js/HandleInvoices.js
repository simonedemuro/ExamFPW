/* pagina caricata! */
$(function() {
    /* carico la tabella delle fatture */
    $.post("getInvoices", {}, function (htmlTblInvoice) {
        if(htmlTblInvoice.includes("Errore:")){
            showMessage(htmlTblInvoice, 3000, false);
            return;
        }
        console.log(htmlTblInvoice);
        /* rimuovo se esistono le fatture */
        $('.invoices-row').remove();
        /* attacco le nuove */
        $('.invoice-tbl-header-row').after(htmlTblInvoice);
        /* faccio vedere la tabella */
        $("#invoice-tbl").show();

        /* se non ci sono dati non faccio vedere la tabella */
        if($("#invoice-tbl tr").length < 2 ) {
            $("#invoice-tbl").hide();
        }
    });
});