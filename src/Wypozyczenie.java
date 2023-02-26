import java.util.Date;
public class Wypozyczenie {
    private Ksiazka ksiazka;
    private Czytelnik czytelnik;
    private String data_wypozyczenia;

    public Wypozyczenie(Ksiazka ksiazka, Czytelnik czytelnik) {
        this.ksiazka = ksiazka;
        this.czytelnik = czytelnik;
    }

    public Wypozyczenie(Ksiazka ksiazka, Czytelnik czytelnik, String data_wypozyczenia) {
        this.ksiazka = ksiazka;
        this.czytelnik = czytelnik;
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    public void setCzytelnik(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
    }

    public void setData_wypozyczenia(String data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    public Czytelnik getCzytelnik() {
        return czytelnik;
    }

    public String getData_wypozyczenia() {
        return data_wypozyczenia;
    }


}
