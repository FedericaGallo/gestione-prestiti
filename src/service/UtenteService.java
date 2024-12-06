package service;

import java.util.List;
import entity.Utente;
import repository.UtenteRepository;

public class UtenteService {
    UtenteRepository utenteRepository = new UtenteRepository();
    public void create(String nome, String cognome) {
        Utente oUtente = new Utente();
        oUtente.setCognome(cognome);
        oUtente.setNome(nome);
        utenteRepository.createUtente(oUtente);
    }


    public List<Utente> readUtente(){
        return utenteRepository.readUtente();
    }


    public void delete(int id) {
        Utente oUtente = new Utente();
        oUtente.setId(id);
        utenteRepository.deleteUtente(oUtente);
    }

    public void update(int id, String nome, String cognome) {
        Utente oUtente = new Utente();
        oUtente.setNome(nome);
        oUtente.setCognome(cognome);
        oUtente.setId(id);
        utenteRepository.updateUtente(oUtente);
    }

}
