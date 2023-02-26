import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka", "root", "")) {
            Biblioteka biblioteka = new Biblioteka(connection);
            Statement stmt = null;

            Scanner input = new Scanner(System.in);
            System.out.println("Nick:  ");
            String nick = input.nextLine();
            System.out.println("Haslo:  ");
            int haslo = input.nextInt();

            String sql = "SELECT * from pracownik where nick = ? and haslo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nick);
            statement.setInt(2, haslo);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            int d=1;
            int rola=9;
            if (resultSet.next()) {
                 rola = resultSet.getInt("rola");
            }
            else {
                rola=9;
            }
                if (rola == 1) {
                    while (d == 1) {
                        int n;
                        for (int k = 0; k < 10; ++k)
                            System.out.println();
                        System.out.flush();
                        System.out.println("1. Dodaj czytelnika");
                        System.out.println("2. Wyszukaj czytelnika");
                        System.out.println("3. Dodaj ksiazke ");
                        System.out.println("4. Wypożycz ksiazke ");
                        System.out.println("5. Zwroc ksiazke ");
                        System.out.println("6. Lista ksiazek ");
                        System.out.println("7. Dodaj Pracownika ");
                        System.out.println("8. Przedłuż wypożyczenie ");
                        System.out.println("9. Zamknij program ");

                        n = input.nextInt();
                        for (int k = 0; k < 10; ++k)
                            System.out.println();
                        System.out.flush();


                        switch (n) {
                            case 1:
                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                System.out.println("DODAWANIE NOWEGO CZYTELNIKA");
                                Czytelnik czytelnik = new Czytelnik();
                                input.nextLine();
                                System.out.println("Imie:");
                                String imie = input.nextLine();
                                czytelnik.setImie(imie);

                                System.out.println("Nazwisko:");
                                String nazwisko = input.nextLine();
                                czytelnik.setNazwisko(nazwisko);
                                int numer_karty_czytelnika = biblioteka.getmax();
                                czytelnik.setNumer_karty(numer_karty_czytelnika);
                                biblioteka.dodaj_czytelnika(czytelnik);
                                System.out.println("Numer Karty czytelnika to " + numer_karty_czytelnika);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                break;
                            case 2:

                                System.out.println("WYSZUKIWANIE CZYTELNIKA");
                                System.out.println("Podaj imie");
                                String imie_szukaj = input.nextLine();
                                imie_szukaj = input.nextLine();

                                System.out.println("Podaj nazwisko");
                                String naziwsko_szukaj = input.nextLine();


                                for (int k = 0; k < 3; ++k)
                                    System.out.println();
                                System.out.flush();


                                List<Czytelnik> czytelnikk = biblioteka.pokaz_czytelnika(imie_szukaj, naziwsko_szukaj);


                                for (Czytelnik czytelnik2 : czytelnikk) {
                                    System.out.println("Imie: " + czytelnik2.getImie());
                                    System.out.println("Nazwisko: " + czytelnik2.getNazwisko());
                                    System.out.println("Numer karty: " + czytelnik2.getNumer_karty());
                                }
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                break;

                            case 3:
                                System.out.println("DODAWANIE KSIAZKI");
                                input.nextLine();
                                System.out.println("Podaj tytul ksiazki");
                                String tytul = input.nextLine();

                                System.out.println("Podaj imie autora");

                                String imie_autora = input.nextLine();

                                System.out.println("Podaj naziwsko autora");

                                String nazwisko_autora = input.nextLine();
                                Autor autor = new Autor(imie_autora, nazwisko_autora);

                                System.out.println("Podaj ilość stron");
                                int ilosc_stron = input.nextInt();
                                System.out.println("Podaj number ISBN");
                                int isbn = input.nextInt();
                                System.out.println("Podaj ilość sztuk");
                                int ilosc_sztuk = input.nextInt();
                                Ksiazka ksiazka = new Ksiazka(tytul, ilosc_stron, ilosc_sztuk, isbn, autor);
                                biblioteka.addbook(ksiazka);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 4:
                                System.out.println("WYPOZYCZENIE KSIAZKI");
                                Czytelnik czytelnik2 = new Czytelnik();
                                System.out.println("Numer Karty:");
                                int numer = input.nextInt();
                                czytelnik2.setNumer_karty(numer);
                                String tytul_wypozyczenie = input.nextLine();
                                Ksiazka ksiazka_wypo = new Ksiazka();
                                System.out.println("Tytuł ksiązki:");
                                tytul_wypozyczenie = input.nextLine();
                                ksiazka_wypo.setTytul(tytul_wypozyczenie);
                                SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

                                Date data = new Date();
                                String data_wypozyczenia = formatter.format(data);
                                Wypozyczenie Wypozyczenie = new Wypozyczenie(ksiazka_wypo, czytelnik2, data_wypozyczenia);
                                biblioteka.wypozycz_ksiazke(Wypozyczenie);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;


                            case 5:
                                System.out.println("ZWROT KSIAZKI");
                                Czytelnik czytelnik_zwrot = new Czytelnik();
                                System.out.println("Numer Karty:");
                                int numer_zwrotu = input.nextInt();
                                czytelnik_zwrot.setNumer_karty(numer_zwrotu);

                                String tytul_zwrot = input.nextLine();
                                Ksiazka ksiazka_zwrot = new Ksiazka();
                                System.out.println("Tytuł ksiązki:");
                                tytul_zwrot = input.nextLine();
                                ksiazka_zwrot.setTytul(tytul_zwrot);
                                SimpleDateFormat formatter_Zwrot = new SimpleDateFormat("yyy-MM-dd");

                                Date data_z = new Date();
                                String data_zwrotu = formatter_Zwrot.format(data_z);
                                Zwrot zwrot = new Zwrot(ksiazka_zwrot, czytelnik_zwrot, data_zwrotu);
                                biblioteka.zwrot_ksiazki(zwrot);

                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 6:
                                System.out.println("LISTA KSIAZEK");
                                biblioteka.pokaz_ksiazki();
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 7:
                                System.out.println("DODAWANIE PRACOWNIKA");
                                System.out.println("Imie:");
                                String imie_pracownika = input.nextLine();
                                imie_pracownika = input.nextLine();


                                System.out.println("Nazwisko:");
                                String nazwisko_pracownika = input.nextLine();

                                System.out.println("Nick:");
                                String Nick_nowego_pracownika = input.nextLine();

                                System.out.println("haslo:");
                                String nowe_haslo = input.nextLine();

                                System.out.println("Rola:");
                                System.out.println("1 - Administrator");
                                System.out.println("2 - Pracownik biblioteki:");
                                int rola_pracownika = input.nextInt();
                                Pracownik pracownikk = new Pracownik(imie_pracownika, nazwisko_pracownika, Nick_nowego_pracownika, nowe_haslo, rola_pracownika);
                                biblioteka.dodaj_pracownika(pracownikk);

                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 8:
                                System.out.println("PRZEDLUZ WYPOZYCZENIE");
                                System.out.println("Numer Karty:");
                                int numer_przedluzenie = input.nextInt();
                                Czytelnik czytelnik_przedluzenie = new Czytelnik(numer_przedluzenie);
                                System.out.println("Tytul ksiazki:");
                                String tytul_przedluzenie = input.nextLine();
                                tytul_przedluzenie = input.nextLine();


                                Ksiazka ksiazka_przedluzenie = new Ksiazka(tytul_przedluzenie);
                                System.out.println("Do kiedy przedłużyć wypożyczenie (YYYY-MM-DD)");
                                String data_przedluzenie = input.nextLine();
                                Przedluzenie przedluzenie = new Przedluzenie(ksiazka_przedluzenie, czytelnik_przedluzenie, data_przedluzenie);
                                biblioteka.przedluzenie(przedluzenie);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();
                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 9:


                                d = 0;


                                break;

                        }

                    }
                }

                    else if (rola == 2) {
                    while (d == 1) {
                    for (; ; ) {
                        int n;
                        for (int k = 0; k < 10; ++k)
                            System.out.println();
                        System.out.println("1. Dodaj czytelnika");
                        System.out.println("2. Wyszukaj czytelnika");
                        System.out.println("3. Dodaj ksiazke ");
                        System.out.println("4. Wypożycz ksiazke ");
                        System.out.println("5. Zwroc ksiazke ");
                        System.out.println("6. Lista ksiazek ");
                        System.out.println("7. Przedłuż wypożyczenie ");
                        System.out.println("8. Zamknij program ");

                        n = input.nextInt();
                        for (int k = 0; k < 10; ++k)
                            System.out.println();
                        System.out.flush();


                        switch (n) {
                            case 1:
                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                System.out.println("DODAWANIE NOWEGO CZYTELNIKA");
                                Czytelnik czytelnik = new Czytelnik();
                                input.nextLine();
                                System.out.println("Imie:");
                                String imie = input.nextLine();
                                czytelnik.setImie(imie);

                                System.out.println("Nazwisko:");
                                String nazwisko = input.nextLine();
                                czytelnik.setNazwisko(nazwisko);

                                czytelnik.setNumer_karty(biblioteka.getmax());
                                biblioteka.dodaj_czytelnika(czytelnik);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                break;
                            case 2:

                                System.out.println("WYSZUKIWANIE CZYTELNIKA");
                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                System.out.println("Podaj imie");
                                String imie_szukaj = input.nextLine();
                                imie_szukaj = input.nextLine();

                                System.out.println("Podaj nazwisko");
                                String naziwsko_szukaj = input.nextLine();

                                System.out.println("imie" + imie_szukaj);
                                System.out.println("naziwsko" + naziwsko_szukaj);


                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();


                                List<Czytelnik> czytelnikk = biblioteka.pokaz_czytelnika(imie_szukaj,naziwsko_szukaj);


                                for (Czytelnik czytelnik2 : czytelnikk) {
                                    System.out.println("Imie: " + czytelnik2.getImie());
                                    System.out.println("Nazwisko: " + czytelnik2.getNazwisko());
                                    System.out.println("Numer karty: " + czytelnik2.getNumer_karty());
                                }
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();

                                break;

                            case 3:
                                System.out.println("DODAWANIE KSIAZKI");
                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                input.nextLine();
                                System.out.println("Podaj tytul ksiazki");
                                String tytul = input.nextLine();

                                System.out.println("Podaj imie autora");

                                String imie_autora = input.nextLine();

                                System.out.println("Podaj naziwsko autora");

                                String nazwisko_autora = input.nextLine();
                                Autor autor = new Autor(imie_autora, nazwisko_autora);

                                System.out.println("Podaj ilość stron");
                                int ilosc_stron = input.nextInt();
                                System.out.println("Podaj number ISBN");
                                int isbn = input.nextInt();
                                System.out.println("Podaj ilość sztuk");
                                int ilosc_sztuk = input.nextInt();
                                Ksiazka ksiazka = new Ksiazka(tytul, ilosc_stron, ilosc_sztuk, isbn, autor);
                                biblioteka.addbook(ksiazka);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 4:
                                System.out.println("WYPOZYCZENIE KSIAZKI");
                                Czytelnik czytelnik2 = new Czytelnik();
                                System.out.println("Numer Karty:");
                                int numer = input.nextInt();
                                czytelnik2.setNumer_karty(numer);
                                String tytul_wypozyczenie = input.nextLine();
                                Ksiazka ksiazka_wypo = new Ksiazka();
                                System.out.println("Tytuł ksiązki:");
                                tytul_wypozyczenie = input.nextLine();
                                ksiazka_wypo.setTytul(tytul_wypozyczenie);
                                SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

                                Date data = new Date();
                                String data_wypozyczenia = formatter.format(data);
                                Wypozyczenie Wypozyczenie = new Wypozyczenie(ksiazka_wypo, czytelnik2, data_wypozyczenia);
                                biblioteka.wypozycz_ksiazke(Wypozyczenie);
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;


                            case 5:
                                System.out.println("ZWROT KSIAZKI");
                                Czytelnik czytelnik_zwrot = new Czytelnik();
                                System.out.println("Numer Karty:");
                                int numer_zwrotu = input.nextInt();
                                czytelnik_zwrot.setNumer_karty(numer_zwrotu);

                                String tytul_zwrot = input.nextLine();
                                Ksiazka ksiazka_zwrot = new Ksiazka();
                                System.out.println("Tytuł ksiązki:");
                                tytul_zwrot = input.nextLine();
                                ksiazka_zwrot.setTytul(tytul_zwrot);
                                SimpleDateFormat formatter_Zwrot = new SimpleDateFormat("yyy-MM-dd");

                                Date data_z = new Date();
                                String data_zwrotu = formatter_Zwrot.format(data_z);
                                Zwrot zwrot = new Zwrot(ksiazka_zwrot, czytelnik_zwrot, data_zwrotu);
                                biblioteka.zwrot_ksiazki(zwrot);

                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;

                            case 6:
                                System.out.println("LISTA KSIAZEK");
                                biblioteka.pokaz_ksiazki();
                                System.out.println("\nWciśnij ENTER aby wrócić do menu");
                                System.in.read();

                                for (int k = 0; k < 10; ++k)
                                    System.out.println();
                                System.out.flush();
                                break;



                            case 7:
                                System.out.println("PRZEDLUZ WYPOZYCZENIE");
                                System.out.println("Numer Karty:");
                                int numer_przedluzenie = input.nextInt();
                                Czytelnik czytelnik_przedluzenie = new Czytelnik(numer_przedluzenie);
                                System.out.println("Tytul ksiazki:");
                                String tytul_przedluzenie = input.nextLine();
                                tytul_przedluzenie = input.nextLine();


                                Ksiazka ksiazka_przedluzenie = new Ksiazka(tytul_przedluzenie);
                                System.out.println("Do kiedy przedłużyć wypożyczenie (YYYY-MM-DD)");
                                String data_przedluzenie = input.nextLine();
                                Przedluzenie przedluzenie = new Przedluzenie(ksiazka_przedluzenie,czytelnik_przedluzenie,data_przedluzenie);
                                biblioteka.przedluzenie(przedluzenie);

                                break;

                            case 8:


                                d=0;


                                break;

                        }
                    }


                }
            } else if (rola==9) {
                    System.out.println("złe haslo lub login");
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }



