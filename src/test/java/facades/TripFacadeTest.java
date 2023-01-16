package facades;

import dtos.TripDTO;
import dtos.UserDTO;
import entities.Guide;
import entities.Traveller;
import entities.Trip;
import entities.User;
import errorhandling.API_Exception;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripFacadeTest {

    private static EntityManagerFactory emf;
    private static TripFacade facade;
    Trip trip1;
    Trip trip2;

    List<Traveller> travellers = new ArrayList<>();
    Guide guide = new Guide("Male","1999","Energisk","Shuda");
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = TripFacade.getTripFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();

            trip1 = new Trip("19","18:00","Copenhagen","2u","Clothing",guide,travellers);
            trip2 = new Trip("18","17:00","Amsterdam","1u","Hjemmerul",guide,travellers);
            em.persist(trip1);
            em.persist(trip2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {

    }
/*
    @Test
    void getTripById() throws AuthenticationException, API_Exception {
        TripDTO actual = facade.getTripById(1);
        TripDTO test = new TripDTO(trip2);
        assertEquals(test,actual);
    }

 */

    @Test
    void getAllTrips() throws NotFoundException {
        List<TripDTO> actual = facade.getAllTrips();
        int expected = 2;
        assertEquals(expected,actual.size());
    }

    @Test
    void createTrip() throws NotFoundException {
    Trip trip = new Trip("hula","bula","shama","lama","dingdong",guide,travellers);
    facade.createTrip(trip);
    List<TripDTO> actual = facade.getAllTrips();
    int expected = 3;
    assertEquals(expected,actual.size());
    }



    @Test
    void updateUser() {
    }
/*
    @Test
    void deleteTrip() throws API_Exception, NotFoundException {
    int expected = 1;
    facade.deleteTrip(10);
    List<TripDTO> actual = facade.getAllTrips();

    assertEquals(expected,actual.size());
    }

 */

}