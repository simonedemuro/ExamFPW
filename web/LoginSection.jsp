<section class="card-login">
    <form id="from-login" action="" method="post">
        <div class="form-login-surround">
            <div class="_2_3-form">
                <label for="Fuser">Username: </label>
                <input autocomplete="off" id="Fuser" name="Fuser" onfocus="this.removeAttribute('readonly');"
                       placeholder="Il tuo username"
                       readonly
                       required type="text">
                <!-- readonly e remove serve a impedire a mozzilla dicashare le informazioni con prepotenza -->
            </div>
            <div class="_2_3-form">
                <label for="Fpass">Password: </label>
                <input autocomplete="off" id="Fpass" name="Fpass" onfocus="this.removeAttribute('readonly');"
                       placeholder="La tua password"
                       readonly
                       required type="password">
            </div>
            <div class="_1_3-form">
                <label for="subm"> </label>
                <input type="submit" id="subm" value="Login">
            </div>
        </div>
    </form>
    <p id="msg-signup"> se non hai ancora un account <a href="Registration.jsp">Registrati</a></p>
</section>