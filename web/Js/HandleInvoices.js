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
    });
});