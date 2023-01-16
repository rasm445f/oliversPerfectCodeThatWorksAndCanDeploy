package facades;

import dtos.GuideDTO;
import dtos.OwnerDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Owner;
import entities.Trip;
import errorhandling.API_Exception;
import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class GuideFacade {
    private static EntityManagerFactory emf;
    private static GuideFacade instance;


    private GuideFacade(){
    }

    public static GuideFacade getGuideFacade(EntityManagerFactory _emf){
        if (instance == null){
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager(){ return emf.createEntityManager();}

    public Guide createGuide(Guide guide){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return guide;
    }

    public List<GuideDTO> getAllGuides() throws NotFoundException{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Guide> query = em.createQuery("SELECT g FROM Guide g", Guide.class);
            if (query == null){
                throw new NotFoundException("Can't find any guides)");
            }
            List<Guide> guides = query.getResultList();
            return GuideDTO.getGuideDTOs(guides);
        } finally {
            em.close();
        }
    }

    public GuideDTO getGuideById(int id) throws API_Exception {
        EntityManager em = getEntityManager();

        Guide guide = em.find(Guide.class, id);
        if (guide == null)
            throw new API_Exception("There's no guide with that id", 404);
        em.close();
        return new GuideDTO(guide);
    }

}
