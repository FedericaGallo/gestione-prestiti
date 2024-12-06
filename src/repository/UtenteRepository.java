package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Config.DbConnection;
import entity.Utente;

public class UtenteRepository {

    public void createUtente(Utente oUtente) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO consumers VALUES('" + oUtente.getNome() + "','" + oUtente.getCognome() + "')");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public Utente readUtenteByID(int id){
        Utente oUtente = new Utente();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM consumers WHERE idc = " + id);
            while (rs.next()) {

                oUtente.setNome(rs.getString("name"));
                oUtente.setCognome(rs.getString("last_name"));
                oUtente.setId(rs.getInt("idc"));

            }

        }catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return oUtente;
    }

    public ArrayList<Utente> readUtente () {
        ArrayList<Utente> listaUtente = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM consumers ORDER BY idc asc");
            while (rs.next()) {
                Utente oUtente = new Utente();
                oUtente.setNome(rs.getString("name"));
                oUtente.setCognome(rs.getString("last_name"));
                oUtente.setId(rs.getInt("idc"));
                listaUtente.add(oUtente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaUtente;
    }
    public void deleteUtente(Utente oUtente) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("DELETE FROM consumers WHERE idc = '" + oUtente.getId() + "'");
            System.out.println("model.dao.Utente eliminato");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void updateUtente(Utente oUtente) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("UPDATE consumers SET name='"+oUtente.getNome()+"', last_name='"+oUtente.getCognome()+"' WHERE idc ="  + oUtente.getId());
            System.out.println("model.dao.Utente aggiornato");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
