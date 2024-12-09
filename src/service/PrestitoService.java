package service;

import java.time.LocalDate;
import java.util.List;

import entity.*;
import repository.LibroRepository;
import repository.PrestitoRepository;
import repository.UtenteRepository;

public class PrestitoService {
    PrestitoRepository prestitoRepository = new PrestitoRepository();
    UtenteRepository oUtenteRepository = new UtenteRepository();
    LibroRepository oLibroRepository = new LibroRepository();
    public void create( int idUtente, String idLibro, LocalDate dataInizioPrestito, LocalDate dataFinePrestito) {
        Prestito oPrestito = new Prestito();
        oPrestito.setFinePrestito(dataFinePrestito);
        oPrestito.setInizioPrestito(dataInizioPrestito);
        Libro oLibro = new Libro();
        Utente oUtente = new Utente();
        oLibro.setId(idLibro);
        oUtente.setId(idUtente);
        oPrestito.setLibro(oLibro);
        oPrestito.setUtente(oUtente);

        prestitoRepository.createPrestito(oPrestito);
    }

    public void delete( int id) {
        Prestito oPrestito = new Prestito();
        oPrestito.setId(id);

        prestitoRepository.deletePrestito(oPrestito);
    }

    public List<Prestito> readPrestito(){
       return prestitoRepository.readPrestito();
    }
    public List<Prestito> readPrestitoByUtente(int id){
        return prestitoRepository.readPrestitoByUtente(id);
    }
    public List<Prestito> readPrestitoByLibro(String id){
        return prestitoRepository.readPrestitoByLibro(id);
    }
    public void update(){}

}
