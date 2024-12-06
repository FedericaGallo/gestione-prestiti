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
            stmt.execute("DELETE FROM lendings WHERE id= "+ oPrestito.getId() );
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
}
