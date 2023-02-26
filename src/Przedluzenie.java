public class Przedluzenie extends Wypozyczenie{
    private String data_przedluzenia;

    public Przedluzenie(Ksiazka ksiazka, Czytelnik czytelnik, String data_przedluzenia) {
        super(ksiazka, czytelnik);
        this.data_przedluzenia = data_przedluzenia;
    }

    public String getData_przedluzenia() {
        return data_przedluzenia;
    }

    public void setData_przedluzenia(String data_przedluzenia) {
        this.data_przedluzenia = data_przedluzenia;
    }
}
