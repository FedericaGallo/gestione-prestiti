package entity;

import java.time.LocalDate;

public class Prestito {
private Libro libro;
private Utente utente;
private LocalDate inizioPrestito;
private LocalDate finePrestito;
private int id;

    public String getLibro() {
        return libro.getAutore()+ " " + libro.getTitolo();
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getUtente() {
        return utente.getCognome()+ " " + utente.getNome();
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getFinePrestito() {
        return finePrestito;
    }

    public void setFinePrestito(LocalDate finePrestito) {
        this.finePrestito = finePrestito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdLibro(String id){
        this.libro.setId(id);}

    public void setIdUtente(int id){this.utente.setId(id);}


    public String getIdLibro(){return libro.getId();}

    public int getIdUtente(){return utente.getId();}
}
