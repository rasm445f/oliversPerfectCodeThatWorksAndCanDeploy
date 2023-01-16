package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TripDTO;
import dtos.UserDTO;
import entities.Trip;
import entities.User;
import errorhandling.API_Exception;
import facades.OwnerFacade;
import facades.TripFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("trip")
public class TripResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final TripFacade FACADE =  TripFacade.getTripFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws NotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllTrips())).build();
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("id") int id) throws API_Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getTripById(id))).build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String trip) {
        Trip tripTwo =GSON.fromJson(trip, Trip.class);
        Trip burner = new Trip(tripTwo.getDate(), tripTwo.getTime(), tripTwo.getLocation(), tripTwo.getDuration(), tripTwo.getPackingList(), tripTwo.getFkidGuide(), tripTwo.getTravellers());
        Trip newTrip = FACADE.createTrip(burner);
        return Response.ok().entity(GSON.toJson(newTrip)).build();
    }
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException, API_Exception {
        TripDTO deleted = FACADE.deleteTrip(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }
}
