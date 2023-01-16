package facades;

import dtos.OwnerDTO;
import dtos.TripDTO;
import dtos.UserDTO;
import entities.Owner;
import entities.Trip;
import entities.User;
import errorhandling.API_Exception;
import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class TripFacade {
    private static EntityManagerFactory emf;
    private static TripFacade instance;


    private TripFacade(){
    }

    public static TripFacade getTripFacade(EntityManagerFactory _emf){
        if (instance == null){
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager(){ return emf.createEntityManager();}

    public Trip createTrip(Trip trip){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return trip;
    }

    public List<TripDTO> getAllTrips() throws NotFoundException{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
            if (query == null){
                throw new NotFoundException("Can't find any owners)");
            }
            List<Trip> trips = query.getResultList();
            return TripDTO.getTripDTOs(trips);
        } finally {
            em.close();
        }
    }

    public TripDTO getTripById(int id) throws API_Exception {
        EntityManager em = getEntityManager();

        Trip trip = em.find(Trip.class, id);
        if (trip == null)
            throw new API_Exception("There's no trip with that id", 404);
        em.close();
        return new TripDTO(trip);
    }

    public TripDTO deleteTrip(int id) throws API_Exception {
        EntityManager em = getEntityManager();
        Trip trip;
        try {
            trip = em.find(Trip.class, id);
            if (trip == null) {
                throw new API_Exception("Can't find a trip with the id: " + id);
            }
            em.getTransaction().begin();
            em.remove(trip);
            em.getTransaction().commit();
            return new TripDTO(trip);
        } finally {
            em.close();
        }
    }




}
