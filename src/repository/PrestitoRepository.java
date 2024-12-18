package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entity.Prestito;
import Config.DbConnection;
import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrestitoRepository {
public List<Prestito> read(){
    ArrayList<Prestito> listaPrestito = new ArrayList();
    return listaPrestito;
}
    public void createPrestito(Prestito oPrestito) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO lendings VALUES('" + oPrestito.getIdLibro() + "','" + oPrestito.getIdUtente() + "','" + oPrestito.getInizioPrestito() + "','" + oPrestito.getFinePrestito() + "')");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
    public void deletePrestito(Prestito oPrestito) {

        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            stmt.execute("DELETE FROM lendings WHERE idl= "+ oPrestito.getId() );
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
    public ArrayList<Prestito> readPrestito () {
    LibroRepository oLibroRepository = new LibroRepository();
    UtenteRepository oUtenteRepository = new UtenteRepository();
        ArrayList<Prestito> listaPrestito = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lendings");
            while (rs.next()) {
                Prestito oPrestito = new Prestito();
                Libro oLibro =  oLibroRepository.readLibroByID(rs.getString("idb"));
                oPrestito.setLibro(oLibro);
                Utente oUtente = oUtenteRepository.readUtenteByID(rs.getInt("idc"));
                oPrestito.setUtente(oUtente);
                oPrestito.setId(rs.getInt("idl"));
                oPrestito.setFinePrestito(rs.getDate("start_loan").toLocalDate());
                oPrestito.setInizioPrestito(rs.getDate("end_loan").toLocalDate());
                listaPrestito.add(oPrestito);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestito;
    }
    public ArrayList<Prestito> readPrestitoByUtente (int id) {
        LibroRepository oLibroRepository = new LibroRepository();
        //UtenteRepository oUtenteRepository = new UtenteRepository();
        //Utente oUtente = oUtenteRepository.readUtenteByID(id);
        ArrayList<Prestito> listaPrestito = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lendings WHERE idc = " + id);
            while (rs.next()) {
                Prestito oPrestito = new Prestito();
                Libro oLibro =  oLibroRepository.readLibroByID(rs.getString("idb"));
                oPrestito.setLibro(oLibro);
                oPrestito.setId(rs.getInt("idl"));
                oPrestito.setFinePrestito(rs.getDate("start_loan").toLocalDate());
                oPrestito.setInizioPrestito(rs.getDate("end_loan").toLocalDate());
                listaPrestito.add(oPrestito);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestito;
    }
    public ArrayList<Prestito> readPrestitoByLibro (String id) {
        UtenteRepository oUtenteRepository = new UtenteRepository();
        ArrayList<Prestito> listaPrestito = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            //System.out.println("Connessione riuscita!");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lendings WHERE idb = '" + id +"'");
            while (rs.next()) {
                Prestito oPrestito = new Prestito();
                Utente oUtente =  oUtenteRepository.readUtenteByID(rs.getInt("idc"));
                oPrestito.setUtente(oUtente);
                oPrestito.setId(rs.getInt("idl"));
                oPrestito.setFinePrestito(rs.getDate("start_loan").toLocalDate());
                oPrestito.setInizioPrestito(rs.getDate("end_loan").toLocalDate());
                listaPrestito.add(oPrestito);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestito;
    }
    public HashMap<Libro, Integer> readCountPrestitoByLibro(){
        HashMap<Libro, Integer> prestitiCount = new HashMap<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT b.title AS title, b.author AS author, COUNT(*) AS count FROM lendings l " +
                    "JOIN books b " +
                    "ON b.idb = l.idb " +
                    "GROUP BY b.title, b.author");

            while (rs.next()) {
                Libro oLibro= new Libro();
                oLibro.setTitolo(rs.getString("title"));
                oLibro.setAutore(rs.getString("author"));
                int count = rs.getInt("count");
                prestitiCount.put(oLibro, count);

            }
        }catch (ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return prestitiCount;
    }
    public HashMap<Utente, Integer> readCountPrestitoByUtente(){
        HashMap<Utente, Integer> prestitiCount = new HashMap<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.name AS name, c.last_name AS last_name, COUNT(*) AS count FROM lendings l " +
                    "JOIN consumers c " +
                    "ON c.idc = l.idc " +
                    "GROUP BY c.name, c.last_name");

            while (rs.next()) {
                Utente oUtente= new Utente();
                oUtente.setNome(rs.getString("name"));
                oUtente.setCognome(rs.getString("last_name"));
                int count = rs.getInt("count");
                prestitiCount.put(oUtente, count);

            }
        }catch (ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return prestitiCount;
    }

}
