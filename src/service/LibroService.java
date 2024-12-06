package service;
import entity.Libro;
import java.util.List;
import repository.LibroRepository;

public class LibroService {
    LibroRepository libroRepository = new LibroRepository();
    public void create(String autore, String titolo, String id) {
        Libro oLibro = new Libro();
        oLibro.setAutore(autore);
        oLibro.setTitolo(titolo);
        oLibro.setId(id);
        libroRepository.createLibro(oLibro);
    }


    public List<Libro> readLibro(){
        return libroRepository.readLibro();
    }


    public void delete(String id) {
        Libro oLibro = new Libro();
        oLibro.setId(id);
        libroRepository.deleteLibro(oLibro);
    }

    public void update(String id, String autore, String titolo) {
        Libro oLibro = new Libro();
        oLibro.setAutore(autore);
        oLibro.setTitolo(titolo);
        oLibro.setId(id);
        libroRepository.updateLibro(oLibro);
    }

}
