public class Czytelnik extends Osoba {

    private int numer_karty;

public Czytelnik() {
    super();

}

    public Czytelnik(int numer_karty) {
        this.numer_karty = numer_karty;
    }

    public Czytelnik(String imie, String nazwisko, int numer_karty) {
        super(imie, nazwisko);
        this.numer_karty = numer_karty;
    }

    public void setNumer_karty(int numer_karty) {
        this.numer_karty = numer_karty;
    }


    public int getNumer_karty() {
        return numer_karty;
    }



}
