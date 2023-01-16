package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TripDTO;
import entities.Guide;
import entities.Traveller;
import entities.Trip;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class TripResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Trip t1, t2;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    List<Traveller> travellers = new ArrayList<>();
    Guide guide = new Guide("Male","1999","Energisk","Shuda");

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        t1 = new Trip("19","18:00","Copenhagen","2u","Clothing",guide,travellers);
        t2 = new Trip("18","17:00","Amsterdam","1u","Hjemmerul",guide,travellers);


        try {
            em.getTransaction().begin();
            em.createQuery("delete from Trip ").executeUpdate();
            em.persist(t1);
            em.persist(t2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/trip/all").then().statusCode(200);
    }

    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/trip/all")
                .then().statusCode(200);
    }

    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/trip/all")
                .then().log().body().statusCode(200);
    }

    @Test
    public void testGetById()  {
        given()
                .contentType(ContentType.JSON)
                .get("/trip/{id}",t1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", equalTo(t1.getId()))
                .body("location", equalTo(t1.getLocation()));
    }

    @Test
    public void testError() {
        given()
                .contentType(ContentType.JSON)
               // .pathParam("id", u1.getId()).when()
                .get("/trip/"+20)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("There's no trip with that id"));
    }



    @Test
    public void testPrintResponse(){
        Response response = given().when().get("/trip/"+t1.getId());
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());

        response
                .then()
                .assertThat()
                .body("location",equalTo("Copenhagen"));
    }


    @Test
    public void exampleJsonPathTest() {
        Response res = given().get("trip/"+t1.getId());
        assertEquals(200, res.getStatusCode());
        String json = res.asString();
        JsonPath jsonPath = new JsonPath(json);
        assertEquals("Copenhagen", jsonPath.get("location"));
    }

    @Test
    void deleteTrip() {
        given()
                .contentType("application/json")
                .pathParam("id", t2.getId())
                .delete("trip/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(t2.getId()));
    }



/*
    @Test
    public void getAllUsers() throws Exception {
        List<Trip> tripDTOs;

        tripDTOs = given()
                .contentType("application/json")
                .when()
                .get("/trip/all")
                .then()
                .extract().body().jsonPath().getList("", Trip.class);

        TripDTO t1DTO = new TripDTO(t1);
        TripDTO t2DTO = new TripDTO(t1);
        assertThat(tripDTOs, containsInAnyOrder(t1DTO, t2DTO));
    }

    */
}
