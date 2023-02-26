public class Zwrot extends Wypozyczenie
{
public String data_zwrotu;

    public Zwrot(Ksiazka ksiazka, Czytelnik czytelnik,  String data_zwrotu) {
        super(ksiazka, czytelnik);
        this.data_zwrotu = data_zwrotu;
    }

    public String getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(String data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }
}
