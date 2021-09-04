<section class="card-registration">
        <div class="registration-form">
                <form id="registration-data" action="">
                        <label for="Fuser">Username: </label>
                        <input type="text" id="Fuser" name="Fuser" placeholder="Il tuo username" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required> <!-- readonly e remove serve a impedire a mozzilla di cashare le informazioni con prepotenza -->
                        <label for="Fpass1">Crea password: </label>
                        <input type="password" id="Fpass1" name="Fpass1" placeholder="La tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>
                        <label for="Fpass2">Ripeti password: </label>
                        <input type="password" id="Fpass2" name="Fpass2" placeholder="Ri-digita la tua nuova password" autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>

                        <h2> Dati Anagrafici</h2>

                        <label for="Fname">Nome: </label>
                        <input type="text" id="Fname" name="Fname" placeholder="Il tuo nome" autocomplete="off" required>
                        <label for="Fsurn">Cognome: </label>
                        <input type="text" id="Fsurn" name="Fsurn" placeholder="Il tuo cognome" autocomplete="off" required>
                        <label for="Fbirt">Data di nascita: </label>
                        <input type="date" id="Fbirt" name="Fbirt" placeholder="Seleziona data di nascita" autocomplete="off" required>
                        <label for="Fcode">Codice fiscale: </label>
                        <input type="text" minlength="16" maxlength="16" id="Fcode" name="Fcode" placeholder="Il tuo codice fiscale a 16 cifre" autocomplete="off" required>
                        <p>Sesso: </p>
                        <label class="lbl-full-width">
                        <input type="radio" name="Fsex" id="isMale" value="M" checked> Male
                        </label>
                        <br>
                        <label class="lbl-full-width">
                        <input type="radio" name="Fsex" id="isFem" value="F"> Female
                        </label>
                        <label for="Fmail">Indirizzo e-mail: </label>
                        <input type="email" id="Fmail" name="Fmail" placeholder="La tua e-mail" autocomplete="off" required>
                        <label for="Fcell">Numero di telefono: </label>
                        <input type="tel" id="Fcell" name="Fcell" placeholder="Il tuo numero di telefono" autocomplete="off" pattern="\d*" required>
                        <p>Si desidera ricevere la fattura? </p>
                        <label class="lbl-full-width">
                        <input type="radio" name="Finvoice" id="invoiceYes" value="true"> Si
                        </label>
                        <br>
                        <label class="lbl-full-width">
                        <input type="radio" name="Finvoice" id="invoiceNo" value="false" checked> No
                        </label>
                        <div class="registration-form-submit">
                        <input type="reset" value="Pulisci">
                        <input type="submit" value="Registrati">
                        </div>
                </form>
        </div>
</section>