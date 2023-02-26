public class Autor {

    private String imie;
    private String nazwisko;

    public Autor( String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }




    public String getImie() {
        return imie;
    }

    public void setimie(String firstName) {
        this.imie = firstName;
    }

    public String getnaziwsko() {
        return nazwisko;
    }

    public void setnaziwsko(String lastName) {
        this.nazwisko = lastName;
    }
}
