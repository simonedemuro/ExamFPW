# Progetto Balneare 

Lo scopo del progettto è ...

***



## Requisiti:

- [ ] Prenotazione Posti

- [ ] Archiviazione Fatture

- [ ] Gestione Utenti

  - [ ] Semplice:

    - [ ] Registrazione 

      **username** (che deve essere univoco)  **password**, e caricando tutti i suoi dati anagrafici. In particolare, ogni utente deve caricare: **nome**, **cognome**, **data di nascita**, **codice fiscale**, **sesso**, **e-mail**, **numero di cellulare** e un parametro che indica se l’utente desidera **ricevere la fattura OPT-IN**  elettronica o meno.

    - [ ] Pagina personale modifica anagrafica

    - [ ] Prenotazione posti in slot (1.)

    - [ ] Visualizzazione estremi fatture

  - [ ] Amministratore

    ​	L'amministratore viene creato manualmente, è solo 1

    - [ ] Inserire nuovi slot *inserendo data, slot, numero posti*
    - [ ] salvataggio estremi fatture emesse:
      - [ ] Anche se il pagamento del servizio e la creazione della eventuale fattura avvengono con software esterni e indipendenti dall'applicazione web corrente, l’amministratore salva nell’applicazione web gli estremi delle fatture emesse per i clienti che la richiedono. Precisamente, l’amministratore può salvare nell’applicazione i dati delle fatture emesse ai clienti che hanno prenotato lo stabilimento. In particolare, l’amministratore analizza una ad una le prenotazioni passate non ancora processate e può decidere di aggiungere i dati della fattura originale nell’applicazione web (ma solo se l’utente che ha effettuato la prenotazione ha indicato, nello specifico campo del suo profilo personale, che è interessato a ricevere la fattura elettronica). I dati della fattura che vengono salvati sono l’identificativo, il prezzo totale, la data, il numero di posti prenotati e una descrizione del servizio offerto in formato testuale.  (3.)
    - [ ] Schermata visualizzazione:
      - [ ] Tutti utenti con i loro dati e numero di prenotazioni effettuate
      - [ ] Elenco di utenti e totale posti prenotati nel tempo con menu per ordinare asc o desc per cognome o numero di posti prenotati nel tempo

  - [ ] Guest

    - [ ] Visualizzazione Slot prenotabili e numero posti in ogni slot

---

1. Due slot al giorno: **mattina** 8:00 - 14:00 **pomeriggio** 14:00 - 20:00. 

2. NB: ogni slot è prenotabile se è disponibile un numero di posti liberi sufficiente a prenotare tutti i posti che l’utente richiede.

3. NB: L’emissione della fattura è legata alla sola prenotazione, quindi i clienti ricevono i dati della fattura anche se non hanno ancora utilizzato il servizio (perché magari hanno prenotato oggi per una data futura). Inoltre, ad ogni prenotazione corrisponde una sola fattura: l’utente che prenota per la sua famiglia di quattro persone vedrà i dati di un’unica fattura e non quattro.

   Per comprendere meglio questa feature si consideri il seguente esempio.

   Il giorno 15/06/2021 gli utenti semplici Mario e Dino prenotano entrambi l’utilizzo dello stabilimento per due differenti date future. Mario, nel suo profilo personale, ha impostato il campo che indica che è interessato a ricevere la fattura elettronica. Dino invece no. Entrambe le prenotazioni vengono confermate dal sistema. L'amministratore, in una data successiva al 15/06/2021 processa tutte le prenotazioni del 15/06/2021. Sia la prenotazione di Mario che quella di Dino vengono rimosse dall’elenco delle prenotazioni. La prenotazione di Mario viene inserita tra le fatture emesse, con i relativi dati, mentre quella di Dino non viene inserita da nessuna parte (è stata quindi semplicemente rimossa dall’elenco delle prenotazioni).
