import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        EventoDAO eventoDAO = new EventoDAO(em);

        // Creazione e salvataggio di un nuovo evento
        Evento evento = new Evento(
                LocalDate.of(2025, 5, 20),
                "Cadendo malati",
                TipoEvento.PUBBLICO,
                500
        );
        eventoDAO.save(evento);

        // Recupero dell'evento appena salvato
        Evento eventoRecuperato = eventoDAO.getById(evento.getId());
        System.out.println("Evento recuperato: " + eventoRecuperato.getDescrizione());

        em.close();
        emf.close();
    }
}
