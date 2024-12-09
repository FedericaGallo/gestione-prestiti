import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import entity.*;
import repository.*;
import service.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choiceEntity;
        System.out.println("1. Per gestire i libri");
        System.out.println("2. Per gestire gli utenti");
        System.out.println("3. Per gestire prestiti");
        System.out.println("4. query");
        choiceEntity = scanner.nextInt();

        switch (choiceEntity){
            case 1:
                gestisciLibro();
                break;
            case 2:
                gestisciUtente();
                break;
            case 3:
                gestisciPrestito();
                break;
            case 4:
                altreQuery();
                break;

            default:
                System.out.println("scelta errata. scegliere un numero da 1 a 3");
        }

    }
    private static void gestisciLibro(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("Classe libro");

            System.out.println("***Menu***");
            System.out.println("1. Crea un nuovo libro");
            System.out.println("2. Aggiorna un libro");
            System.out.println("3. Visualizza la lista dei libri");
            System.out.println("4. Elimina un libro");
            System.out.println("9. Exit");
            System.out.print("inserisci la tua scelta: ");
            //System.out.print("test commit push");
            // Read user input
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createLibro();
                    break;
                case 2:
                    updateLibro();
                    break;
                case 3:
                    readLibro();
                    break;
                case 4:
                    deleteLibro();
                    break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("scelta errata. scegliere un numero da 1 a 8");
            }

        } while (choice != 9);
        scanner.close();

    }
    private static void gestisciUtente(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("Classe utente");

            System.out.println("***Menu***");
            System.out.println("1. Crea un nuovo utente");
            System.out.println("2. Aggiorna un utente");
            System.out.println("3. Visualizza la lista degli utenti");
            System.out.println("4. Elimina un utente");
            System.out.println("9. Exit");
            System.out.print("inserisci la tua scelta: ");

            // Read user input
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createUtente();
                    break;
                case 2:
                    updateUtente();
                    break;
                case 3:
                    readUtente();
                    break;
                case 4:
                    deleteUtente();
                    break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("scelta errata. scegliere un numero da 1 a 8");
            }

        } while (choice != 9);
        scanner.close();

    }
    private static void gestisciPrestito(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("Classe prestito");

            System.out.println("***Menu***");
            System.out.println("1. Crea un nuovo prestito");
            System.out.println("2. Aggiorna un prestito");
            System.out.println("3. Visualizza la lista dei prestiti");
            System.out.println("4. Elimina un prestito");
            System.out.println("5. Visualizza la lista dei prestiti per utente");
            System.out.println("9. Exit");
            System.out.print("inserisci la tua scelta: ");

            // Read user input
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createPrestito();
                    break;
                case 2:

                    updatePrestito();
                    break;
                case 3:
                    readPrestito();
                    break;
                case 4:
                    deletePrestito();
                    break;
                case 5:
                    readPrestitoByUtente();
                    break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("scelta errata. scegliere un numero da 1 a 8");
            }

        } while (choice != 9);
        scanner.close();

    }
    private static void altreQuery(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("Query");

            System.out.println("***Menu***");
            System.out.println("1. Visualizza tutte le persone che hanno preso in prestito un libro");
            System.out.println("2. Il numero di prestiti per ogni utente");
            System.out.println("3. Il numero di prestito per ogni libro");
            System.out.println("4. Visualizza libri mai prestati");
            System.out.println("9. Exit");
            System.out.print("inserisci la tua scelta: ");

            // Read user input
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    readPrestitoByLibro();
                    break;
                case 2:
                    readCountPrestitiByUtente();
                    break;
                case 3:
                    readCountPrestitiByLibro();
                    break;
                case 4:
                    readLibriMaiPrestati();
                    break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("scelta errata. scegliere un numero da 1 a 8");
            }

        } while (choice != 9);
        scanner.close();

    }

  //CRUD LIBRO
    private static void readLibro() {
        System.out.println("ecco la lista dei libri: ");
        LibroService oLibroService = new LibroService();
        List<Libro> listaLibro= oLibroService.readLibro();
        int i = 0;
        while(i<listaLibro.size()){
            System.out.println(listaLibro.get(i).getId()+" "+listaLibro.get(i).getTitolo()+" "+listaLibro.get(i).getAutore());
            i++;}

    }
    private static void createLibro () {
        System.out.println("inserisci titolo: ");
        Scanner scanner = new Scanner(System.in);
        String titolo = scanner.nextLine();
        System.out.println("inserisci autore: ");
        String autore = scanner.nextLine();
        System.out.println("inserisci id alfanumerico: ");
        String id = scanner.nextLine();
        LibroService oLibroService = new LibroService();
        oLibroService.create(autore, titolo, id);

    }
    private static void deleteLibro () {
        readLibro();
        System.out.println("Elimina il libro con id: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        LibroService oLibroService = new LibroService();
        oLibroService.delete(id);

    }
    private static void updateLibro () {
        readLibro();
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci l'id del libro da modificare:");
        String id = scanner.next();
        System.out.println("inserisci il nuovo titolo:");
        String titolo = scanner.next();
        System.out.println("inserisci il nuovo autore:");
        String autore = scanner.next();
        LibroService oLibroService = new LibroService();
        oLibroService.update(id, autore, titolo);

    }

  //CRUD UTENTE
    private static void createUtente () {
        System.out.println("inserisci nome: ");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.next();
        System.out.println("inserisci cognome: ");
        String cognome = scanner.next();
        UtenteService oUtenteService = new UtenteService();
        oUtenteService.create(nome, cognome);

    }
    private static void readUtente() {
        System.out.println("ecco la lista degli utenti: ");
        UtenteService oUtenteService = new UtenteService();
        List<Utente> listaUtente= oUtenteService.readUtente();
        int i = 0;
        while(i<listaUtente.size()){
            System.out.println(listaUtente.get(i).getId()+". "+ listaUtente.get(i).getCognome()+" "+listaUtente.get(i).getNome());
            i++;}

    }
    private static void updateUtente () {
        readUtente();
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci l'id dell'utente da modificare:");
        int id = scanner.nextInt();
        System.out.println("inserisci il nuovo nome:");
        String nome = scanner.next();
        System.out.println("inserisci il nuovo cognome:");
        String cognome = scanner.next();
       UtenteService oUtenteService = new UtenteService();
        oUtenteService.update(id, nome, cognome);

    }
    private static void deleteUtente () {
        readUtente();
        System.out.println("Elimina l'utente con id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        UtenteService oUtenteService = new UtenteService();
        oUtenteService.delete(id);

    }

    //CRUD PRESTITO
    private static void createPrestito(){
        readUtente();
        System.out.println("inserisci id utenti: ");
        Scanner scanner = new Scanner(System.in);
        int idUtente = scanner.nextInt();
        scanner.nextLine();
        readLibro();
        System.out.println("inserisci id libro: ");
        String idLibro = scanner.nextLine();
        System.out.print("Inserisci la data di inizio prestito nel formato YYYY-MM-DD: ");
        String dataInizioPrestito = scanner.nextLine();
        System.out.print("Inserisci la data di fine prestito nel formato YYYY-MM-DD: ");
        String dataFinePrestito = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInizioPrestitoFormatted = LocalDate.parse(dataInizioPrestito, formatter);
        LocalDate dataFinePrestitoFormatted = LocalDate.parse(dataFinePrestito, formatter);
        PrestitoService oPrestitoService = new PrestitoService();
        oPrestitoService.create(idUtente, idLibro, dataInizioPrestitoFormatted, dataFinePrestitoFormatted);
    }
    private static void deletePrestito(){
        readPrestito();
        System.out.println("inserisci id prestito che vuoi cancellare: ");
        Scanner scanner = new Scanner(System.in);
        int idPrestito = scanner.nextInt();
        PrestitoService oPrestitoService = new PrestitoService();
        oPrestitoService.delete(idPrestito);
    }
    private static void readPrestitoByUtente(){
        readUtente();
        System.out.println("inserisci l'id dell'utente del quale vuoi visualizzare i prestiti: ");
        Scanner scanner = new Scanner(System.in);
        int idUtente = scanner.nextInt();
        PrestitoService oPrestitoService = new PrestitoService();
        List<Prestito> listaPrestito = oPrestitoService.readPrestitoByUtente(idUtente);
        int i = 0;
        while(i<listaPrestito.size()){
            System.out.println(listaPrestito.get(i).getId()+". " + listaPrestito.get(i).getLibro()+"  dal "+listaPrestito.get(i).getInizioPrestito()+" al "+listaPrestito.get(i).getFinePrestito());
            i++;}
    }
    private static void readPrestito() {
        System.out.println("ecco la lista dei prestiti: ");
        PrestitoService oPrestitoService = new PrestitoService();
        List<Prestito> listaPrestito= oPrestitoService.readPrestito();
        int i = 0;
        while(i<listaPrestito.size()){
            System.out.println(listaPrestito.get(i).getId()+". " + listaPrestito.get(i).getLibro()+" "+ listaPrestito.get(i).getUtente()+" dal "+listaPrestito.get(i).getInizioPrestito()+" al "+listaPrestito.get(i).getFinePrestito());
            i++;}

    }
    public static void updatePrestito(){
        readPrestito();
        System.out.println("Seleziona il prestito che vuoi modificare: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        readUtente();
        System.out.println("inserisci id utente aggiornato: ");
        int idStudente = scanner.nextInt();
        readLibro();
        System.out.println("inserisci id libro aggiornato: ");
        String idLibro = scanner.nextLine();
        System.out.print("Inserisci la data di inizio prestito aggiornata nel formato YYYY-MM-DD: ");
        String dataInizioPrestito = scanner.nextLine();
        System.out.print("Inserisci la data di fine prestito aggiornata nel formato YYYY-MM-DD: ");
        String dataFinePrestito = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInizioPrestitoFormatted = LocalDate.parse(dataInizioPrestito, formatter);
        LocalDate dataFinePrestitoFormatted = LocalDate.parse(dataFinePrestito, formatter);
        PrestitoService oPrestitoService = new PrestitoService();
        //oPrestitoService.update(id, idStudente, idLibro, dataInizioPrestitoFormatted, dataFinePrestitoFormatted);
    }
  //QUERY
    public static void readPrestitoByLibro(){
        readLibro();
        System.out.println("Seleziona l'id del libro: ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        PrestitoService oPrestitoService= new PrestitoService();
       List<Prestito> listaPrestito = oPrestitoService.readPrestitoByLibro(id);
        int i = 0;
        while(i<listaPrestito.size()){
            System.out.println(listaPrestito.get(i).getId()+". "+ listaPrestito.get(i).getUtente()+" dal "+listaPrestito.get(i).getInizioPrestito()+" al "+listaPrestito.get(i).getFinePrestito());
            i++;}
    }
    public static void readCountPrestitiByLibro(){
        PrestitoRepository prestitoRepository = new PrestitoRepository();
        HashMap<Libro, Integer> libroCountPrestito = prestitoRepository.readCountPrestitoByLibro();
        for (Libro i : libroCountPrestito.keySet()) {
            System.out.println("libro: " + i.getAutore() +" "+ i.getTitolo()+ " prestato n: " + libroCountPrestito.get(i)+ " volte");
        }
    }
    public static void readCountPrestitiByUtente(){
        PrestitoRepository prestitoRepository = new PrestitoRepository();
        HashMap<Utente, Integer> utenteCountPrestito = prestitoRepository.readCountPrestitoByUtente();
        for (Utente i : utenteCountPrestito.keySet()) {
            System.out.println("utente: " + i.getNome() +" "+ i.getCognome()+ " ha preso in prestito n: " + utenteCountPrestito.get(i)+ " libri");
        }
    }
    public static void readLibriMaiPrestati(){
        System.out.println("ecco la lista dei libri mai prestati: ");
        LibroRepository oLibroRepository = new LibroRepository();
        List<Libro> listaLibro= oLibroRepository.readLibroMaiPrestati();
        System.out.println("ciaoo");
        int i = 0;
        while(i<listaLibro.size()){
            System.out.println(listaLibro.get(i).getId()+" "+listaLibro.get(i).getTitolo()+" "+listaLibro.get(i).getAutore());
            i++;}
    }
}