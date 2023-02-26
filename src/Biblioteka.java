import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//tabela wypozyczenia [id,id_ksiazki,id_czytelnika,data_wypożyczenia,data_zwrotu]
//tabela opłaty za przetrzymanie wypożyczenia[id_czytelnika, opłata,]
//i zliczam czy dostępność dla tego id_ksiazki nie jest większa niż zliczony id_ksiazki
public class Biblioteka {
    private Connection connection;

    public Biblioteka(Connection connection) {
        this.connection = connection;
    }

    public Biblioteka() {
    }

    ;

    public void dodaj_czytelnika(Czytelnik czytelnik) throws SQLException {
        String sql = "INSERT INTO czytelnik (imie, nazwisko, numer_karty) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, czytelnik.getImie());
        statement.setString(2, czytelnik.getNazwisko());
        statement.setInt(3, czytelnik.getNumer_karty());
        statement.executeUpdate();
    }


    public int getmax() throws SQLException {
        String sql = "SELECT MAX(numer_karty) as numer FROM czytelnik";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("numer");
        }

        return i+1;
    }

    public List<Czytelnik> pokaz_czytelnika(String imie, String naziwsko) throws SQLException {
        List<Czytelnik> lista_czywtlnikow = new ArrayList<>();
        String sql = "SELECT * FROM czytelnik WHERE imie= ? and nazwisko = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, imie);
        statement.setString(2, naziwsko);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Czytelnik czytelnik = new Czytelnik();
                czytelnik.setImie(resultSet.getString("imie"));
                czytelnik.setNazwisko(resultSet.getString("nazwisko"));
                czytelnik.setNumer_karty(resultSet.getInt("numer_karty"));
                lista_czywtlnikow.add(czytelnik);
            }
        }

        return lista_czywtlnikow;
    }



    public int czy_autor_istnieje(Ksiazka ksiazka) throws SQLException {
        String sql = "SELECT * FROM autor WHERE imie = ? and nazwisko = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ksiazka.getAutor().getImie());
        statement.setString(2, ksiazka.getAutor().getnaziwsko());
        int id = 0;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                id = resultSet.getInt("id");

            }
        }
      return id;

    }

    public void addbook(Ksiazka ksiazka) throws SQLException {

        if(czy_autor_istnieje(ksiazka)!=0)
        {
            String sql = "INSERT INTO ksiazka (tytul ,id_autora, ilosc_stron , dostepnosc,isbn) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ksiazka.getTytul());
            statement.setInt(2, czy_autor_istnieje(ksiazka));
            statement.setInt(3, ksiazka.getIlość_stron());
            statement.setInt(4, ksiazka.getIlosc());
            statement.setInt(5, ksiazka.getIsbn());
            statement.executeUpdate();
        }
        else{
            System.out.println("Taki autor nie istnieje. Najpierw musisz dodać autora");
        }

    }

    public boolean dostepnosc(Ksiazka ksiazka) throws SQLException {
        String sql = "SELECT dostepnosc FROM ksiazka WHERE tytul = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ksiazka.getTytul());
        int a = 0;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                a = resultSet.getInt("dostepnosc");


            }
        }
        if (a != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int get_id_czytelnika(Czytelnik czytelnik) throws SQLException {
        String sql = "SELECT id FROM czytelnik WHERE numer_karty = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = connection.prepareStatement(sql);
        statement.setInt(1, czytelnik.getNumer_karty());

        int id_czytelnika = 0;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                id_czytelnika = resultSet.getInt("id");


            }
        }
        return id_czytelnika;
    }

    public int get_id_ksiazki(Ksiazka ksiazka) throws SQLException {
        String sql = "SELECT id FROM ksiazka WHERE tytul = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = connection.prepareStatement(sql);
        statement.setString(1, ksiazka.getTytul());

        int id_ksiazki = 0;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                id_ksiazki = resultSet.getInt("id");


            }
        }

        return id_ksiazki;
    }

    public void wypozycz_ksiazke(Wypozyczenie wypozyczenie) throws SQLException {




        if (dostepnosc(wypozyczenie.getKsiazka()) == true) {
            int id_czytelnika = get_id_czytelnika(wypozyczenie.getCzytelnik());


            int id_ksiazki = get_id_ksiazki(wypozyczenie.getKsiazka());





            String sql = "INSERT INTO `wypozyczenia`( `id_ksiazki`, `id_czytelnika`, `data_wypozyczenia`) VALUES(?, ?, ?)";
            connection.prepareStatement(sql);
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id_ksiazki);
            statement.setInt(2, id_czytelnika);
            statement.setString(3, wypozyczenie.getData_wypozyczenia());

            statement.executeUpdate();

            sql = "Update ksiazka set dostepnosc = dostepnosc - 1 where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id_ksiazki);
            statement.executeUpdate();


        } else {
            System.out.println("Książka nie jest dostępna");
        }


    }

    public int kara(String data_, int id_ksiazka, int id_czytelnik) throws SQLException {

        String sql = "SELECT id  from wypozyczenia where id_ksiazki = ? and id_czytelnika = ? and data_zwrotu is null";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setInt(1, id_ksiazka);
        statement.setInt(2, id_czytelnik);
        int id = 0;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                id = resultSet.getInt("id");


            }
        }
        if (id!=0)
        {

             sql = "SELECT DATEDIFF(?,data_wypozyczenia) as liczba from wypozyczenia WHERE `id_ksiazki` =? and `id_czytelnika`=?";
             statement = connection.prepareStatement(sql);

            statement.setString(1, data_);
            statement.setInt(2, id_ksiazka);
            statement.setInt(3, id_czytelnik);
            int liczba_dni = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    liczba_dni = resultSet.getInt("liczba");


                }
            }
            return liczba_dni;
        }
        else{
            sql = "SELECT DATEDIFF(?,data_zwrotu) as liczba from wypozyczenia WHERE `id_ksiazki` =? and `id_czytelnika`=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, data_);
            statement.setInt(2, id_ksiazka);
            statement.setInt(3, id_czytelnik);
            int liczba_dni = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    liczba_dni = resultSet.getInt("liczba");


                }
            }
            return liczba_dni;

        }




    }

    public void zwrot_ksiazki(Zwrot zwrot) throws SQLException {
        int id_czytelnika = get_id_czytelnika(zwrot.getCzytelnik());
        int id_ksiazki = get_id_ksiazki(zwrot.getKsiazka());


        String sql = "Update wypozyczenia set kiedy_zwrocono = ? where id_ksiazki = ? and id_czytelnika = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, zwrot.getData_zwrotu());
        statement.setInt(2, id_ksiazki);
        statement.setInt(3, id_czytelnika);
        statement.executeUpdate();
        sql = "Update ksiazka set dostepnosc = dostepnosc + 1 where id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id_ksiazki);
        statement.executeUpdate();
        int ile_dni = kara(zwrot.data_zwrotu, id_ksiazki, id_czytelnika);
        int roznica = ile_dni-60;

        DecimalFormat df = new DecimalFormat("#.00");

        if (roznica > 0)
        {
            double kara_pieniezna = roznica * 1;
            System.out.println("Do zapłaty " + df.format(kara_pieniezna) + " zł");
        }

    }

    public void pokaz_ksiazki() throws SQLException {
        String sql = "Select tytul,ilosc_stron,dostepnosc,isbn,imie,nazwisko from ksiazka inner join autor on autor.id = ksiazka.id_autora";
        PreparedStatement statement = connection.prepareStatement(sql);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                System.out.println("Tytul " + resultSet.getString("tytul"));

                System.out.println("Imie autora " + resultSet.getString("imie"));

                System.out.println("Nazwisko autora " + resultSet.getString("nazwisko"));
                System.out.println("Ilosc stron " + resultSet.getString("ilosc_stron"));
                System.out.println("Dostepnosc " + resultSet.getString("dostepnosc"));
                System.out.println("ISBN " + resultSet.getString("isbn"));

                System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||");



            }

        }

    }
    public void dodaj_pracownika(Pracownik pracownik) throws SQLException {
        String sql = "INSERT INTO pracownik ( `imie`, `naziwsko`, `nick`, `haslo`, `rola`) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, pracownik.getImie());
        statement.setString(2, pracownik.getNazwisko());
        statement.setString(3, pracownik.getNick());
        statement.setString(4, pracownik.getHaslo());
        statement.setInt(5, pracownik.getRola());

        statement.executeUpdate();



    }
    public void przedluzenie(Przedluzenie przedluzenie) throws SQLException {
        int id_czytelnika = get_id_czytelnika(przedluzenie.getCzytelnik());
        int id_ksiazki = get_id_ksiazki(przedluzenie.getKsiazka());
        String sql = "UPDATE wypozyczenia set data_zwrotu = ? where id_ksiazki = ? and id_czytelnika=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, przedluzenie.getData_przedluzenia());
        statement.setInt(2, id_ksiazki);
        statement.setInt(3, id_czytelnika);
        statement.executeUpdate();




    }
}