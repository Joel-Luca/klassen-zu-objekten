package account.teil1;

public class Account {

    private double saldo = 0;
    private double zinssatz = 0.01;
    public static final String waehrung = "CHF";

    public double getSaldo() {
        return saldo;
    }

    public double getZinssatz() {
        return zinssatz;
    }

    public double getZinsbetrag() {
        return saldo * zinssatz;
    }

    public void zahleEin(double betrag) {
        if(betrag > 0) {
            saldo += betrag;
        }
        else {
            throw new IllegalArgumentException(
                    "Der Betrag muss grösser als 0 sein, um ihn aufs Konto zu laden."
            );
        }
    }

    public void hebeAb(double betrag) throws IllegalArgumentException {
        if(betrag <= saldo) {
            saldo -= betrag;
        }
        else {
            throw new IllegalArgumentException(
                    "Der Betrag, der abgehoben werden sollte, muss kleiner oder gleich gross wie der Kontostand sein."
            );
        }
    }

    public void setZinssatz(double zinssatz) {
        this.zinssatz = zinssatz;
    }

    public static Account[] createAccounts(int numberOfAccountsToCreate) {
        Account[] accounts = new Account[numberOfAccountsToCreate];
        for (int i = 0; i < numberOfAccountsToCreate; i++) {
            accounts[i] = new Account();
        }
        return accounts;
    }
}