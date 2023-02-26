public class Ksiazka {

    private String tytul;

    private int ilość_stron;
    private int ilosc;
    private int isbn;

private Autor autor;
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Ksiazka(String tytul) {
        this.tytul = tytul;
    }

    public Ksiazka(String tytul, int ilość_stron, int ilosc, int isbn, Autor autor) {
        this.tytul = tytul;
        this.ilość_stron = ilość_stron;
        this.ilosc = ilosc;
        this.isbn = isbn;
        this.autor = autor;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public  String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getIlość_stron() {
        return ilość_stron;
    }

    public void setIlość_stron(int ilość_stron) {
        this.ilość_stron = ilość_stron;
    }


public Ksiazka(){};


}
