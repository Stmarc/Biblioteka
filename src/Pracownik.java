public class Pracownik extends Osoba {

    private String nick;
    private String haslo;
    private int rola;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }


    public Pracownik(String imie, String nazwisko, String nick, String haslo, int rola) {
        super(imie, nazwisko);
        this.nick = nick;
        this.haslo = haslo;
        this.rola = rola;
    }

    public int getRola() {
        return rola;
    }

    public void setRola(int rola) {
        this.rola = rola;
    }
}
