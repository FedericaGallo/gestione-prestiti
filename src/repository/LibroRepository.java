package repository;

import Config.DbConnection;
import entity.Libro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroRepository {
    public void createLibro(Libro oLibro) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO books VALUES('" + oLibro.getId() + "', '" + oLibro.getTitolo() + "', '"+ oLibro.getAutore() +"')");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public Libro readLibroByID(String id){
        Libro oLibro = new Libro();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE idb = " + "'"+ id +"'");
            while (rs.next()) {

                oLibro.setTitolo(rs.getString("title"));
                oLibro.setAutore(rs.getString("author"));
                oLibro.setId(rs.getString("idb"));

            }

        }catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return oLibro;
    }

    public ArrayList<Libro> readLibro () {
        ArrayList<Libro> listaLibro = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books ORDER BY idb asc");
            while (rs.next()) {
                Libro oLibro = new Libro();
                oLibro.setTitolo(rs.getString("title"));
                oLibro.setAutore(rs.getString("author"));
                oLibro.setId(rs.getString("idb"));
                listaLibro.add(oLibro);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaLibro;
    }
    public void deleteLibro(Libro oLibro) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("DELETE FROM books WHERE idb = '" + oLibro.getId() + "'");
            System.out.println("model.dao.Libro eliminato");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void updateLibro(Libro oLibro) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("UPDATE books SET title='"+oLibro.getTitolo()+"', author='"+oLibro.getAutore()+"' WHERE idb ='"  + oLibro.getId() +"'");
            System.out.println("model.dao.Libro aggiornato");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
